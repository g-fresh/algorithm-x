package me.gfresh.algorithmx;

import me.gfresh.algorithmx.NodeIterator.Direction;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

public class DLX {

    private Deque<Integer> solution = new LinkedList<>();
    private Function<Set<Integer>, Boolean> callback;
    private Matrix matrix;

    DLX(Matrix matrix, Function<Set<Integer>, Boolean> callback) {
        this.matrix = matrix;
        this.callback = callback;
    }

    void search() {
        if (foundSolution()) {
            notify(solution);
        } else {
            Head column = matrix.selectNextColumn();
            column.cover();
            for (Node node : column.iterate(Direction.Down)) {
                solution.push(node.row);
                node.iterate(Direction.Right).forEach(Node::cover);
                search();
                solution.pop();
                node.iterate(Direction.Left).forEach(Node::uncover);
                column = node.head;
            }
            column.uncover();
        }
    }

    private boolean foundSolution() {
        return matrix.isEmpty();
    }

    private void notify(Deque<Integer> solution) {
        callback.apply(copy(solution));
    }

    private Set<Integer> copy(Deque<Integer> solution) {
        return new TreeSet<>(solution);
    }


//    void search() {
//        if (matrix.isEmpty()) {
//            callback.apply(new TreeSet<>(solution));
//            return;
//        }
//        Head column = matrix.selectNextColumn();
//        column.cover();
//        for (Node node : column.iterate(Direction.Down)) {
//            solution.push(node.row);
//            node.iterate(Direction.Right).forEach(Node::cover);
//            search();
//            solution.pop();
//            node.iterate(Direction.Left).forEach(Node::uncover);
//            column = node.head;
//        }
//        column.uncover();
//    }

}

