package me.gfresh.sudoku;

import org.junit.Test;

import java.io.InputStream;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class PuzzleTest {

    private PuzzleParser parser = new PuzzleParser();

    @Test
    public void solveShouldReturnValidSolution() throws Exception
    {
        Set<Entry> clues = parser.parsePuzzle(getResource("/puzzle/sample"));
        Set<Entry> solution = new Puzzle(clues).solve();
        Set<Entry> expected = parser.parsePuzzle(getResource("/puzzle/sample-solution"));
        assertThat(solution, containsInAnyOrder(expected.toArray()));
    }

    private static InputStream getResource(String location) {
        return PuzzleTest.class.getResourceAsStream(location);
    }

}
