package com.tellapart;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tellapart.TicTacToeBoard.Cell;

public class SimpleMoveStrategyTest {
  @Test
  public void testSimpleMoveStrategy() {
    int preset_board[][] = new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
    TicTacToeBoard board = new TicTacToeBoard(preset_board);
    SimpleMoveStrategy strategy = new SimpleMoveStrategy(3, 3);
    assertEquals(new Cell(0, 0), strategy.move(board));

    // Skips a set value.
    preset_board[0][0] = 1;
    assertEquals(new Cell(1, 0), strategy.move(board));

    // Full board
    preset_board = new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
    board = new TicTacToeBoard(preset_board);
    strategy = new SimpleMoveStrategy(3, 3);
    assertEquals(TicTacToeBoard.ILLEGAL_CELL, strategy.move(board));
  }
}
