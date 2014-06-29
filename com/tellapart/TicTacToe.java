package com.tellapart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.tellapart.TicTacToeBoard.Cell;
import com.tellapart.TicTacToeBoard.WinConfig;

class TicTacToe {
  protected static final int N = 3;
  private static BufferedReader reader = new BufferedReader(
      new InputStreamReader(System.in));

  private TicTacToeBoard board;
  private Player player1, player2;

  protected static String getUserInput() {
    String input = "";
    try {
      input = reader.readLine();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return input;
  }

  TicTacToe(TicTacToeBoard board, Player player1, Player player2) {
    this.board = board;
    this.player1 = player1;
    this.player2 = player2;
  }

  boolean setMove(Cell cell, TicTacToeBoard.Value p_type) {
    if (board.getCell(cell) == TicTacToeBoard.Value.None) {
      board.setCell(cell, p_type);
      return true;
    } else {
      System.out.println("Invalid move");
      return false;
    }
  }

  Player runGame() {
    Cell move1;
    Cell move2;

    while (board.isWinningConfig() == WinConfig.NONE) {
      do {
        move1 = player1.getMove(board);
      } while (!setMove(move1, player1.getPlayerType()));

      if ((board.isWinningConfig()) == WinConfig.WIN) {
        System.out.println("");
        System.out.println(board.toString());
        System.out.println("You have beaten my poor AI!");
        return player1;
      } else if (board.isWinningConfig() == WinConfig.DRAW) {
        System.out.println("");
        System.out.println(board.toString());
        System.out.println("Well played. It is a draw!");
        return null;
      }

      move2 = player2.getMove(board);
      System.out.println("");
      System.out.println("You have put an " + player1.getPlayerType().toString() + " in the "
          + TicTacToeBoard.getPosDescription(move1)
          + ". I will put a " + player2.getPlayerType().toString() + " in the "
          + TicTacToeBoard.getPosDescription(move2) + ".");
      setMove(move2, player2.getPlayerType());

      if (board.isWinningConfig() == WinConfig.WIN) {
        System.out.println("");
        System.out.println(board.toString());
        System.out.println("I won. Thanks for playing.");
        return player2;
      }
    }
    return null;
  }

  public static void main(String[] args) {
    System.out.println("Welcome to Tic-Tac-Toe.");
    System.out.println("");

    System.out.println("Enter player name");
    Player player1 = new Player(getUserInput(), TicTacToeBoard.Value.X, 0,
        new HumanMove(N, N));

    Player player2 = new Player("", TicTacToeBoard.Value.O, 1,
        new SimpleMoveStrategy(N, N));
    System.out.println("\nHuman player " + player1.getName()
        + " vs Computer Player " + player2.getName() + ":");

    TicTacToeBoard board = new TicTacToeBoard(N, N);
    TicTacToe game = new TicTacToe(board, player1, player2);

    System.out
        .println("Please make your move selection by entering a number 1-9 corresponding to the movement key on the right.\n");
    System.out.println(board.toString());
    game.runGame();
    System.out.println(board.toString());
  }
}