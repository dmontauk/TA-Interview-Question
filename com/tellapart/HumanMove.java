package com.tellapart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.tellapart.TicTacToeBoard.Cell;

/**
 * Provides a command-line move interface for human users.
 * 
 * Currently hard coded to 2-dimensional boards.
 * 
 * @author dobromirv
 */
class HumanMove implements MoveMethod {
  private static BufferedReader reader = new BufferedReader(
      new InputStreamReader(System.in));
  private final int max_x;
  private final int max_y;
  
  HumanMove(int max_x, int max_y) {
    this.max_x = max_x;
    this.max_y = max_y;
  }

  public Cell move(TicTacToeBoard board) {
    String move_str;
    int move_int = 0;
    boolean valid_input = false;
    while (!valid_input) {
      System.out.print("Where to ? ");
      move_str = getUserInput();
      if (Character.isDigit(move_str.toCharArray()[0])) {
        move_int = Integer.parseInt(move_str);
        if ((move_int <= (max_x) * (max_y)) && move_int >= 1) {
          valid_input = true;
          break;
        }
      }

      if (!valid_input) {
        System.out.println("Invalid input");
        continue;
      }
    }
    return convertHumanInputToCell(move_int);
  }
  
  // Visible for testing. Should probably be refactored so we can just test the "move" method.
  Cell convertHumanInputToCell(int move_int) {
    int x_coord = (move_int - 1) % max_x;
    int y_coord = (move_int - 1) / max_x;
    return new Cell(x_coord, y_coord);
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