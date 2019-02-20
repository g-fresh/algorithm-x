package me.gfresh.sudoku;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class PuzzleParserTest {

    private PuzzleParser parser = new PuzzleParser();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldParseEntriesFromMinimalPuzzle() throws IOException
    {
        InputStream stream = getResourceAsStream("/puzzle/minimal");
        verify(parser.parsePuzzle(stream));
    }

    @Test
    public void shouldParseEntriesFromDecoratedPuzzle() throws IOException
    {
        InputStream stream = getResourceAsStream("/puzzle/decorated");
        verify(parser.parsePuzzle(stream));
    }

    private void verify(Set<Entry> entries) {
        assertThat(entries, containsInAnyOrder(
                entry(1, 1, 5), entry(1, 2, 3), entry(1, 5, 7),
                entry(2, 1, 6), entry(2, 4, 1), entry(2, 5, 9), entry(2, 6, 5),
                entry(3, 2, 9), entry(3, 3, 8), entry(3, 8, 6),
                entry(4, 1, 8), entry(4, 5, 6), entry(4, 9, 3),
                entry(5, 1, 4), entry(5, 4, 8), entry(5, 6, 3), entry(5, 9, 1),
                entry(6, 1, 7), entry(6, 5, 2), entry(6, 9, 6),
                entry(7, 2, 6), entry(7, 7, 2), entry(7, 8, 8),
                entry(8, 4, 4), entry(8, 5, 1), entry(8, 6, 9), entry(8, 9, 5),
                entry(9, 5, 8), entry(9, 8, 7), entry(9, 9, 9)
        ));
    }

    @Test
    public void shouldThrowExceptionOnMalformedInput() throws IOException
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Malformed input");
        parser.parsePuzzle(getResourceAsStream("/puzzle/malformed"));
    }

    @Test
    public void shouldThrowExceptionOnIncompleteInput() throws IOException
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Incomplete input");
        parser.parsePuzzle(getResourceAsStream("/puzzle/incomplete"));
    }

    private static InputStream getResourceAsStream(String name) {
        return PuzzleParser.class.getResourceAsStream(name);
    }

    private static Entry entry(int row, int col, int num) {
        return new Entry(row, col, num);
    }

}
