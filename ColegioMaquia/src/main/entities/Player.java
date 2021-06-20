package main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Constants;
import main.control.ControlGestor;
import main.sprites.SpriteSheet;

public class Player {

	private double x;
	private double y;

	private int animationState;

	private int dir;

	private boolean moving;

	private SpriteSheet sheet;

	private BufferedImage actualImage;

	public Player(final double x, final double y) {
		this.x = x;
		this.y = y;

		animationState = 0;

		dir = 1;

		moving = false;

		sheet = new SpriteSheet("/images/playerSheets/1.png", Constants.SPRITE_SIDE, false);

		actualImage = sheet.getSprite(0).getImage();
	}

	public void update() {
		if (ControlGestor.KEYBOARD.isUp()) {
			dir = 1;
			y -= 1;
			moving = true;
			animate();
		}
		if (ControlGestor.KEYBOARD.isDown()) {
			dir = 0;
			y += 1;
			moving = true;
			animate();
		}
		if (ControlGestor.KEYBOARD.isLeft()) {
			dir = 3;
			x -= 1;
			moving = true;
			animate();
		}
		if (ControlGestor.KEYBOARD.isRight()) {
			dir = 2;
			x += 1;
			moving = true;
			animate();
		}
		moving = false;
	}

	private void animate() {
		int animationFrecuency = 10;
		int stateLimit = 4;

		if (moving) {
			if (Constants.APS % animationFrecuency == 0) {
				animationState++;
				if (animationState >= stateLimit) {
					animationState = 0;
				}

				switch (animationState) {
				case 0:
					actualImage = sheet.getSprite(dir, 1).getImage();
					break;
				case 1:
					actualImage = sheet.getSprite(dir, 0).getImage();
					break;
				case 2:
					actualImage = sheet.getSprite(dir, 2).getImage();
					break;
				case 3:
					actualImage = sheet.getSprite(dir, 0).getImage();
				}
			}
		} else {
			actualImage = sheet.getSprite(dir, 0).getImage();
		}
	}

	public void draw(Graphics g) {
		final int Xcenter = Constants.SCREEN_WIDTH / 2 - Constants.SPRITE_SIDE / 2;
		final int Ycenter = Constants.SCREEN_HEIGHT / 2 - Constants.SPRITE_SIDE / 2;

		g.setColor(Color.GREEN);
		g.drawImage(actualImage, Xcenter, Ycenter, null);
		// g.drawRect(Xcenter, Ycenter, 32, 32);
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
