package com.tellapart;

import com.tellapart.TicTacToeBoard.Cell;

class Player {
	private String name;
	private TicTacToeBoard.Value player_type;
	private int player_order;
	private MoveMethod move_strategy;

	public Player(String pname, TicTacToeBoard.Value type, int order, MoveMethod move_s) {
		name = pname;
		player_type = type;
		player_order = order;
		move_strategy = move_s;
	}

	public String getName() {
		return name;
	}

	public TicTacToeBoard.Value getPlayerType() {
		return player_type;
	}

	public Cell getMove(TicTacToeBoard board) {
		return move_strategy.move(board);
	}
}