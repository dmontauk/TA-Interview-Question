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
  public static final Cell ILLEGAL_CELL = new Cell(-1, -1);
  private static final int HSPACE = 20;

  private final int max_x;
  private final int max_y;
  private final int[][] board;

  /**
   * Represents a particular cell on a TicTacToe board.
   * 
   * Currently hard-coded to 2-dimensions.
   */
  static class Cell {
    private final int x;
    private final int y;

    Cell(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + x;
      result = prime * result + y;
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Cell other = (Cell) obj;
      if (x != other.x)
        return false;
      if (y != other.y)
        return false;
      return true;
    }
    
    @Override
    public String toString() {
      return "Cell(" + x + "," + y + ")";
    }
  }

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

  TicTacToeBoard(int max_x, int max_y) {
    if (max_y <= 0) {
      throw new IllegalArgumentException("A TicTacToe board must have >0 rows.");
    }
    if (max_x <= 0) {
      throw new IllegalArgumentException("A TicTacToe board must have >0 cols.");
    }
    this.max_y = max_y;
    this.max_x = max_x;
    board = new int[max_y][max_x];
  }

  /**
   * This constructor should only be used by tests. The input is not validated.
   */
  TicTacToeBoard(int[][] board) {
    max_y = board.length;
    max_x = board[0].length;
    this.board = board;
  }

  private void validateRowCol(int x, int y) {
    if (y < 0 || y >= max_y) {
      throw new IllegalArgumentException("Illegal row argument: " + y
          + ". Row must be between 0 and " + (max_y - 1));
    }
    if (x < 0 || x >= max_x) {
      throw new IllegalArgumentException("Illegal col argument: " + x
          + ". Col must be between 0 and " + (max_x - 1));
    }
  }

  void setCell(Cell cell, Value value) {
    validateRowCol(cell.y, cell.x);
    board[cell.x][cell.y] = value.valueToInt();
  }

  Value getCell(Cell cell) {
    validateRowCol(cell.y, cell.x);
    return Value.intToValue(board[cell.x][cell.y]);
  }

  /**
   * @return the winning status of the board. Note that this method is currently
   *         hard-coded for 3x3 boards; this should be fixed.
   */
  WinConfig isWinningConfig() {
    WinConfig w = WinConfig.WIN;
    // rows
    for (int i = 0; i < max_y; i++) {
      if ((board[i][0] != 0) && (board[i][0] == board[i][1])
          && (board[i][0] == board[i][2])) {
        return w;
      }
    }
    // columns
    for (int i = 0; i < max_x; i++) {
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
    for (int i = 0; i < max_y; i++)
      for (int j = 0; j < max_x; j++) {
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
  private String getRowString(int y) {
    String s = "";
    for (int x = 0; x < max_x; x++) {
      switch (board[x][y]) {
      case 0:
        s += " ";
        break;
      case 1:
        s += "O";
        break;
      case 2:
        s += "X";
      }

      if (x != max_x - 1) {
        s += " | ";
      }
    }

    s += String.format("%" + HSPACE + "s", "");

    for (int i = 0; i < max_x; i++) {
      s += y * 3 + i + 1;

      if (i == max_y - 1) {
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
    for (int i = 0; i < max_y; i++) {
      s += getRowString(i);
    }
    return s;
  }

  /**
   * Prints a human-readable string representing the position chosen.
   * 
   * Note that this currently only works on 3x3 boards.
   */
  public static String getPosDescription(Cell cell) {
    String str = "";
    if (cell.x == 1 && cell.y == 1) {
      str = "center";
      return str;
    }

    if (cell.y == 0) {
      str += "upper ";
    } else if (cell.y == 1) {
      str += "middle ";
    } else
      str += "lower ";

    if (cell.x == 0)
      str += "left";
    else if (cell.x == 1)
      str += "middle";
    else
      str += "right";

    return str;
  }
}
