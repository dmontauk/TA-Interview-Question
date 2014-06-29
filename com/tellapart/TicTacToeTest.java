package com.tellapart;

import static org.junit.Assert.*;

import org.junit.Test;

/** A first test for the TicTacToe program. This is pretty futile since the program is structured
 * in a very hard-to-test way, but it's a start.
 * @author dobromirv
 */
public class TicTacToeTest {

	@Test
	public void testSimpleMoveStrategy() {
	  int preset_board[][] = new int[][] { {0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
	  TicTacToeBoard board = new TicTacToeBoard(preset_board);
	  SimpleMoveStrategy strategy = new SimpleMoveStrategy();
	  assertEquals(1, strategy.move(board));
	  
	  // Skips a set value.
	  preset_board[0][0] = 1;
    assertEquals(2, strategy.move(board));
    
    // Full board
    preset_board = new int[][] { {1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    board = new TicTacToeBoard(preset_board);
    strategy = new SimpleMoveStrategy();
    assertEquals(0, strategy.move(board));
	}
}

