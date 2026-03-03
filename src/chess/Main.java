
package chess;

import chess.model.Game;
import chess.ui.ChessFrame;
aaaa
public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		ChessFrame frame = new ChessFrame(game);
		frame.setVisible(true);

	}

}
