package chess.model.pieces;

import chess.model.Board;
import chess.model.Game;

public class Knight extends Piece {
	public Knight(char color, Board board, Game game) {
		super(color, board, game);
	}

	public void calculateLegalMoves() {
		int col = this.getCol();
		int row = this.getRow();
		int[][] knightMoves = { { 1, 2 }, { 2, 1 }, { -1, -2 }, { -2, -1 }, { -1, 2 }, { 2, -1 }, { 1, -2 },
				{ -2, 1 } };

		for (int[] move : knightMoves) {
			int r = row + move[0];
			int c = col + move[1];

			if (c < 8 && c >= 0 && r < 8 && r >= 0) {
				if (chessBoard[r][c] == null || chessBoard[r][c].getColor() != this.getColor()) {
					legalMoves[r][c] = true;
				}
			}

		}

	}

}


