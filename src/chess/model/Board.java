package chess.model;

import chess.model.pieces.*;

public class Board {
	private Game game;
	private Piece[][] board;
	private King blackKing;
	private King whiteKing;

	public Board(Game game) {
		this.game = game;
		board = new Piece[8][8];
		generateStartingBoard();
	}

	private void generateStartingBoard() {
		
		setUpPieces(7, 6, 'w');
		setUpPieces(0, 1, 'b');

	}
	
	private void setUpPieces(int backrank, int pawnRow, char c) {

		board[backrank][0] = new Rook(c, this, game);
		board[backrank][1] = new Knight(c, this, game);
		board[backrank][2] = new Bishop(c, this, game);
		board[backrank][3] = new Queen(c, this, game);
		board[backrank][4] = new King(c, this, game);
		
		if (c == 'w') {
			whiteKing = (King) board[backrank][4];
		} else {
			blackKing = (King) board[backrank][4];

		}

		board[backrank][5] = new Bishop(c, this, game);
		board[backrank][6] = new Knight(c, this, game);
		board[backrank][7] = new Rook(c, this, game);

		for (int i = 0; i < 8; i++) {
			board[pawnRow][i] = new Pawn(c, this, game);
		}

	}
	

	public King getKing() {
		King king = game.isWhitesTurn() ? whiteKing : blackKing;
		return king;
	}

	public boolean executeMove(Move move) {
		Piece piece = getPiece(move);

		if (!isMoveValid(move, piece)) {
			return false;

		}
		if (handlePromotionIfNeeded(move, piece)) {
			return true;
		}

		applyMove(move, piece);

		return true;

	}

	private boolean handlePromotionIfNeeded(Move move, Piece piece) {
		if (!move.isPromotion(piece)) {
			return false;
		}
		game.handlePromotion(piece, move.getToRow(), move.getToCol());
		return true;

	}
	
	

	private void applyMove(Move move, Piece piece) {
		
		move.setPendingPromotion(false);
		move.specialMove(move, board);
		board[move.getFromRow()][move.getFromCol()] = null;
		board[move.getToRow()][move.getToCol()] = piece;
		piece.setHasMoved(true);
		piece.resetLegalMoves();
		game.toggleTurn();

	}

	private Piece getPiece(Move move) {
		return board[move.getFromRow()][move.getFromCol()];

	}

	private boolean isMoveValid(Move move, Piece piece) {
		piece.calculateLegalMoves();

		return piece.isLegal(move.getToRow(), move.getToCol())
				&& getKing().isNotInCheck(move.getToRow(), move.getToCol(), piece);
	}

	public void attemptPromotion(Piece promotingPawn, int selectedRow, int selectedCol, int promotingRow,
			int promotingCol) {
		Piece piece = game.getPromotingPawn().selectPromotingPiece(game.getPromotionSquareCol(), selectedRow,
				selectedCol);

		if (piece != null) {
			board[promotingPawn.getRow()][promotingPawn.getCol()] = null;
			board[promotingRow][promotingCol] = piece;
			game.toggleTurn();
		}

		game.setPromotionPending(false);

	}

	public Piece selectPiece(int row, int col) {

		if (board[row][col] != null && board[row][col].getColor() == game.getTurn()) {
			return board[row][col];
		}

		return null;

	}

	public void removeCapturedEnpassantPawn(int row, int col) {

		board[row][col] = null;
	}

	public void handleCastles(int fromCol, int toCol) {
		int backrank = game.isWhitesTurn() ? 7 : 0;
		if (toCol - fromCol > 0) {
			board[backrank][5] = board[backrank][7];
			board[backrank][7] = null;
		} else {
			board[backrank][3] = board[backrank][0];
			board[backrank][0] = null;

		}

	}

	public Piece[][] getBoard() {
		return board;
	}

}
