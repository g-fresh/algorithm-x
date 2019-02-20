package me.gfresh.algorithmx;

import me.gfresh.algorithmx.NodeIterator.Direction;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;
import static me.gfresh.algorithmx.Node.stream;

public final class Matrix {

    private Head root;
    private int numRows;

    public Matrix(int numColumns) {
        root = createColumnHeader(numColumns);
        numRows = 0;
    }

    private static Head createColumnHeader(int numColumns) {
        Head head = new Head(0);
        Node prev = head;
        for (int col = 1; col <= numColumns; col++) {
            prev = prev.insertLeft(new Head(col));
        }
        return head;
    }

    public Matrix addRow(Set<Integer> elements) {
        addRow(++numRows, sort(elements));
        return this;
    }

    private static SortedSet<Integer> sort(Set<Integer> elements) {
        return (elements instanceof SortedSet) ? (SortedSet) elements : new TreeSet<>(elements);
    }

    private void addRow(int row, SortedSet<Integer> elements) {
        List<Node> nodes = new LinkedList<>();
        elements.forEach(col -> {
            Head head = root.getColumn(col);
            Node lastRow = head.up;
            nodes.add(lastRow.insertBelow(new Node(head, row)));
        });
        link(nodes);
    }

    private static void link(List<Node> nodes) {
        Iterator<Node> itr = nodes.iterator();
        for (Node prev = itr.next(); itr.hasNext(); ) {
            prev = prev.insertLeft(itr.next());
        }
    }

    boolean isEmpty() {
        return root.right == root;
    }

    Head getHeader() {
        return root;
    }

    //
    // TODO document this: Selects element which appears in the smallest number of sets.
    // (use consistent terminology: universe/sets vs. constraints)
    //
    Head selectNextColumn() {
        Optional<Node> min = stream(root.iterate(Direction.Right))
                .min(comparing(node -> ((Head) node).getNodeCount()));
        return (Head) min.orElseThrow(() -> new IllegalStateException("Empty matrix"));
    }

    // TODO review: keep? remove? move?
    void print(PrintStream s) {
        s.print("   ");
        root.iterate(Direction.Right).forEach(node -> s.print("(" + node.getColumn() + ") "));
        s.println();
        Set<String> ns = new HashSet<>();
        Set<Integer> rows = new TreeSet<>();
        for (Node column : root.iterate(Direction.Right)) {
            column.iterate(Direction.Down).forEach(n -> {
                ns.add(String.valueOf(n.row) + "," + String.valueOf(n.getColumn()));
                rows.add(n.row);
            });
        }
        rows.forEach(row -> {
            s.print("(" + row + ")" );
            root.iterate(Direction.Right).forEach(col -> {
                String key = "" + row + "," + col.getColumn();
                s.print(ns.contains(key) ? " 1  " : " 0  ");
            });
            s.println();
        });
    }

}

