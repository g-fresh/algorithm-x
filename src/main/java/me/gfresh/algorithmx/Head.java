package me.gfresh.algorithmx;

import me.gfresh.algorithmx.NodeIterator.Direction;

class Head extends Node {

    protected int col;
    protected int nodeCount;

    Head(int col) {
        super(null, -1);
        this.col = col;
        this.head = this;
    }

    Head getColumn(int col) {
        for (Node node : iterate(Direction.Right)) {
            if (node.getColumn() == col)
                return node.getColumnHeader();
        }
        throw new IllegalArgumentException(String.valueOf(col));
    }

    int getNodeCount() {
        return nodeCount;
    }

}
