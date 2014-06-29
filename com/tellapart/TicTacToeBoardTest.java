package com.tellapart;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.tellapart.TicTacToeBoard.Value;
import com.tellapart.TicTacToeBoard.WinConfig;

public class TicTacToeBoardTest {
  private TicTacToeBoard default_board;
  
  @Before
  public void setUp() {
    default_board = new TicTacToeBoard(3, 3);
  }

  @Test
  public void testNewBoardIsEmpty() {
    TicTacToeBoard board = new TicTacToeBoard(3, 3);
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        assertEquals(Value.None, board.getCell(x, y));
      }
    }
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testZeroRowSize() {
    new TicTacToeBoard(0, 3);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testNegativeRowSize() {
    new TicTacToeBoard(-1, 3);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testZeroColSize() {
    new TicTacToeBoard(3, 0);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testNegativeColSize() {
    new TicTacToeBoard(3, -1);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSetCellRowTooLarge() {
    default_board.setCell(3, 1, Value.O);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSetCellRowTooSmall() {
    default_board.setCell(-1, 1, Value.O);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSetCellColTooLarge() {
    default_board.setCell(1, 3, Value.O);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSetCellColTooSmall() {
    default_board.setCell(1, -1, Value.O);
  }
  
  // TODO(dobromirv): we should have the same tests for getCell but will skip for brevity.
  
  @Test
  public void testGetCell() {
    assertEquals(Value.None, default_board.getCell(1, 1));
    default_board.setCell(1, 1, Value.O);
    assertEquals(Value.O, default_board.getCell(1, 1));
    default_board.setCell(1, 1, Value.X);
    assertEquals(Value.X, default_board.getCell(1, 1));
  }
  
  @Test
  public void testIsWinningConfig() {
    int preset_board[][] = new int[][] { {1, 1, 1}, {0, 0, 0}, {0, 0, 0}};
    assertEquals(WinConfig.WIN, new TicTacToeBoard(preset_board).isWinningConfig());
    
    preset_board = new int[][] { {1, 0, 0}, {1, 0, 0}, {1, 0, 0}};
    assertEquals(WinConfig.WIN, new TicTacToeBoard(preset_board).isWinningConfig());
    
    preset_board = new int[][] { {1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
    assertEquals(WinConfig.WIN, new TicTacToeBoard(preset_board).isWinningConfig());
    
    preset_board = new int[][] { {1, 2, 2}, {2, 1, 1}, {1, 1, 2}};
    assertEquals(WinConfig.DRAW, new TicTacToeBoard(preset_board).isWinningConfig());

    preset_board = new int[][] { {0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    assertEquals(WinConfig.NONE, new TicTacToeBoard(preset_board).isWinningConfig());
  }

}
