package chess.model.pieces;

import chess.model.Board;
import chess.model.Game;
import chess.model.ThreatDetection;

public class King extends Piece {

	ThreatDetection square = new ThreatDetection();
	static final int KING_STARTING_POSITION = 4;

	public King(char color, Board board, Game game) {
		super(color, board, game);

	}

	public boolean isNotInCheck(int row, int col, Piece piece) {
		Piece[][] copy = new Piece[BOARD_SIZE][BOARD_SIZE];
		int kingRow = this.getRow();
		int kingCol = this.getCol();
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				copy[i][j] = board.getBoard()[i][j];

			}
		}
		if (piece instanceof King) {
			kingRow = row;
			kingCol = col;
		}
		copy[piece.getRow()][piece.getCol()] = null;
		copy[row][col] = piece;

		return square.isNonCheck(this, game, copy, kingRow, kingCol);
	}

	public void calculateLegalMoves() {
		int row = this.getRow();
		int col = this.getCol();

		if (canShortCastle()) {
			legalMoves[row][col + 2] = true;
		}

		if (canLongCastle()) {
			legalMoves[row][col - 2] = true;
		}

		int[][] directions = { { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 }, { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

		for (int[] dir : directions) {
			int r = row + dir[0];
			int c = col + dir[1];
			if (r >= BOARD_SIZE|| r < 0 || c >= BOARD_SIZE|| c < 0) {
				continue;
			}

			if (square.isNonCheck(this, game, chessBoard, r, c)) {
				legalMoves[r][c] = true;
			}

		}

	}

	public boolean canLongCastle() {
		int backrank = game.getTurn() == 'w' ? 7 : 0;

		for (int col = 1; col < KING_STARTING_POSITION; col++) {
			if (!(chessBoard[backrank][0] instanceof Rook) || chessBoard[backrank][0].getHasMoved()
					|| chessBoard[backrank][col] != null) {
				return false;
			}
			if (col <= 2) {
				if (!square.isNonCheck(this, game, chessBoard, backrank, KING_STARTING_POSITION - col)) {
					return false;
				}
			}

		}

		return !this.getHasMoved();

	}

	public boolean canShortCastle() {
		int backrank = game.getTurn() == 'w' ? 7 : 0;
		for (int col = KING_STARTING_POSITION + 1; col < 7; col++) {
			int i = 1;
			if (!(chessBoard[backrank][7] instanceof Rook) || chessBoard[backrank][7].getHasMoved()
					|| chessBoard[backrank][col] != null) {
				return false;

			}

			if (col < 7) {
				if (!square.isNonCheck(this, game, chessBoard, backrank, KING_STARTING_POSITION + i++)) {
					return false;
				}

			}

		}
		return !this.getHasMoved();

	}
}
