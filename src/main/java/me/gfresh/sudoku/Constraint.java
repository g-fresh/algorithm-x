package me.gfresh.sudoku;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Collections.singleton;

abstract class Constraint {

    protected Set<Entry> entries = new HashSet<>();

    void add(Entry entry) {
        entries.add(entry);
    }

    public Stream<Entry> entries() {
        return entries.stream();
    }

    void removeContradictions(Entry clue) {
        if (entries.contains(clue)) retainOnly(clue);
        else removeContradiction(clue);
    }

    private void retainOnly(Entry clue) {
        entries.retainAll(singleton(clue));
    }

    protected void removeContradiction(Entry clue) {
        entries.removeIf(sameNumber(clue).and(sameRow(clue).or(sameCol(clue))));
    };

    protected Predicate<Entry> sameNumber(Entry clue) {
        return entry -> entry.number == clue.number;
    }

    protected Predicate<Entry> sameRow(Entry clue) {
        return entry -> entry.row == clue.row;
    }

    protected Predicate<Entry> sameCol(Entry clue) {
        return entry -> entry.col == clue.col;
    }

    @Override
    public String toString() {
        return String.valueOf(entries);
    }

}

class CellConstraint extends Constraint {}

class RowConstraint extends Constraint {

    @Override
    protected void removeContradiction(Entry clue) {
        // Each number must only exists once in each row, therefore remove other possibilities.
        entries.removeIf(sameNumber(clue).and(sameCol(clue)));
    }
}

class ColumnConstraint extends Constraint {

    @Override
    protected void removeContradiction(Entry clue) {
        // Each number must only exists once in each row, therefore remove other possibilities.
        entries.removeIf(sameNumber(clue).and(sameRow(clue)));
    }
}

class BoxConstraint extends Constraint {}

