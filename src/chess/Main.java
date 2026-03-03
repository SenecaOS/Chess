<<<<<<< HEAD

=======
>>>>>>> 2f3cab684f5ea8170b087c55b81f3940ac396dc6
package chess;

import chess.model.Game;
import chess.ui.ChessFrame;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		ChessFrame frame = new ChessFrame(game);
		frame.setVisible(true);

	}

}
