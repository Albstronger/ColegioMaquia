package main.maps;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.sprites.Sprite;
import main.sprites.SpriteSheet;
import main.tools.ResourceLoader;

public class Map {

	private final String[] parts;

	private final int width;
	private final int height;

	private final Sprite[] palette;

	private final boolean[] collisions;

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
			if (collisionString.charAt(i) == 0) {
				collisions[i] = false;
			} else {
				collisions[i] = true;
			}
		}
		return null;
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

	public void draw(Graphics g) {
		int spriteWidth = this.palette[0].getWidth();
		int spriteHeight = this.palette[0].getHeight();

		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				BufferedImage image = palette[sprites[x + y * this.width]].getImage();

				g.drawImage(image, x * spriteWidth, y * spriteHeight, null);
			}
		}
	}
}
