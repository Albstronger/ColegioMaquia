package main.statemachine.states.game;

import java.awt.Color;
import java.awt.Graphics;

import main.MainGestor;
import main.control.ControlGestor;
import main.entities.Player;
import main.maps.Map;
import main.statemachine.GameState;
import main.userinterface.HUD;

public class GameGestor implements GameState {

	private Map map = new Map("/text/testMap.txt");
	private Player player = new Player(0, 0, map);

	public void update() {
		player.update();
		map.update((int) player.getX(), (int) player.getY());
	}

	public void draw(Graphics g) {
		map.draw(g, (int) player.getX(), (int) player.getY());
		player.draw(g);
		HUD.drawResBar(g, player.getResistance());
		if (ControlGestor.KEYBOARD.debug) {
			g.setColor(Color.RED);
			g.drawString("X = " + player.getX(), 10, 15);
			g.drawString("Y = " + player.getY(), 10, 30);
			g.drawString("APS = " + MainGestor.getAps(), 10, 45);
			g.drawString("FPS = " + MainGestor.getFps(), 10, 60);
		}
	}

}
