package me.gfresh.algorithmx;

import java.util.Iterator;
import java.util.function.Function;

class NodeIterator implements Iterator<Node> {

    enum Direction implements Function<Node, Node> {
        Up    (node -> node.up),
        Down  (node -> node.down),
        Left  (node -> node.left),
        Right (node -> node.right);

        private final Function<Node, Node> next;

        Direction(Function<Node, Node> next) {
            this.next = next;
        }

        @Override
        public Node apply(Node node) {
            return next.apply(node);
        }
    }

    private final Node start;
    private Node curr;
    private final Direction direction;

    NodeIterator(Node start, Direction direction) {
        this.start = start;
        this.curr = start;
        this.direction = direction;
    }

    @Override
    public boolean hasNext() {
        return nextNode() != start;
    }

    @Override
    public Node next() {
        return curr = nextNode();
    }

    private Node nextNode() {
        return direction.apply(curr);
    }

}
