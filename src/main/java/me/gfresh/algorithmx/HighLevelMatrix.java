package me.gfresh.algorithmx;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.StreamSupport.*;

class HighLevelMatrix {

    HighLevelMatrix(Iterable<Constraint<?>> constraints) {
//        Map<Object, Set<Integer>> map = buildConstraintMapping(constraints);

//        Head matrix = DLX.createMatrix(constraintNum);
//        Map<Integer, Object> reverseMapping = new HashMap<>();
//        Iterator<Map.Entry<Object, Set<Integer>>> itr = map.entrySet().iterator();
//        for (int row = 1; itr.hasNext(); row++) {
//            Map.Entry<Object, Set<Integer>> entry = itr.next();
//            reverseMapping.put(row, entry.getKey());
//            DLX.createRow(matrix, row, entry.getValue());
//        }
    }

    /*
    private static Map<Object, Set<Integer>> buildConstraintMapping(Iterable<Constraint<?>> constraints) {

        PrimitiveIterator.OfInt i = IntStream.range(1, Integer.MAX_VALUE).iterator();
        Map<Integer, Set<?>> numberedConstraints = stream(constraints.spliterator(), false)
//                .map(c -> c.elements)
                .collect(toMap(c -> i.next(), c -> c.elements));

        Map<Object, Set<Integer>> inverseMapping = numberedConstraints.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream().map(n -> new AbstractMap.SimpleEntry<>(n, entry.getKey())))
                .collect(groupingBy(Map.Entry::getKey, mapping(Map.Entry::getValue, toSet())));

        return inverseMapping;

//        Map<Object, Set<Integer>> map = new HashMap<>();
//        Iterator<Constraint<?>> itr = constraints.iterator();
//        for (int nr = 1; itr.hasNext(); nr++) {
//            Constraint<?> constraint = itr.next();
//            for (Object e : constraint.elements) {
//                Set<Integer> nrs = map.computeIfAbsent(e, dontcare -> new TreeSet<>());
//                nrs.add(nr);
//            }
//        }
//        return map;
    }
    */

}
