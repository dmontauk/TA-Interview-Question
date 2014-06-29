package com.tellapart;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tellapart.TicTacToeBoard.Cell;

public class HumanMoveTest {

  @Test
  public void testConvertHumanInputToCell3x3() {
    HumanMove strategy = new HumanMove(3, 3);
    assertEquals(new Cell(0, 0), strategy.convertHumanInputToCell(1));
    assertEquals(new Cell(1, 0), strategy.convertHumanInputToCell(2));
    assertEquals(new Cell(2, 0), strategy.convertHumanInputToCell(3));
    assertEquals(new Cell(0, 1), strategy.convertHumanInputToCell(4));
    assertEquals(new Cell(1, 1), strategy.convertHumanInputToCell(5));
    assertEquals(new Cell(2, 1), strategy.convertHumanInputToCell(6));
    assertEquals(new Cell(0, 2), strategy.convertHumanInputToCell(7));
    assertEquals(new Cell(1, 2), strategy.convertHumanInputToCell(8));
    assertEquals(new Cell(2, 2), strategy.convertHumanInputToCell(9));
  }
  
  @Test
  public void testConvertHumanInputToCell3x2() {
    HumanMove strategy = new HumanMove(3, 2);
    assertEquals(new Cell(0, 0), strategy.convertHumanInputToCell(1));
    assertEquals(new Cell(1, 0), strategy.convertHumanInputToCell(2));
    assertEquals(new Cell(2, 0), strategy.convertHumanInputToCell(3));
    assertEquals(new Cell(0, 1), strategy.convertHumanInputToCell(4));
    assertEquals(new Cell(1, 1), strategy.convertHumanInputToCell(5));
    assertEquals(new Cell(2, 1), strategy.convertHumanInputToCell(6));
  }
  
  @Test
  public void testConvertHumanInputToCell2x4() {
    HumanMove strategy = new HumanMove(2, 4);
    assertEquals(new Cell(0, 0), strategy.convertHumanInputToCell(1));
    assertEquals(new Cell(1, 0), strategy.convertHumanInputToCell(2));
    assertEquals(new Cell(0, 1), strategy.convertHumanInputToCell(3));
    assertEquals(new Cell(1, 1), strategy.convertHumanInputToCell(4));
    assertEquals(new Cell(0, 2), strategy.convertHumanInputToCell(5));
    assertEquals(new Cell(1, 2), strategy.convertHumanInputToCell(6));
    assertEquals(new Cell(0, 3), strategy.convertHumanInputToCell(7));
    assertEquals(new Cell(1, 3), strategy.convertHumanInputToCell(8));
  }
}
