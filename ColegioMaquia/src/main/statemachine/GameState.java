package main.statemachine;

import java.awt.Graphics;

public interface GameState {
	void update();

	void draw(final Graphics g);
}
