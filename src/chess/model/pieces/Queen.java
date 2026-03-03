package chess.model.pieces;

import chess.model.Board;
import chess.model.Game;

public class Queen extends Piece {

	public Queen(char color, Board board, Game game) {

		super(color, board, game);
	}

	public void calculateLegalMoves() {

	
		calculateDiagonalMoves();
		calculateVerticalHorizontalMoves();
	}
	



}