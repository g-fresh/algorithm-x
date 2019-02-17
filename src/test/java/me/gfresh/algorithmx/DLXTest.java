package me.gfresh.algorithmx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static me.gfresh.algorithmx.Node.stream;

public class DLXTest {

//    @Test
//    public void testNodeLinks() throws Exception
//    {
//        Matrix matrix = new Matrix(3)
//                .addRow(ImmutableSet.of(1, 3))
//                .addRow(ImmutableSet.of(2))
//                .addRow(ImmutableSet.of(2, 3));
//
//        Function<Node, Integer> columnNumber = node -> node.getColumn();
//
//        List<Integer> columnNumbers = collect(columnNumber, matrix.iterate(Direction.Right));
//        assertEquals(asList(1, 2, 3), columnNumbers);
//        columnNumbers = collect(columnNumber, matrix.iterate(Direction.Left));
//        assertEquals(asList(3, 2, 1), columnNumbers);
//
//
//        Node n = matrix.getColumn(1).down;
//        System.out.println(String.format(">>> (%s, %s)", n.row, n.getColumn()));
//        n = n.right;
//        System.out.println(String.format(">>> (%s, %s)", n.row, n.getColumn()));
//
//        columnNumbers = collect(columnNumber, matrix.getColumn(1).down.iterate(Direction.Right));
//        assertEquals(asList(1, 3), columnNumbers);
//        columnNumbers = collect(columnNumber, matrix.getColumn(1).down.iterate(Direction.Left));
//        assertEquals(asList(3, 1), columnNumbers);
//
//        Function<Node, Integer> rowNumber = node -> node.row;
//
//        List<Integer> rowNumbers = collect(rowNumber, matrix.getColumn(1).iterate(Direction.Down));
//        assertEquals(asList(1), rowNumbers);
//        rowNumbers = collect(rowNumber, matrix.getColumn(1).iterate(Direction.Up));
//        assertEquals(asList(1), rowNumbers);
//
//        rowNumbers = collect(rowNumber, matrix.getColumn(2).iterate(Direction.Down));
//        assertEquals(asList(2, 3), rowNumbers);
//        rowNumbers = collect(rowNumber, matrix.getColumn(2).iterate(Direction.Up));
//        assertEquals(asList(3, 2), rowNumbers);
//
//        rowNumbers = collect(rowNumber, matrix.getColumn(3).iterate(Direction.Down));
//        assertEquals(asList(1, 3), rowNumbers);
//        rowNumbers = collect(rowNumber, matrix.getColumn(3).iterate(Direction.Up));
//        assertEquals(asList(3, 1), rowNumbers);
//    }

    private <T> List<T> collect(Function<Node, T> mapper, Iterable<Node> iterable) {
        return stream(iterable).map(mapper).collect(toList());
    }

    @Test
    public void buildMatrixFromConstraints()
    {
        Map<Mark, Set<Integer>> map = new HashMap<>();
        int constraintNum = 0;
        // some number in row/rol constraints
        for (int row = 1; row <= 2; row++) {
            for (int col = 1; col <= 2; col++) {
                constraintNum++;
                for (int num = 1; num <= 2; num++) {
                    map.computeIfAbsent(new Mark(row, col, num), (m) -> new TreeSet<>()).add(constraintNum);
                }
            }
        }
        // number must appear in row
        for (int num = 1; num <= 2; num++) {
            for (int row = 1; row <= 2; row++) {
                constraintNum++;
                for (int col = 1; col <= 2; col++) {
                    map.computeIfAbsent(new Mark(row, col, num), (m) -> new TreeSet<>()).add(constraintNum);
                }
            }
        }
        // number must appear in column
        for (int col = 1; col <= 2; col++) {
            for (int num = 1; num <= 2; num++) {
                constraintNum++;
                for (int row = 1; row <= 2; row++) {
                    map.computeIfAbsent(new Mark(row, col, num), (m) -> new TreeSet<>()).add(constraintNum);
                }
            }
        }

        Matrix matrix = new Matrix(constraintNum);
        Map<Integer, Mark> reverseMapping = new HashMap<>();
        Iterator<Map.Entry<Mark, Set<Integer>>> itr = map.entrySet().iterator();
        for (int row = 1; itr.hasNext(); row++) {
            Map.Entry<Mark, Set<Integer>> entry = itr.next();
            reverseMapping.put(row, entry.getKey());
            matrix.addRow(entry.getValue());
        }

        matrix.print(System.out);

        List<Mark> marksForSolution = new ArrayList<>();
        Function<Set<Integer>, Boolean> onSolution = solution -> {
            solution.forEach(n -> marksForSolution.add(reverseMapping.get(n)));
            System.out.println("Solution: " + marksForSolution);
            marksForSolution.clear();
            return true;
        };
        new DLX(matrix, onSolution).search();

    }

    static class Mark {

        final int row, col, number;

        public Mark(int row, int col, int number) {
            this.row = row;
            this.col = col;
            this.number = number;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (! (obj instanceof Mark)) return false;
            Mark other = (Mark) obj;
            return row == other.row && col == other.col && number == other.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, number);
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)=%d", row, col, number);
        }

    }

}
