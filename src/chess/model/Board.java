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

	

		board[0][0] = new Rook('b', this, game);
		board[0][1] = new Knight('b', this, game);
		board[0][2] = new Bishop('b', this, game);
		board[0][3] = new Queen('b', this, game);
		board[0][4] = new King('b', this, game);
		blackKing = (King) board[0][4];
		board[0][5] = new Bishop('b', this, game);
		board[0][6] = new Knight('b', this, game);
		board[0][7] = new Rook('b', this, game);

		for (int i = 0; i < 8; i++) {
			board[1][i] = new Pawn('b', this, game);
		}

		board[7][0] = new Rook('w', this, game);
		board[7][1] = new Knight('w', this, game);
		board[7][2] = new Bishop('w', this, game);
		board[7][3] = new Queen('w', this, game);
		board[7][4] = new King('w', this, game);
		whiteKing = (King) board[7][4];
		board[7][5] = new Bishop('w', this, game);
		board[7][6] = new Knight('w', this, game);
		board[7][7] = new Rook('w', this, game);
		for (int i = 0; i < 8; i++) {
			board[6][i] = new Pawn('w', this, game);
		}
		

	}
	
	public King getKing() {
		King king = game.isWhitesTurn()? whiteKing : blackKing; 
		return king;
	}
	
	public boolean executeMove(Move move) {
		Piece piece = board[move.getFromRow()][move.getFromCol()];
		piece.calculateLegalMoves();

		if (piece.isLegal(move.getToRow(), move.getToCol()) && getKing().isNotInCheck(move.getToRow(), move.getToCol(), piece)) {
			
			if(move.isPromotion(piece)) {
				game.setPromotingPawn(piece);
				game.handlePromotion(piece, move.getToRow(), move.getToCol());
				move.setPendingPromotion(true);
				return true;
			}
			
			move.setPendingPromotion(false);
			move.specialMove(move, board);
			board[move.getFromRow()][move.getFromCol()] = null;
			board[move.getToRow()][move.getToCol()] = piece;
			piece.setHasMoved(true);
			piece.resetLegalMoves();
			game.toggleTurn();
			return true;
			

		}
		return false;
	}
	public void attemptPromotion(Piece promotingPawn, int selectedRow, int selectedCol, int promotingRow, int promotingCol) {
		Piece piece = game.getPromotingPawn().selectPromotingPiece(game.getPromotionSquareCol(), selectedRow,
				selectedCol);
		
		if(piece != null) {
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
		int backrank = game.isWhitesTurn()? 7 : 0; 
			if(toCol-fromCol > 0) {
				board[backrank][5] = board[backrank][7];
				board[backrank][7] = null;
	}
			else {
				board[backrank][3] = board[backrank][0];
				board[backrank][0] = null;
				
			}
			
	}
	
	
	
	public Piece[][] getBoard() {
		return board;
	}

}
