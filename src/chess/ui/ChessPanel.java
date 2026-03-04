package chess.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import chess.model.Game;
import chess.model.PromotionType;
import chess.model.pieces.Piece;

public class ChessPanel extends JPanel {
	private Game game;
	private static final int TILE_SIZE = 60;

	private int selectedRow;
	private int selectedCol;
	private Piece selectedPiece;
	private static final int BOARD_SIZE = 8;

	private static final Color LIGHT_BLUE = new Color(51, 153, 255);
	private static final Color OVERLAY_COLOR = new Color(0, 0, 0, 125);

	private static final PromotionType[] PROMOTION_OPTIONS = { PromotionType.QUEEN, PromotionType.KNIGHT,
			PromotionType.ROOK, PromotionType.BISHOP };
	private static final int WIDTH = 480;
	private static final int HEIGHT = 480;

	private static final long serialVersionUID = 1L;

	public ChessPanel(Game game) {
		this.game = game;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		initMouseListener();

	}

	public void initMouseListener() {

		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {

				selectedRow = e.getY() / TILE_SIZE;
				selectedCol = e.getX() / TILE_SIZE;

				if (game.getPromotionPending()) {
					handlePromotionClick(e);
					repaint();
					return;

				}

				if (game.pieceSameColor(selectedRow, selectedCol)) {
					selectedPiece = game.selectPiece(selectedRow, selectedCol);
					return;
				}
				if (selectedPiece != null) {

					int oldRow = selectedPiece.getRow();
					int oldCol = selectedPiece.getCol();

					if (game.attemptMove(oldRow, oldCol, selectedRow, selectedCol)) {
						selectedPiece = null;
						repaint();
					}

				}

			}
		});

	}

	protected void handlePromotionClick(MouseEvent e) {

		game.attemptPromotion(selectedRow, selectedCol);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBoard(g);
		drawPieces(g);
		if (game.getPromotionPending()) {
			drawPromotionUI(g);
		}

	}

	private void drawBoard(Graphics g) {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				Color color = (row + col) % 2 == 0 ? Color.white : LIGHT_BLUE;
				g.setColor(color);
				g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);

			}
		}
	}

	private void drawPieces(Graphics g) {
		Piece[][] board = game.getBoard();

		for (int row = 0; row < BOARD_SIZE; row++) {
			int y = row * TILE_SIZE;
			for (int col = 0; col < BOARD_SIZE; col++) {
				int x = col * TILE_SIZE;
				Piece piece = board[row][col];
				if (piece != null) {
					ImageIcon icon = piece.getIcon();
					icon.paintIcon(this, g, x, y);
				}

			}
		}

	}

	public ImageIcon getIconForPromotingChoice(PromotionType type) {
		String pieceType = type.name().charAt(0) + type.name().substring(1).toLowerCase();
		String color = game.getTurn() == 'w' ? "White" : "Black";
		String fileName = color + pieceType + ".png";

		return new ImageIcon(getClass().getResource("/chess/images/" + fileName));
	}

	public void drawPromotionUI(Graphics g) {

		int baseRow = game.getPromotingPawnRow();
		int baseCol = game.getPromotingPawnCol();

		Color color = (baseRow + baseCol) % 2 == 0 ? Color.white : LIGHT_BLUE;
		g.setColor(color);
		g.fillRect(baseCol * TILE_SIZE, baseRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);

		g.setColor(OVERLAY_COLOR);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		int direction = game.getPromotingPawn().getColor() == 'w' ? -1 : 1;

		int x = game.getPromotionSquareCol() * TILE_SIZE;

		for (int i = 0; i < 4; i++) {
			int row = (game.getPromotionSquareRow() - i * direction);
			int y = row * TILE_SIZE;

			g.setColor(Color.white);
			g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
			ImageIcon icon = getIconForPromotingChoice(PROMOTION_OPTIONS[i]);
			icon.paintIcon(this, g, x, y);

		}

	}

}
