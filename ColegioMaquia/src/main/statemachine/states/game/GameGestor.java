package main.statemachine.states.game;

import java.awt.Color;
import java.awt.Graphics;

import main.entities.Player;
import main.maps.Map;
import main.statemachine.GameState;

public class GameGestor implements GameState {

	private Map map = new Map("/text/testMap.map");
	private Player player = new Player(0, 0);

	public void update() {
		player.update();
	}

	public void draw(Graphics g) {
		map.draw(g, (int) player.getX(), (int) player.getY());
		player.draw(g);
		g.setColor(Color.RED);
		g.drawString("X = " + player.getX(), 10, 15);
		g.drawString("Y = " + player.getY(), 10, 30);
	}

}
