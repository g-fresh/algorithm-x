package me.gfresh.sudoku;

import java.util.Objects;

/**
 * A class representing a number entered into one of the puzzle's cells.
 *
 * Cells are identified via row and column number, starting at one.
 */
public final class Entry {

    public final int row, col, number;

    public Entry(int row, int col, int number) {
        this.row = row;
        this.col = col;
        this.number = number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, number);
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Entry)) return false;
        Entry other = (Entry) obj;
        return row == other.row && col == other.col && number == other.number;
    }

    @Override
    public String toString() {
        return String.format("R%dC%d#%d", row, col, number);
    }

}


