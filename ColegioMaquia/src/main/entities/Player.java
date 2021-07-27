package main.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Constants;
import main.control.ControlGestor;
import main.maps.Map;
import main.sprites.SpriteSheet;

public class Player {

	private double x;
	private double y;

	private int dir;

	private double speed = 1;

	private boolean moving;

	private SpriteSheet sheet;

	private BufferedImage actualImage;

	private final int PLAYER_WIDTH = 16;
	private final int PLAYER_HEIGHT = 16;

	private final Rectangle UP_LIMIT = new Rectangle(Constants.WINDOW_X_CENTER - PLAYER_WIDTH / 2,
			Constants.WINDOW_Y_CENTER, PLAYER_WIDTH, 1);
	private final Rectangle DOWN_LIMIT = new Rectangle(Constants.WINDOW_X_CENTER - PLAYER_WIDTH / 2,
			Constants.WINDOW_Y_CENTER + PLAYER_HEIGHT, PLAYER_WIDTH, 1);
	private final Rectangle LEFT_LIMIT = new Rectangle(Constants.WINDOW_X_CENTER - PLAYER_WIDTH / 2,
			Constants.WINDOW_Y_CENTER, 1, PLAYER_HEIGHT);
	private final Rectangle RIGHT_LIMIT = new Rectangle(Constants.WINDOW_X_CENTER + PLAYER_WIDTH / 2,
			Constants.WINDOW_Y_CENTER, 1, PLAYER_HEIGHT);

	private int animation;
	private int state;
	
	public static final int MAX_RESISTANCE = 600;
	public static final int MAX_RECUPERATION = 200;
	
	private int resistance = MAX_RESISTANCE;
	private int recuperation = 0;
	private boolean recovered = true;

	private Map map;

	public Player(double x, double y, Map map) {
		this.x = x;
		this.y = y;

		dir = 1;

		moving = false;

		sheet = new SpriteSheet("/images/playerSheets/1.png", Constants.SPRITE_SIDE, false);

		actualImage = sheet.getSprite(0).getImage();

		animation = 0;
		state = 0;

		this.map = map;
	}

	public void update() {
		manageSpeedAndResistance();
		changeAnimationState();
		moving = false;
		determinateDirection();
		animate();
	}

	private void manageSpeedAndResistance() {
		if (ControlGestor.KEYBOARD.running && resistance > 0) {
			speed = 2;
			recovered = false;
			recuperation = 0;
		} else {
			speed = 1;
			if (!recovered && recuperation < MAX_RECUPERATION) {
				recuperation++;
			}
			if (recuperation == MAX_RECUPERATION && resistance < 600) {
				resistance++;
			}
		}
	}

	private void changeAnimationState() {
		if (animation < 30) {
			animation++;
		} else {
			animation = 0;
		}

		if (animation < 15) {
			state = 1;
		} else {
			state = 2;
		}
	}

	private void determinateDirection() {
		final int xSpeed = evaluateXSpeed();
		final int ySpeed = evaluateYSpeed();

		if (xSpeed == 0 && ySpeed == 0) {
			return;
		}

		if ((xSpeed != 0 && ySpeed == 0) || (xSpeed == 0 && ySpeed != 0)) {
			move(xSpeed, ySpeed);
		} else {
			if (xSpeed == -1 && ySpeed == -1) {
				if (ControlGestor.KEYBOARD.left.getLastPress() > ControlGestor.KEYBOARD.up.getLastPress()) {
					move(xSpeed, 0);
				} else {
					move(0, ySpeed);
				}
			}

			if (xSpeed == -1 && ySpeed == 1) {
				if (ControlGestor.KEYBOARD.left.getLastPress() > ControlGestor.KEYBOARD.down.getLastPress()) {
					move(xSpeed, 0);
				} else {
					move(0, ySpeed);
				}
			}

			if (xSpeed == 1 && ySpeed == -1) {
				if (ControlGestor.KEYBOARD.right.getLastPress() > ControlGestor.KEYBOARD.up.getLastPress()) {
					move(xSpeed, 0);
				} else {
					move(0, ySpeed);
				}
			}

			if (xSpeed == 1 && ySpeed == 1) {
				if (ControlGestor.KEYBOARD.right.getLastPress() > ControlGestor.KEYBOARD.down.getLastPress()) {
					move(xSpeed, 0);
				} else {
					move(0, ySpeed);
				}
			}
		}
	}

	private int evaluateXSpeed() {
		int xSpeed = 0;

		if (ControlGestor.KEYBOARD.left.isPressed() && !ControlGestor.KEYBOARD.right.isPressed()) {
			xSpeed = -1;
		} else if (ControlGestor.KEYBOARD.right.isPressed() && !ControlGestor.KEYBOARD.left.isPressed()) {
			xSpeed = 1;
		}

		return xSpeed;
	}

	private int evaluateYSpeed() {
		int ySpeed = 0;

		if (ControlGestor.KEYBOARD.up.isPressed() && !ControlGestor.KEYBOARD.down.isPressed()) {
			ySpeed = -1;
		} else if (ControlGestor.KEYBOARD.down.isPressed() && !ControlGestor.KEYBOARD.up.isPressed()) {
			ySpeed = 1;
		}

		return ySpeed;
	}

