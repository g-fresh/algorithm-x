package me.gfresh.sudoku;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

/**
 * A parser for reading puzzle representations from an input stream.
 *
 * The expected format is nine rows with nine entries each.
 * Unknown entries are represented using underscore.
 *
 * This is an example of the minimal representation:
 * <pre>
 *   53__7____
 *   6__195___
 *   _98____6_
 *   8___6___3
 *   4__8_3__1
 *   7___2___6
 *   _6____28_
 *   ___419__5
 *   ____8__79
 * </pre>
 *
 * The parser strips any extra characters prior to parsing,
 * which allows for some basic beautification:
 * <pre>
 *  +-----+-----+-----+
 *  |5 3 _|_ 7 _|_ _ _|
 *  |6 _ _|1 9 5|_ _ _|
 *  |_ 9 8|_ _ _|_ 6 _|
 *  +-----+-----+-----+
 *  |8 _ _|_ 6 _|_ _ 3|
 *  |4 _ _|8 _ 3|_ _ 1|
 *  |7 _ _|_ 2 _|_ _ 6|
 *  +-----+-----+-----+
 *  |_ 6 _|_ _ _|2 8 _|
 *  |_ _ _|4 1 9|_ _ 5|
 *  |_ _ _|_ 8 _|_ 7 9|
 *  +-----+-----+-----+
 * </pre>
 */
final class PuzzleParser {

    public Set<Entry> parsePuzzle(InputStream in) throws IOException {
        List<String> lines = readLines(new LineNumberReader(new InputStreamReader(in)));
        return parseEntries(lines.stream().collect(joining()));
    }

    private List<String> readLines(LineNumberReader reader) throws IOException {
        int numRead = 0;
        List<String> lines = new ArrayList<>(9);
        String line;
        while ((line = reader.readLine()) != null) {
            line = stripComment(line);
            line = stripDecoration(line);
            if (line.isEmpty()) continue;
            numRead++;
            lines.add(checkFormat(line));
            if (numRead == 9) return lines;
        }
        throw new IllegalArgumentException("Incomplete input");
    }

    private String stripComment(String line) {
        int idx = line.indexOf('#');
        return (idx > 0) ? line.substring(0, idx-1) : line;
    }

    private String stripDecoration(String line) {
        Matcher matcher = Pattern.compile("[^1-9_]").matcher(line);
        return matcher.replaceAll("");
    }

    private String checkFormat(String line) {
        Matcher matcher = Pattern.compile("[1-9_]{9}").matcher(line);
        if (! matcher.matches()) throw new IllegalArgumentException("Malformed input");
        return line;
    }

    private Set<Entry> parseEntries(String fields) {
        Set<Entry> entries = new HashSet<>();
        for (int i = 0; i < fields.length(); i++) {
            char num = fields.charAt(i);
            if (num == '_') continue;
            int row = i/9 + 1;
            int col = i%9 + 1;
            entries.add(new Entry(row, col, Integer.valueOf(String.valueOf(num))));
        }
        return entries;
    }

}

