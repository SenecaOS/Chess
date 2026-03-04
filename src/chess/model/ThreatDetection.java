package chess.model;

import chess.model.pieces.Bishop;
import chess.model.pieces.King;
import chess.model.pieces.Knight;
import chess.model.pieces.Pawn;
import chess.model.pieces.Piece;
import chess.model.pieces.Queen;
import chess.model.pieces.Rook;

public class ThreatDetection {

	public boolean isNotThreatenedByBishop(King king, Piece board[][], int row, int col) {

		int[][] directions = { { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };

		for (int[] dir : directions) {
			int i = 1;
			while (true) {
				int r = row + dir[0] * i;
				int c = col + dir[1] * i;

				if (r > 7 || r < 0 || c > 7 || c < 0) {
					break;
				}

				if (board[r][c] != null && board[r][c].getColor() != king.getColor()
						&& (board[r][c] instanceof Bishop || board[r][c] instanceof Queen)) {
					return false;
				}

				if (board[r][c] != null && board[r][c] != king) {
					break;
				}

				i++;

			}

		}

		return true;
	}

	public boolean isNotThreatenedByKnight(King king, Piece[][] board, int row, int col) {

		int[][] knightMoves = { { 1, 2 }, { 2, 1 }, { -1, -2 }, { -2, -1 }, { -1, 2 }, { 2, -1 }, { 1, -2 },
				{ -2, 1 } };

		for (int[] move : knightMoves) {
			int r = row + move[0];
			int c = col + move[1];

			if (c < 8 && c >= 0 && r < 8 && r >= 0) {
				if (board[r][c] instanceof Knight && board[r][c].getColor() != king.getColor()) {
					return false;
				}
			}

		}
		return true;

	}

	public boolean isNotThreatenedByKing(King king, Piece[][] board, int row, int col) {

		int[][] directions = { { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 }, { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		for (int[] dir : directions) {
			int r = row + dir[0];
			int c = col + dir[1];
			if (r > 7 || r < 0 || c > 7 || c < 0) {
				continue;
			}

			Piece square = board[r][c];
			if (square instanceof King && square.getColor() != king.getColor()) {
				return false;
			}

		}
		return true;

	}

	public boolean isNotThreatenedByPawn(King king, Game game, Piece[][] board, int row, int col) {

		int i = game.getTurn() == 'w' ? -1 : 1;
		int[][] directions = { { 1, 1 }, { 1, -1 } };

		for (int[] dir : directions) {
			int r = row + dir[0] * i;
			int c = col + dir[1] * i;
			if (r < 0 || r >= 8 || c < 0 || c >= 8) {
				continue;
			}
			Piece square = board[r][c];

			if (square instanceof Pawn && square.getColor() != king.getColor()) {
				return false;
			}
		}
		return true;

	}

	public boolean isNotThreatenedByRook(King king, Piece[][] board, int row, int col) {

		int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		for (int[] dir : directions) {
			int i = 1;
			while (true) {
				int r = row + dir[0] * i;
				int c = col + dir[1] * i;

				if (r > 7 || r < 0 || c > 7 || c < 0) {
					break;
				}

				if (board[r][c] != null && board[r][c].getColor() != king.getColor()
						&& (board[r][c] instanceof Rook || board[r][c] instanceof Queen)) {
					return false;
				}

				if (board[r][c] != null && board[r][c] != king) {
					break;
				}

				i++;

			}

		}
		return true;

	}

	public boolean isNonCheck(King king, Game game, Piece[][] board, int row, int col) {
		return isNotThreatenedByKnight(king, board, row, col) && isNotThreatenedByRook(king, board, row, col)
				&& isNotThreatenedByBishop(king, board, row, col) && isNotThreatenedByPawn(king, game, board, row, col)
				&& isNotThreatenedByKing(king, board, row, col);

	}

}
