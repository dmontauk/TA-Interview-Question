package com.tellapart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.tellapart.TicTacToe.Player;
import com.tellapart.TicTacToeBoard.WinConfig;

// Strategy pattern. 
// The computer player's behavior/strategy can be replaced by inheriting from the interface below 
// Also, the human player's behavior inherits from the same interface 
// This also makes it easy to modify the game for 2 human players, 2 computer players etc. 

interface MoveMethod {
	public int move(TicTacToeBoard board);
}

class SimpleMoveStrategy implements MoveMethod {
	public int move(TicTacToeBoard board) {

		for (int i = 0; i < TicTacToe.N; i++) {
			for (int j = 0; j < TicTacToe.N; j++) {
			  TicTacToeBoard.Value value = board.getCell(i, j);
				if (value == TicTacToeBoard.Value.None) {
					return (i * 3 + j + 1);
				}
			}
		}
		return 0;
	}
}

class HumanMove implements MoveMethod {
	public int move(TicTacToeBoard board) {

		String move_str;
		int move_int = 0;
		boolean valid_input = false;
		while (!valid_input) {
			System.out.print("Where to ? ");
			move_str = TicTacToe.getUserInput();
			if (Character.isDigit(move_str.toCharArray()[0])) {
				move_int = Integer.parseInt(move_str);
				if ((move_int <= (TicTacToe.N) * (TicTacToe.N))
						&& move_int >= 1) {
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

}

class TicTacToe {
	protected static final int N = 3;
	private TicTacToeBoard board;
	private static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in));

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

		public int getMove() {
			return move_strategy.move(board);
		}
	}

	private Player player1, player2;

	public Player getplayer1() {
		return player1;
	}

	public Player getplayer2() {
		return player2;
	}

	public static String getPosDescription(int pos) {
		String str = "";
		if (pos == 5) {
			str = "center";
			return str;
		}

		if ((pos - 1) / 3 == 0) {
			str += "upper ";
		} else if ((pos - 1) / 3 == 1) {
			str += "middle ";
		} else
			str += "lower ";

		if ((pos - 1) % 3 == 0)
			str += "left";
		else if ((pos - 1) % 3 == 1)
			str += "middle";
		else
			str += "right";

		return str;
	}

	protected static String getUserInput() {
		String input = "";
		try {
			input = reader.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return input;
	}

	public TicTacToe(TicTacToeBoard board) {
	  this.board = board;
	  
		System.out.println("Enter player name");
		player1 = new Player(getUserInput(), TicTacToeBoard.Value.X, 0, new HumanMove());

		player2 = new Player("", TicTacToeBoard.Value.O, 1, new SimpleMoveStrategy());
		System.out.println("\nHuman player " + player1.getName()
				+ " vs Computer Player " + player2.getName() + ":");
	}

	public boolean setMove(int move, TicTacToeBoard.Value p_type) {
		int x_coord = (move - 1) / 3;
		int y_coord = (move - 1) % 3;

		if (board.getCell(x_coord, y_coord) == TicTacToeBoard.Value.None) {
			board.setCell(x_coord, y_coord, p_type);
			return true;
		} else {
			System.out.println("Invalid move");
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println("Welcome to Tic-Tac-Toe.");
		System.out.println("");

		TicTacToeBoard board = new TicTacToeBoard(N,  N);
		TicTacToe game = new TicTacToe(board);
		String move_str;
		int move1 = 0;
		int move2 = 0;
		int player_type = 0;
		WinConfig w = WinConfig.NONE;

		System.out
				.println("Please make your move selection by entering a number 1-9 corresponding to the movement key on the right.\n");
		System.out.println(board.toString());

		while (board.isWinningConfig() == WinConfig.NONE) {
			do {
				move1 = game.getplayer1().getMove();
			} while (!game.setMove(move1, game.getplayer1().getPlayerType()));

			if ((w = board.isWinningConfig()) == WinConfig.WIN) {
				System.out.println("");
				System.out.println(board.toString());
				System.out.println("You have beaten my poor AI!");
				break;
			} else if (w == WinConfig.DRAW) {
				System.out.println("");
				System.out.println(board.toString());
				System.out.println("Well played. It is a draw!");
				break;
			}

			move2 = game.getplayer2().getMove();
			System.out.println("");
			System.out.println("You have put an X in the "
					+ TicTacToe.getPosDescription(move1)
					+ ". I will put a O in the "
					+ TicTacToe.getPosDescription(move2) + ".");
			game.setMove(move2, game.getplayer2().getPlayerType());

			if (board.isWinningConfig() == WinConfig.WIN) {
				System.out.println("");
				System.out.println(board.toString());
				System.out.println("I won. Thanks for playing.");
				break;
			}
			System.out.println(board.toString());
		}

	}
}