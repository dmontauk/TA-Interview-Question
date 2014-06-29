package com.tellapart;

/**
 * Strategy pattern for moves on the TicTacToe board. 
 * 
 * The computer player's behavior/strategy can be replaced by inheriting from the interface below 
 * Also, the human player's behavior inherits from the same interface 
 * This also makes it easy to modify the game for 2 human players, 2 computer players etc. 
 */
interface MoveMethod {
	public int move(TicTacToeBoard board);
}