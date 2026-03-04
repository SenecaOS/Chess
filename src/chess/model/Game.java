package chess.model;

import chess.model.pieces.Pawn;
import chess.model.pieces.Piece;

public class Game {

	boolean isWhitesTurn;
	private Pawn doublePushedPawn;
	int[] enpassantSquare = { -1, -1 }; //dummy values
	private boolean promotionPending;
	private int[] promotionSquare = new int[2];
	private Pawn promotingPawn;
	Board board;

	public Game() {
		board = new Board(this);
		isWhitesTurn = true;

	}

	public void setPromotionPending(boolean bool) {
		promotionPending = bool;

	}

	public boolean getPromotionPending() {
		return promotionPending;
	}

	public boolean pieceSameColor(int selectedRow, int selectedCol) {
		if (board.getBoard()[selectedRow][selectedCol] != null
				&& board.getBoard()[selectedRow][selectedCol].getColor() == getTurn()) {
			return true;
		}
		return false;

	}

	public Piece[][] getBoard() {
		return board.getBoard();
	}

	public Piece selectPiece(int row, int col) {

		return board.getBoard()[row][col];

	}

	public void setDoublePushedPawn(Pawn doublePushedPawn) {
		this.doublePushedPawn = doublePushedPawn;

	}

	public void attemptPromotion(int selectedRow, int selectedCol) {
		board.attemptPromotion(getPromotingPawn(), selectedRow, selectedCol, getPromotionSquareRow(),
				getPromotionSquareCol());

	}

	public Pawn getDoublePushedPawn() {
		return doublePushedPawn;

	}

	public void resetEnpassantSquare() {
		enpassantSquare[0] = -1;
		enpassantSquare[1] = -1;
	}

	public void setEnpassantSquare(int row, int col) {
		enpassantSquare[0] = row;
		enpassantSquare[1] = col;

	}

	public int getEnpassantSquareRow() {
		return enpassantSquare[0];

	}

	public int getEnpassantSquareCol() {
		return enpassantSquare[1];

	}

	public void resetDoublePushedPawn() {

		if (doublePushedPawn != null && doublePushedPawn.getColor() == this.getTurn()) {
			doublePushedPawn = null;
		}

	}

	public boolean attemptMove(int oldRow, int oldCol, int selectedRow, int selectedCol) {
		Move move = new Move(board, this, oldRow, oldCol, selectedRow, selectedCol);
		return board.executeMove(move);

	}

	public void setPromotingPawn(Piece piece) {
		promotingPawn = (Pawn) piece;

	}

	public int getPromotingPawnCol() {
		return promotingPawn.getCol();
	}

	public int getPromotingPawnRow() {
		return promotingPawn.getRow();
	}

	public char getTurn() {
		return isWhitesTurn() ? 'w' : 'b';
	}

	public boolean isWhitesTurn() {
		return isWhitesTurn;
	}

	public void toggleTurn() {
		isWhitesTurn = !isWhitesTurn;
		resetDoublePushedPawn();
		resetEnpassantSquare();
	}

	public void handlePromotion(Piece selectedPiece, int selectedRow, int selectedCol) {
		setPromotingPawn(selectedPiece);
		setPromotionSquare(selectedRow, selectedCol);
		setPromotionPending(true);
		setPromotionPawn((Pawn) selectedPiece);
		

	}


	public Pawn getPromotingPawn() {
		return promotingPawn;
	}

	private void setPromotionPawn(Pawn selectedPiece) {
		promotingPawn = selectedPiece;

	}

	public int getPromotionSquareRow() {
		return promotionSquare[0];

	}

	public int getPromotionSquareCol() {
		return promotionSquare[1];

	}

	private void setPromotionSquare(int selectedRow, int selectedCol) {
		promotionSquare[0] = selectedRow;
		promotionSquare[1] = selectedCol;

	}

}
