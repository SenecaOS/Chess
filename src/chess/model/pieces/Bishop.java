package chess.model.pieces;

import chess.model.Board;
import chess.model.Game;

public class Bishop extends Piece {
	public Bishop(char color, Board board, Game game) {
		super(color, board, game);

	}

	public void calculateLegalMoves() {
		calculateDiagonalMoves();
	}
}