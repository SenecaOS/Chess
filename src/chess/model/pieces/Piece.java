package chess.model.pieces;

import java.util.Arrays;
import javax.swing.ImageIcon;

import chess.model.Board;
import chess.model.Game;

public class Piece {
	private char color;
	boolean hasMoved;
	protected final static int BOARD_SIZE = 8;

	boolean[][] legalMoves = new boolean[BOARD_SIZE][BOARD_SIZE];
	protected Board board;
	Piece[][] chessBoard;
	int row;
	int col;
	Game game;

	public Piece(char color, Board board, Game game) {
		this.color = color;
		hasMoved = false;
		this.board = board;
		chessBoard = board.getBoard();
		this.game = game;

	}

	public char getColor() {

		return color;

	}

	public int getRow() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				if (board.getBoard()[row][col] == this) {
					return row;
				}
			}

		}
		return -1;
	}

	public void setHasMoved(boolean moved) {
		hasMoved = moved;
	}

	public void resetLegalMoves() {
		for (boolean arr[] : legalMoves) {
			Arrays.fill(arr, false);
		}
	}

	public Piece selectPiece(int row, int col) {

		if (board.getBoard()[row][col] != null && board.getBoard()[row][col].getColor() == game.getTurn()) {
			return board.getBoard()[row][col];
		}
		return null;
	}

	public int getCol() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				if (board.getBoard()[row][col] == this) {
					return col;
				}
			}

		}
		return -1;
	}

	public boolean isLegal(int row, int col) {
		return legalMoves[row][col];

	}

	public ImageIcon getIcon() {

		String color = this.getColor() == 'w' ? "White" : "Black";
		String piece = this.getClass().getSimpleName();

		String fileName = color + piece + ".png";
		return new ImageIcon(getClass().getResource("/chess/images/" + fileName));

	}

	public boolean isValidMove(int row, int col) {
		this.resetLegalMoves();
		this.calculateLegalMoves();

		if (!this.isLegal(row, col)) {
			return false;
		}
		return true;
	}

	public void calculateLegalMoves() {

	}

	public void calculateDiagonalMoves() {
		int col = this.getCol();
		int row = this.getRow();
		int[][] directions = { { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };

		for (int[] dir : directions) {
			int i = 1;
			while (true) {
				int r = row + dir[0] * i;
				int c = col + dir[1] * i;

				if (r >= BOARD_SIZE || r < 0 || c >= BOARD_SIZE || c < 0) {
					break;
				}
				if (board.getBoard()[r][c] == null || board.getBoard()[r][c].getColor() != this.getColor()) {
					legalMoves[r][c] = true;
				}

				if (board.getBoard()[r][c] != null) {
					break;
				}

				i++;

			}

		}

	}

	public void calculateVerticalHorizontalMoves() {
		int col = this.getCol();
		int row = this.getRow();
		int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		for (int[] dir : directions) {
			int i = 1;
			while (true) {
				int r = row + dir[0] * i;
				int c = col + dir[1] * i;
				if (r < 0 || r >= BOARD_SIZE || c < 0 || c >= BOARD_SIZE) {
					break;
				}
				if (board.getBoard()[r][c] == null || board.getBoard()[r][c].getColor() != this.getColor()) {
					legalMoves[r][c] = true;

				}

				if (board.getBoard()[r][c] != null) {
					break;

				}
				i++;

			}

		}

	}

	public boolean getHasMoved() {
		return hasMoved;
	}
}
