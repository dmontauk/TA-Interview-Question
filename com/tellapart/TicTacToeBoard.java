package com.tellapart;

/** Represents a TicTacToe board.
 * 
 * @author dobromirv
 */
class TicTacToeBoard {
  private static final int HSPACE = 20;
  
  private final int rows;
  private final int cols;
  private final int[][] board;
  
  enum Value {
    None,
    O,
    X;
    
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
    this.rows = rows;
    this.cols = cols;
    board = new int[rows][cols];
    // Pretty sure this isn't necessary since Java inits to 0 by default.
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        board[i][j] = 0;
      }
    }
  }
  
  TicTacToeBoard(int[][] board) {
    rows = board.length;
    if (rows == 0) {
      // TODO(dobromirv): throw an exception
    }
    cols = board[0].length;
    this.board = board;
  }
  
  void setCell(int row, int col, Value player) {
    if (row >= rows) {
      // Throw an exception.
    }
    if (col >= cols) {
      // Throw an exception
    }
    board[row][col] = player.valueToInt();
  }
  
  Value getCell(int row, int col) { 
    // TODO(dobromirv): validate input
    return Value.intToValue(board[row][col]);
  }
  
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

  public String toString() {
    String s = "";
    // iterate through the rows
    for (int i = 0; i < rows; i++) {
      s += getRowString(i);
    }
    return s;
  }
}

