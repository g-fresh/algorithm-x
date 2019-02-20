package me.gfresh.algorithmx;

import me.gfresh.algorithmx.NodeIterator.Direction;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

public class DLX {

    private Deque<Integer> solution = new LinkedList<>();
    private Function<Set<Integer>, Boolean> callback;
    private Matrix matrix;

    public DLX(Matrix matrix, Function<Set<Integer>, Boolean> callback) {
        this.matrix = matrix;
        this.callback = callback;
    }

    public void search() {
        searchRecursively();
    }

    private boolean searchRecursively() {
        if (foundSolution()) {
            return notify(solution);
        } else {
            Head column = matrix.selectNextColumn();
            column.cover();
            for (Node node : column.iterate(Direction.Down)) {
                solution.push(node.row);
                node.iterate(Direction.Right).forEach(Node::cover);
                if (! searchRecursively()) return false;
                solution.pop();
                node.iterate(Direction.Left).forEach(Node::uncover);
                column = node.head;
            }
            column.uncover();
        }
        return true;
    }

    private boolean foundSolution() {
        return matrix.isEmpty();
    }

    private boolean notify(Deque<Integer> solution) {
        return callback.apply(copy(solution));
    }

    private Set<Integer> copy(Deque<Integer> solution) {
        return new TreeSet<>(solution);
    }

}
