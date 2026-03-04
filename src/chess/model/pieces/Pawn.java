package chess.model.pieces;

import chess.model.Board;
import chess.model.Game;

public class Pawn extends Piece {

	public Pawn(char color, Board board, Game game) {
		super(color, board, game);

	}

	public void calculateLegalMoves() {
		isValidEnPassant();
		int col = this.getCol();
		int row = this.getRow();
		int direction = this.getColor() == 'w' ? -1 : 1;

		if (row + direction < BOARD_SIZE && chessBoard[row + direction][col] == null) {
			legalMoves[row + direction][col] = true;
			if (!this.hasMoved && row + direction * 2 < 8 && chessBoard[row + direction * 2][col] == null) {
				legalMoves[row + direction * 2][col] = true;
			}
		}

		int[][] directions = { { 1, 1 }, { 1, -1 } };

		for (int[] dir : directions) {
			int r = row + dir[0] * direction;
			int c = col + dir[1] * direction;
			if (r < 0 || r >= BOARD_SIZE || c < 0 || c >= BOARD_SIZE) {
				continue;
			}
			Piece square = chessBoard[r][c];

			if (square != null && square.getColor() != this.getColor()) {
				legalMoves[r][c] = true;
			}
		}

	}

	public void isValidEnPassant() {
		int row = this.getRow();
		int col = this.getCol();
		int[] offsets = { 1, -1 };
		int direction = this.getColor() == 'w' ? -1 : 1;

		for (int i = 0; i < 2; i++) {
			if (col + offsets[i] < BOARD_SIZE && col + offsets[i] >= 0) {
				if (game.getDoublePushedPawn() != null
						&& game.getDoublePushedPawn() == chessBoard[row][col + offsets[i]]
						&& game.getDoublePushedPawn().getColor() != this.getColor()) {
					game.setEnpassantSquare(row + direction, col + offsets[i]);
					legalMoves[row + direction][col + offsets[i]] = true;

				}
			}
		}

	}

	public Piece selectPromotingPiece(int promotionSquareCol, int row, int col) {
		int direction = this.getColor() == 'w' ? 1 : -1;
		int finalRank = this.getColor() == 'w' ? 0 : 7;
		Piece[] pieces = { new Queen(this.getColor(), board, game), new Knight(this.getColor(), board, game),
				new Rook(this.getColor(), board, game), new Bishop(this.getColor(), board, game) };

		for (int r = 0; r < pieces.length; r++) {
			if (finalRank + r * direction == row && promotionSquareCol == col) {
				return pieces[r];
			}

		}

		return null;

	}

}
