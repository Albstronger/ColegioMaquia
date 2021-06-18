package main.statemachine.states.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.sprites.SpriteSheet;
import main.statemachine.GameState;

public class GameGestor implements GameState {

	private MapGestor mapGestor;
	SpriteSheet ssh = new SpriteSheet("/images/textureSheets/001.png", 32, true);

	public void update() {

	}

	public void draw(Graphics g) {
		BufferedImage image = ssh.getSprite(0, 0).getImage();
		g.drawImage(image, 100, 100, null);
	}

}