	private void move(final int xSpeed, final int ySpeed) {
		moving = true;

		changeDirection(xSpeed, ySpeed);

		if (!outOfMap(xSpeed, ySpeed)) {
			if (xSpeed == -1 && !leftColliding(xSpeed)) {
				x += xSpeed * speed;
				subtractResistance();
				return;
			}
			if (xSpeed == 1 && !rightColliding(xSpeed)) {
				x += xSpeed * speed;
				subtractResistance();
				return;
			}
			if (ySpeed == -1 && !upColliding(ySpeed)) {
				y += ySpeed * speed;
				subtractResistance();
				return;
			}
			if (ySpeed == 1 && !downColliding(ySpeed)) {
				y += ySpeed * speed;
				subtractResistance();
				return;
			}
		}
	}

	private void subtractResistance() {
		if (ControlGestor.KEYBOARD.running && resistance > 0) {
			resistance--;
		}
	}

	private boolean upColliding(final int ySpeed) {
		for (int r = 0; r < map.collisionAreas.size(); r++) {
			final Rectangle area = map.collisionAreas.get(r);

			int xOrigin = area.x;
			int yOrigin = area.y + ySpeed * (int) speed + 3 * (int) speed;

			final Rectangle futureArea = new Rectangle(xOrigin, yOrigin, Constants.SPRITE_SIDE, Constants.SPRITE_SIDE);

			if (UP_LIMIT.intersects(futureArea)) {
				return true;
			}
		}

		return false;
	}

	private boolean downColliding(final int ySpeed) {
		for (int r = 0; r < map.collisionAreas.size(); r++) {
			final Rectangle area = map.collisionAreas.get(r);

			int xOrigin = area.x;
			int yOrigin = area.y + ySpeed * (int) speed - 3 * (int) speed;

			final Rectangle futureArea = new Rectangle(xOrigin, yOrigin, Constants.SPRITE_SIDE, Constants.SPRITE_SIDE);

			if (DOWN_LIMIT.intersects(futureArea)) {
				return true;
			}
		}

		return false;
	}

	private boolean leftColliding(final int xSpeed) {
		for (int r = 0; r < map.collisionAreas.size(); r++) {
			final Rectangle area = map.collisionAreas.get(r);

			int xOrigin = area.x + xSpeed * (int) speed + 3 * (int) speed;
			int yOrigin = area.y;

			final Rectangle futureArea = new Rectangle(xOrigin, yOrigin, Constants.SPRITE_SIDE, Constants.SPRITE_SIDE);

			if (LEFT_LIMIT.intersects(futureArea)) {
				return true;
			}
		}

		return false;
	}

	private boolean rightColliding(final int xSpeed) {
		for (int r = 0; r < map.collisionAreas.size(); r++) {
			final Rectangle area = map.collisionAreas.get(r);

			int xOrigin = area.x + xSpeed * (int) speed - 3 * (int) speed;
			int yOrigin = area.y;

			final Rectangle futureArea = new Rectangle(xOrigin, yOrigin, Constants.SPRITE_SIDE, Constants.SPRITE_SIDE);

			if (RIGHT_LIMIT.intersects(futureArea)) {
				return true;
			}
		}

		return false;
	}

	private boolean outOfMap(final int xSpeed, final int ySpeed) {

		int futureX = (int) x + xSpeed * (int) speed;
		int futureY = (int) y + ySpeed * (int) speed;

		final Rectangle mapBorders = map.getBorders(futureX, futureY, PLAYER_WIDTH, PLAYER_HEIGHT);

		final boolean out;

		if (UP_LIMIT.intersects(mapBorders) || DOWN_LIMIT.intersects(mapBorders) || LEFT_LIMIT.intersects(mapBorders)
				|| RIGHT_LIMIT.intersects(mapBorders)) {
			out = false;
		} else {
			out = true;
		}
		return out;
	}

	private void changeDirection(final int xSpeed, final int ySpeed) {
		if (xSpeed == -1) {
			dir = 3;
		} else if (xSpeed == 1) {
			dir = 2;
		}

		if (ySpeed == -1) {
			dir = 1;
		} else if (ySpeed == 1) {
			dir = 0;
		}
	}

	private void animate() {
		if (!moving) {
			state = 0;
			animation = 0;
		}

		actualImage = sheet.getSprite(dir, state).getImage();
	}

	public void draw(Graphics g) {
		final int Xcenter = Constants.GAME_WIDTH / 2 - Constants.SPRITE_SIDE / 2;
		final int Ycenter = Constants.GAME_HEIGHT / 2 - Constants.SPRITE_SIDE / 2;

		g.drawImage(actualImage, Xcenter, Ycenter, null);
		if (ControlGestor.KEYBOARD.debug) {
			g.setColor(Color.GREEN);
			g.drawRect(UP_LIMIT.x, UP_LIMIT.y, UP_LIMIT.width, UP_LIMIT.height);
			g.drawRect(DOWN_LIMIT.x, DOWN_LIMIT.y, DOWN_LIMIT.width, DOWN_LIMIT.height);
			g.drawRect(LEFT_LIMIT.x, LEFT_LIMIT.y, LEFT_LIMIT.width, LEFT_LIMIT.height);
			g.drawRect(RIGHT_LIMIT.x, RIGHT_LIMIT.y, RIGHT_LIMIT.width, RIGHT_LIMIT.height);
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getResistance() {
		return resistance;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public void setY(final double y) {
		this.y = y;
	}
}
