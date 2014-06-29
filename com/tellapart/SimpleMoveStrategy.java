package com.tellapart;

import com.tellapart.TicTacToeBoard.Cell;

/**
 * Finds the next empty space on a board. A pretty dumb AI that lets a computer play.
 * 
 * @author dobromirv
 */
class SimpleMoveStrategy implements MoveMethod {
  
  private final int max_x;
  private final int max_y;

  SimpleMoveStrategy(int max_x, int max_y) {
    this.max_x = max_x;
    this.max_y = max_y;
  }
  
  public Cell move(TicTacToeBoard board) {
    for (int y = 0; y < max_y; y++) {
      for (int x = 0; x < max_x; x++) {
        Cell cell = new Cell(x, y);
        if (board.getCell(cell) == TicTacToeBoard.Value.None) {
          return cell;
        }
      }
    }
    return TicTacToeBoard.ILLEGAL_CELL;
  }
}