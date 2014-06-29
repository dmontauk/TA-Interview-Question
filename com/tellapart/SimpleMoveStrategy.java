package com.tellapart;

/**
 * Finds the next empty space on a board. A pretty dumb AI that lets a computer play.
 * 
 * @author dobromirv
 */
class SimpleMoveStrategy implements MoveMethod {
  
  public int move(TicTacToeBoard board) {
    for (int i = 0; i < TicTacToe.N; i++) {
      for (int j = 0; j < TicTacToe.N; j++) {
        TicTacToeBoard.Value value = board.getCell(i, j);
        if (value == TicTacToeBoard.Value.None) {
          return (i * 3 + j + 1);
        }
      }
    }
    return 0;
  }
}