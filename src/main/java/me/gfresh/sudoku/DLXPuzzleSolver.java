package me.gfresh.sudoku;

import me.gfresh.algorithmx.DLX;
import me.gfresh.algorithmx.Matrix;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.StreamSupport.stream;

class DLXPuzzleSolver {

    public Set<Entry> computeSolution(Set<Entry> clues) {
        List<Constraint> constraints = Constraints.generateConstraints(clues);
        Map<Entry, Set<Integer>> constraintMapping = generateConstraintMapping(constraints);

        Matrix matrix = new Matrix(constraints.size());
        Map<Integer, Entry> solutionMapping = fillMatrix(matrix, constraintMapping);

        Set<Entry> solution = new HashSet<>();
        new DLX(matrix, es -> {
            es.stream().map(solutionMapping::get).forEach(solution::add);
//           List<Entry> solution = es.stream().map(mapping::get).collect(toList());
            return false;
        }).search();

        return solution;
    }

    private Map<Integer, Entry> fillMatrix(Matrix matrix, Map<Entry, Set<Integer>> constraintMapping) {
        Map<Integer, Entry> mapping = new HashMap<>();
        int row = 1;
        for (Map.Entry<Entry, Set<Integer>> e : constraintMapping.entrySet()) {
            Entry entry = e.getKey();
            Set<Integer> constraintNumbers = e.getValue();
            mapping.put(row++, entry);
            matrix.addRow(constraintNumbers);
        }
        return mapping;
    }

    static Map<Entry, Set<Integer>> generateConstraintMapping(List<Constraint> constraints) {
        Map<Integer, Constraint> numberedConstraints = numberConstraints(constraints);
        return createInverseMapping(numberedConstraints);
    }

    /**
     * Numbers the constraints sequentially starting from one.
     */
    static Map<Integer, Constraint> numberConstraints(List<Constraint> constraints) {
        PrimitiveIterator.OfInt i = IntStream.range(1, Integer.MAX_VALUE).iterator();
        return stream(constraints.spliterator(), false)
                .collect(toMap(c -> i.next(), identity()));
    }

    /**
     * Returns a mapping from each entry to the constraints it appears in.
     * For example: entry appears in constraints number 1, 2 and 3
     */
    static Map<Entry, Set<Integer>> createInverseMapping(Map<Integer, Constraint> numberedConstraints) {
        return numberedConstraints.entrySet().stream()
                .flatMap(entry -> entry.getValue().entries()
                        .map(n -> new AbstractMap.SimpleEntry<>(n, entry.getKey())))
                .collect(groupingBy(Map.Entry::getKey, mapping(Map.Entry::getValue, toSet())));
    }

}
