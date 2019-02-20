
## What it is

A Sudoku puzzle solver implementing [Donald Knuth](https://cs.stanford.edu/~knuth/)'s [Algorithm X](https://en.wikipedia.org/wiki/Knuth%27s_Algorithm_X), specifically using the [Dancing Links (DLX)](https://en.wikipedia.org/wiki/Dancing_links) technique.  

## How to use

Compute the Sudoku solution using:

    java me.gfresh.sudoku.SudokuSolver /path/to/puzzle

The partially solved Sudoku puzzle has to be specified in a file which
has the following format:

    53__7____
    6__195___
    _98____6_
    8___6___3
    4__8_3__1
    7___2___6
    _6____28_
    ___419__5
    ____8__79

Alternatively, the solver can read the puzzle from `stdin` like so:

    java me.gfresh.sudoku.SudokuSolver - << EOF
    (enter puzzle)
    EOF

