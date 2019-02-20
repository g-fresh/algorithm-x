package me.gfresh.algorithmx;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static me.gfresh.algorithmx.Node.stream;
import static me.gfresh.algorithmx.NodeIterator.*;
import static org.junit.Assert.assertEquals;

public class MatrixTest {

    @Test
    public void nodesShouldBeLinkedCorrectly()
    {
        Matrix matrix = new Matrix(3)
                .addRow(asSet(1, 3))    //  1 0 1
                .addRow(asSet(2))       //  0 1 0
                .addRow(asSet(2, 3));   //  0 1 1

        Head header = matrix.getHeader();

        Function<Node, Integer> columnNumber = node -> node.getColumn();

        List<Integer> columnNumbers = collect(columnNumber, header.iterate(Direction.Right));
        assertEquals(asList(1, 2, 3), columnNumbers);
        columnNumbers = collect(columnNumber, header.iterate(Direction.Left));
        assertEquals(asList(3, 2, 1), columnNumbers);

//        columnNumbers = collect(columnNumber, header.getColumn(1).down.iterate(Direction.Right));
//        assertEquals(asList(1, 3), columnNumbers);
//        columnNumbers = collect(columnNumber, header.getColumn(1).down.iterate(Direction.Left));
//        assertEquals(asList(3, 1), columnNumbers);

//        columnNumbers = collect(columnNumber, header.getColumn(2).down.iterate(Direction.Right));
//        assertEquals(asList(2), columnNumbers);
//        columnNumbers = collect(columnNumber, header.getColumn(2).down.iterate(Direction.Left));
//        assertEquals(asList(2), columnNumbers);

//        Node node_2_3 = header.getColumn(2).down.down;
//        columnNumbers = collect(columnNumber, node_2_3.iterate(Direction.Right));
//        assertEquals(asList(2, 3), columnNumbers);
//        columnNumbers = collect(columnNumber, node_2_3.iterate(Direction.Left));
//        assertEquals(asList(3, 2), columnNumbers);

        Function<Node, Integer> rowNumber = node -> node.row;

        List<Integer> rowNumbers = collect(rowNumber, header.getColumn(1).iterate(Direction.Down));
        assertEquals(asList(1), rowNumbers);
        rowNumbers = collect(rowNumber, header.getColumn(1).iterate(Direction.Up));
        assertEquals(asList(1), rowNumbers);

        rowNumbers = collect(rowNumber, header.getColumn(2).iterate(Direction.Down));
        assertEquals(asList(2, 3), rowNumbers);
        rowNumbers = collect(rowNumber, header.getColumn(2).iterate(Direction.Up));
        assertEquals(asList(3, 2), rowNumbers);

        rowNumbers = collect(rowNumber, header.getColumn(3).iterate(Direction.Down));
        assertEquals(asList(1, 3), rowNumbers);
        rowNumbers = collect(rowNumber, header.getColumn(3).iterate(Direction.Up));
        assertEquals(asList(3, 1), rowNumbers);
    }

    private <T> List<T> collect(Function<Node, T> mapper, Iterable<Node> iterable) {
        return stream(iterable).map(mapper).collect(toList());
    }

    private static Set<Integer> asSet(Integer... values) {
        return new TreeSet<>(Arrays.asList(values));
    }

    private static List<Integer> asList(Integer... values) {
        return Arrays.asList(values);
    }

}
