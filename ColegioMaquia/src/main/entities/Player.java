package main.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Constants;
import main.control.ControlGestor;
import main.sprites.SpriteSheet;

public class Player {

	private double x;
	private double y;

	private char dir;

	private SpriteSheet sheet;

	private BufferedImage actualImage;

	public Player(final double x, final double y) {
		this.x = x;
		this.y = y;

		dir = 's';

		sheet = new SpriteSheet("/images/playerSheets/1.png", Constants.SPRITE_SIDE, false);

		actualImage = sheet.getSprite(0).getImage();
	}

	public void update() {
		dir = 'n';
		if (ControlGestor.KEYBOARD.isUp()) {
			dir = 'n';
			animate();
			y -= 1;
		}
		if (ControlGestor.KEYBOARD.isDown()) {
			dir = 's';
			animate();
			y += 1;
		}
		if (ControlGestor.KEYBOARD.isLeft()) {
			dir = 'w';
			animate();
			x -= 1;
		}
		if (ControlGestor.KEYBOARD.isRight()) {
			dir = 'e';
			animate();
			x += 1;
		}
	}

	public void animate() {
		switch (dir) {
		case 'n':
			actualImage = sheet.getSprite(1, 0).getImage();
			break;
		case 's':
			actualImage = sheet.getSprite(0, 0).getImage();
			break;
		case 'w':
			actualImage = sheet.getSprite(3, 0).getImage();
			break;
		case 'e':
			actualImage = sheet.getSprite(2, 0).getImage();
			break;
		}
	}

	public void draw(Graphics g) {
		final int Xcenter = Constants.SCREEN_WIDTH / 2 - Constants.SPRITE_SIDE / 2;
		final int Ycenter = Constants.SCREEN_HEIGHT / 2 - Constants.SPRITE_SIDE / 2;

		g.drawImage(actualImage, Xcenter, Ycenter, null);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public void setY(final double y) {
		this.y = y;
	}
}
