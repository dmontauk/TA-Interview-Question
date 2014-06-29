package com.tellapart;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tellapart.TicTacToeBoard.Cell;
import com.tellapart.TicTacToeBoard.Value;

/**
 * Tests the {@code TicTacToe} game engine.
 * 
 * @author dobromirv
 */
public class TicTacToeTest {

  class TestMoveStrategy implements MoveMethod {
    private Cell[] moves;
    int next_move = 0;

    TestMoveStrategy(Cell... moves) {
      this.moves = moves;
    }

    @Override
    public Cell move(TicTacToeBoard board) {
      return moves[next_move++];
    }
  }

  @Test
  public void testPlayer1Wins() {
    TicTacToeBoard board = new TicTacToeBoard(3, 3);
    Player player1 = new Player("", Value.O, 0, new TestMoveStrategy(new Cell(
        0, 0), new Cell(0, 1), new Cell(0, 2)));
    Player player2 = new Player("", Value.X, 0, new TestMoveStrategy(new Cell(
        1, 0), new Cell(1, 1)));
    TicTacToe game = new TicTacToe(board, player1, player2);
    assertEquals(player1, game.runGame());
  }

  @Test
  public void testPlayer2Wins() {
    TicTacToeBoard board = new TicTacToeBoard(3, 3);
    Player player1 = new Player("", Value.O, 0, new TestMoveStrategy(new Cell(
        0, 0), new Cell(0, 1), new Cell(1, 1)));
    Player player2 = new Player("", Value.X, 0, new TestMoveStrategy(new Cell(
        0, 2), new Cell(1, 2), new Cell(2, 2)));
    TicTacToe game = new TicTacToe(board, player1, player2);
    assertEquals(player2, game.runGame());
  }

  @Test
  public void testDraw() {
    TicTacToeBoard board = new TicTacToeBoard(3, 3);
    Player player1 = new Player("", Value.O, 0, new TestMoveStrategy(new Cell(
        0, 0), new Cell(0, 1), new Cell(2, 1), new Cell(1, 2), new Cell(2, 0)));
    Player player2 = new Player("", Value.X, 0, new TestMoveStrategy(new Cell(
        1, 0), new Cell(1, 1), new Cell(0, 2), new Cell(2, 2)));
    TicTacToe game = new TicTacToe(board, player1, player2);
    assertEquals(null, game.runGame());
  }

}
