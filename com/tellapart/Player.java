package com.tellapart;

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

	public int getMove(TicTacToeBoard board) {
		return move_strategy.move(board);
	}
}