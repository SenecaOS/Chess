<<<<<<< HEAD
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
=======
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
>>>>>>> a27aabb54f748658a8062a38db47d727cad65670
}