package me.gfresh.sudoku;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class Constraints {

    /**
     * Returns the constraints for the Sudoku puzzle taking into account
     * the specified clues.
     *
     * Example:
     * For the row constraint R1#5:
     * [R1C1#5, R1C2#5, R1C3#5, R1C4#5, R1C5#5, R1C6#5, R1C7#5, R1C8#5, R1C9#5]
     * and the clue R1C1#5, the constraint can be updated to:
     * [R1C1#5]
     * (Since number 5 is known to be at column 1, it cannot appear in any other column of that row.)
     */
    static List<Constraint> generateConstraints(Set<Entry> clues) {
        List<Constraint> constraints = generateConstraints();
        clues.forEach(clue -> constraints.forEach(constraint -> constraint.removeContradictions(clue)));
        return constraints;
    }

    static List<Constraint> generateConstraints() {
        List<Constraint> constraints = new LinkedList<>();
        constraints.addAll(getCellConstraints());
        constraints.addAll(getRowConstraints());
        constraints.addAll(getColumnConstraints());
        constraints.addAll(getBoxConstraints());
        return constraints;
    }

    private static List<Constraint> getCellConstraints() {
        List<Constraint> constraints = new LinkedList<>();
        // some number in each cell
        for (int row = 1; row <= 9; row++) {
            for (int col = 1; col <= 9; col++) {
                Constraint c = new CellConstraint();
                for (int num = 1; num <= 9; num++)
                    c.add(new Entry(row, col, num));
                constraints.add(c);
            }
        }
        return constraints;
    }

    private static List<Constraint> getRowConstraints() {
        List<Constraint> constraints = new LinkedList<>();
        // each number must appear in each row
        for (int num = 1; num <= 9; num++) {
            for (int row = 1; row <= 9; row++) {
                Constraint c = new RowConstraint();
                for (int col = 1; col <= 9; col++)
                    c.add(new Entry(row, col, num));
                constraints.add(c);
            }
        }
        return constraints;
    }

    private static List<Constraint> getColumnConstraints() {
        List<Constraint> constraints = new LinkedList<>();
        // each number must appear in each column
        for (int num = 1; num <= 9; num++) {
            for (int col = 1; col <= 9; col++) {
                Constraint c = new ColumnConstraint();
                for (int row = 1; row <= 9; row++)
                    c.add(new Entry(row, col, num));
                constraints.add(c);
            }
        }
        return constraints;
    }

    private static List<Constraint> getBoxConstraints() {
        List<Constraint> constraints = new LinkedList<>();
        // each number must appear in each square
        for (int num = 1; num <= 9; num++) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    Constraint a = new BoxConstraint();
                    for (int r = 1; r <= 3; r++) {
                        int row = y * 3 + r;
                        for (int c = 1; c <= 3; c++) {
                            int col = x * 3 + c;
                            a.add(new Entry(row, col, num));
                        }
                    }
                    constraints.add(a);
                }
            }
        }
        return constraints;
    }

}

