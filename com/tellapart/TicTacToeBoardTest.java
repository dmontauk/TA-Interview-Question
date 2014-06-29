package com.tellapart;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tellapart.TicTacToeBoard.Cell;
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
        assertEquals(Value.None, board.getCell(new Cell(x, y)));
      }
    }
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testZeroXSize() {
    new TicTacToeBoard(0, 3);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testNegativeXSize() {
    new TicTacToeBoard(-1, 3);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testZeroYSize() {
    new TicTacToeBoard(3, 0);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testNegativeYSize() {
    new TicTacToeBoard(3, -1);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSetCellXTooLarge() {
    default_board.setCell(new Cell(3, 1), Value.O);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSetCellXTooSmall() {
    default_board.setCell(new Cell(-1, 1), Value.O);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSetCellYTooLarge() {
    default_board.setCell(new Cell(1, 3), Value.O);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testSetCellYTooSmall() {
    default_board.setCell(new Cell(1, -1), Value.O);
  }
  
  // TODO(dobromirv): we should have the same tests for getCell but will skip for brevity.
  
  @Test
  public void testGetCell() {
    assertEquals(Value.None, default_board.getCell(new Cell(1, 1)));
    default_board.setCell(new Cell(1, 1), Value.O);
    assertEquals(Value.O, default_board.getCell(new Cell(1, 1)));
    default_board.setCell(new Cell(1, 1), Value.X);
    assertEquals(Value.X, default_board.getCell(new Cell(1, 1)));
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
