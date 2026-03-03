<<<<<<< HEAD
package chess.ui;

import java.awt.Color;

import javax.swing.JFrame;

import chess.model.Game;

public class ChessFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public ChessFrame(Game game) {

		setTitle("SolidChess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBackground(Color.black);
		ChessPanel panel = new ChessPanel(game);
		add(panel);
		pack();

	}

}
=======
package chess.ui;

import java.awt.Color;

import javax.swing.JFrame;

import chess.model.Game;

public class ChessFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public ChessFrame(Game game) {

		setTitle("SolidChess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBackground(Color.black);
		ChessPanel panel = new ChessPanel(game);
		add(panel);
		pack();

	}

}
>>>>>>> a27aabb54f748658a8062a38db47d727cad65670
