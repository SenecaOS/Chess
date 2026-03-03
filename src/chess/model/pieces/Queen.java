<<<<<<< HEAD
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
	



=======
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
	



>>>>>>> a27aabb54f748658a8062a38db47d727cad65670
}