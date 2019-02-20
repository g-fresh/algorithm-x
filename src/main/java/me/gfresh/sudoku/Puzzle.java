package me.gfresh.sudoku;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Set;

public class Puzzle {

    private static final PuzzleParser parser = new PuzzleParser();

    private Set<Entry> clues;
    private Set<Entry> solution;

    public Puzzle(Set<Entry> clues) {
        this.clues = clues;
    }

    public static Puzzle parse(InputStream stream) throws IOException {
        return new Puzzle(parser.parsePuzzle(stream));
    }

    public Set<Entry> solve() {
        return solution = new DLXPuzzleSolver().computeSolution(clues);
    }

    public void print(PrintStream stream) {
        print(clues, stream);
    }

    public void printSolution(PrintStream stream) {
        print(solveIfNecessary(), stream);
    }

    private Set<Entry> solveIfNecessary() {
        if (solution == null) solve();
        return solution;
    }

    private static void print(Set<Entry> entries, PrintStream stream) {
        Template.newInstance().add(entries).print(stream);
    }

}

