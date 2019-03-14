package me.gfresh.algorithmx;

import me.gfresh.algorithmx.NodeIterator.Direction;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

// TODO review naming: Matrix, Column, Element vs. Head, Node
class Node {

    protected Node up, down, left, right;
    protected Head head;
    protected int row;

    Node(Head head, int row) {
        this.head = head;
        this.row = row;
        up = down = left = right = this;
    }

    int getColumn() {
        return head.col;
    }

    Head getColumnHeader() {
        return head;
    }

    Node insertLeft(Node node) {
        right.left = node;
        node.right = right;
        right = node;
        node.left = this;
        return node;
    }

    Node insertBelow(Node node) {
        down.up = node;
        node.down = down;
        down = node;
        node.up = this;
        head.nodeCount++;
        return node;
    }

    void cover() {
        Head column = head;
        unlinkColumn(column);
        for (Node c : column.iterate(Direction.Down))
            for (Node r : c.iterate(Direction.Right))
                unlinkRow(r);
    }

    void uncover() {
        Head column = head;
        for (Node c : column.iterate(Direction.Up))
            for (Node r : c.iterate(Direction.Left))
                relinkRow(r);
        relinkColumn(column);
    }

    private static void unlinkColumn(Node node) {
        node.left.right =  node.right;
        node.right.left =  node.left;
    }

    private static void relinkColumn(Node node) {
        node.right.left = node;
        node.left.right = node;
    }

    private static void unlinkRow(Node node) {
        node.up.down = node.down;
        node.down.up = node.up;
        node.head.nodeCount--;
    }

    private static void relinkRow(Node node) {
        node.up.down = node;
        node.down.up = node;
        node.head.nodeCount++;
    }

    Iterable<Node> iterate(Direction direction) {
        return () -> new NodeIterator(this, direction);
    }

    static Stream<Node> stream(Iterable<Node> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }

}

