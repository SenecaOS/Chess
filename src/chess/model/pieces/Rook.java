<<<<<<< HEAD
package chess.model.pieces;

import chess.model.Board;
import chess.model.Game;

public class Rook extends Piece{
	char color;
	String position;
	
	
	public Rook(char color, Board board, Game game) {
		super(color, board, game);
		
	}
	
	public void calculateLegalMoves(){
		calculateVerticalHorizontalMoves();

	}
	
	

=======
package chess.model.pieces;

import chess.model.Board;
import chess.model.Game;

public class Rook extends Piece{
	char color;
	String position;
	
	
	public Rook(char color, Board board, Game game) {
		super(color, board, game);
		
	}
	
	public void calculateLegalMoves(){
		calculateVerticalHorizontalMoves();

	}
	
	

>>>>>>> a27aabb54f748658a8062a38db47d727cad65670
}