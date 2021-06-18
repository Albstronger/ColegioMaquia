package main.statemachine.states.game;

import java.awt.Graphics;

import main.maps.Map;
import main.statemachine.GameState;

public class GameGestor implements GameState {

	private MapGestor mapGestor;
	private Map map = new Map("/text/testMap.map");

	public void update() {
	}

	public void draw(Graphics g) {
		map.draw(g);
	}

}
