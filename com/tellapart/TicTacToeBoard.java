package com.tellapart;

/**
 * Represents a TicTacToe board.
 * 
 * This is a dumb data structure that can update the board, read a cell, print a
 * picture of the board, and check the win status. It doesn't understand
 * players, turns, etc. Such logic should be implemented by a game engine.
 * 
 * Not thread-safe (yet).
 * 
 * @author dobromirv
 */
class TicTacToeBoard {
  private static final int HSPACE = 20;

  private final int rows;
  private final int cols;
  private final int[][] board;

  enum Value {
    None, O, X;

    int valueToInt() {
      if (this == None) {
        return 0;
      }
      if (this == O) {
        return 1;
      }
      return 2;
    }

    static Value intToValue(int x) {
      if (x == 0) {
        return None;
      }
      if (x == 1) {
        return O;
      }
      return X;
    }
  }

  enum WinConfig {
    DRAW, WIN, NONE
  }

  TicTacToeBoard(int rows, int cols) {
    if (rows <= 0) {
      throw new IllegalArgumentException("A TicTacToe board must have >0 rows.");
    }
    if (cols <= 0) {
      throw new IllegalArgumentException("A TicTacToe board must have >0 cols.");
    }
    this.rows = rows;
    this.cols = cols;
    board = new int[rows][cols];
  }

  /**
   * This constructor should only be used by tests. The input is not validated.
   */
  TicTacToeBoard(int[][] board) {
    rows = board.length;
    cols = board[0].length;
    this.board = board;
  }
  
  private void validateRowCol(int row, int col) {
    if (row < 0 || row >= rows) {
      throw new IllegalArgumentException("Illegal row argument: " + row
          + ". Row must be between 0 and " + (rows - 1));
    }
    if (col < 0 || col >= cols) {
      throw new IllegalArgumentException("Illegal col argument: " + col
          + ". Col must be between 0 and " + (cols - 1));
    }
  }

  void setCell(int row, int col, Value value) {
    validateRowCol(row, col);
    board[row][col] = value.valueToInt();
  }

  Value getCell(int row, int col) {
    validateRowCol(row, col);
    return Value.intToValue(board[row][col]);
  }

  /**
   * @return the winning status of the board. Note that this method is currently
   *         hard-coded for 3x3 boards; this should be fixed.
   */
  WinConfig isWinningConfig() {
    WinConfig w = WinConfig.WIN;
    // rows
    for (int i = 0; i < rows; i++) {
      if ((board[i][0] != 0) && (board[i][0] == board[i][1])
          && (board[i][0] == board[i][2])) {
        return w;
      }
    }
    // columns
    for (int i = 0; i < cols; i++) {
      if ((board[0][i] != 0) && (board[0][i] == board[1][i])
          && (board[0][i] == board[2][i])) {
        return w;
      }
    }
    // diags
    // TODO(dobromirv): this is hardcoded - may want to switch to vars.
    if ((board[0][0] != 0) && (board[0][0] == board[1][1])
        && (board[0][0] == board[2][2])) {
      return w;
    }

    if ((board[2][0] != 0) && (board[2][0] == board[1][1])
        && (board[2][0] == board[0][2])) {
      return w;
    }

    // draw
    w = WinConfig.DRAW;
    for (int i = 0; i < rows; i++)
      for (int j = 0; j < cols; j++) {
        if (board[i][j] == 0)
          w = WinConfig.NONE;
      }
    return w;
  }

  /**
   * @param row
   *          the row to print
   * @return a string representing the current state of the board's "row" row
   */
  private String getRowString(int row) {
    String s = "";
    for (int i = 0; i < cols; i++) {
      switch (board[row][i]) {
      case 0:
        s += " ";
        break;
      case 1:
        s += "O";
        break;
      case 2:
        s += "X";
      }

      if (i != cols - 1) {
        s += " | ";
      }
    }

    s += String.format("%" + HSPACE + "s", "");

    for (int i = 0; i < cols; i++) {
      s += row * 3 + i + 1;

      if (i == rows - 1) {
        s += "\n";
      } else {
        s += " | ";
      }
    }
    return s;
  }

  /**
   * @return a string representing the current state of the board
   */
  public String toString() {
    String s = "";
    // iterate through the rows
    for (int i = 0; i < rows; i++) {
      s += getRowString(i);
    }
    return s;
  }
}
