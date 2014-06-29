package com.tellapart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Provides a command-line move interface for human users.
 * 
 * @author dobromirv
 */
class HumanMove implements MoveMethod {
  private static BufferedReader reader = new BufferedReader(
      new InputStreamReader(System.in));

  public int move(TicTacToeBoard board) {
    String move_str;
    int move_int = 0;
    boolean valid_input = false;
    while (!valid_input) {
      System.out.print("Where to ? ");
      move_str = getUserInput();
      if (Character.isDigit(move_str.toCharArray()[0])) {
        move_int = Integer.parseInt(move_str);
        if ((move_int <= (TicTacToe.N) * (TicTacToe.N)) && move_int >= 1) {
          valid_input = true;
          break;
        }
      }

      if (!valid_input) {
        System.out.println("Invalid input");
        continue;
      }
    }
    return move_int;
  }

  private static String getUserInput() {
    String input = "";
    try {
      input = reader.readLine();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return input;
  }

}