
package chess.model;

import chess.model.pieces.King;
import chess.model.pieces.Pawn;
import chess.model.pieces.Piece;

public class Move {
	private int fromRow;
	private int fromCol;
	private int toRow;
	private int toCol;
	private Pawn doublePushedPawn;
	private Board boardInstance;
	King king;
	

	private Game game;
	private Piece piece;
	private Pawn promotingPawn;
	private int promotingRow;
	private int promotingCol;
	private boolean pendingPromotion;

	public Move(Board boardInstance, Game game, int fromRow, int fromCol, int toRow, int toCol) {
		this.fromRow = fromRow;
		this.fromCol = fromCol;
		this.toRow = toRow;
		this.toCol = toCol;
		this.game = game;
		this.boardInstance = boardInstance;
		king = boardInstance.getKing();
		
		
	}
	

	
	
	

	public int getToRow() {
		return toRow;
	}

	public int getToCol() {
		return toCol;
	}

	public int getFromCol() {
		return fromCol;
	}

	public int getFromRow() {
		return fromRow;
	}
	
	
	

	public Piece selectPiece(Piece[][] board, int row, int col) {

		Piece piece = board[row][col];

		if (piece != null && piece.getColor() == game.getTurn()) {
			return piece;
		}
		return null;
	}

	public boolean isEnpassant() {
		if (game.getEnpassantSquareRow() == toRow && game.getEnpassantSquareCol() == toCol && game.getDoublePushedPawn() != null) {
			return true;
		}
		return false;
	}


	
	public boolean getPendingPromotion(){
		return pendingPromotion;
	}

	public void setPendingPromotion(boolean bool) {
		pendingPromotion = bool;
		
	}



	public void resetDoublePushedPawn() {
		doublePushedPawn = null;
	}

	public void specialMove(Move move, Piece[][] board) {
		
		piece = board[move.getFromRow()][move.getFromCol()];
		if(piece instanceof King) {
			if(move.isCastles()) {
				boardInstance.handleCastles(move.getFromCol(), move.getToCol());
			}
		}

		if (piece instanceof Pawn) {
			if (move.isEnpassant()) {
				boardInstance.removeCapturedEnpassantPawn(game.getDoublePushedPawn().getRow(), game.getDoublePushedPawn().getCol());
		}
			
			if(isDoublePawnPush()) {
				doublePushedPawn = (Pawn) piece;
				game.setDoublePushedPawn(doublePushedPawn);
		}

	}
		if(isPromotion(piece)) {
			setPromotionSquare();
			setPromotingPawn(piece);
			
		}
		
		game.setPromotionPending(isPromotion(piece));
		
	}
	
	public boolean isPromotion(Piece piece) {
		if(piece != null) {
			int backrank = piece.getColor() == 'w'? 0 : 7;
			return piece instanceof Pawn && toRow == backrank;
		}
		return false;	
	}
	
	public void setPromotionSquare(){
		promotingRow = toRow;
		promotingCol = toCol;
	}
	
	public int getPromotingRow() {
		return promotingRow;
	}
	
	
	public int getPromotingCol() {
		return promotingCol;
	} 
	
	
	
	public void setPromotingPawn(Piece piece) {
		promotingPawn = (Pawn) piece;
		
	}

	
	public Pawn getPromotingPawn() {
		return promotingPawn;
	}
	
	
	public boolean isCastles(){
		if (Math.abs(fromCol - toCol) == 2) {
			return true;
		}
		return false;
		
	}
	
	

	public boolean isDoublePawnPush() {
		if (piece instanceof Pawn && Math.abs(fromRow - toRow) == 2) {
			return true;
		}
		return false;
	}

}
