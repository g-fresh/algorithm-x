package me.gfresh.algorithmx;

import org.junit.Test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static me.gfresh.algorithmx.Node.stream;
import static org.junit.Assert.assertEquals;

public class DLXTest {

//    @Test
//    public void testNodeLinks() throws Exception
//    {
//        Matrix matrix = new Matrix(3)
//                .addRow(asSet(1, 3))
//                .addRow(asSet(2))
//                .addRow(asSet(2, 3));
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

}
