package me.gfresh.sudoku;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * A helper class used for pretty-printing solved puzzles.
 *
 * It makes use of a file template of an 'empty' ASCII-art Sudoku puzzle
 * and fills in the entries representing the solution.
 */
final class Template {

    private List<char[]> lines = new ArrayList<>();

    private Template(List<char[]> lines) {
        this.lines = lines;
    }

    Template add(Set<Entry> entries) {
        entries.forEach(this::add);
        return this;
    }

    private void add(Entry entry) {
        char[] chars = lines.get(computeRowOffset(entry.row));
        chars[computeColumnOffset(entry.col)] = Character.forDigit(entry.number, 10);
    }

    private static int computeRowOffset(int row) {
        return row + (row-1) / 3;
    }

    private static int computeColumnOffset(int col) {
        return (col-1) * 2 + 1;
    }

    void print(PrintStream stream) {
        lines.stream().map(String::new).forEach(stream::println);
    }

    static Template newInstance() {
        return newInstance("/puzzle/empty");
    }

    static Template newInstance(String location) {
        LineNumberReader reader = getReader(getResourceAsStream(location));
        return new Template(readLines(reader));
    }

    private static List<char[]> readLines(LineNumberReader reader) {
        try {
            List<char[]> lines = new ArrayList<>();
            for (String line = reader.readLine(); line != null; line = reader.readLine())
                lines.add(line.toCharArray());
            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static LineNumberReader getReader(InputStream stream) {
        return new LineNumberReader(new InputStreamReader(stream));
    }

    private static InputStream getResourceAsStream(String name) {
        return Template.class.getResourceAsStream(name);
    }

}
