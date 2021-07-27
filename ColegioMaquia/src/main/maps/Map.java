package main.maps;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import main.Constants;
import main.control.ControlGestor;
import main.sprites.Sprite;
import main.sprites.SpriteSheet;
import main.tools.ResourceLoader;

public class Map {

	private final String[] parts;

	private final int width;
	private final int height;

	private final Sprite[] palette;

	private final boolean[] collisions;

	public List<Rectangle> collisionAreas = new ArrayList<Rectangle>();

	private final int X_MARGIN = Constants.GAME_WIDTH / 2 - Constants.SPRITE_SIDE / 2;
	private final int Y_MARGIN = Constants.GAME_HEIGHT / 2 - Constants.SPRITE_SIDE / 2;

	private final int[] sprites;

	public Map(final String path) {

		String content = ResourceLoader.readTextFile(path);

		parts = content.split("\\*");

		width = Integer.parseInt(parts[0]);
		height = Integer.parseInt(parts[1]);

		String usedSheets = parts[2];
		String[] separateSheets = usedSheets.split(",");

		String entirePalette = parts[3];
		String[] paletteParts = entirePalette.split("#");

		palette = assignSprites(paletteParts, separateSheets);

		String entireCollisions = parts[4];
		collisions = extractCollisions(entireCollisions);

		String entireSprites = parts[5];
		String[] spriteString = entireSprites.split(" ");

		sprites = extractSprites(spriteString);

	}

	private Sprite[] assignSprites(final String[] paletteParts, final String[] separateSheets) {
		Sprite[] palette = new Sprite[paletteParts.length];

		SpriteSheet sheet = new SpriteSheet("/images/textureSheets/" + separateSheets[0] + ".png", 32, true);

		for (int i = 0; i < paletteParts.length; i++) {
			String temporalSprite = paletteParts[i];

			String[] spriteParts = temporalSprite.split("-");

			int paletteIndex = Integer.parseInt(spriteParts[0]);

			int spriteInSheetIndex = Integer.parseInt(spriteParts[2]);

			palette[paletteIndex] = sheet.getSprite(spriteInSheetIndex);
		}

		return palette;
	}

	private boolean[] extractCollisions(final String collisionString) {
		boolean[] collisions = new boolean[collisionString.length()];

		for (int i = 0; i < collisionString.length(); i++) {
			if (collisionString.charAt(i) == '0') {
				collisions[i] = false;
			} else {
				collisions[i] = true;
			}
		}
		return collisions;
	}

	private int[] extractSprites(final String[] spriteArray) {
		ArrayList<Integer> sprites = new ArrayList<Integer>();

		for (int i = 0; i < spriteArray.length; i++) {
			if (spriteArray[i].length() == 2) {
				sprites.add(Integer.parseInt(spriteArray[i]));
			} else {
				String one = "";
				String two = "";

				String error = spriteArray[i];

				one += error.charAt(0);
				one += error.charAt(1);

				two += error.charAt(2);
				two += error.charAt(3);

				sprites.add(Integer.parseInt(one));
				sprites.add(Integer.parseInt(two));
			}
		}

		int[] spriteVector = new int[sprites.size()];

		for (int i = 0; i < sprites.size(); i++) {
			spriteVector[i] = sprites.get(i);
		}

		return spriteVector;
	}

	public void update(final int playerX, final int playerY) {
		updateCollisionAreas(playerX, playerY);
	}

	private void updateCollisionAreas(final int playerX, final int playerY) {
		if (!collisionAreas.isEmpty()) {
			collisionAreas.clear();
		}

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int xPoint = x * Constants.SPRITE_SIDE - playerX + X_MARGIN;
				int yPoint = y * Constants.SPRITE_SIDE - playerY + Y_MARGIN;

				if (collisions[x + y * width]) {
					final Rectangle r = new Rectangle(xPoint, yPoint, Constants.SPRITE_SIDE, Constants.SPRITE_SIDE);
					collisionAreas.add(r);
				}
			}
		}
	}

	public void draw(Graphics g, int PlayerX, int PlayerY) {

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				BufferedImage image = palette[sprites[x + y * width]].getImage();

				int xPoint = x * Constants.SPRITE_SIDE - PlayerX + X_MARGIN;
				int yPoint = y * Constants.SPRITE_SIDE - PlayerY + Y_MARGIN;

				g.drawImage(image, xPoint, yPoint, null);

				if(ControlGestor.KEYBOARD.debug) {
					g.setColor(Color.GREEN);
					for (int r = 0; r < collisionAreas.size(); r++) {
						Rectangle area = collisionAreas.get(r);
						g.drawRect(area.x, area.y, area.width, area.height);
					}
				}
			}
		}
	}

	public Rectangle getBorders(final int xPos, final int yPos, final int playerWidth, final int playerHeight) {
		int x = X_MARGIN - xPos + playerWidth;
		int y = Y_MARGIN - yPos + playerHeight;
		int width = this.width * Constants.SPRITE_SIDE - playerWidth * 2;
		int height = this.height * Constants.SPRITE_SIDE - playerHeight * 2;

		return new Rectangle(x, y, width, height);
	}
}
