package me.gfresh.sudoku;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public final class SudokuSolver {

    public static void main(String... args) throws IOException {
        Puzzle puzzle = Puzzle.parse(createInputStream(args));
        puzzle.solve();
        puzzle.printSolution(System.out);
    }

    private static InputStream createInputStream(String[] args) throws FileNotFoundException {
        if (args.length == 0) {
            printUsage(System.out);
            System.exit(1);
        }
        return args[0].equals("-") ? System.in : new FileInputStream(args[0]);
    }

    private static void printUsage(PrintStream stream) {
        stream.println("Usage: SudokuSolver PUZZLE");
        stream.println("   or: SudokuSolver -");
        stream.println("Solves Sudoku puzzles. PUZZLE is a file containing a partially solved Sudoku.");
        stream.println("The expected format looks like this:");
        Template.newInstance("/puzzle/minimal").print(stream);
    }

}

