package main.sprites;

import java.awt.image.BufferedImage;

import main.tools.ResourceLoader;

public class SpriteSheet {

	final private int sheetPixelWidth;
	final private int sheetPixelHeight;

	final private int sheetSpriteWidth;
	final private int sheetSpriteHeight;

	final private int spriteWidth;
	final private int spriteHeight;

	final private Sprite[] sprites;

	public SpriteSheet(final String path, final int spriteSide, final boolean opaqueSheet) {
		BufferedImage image;

		if (opaqueSheet) {
			image = ResourceLoader.loadOpaqueCompatibleImage(path);
		} else {
			image = ResourceLoader.loadTranslucentCompatibleImage(path);
		}

		sheetPixelWidth = image.getWidth();
		sheetPixelHeight = image.getHeight();

		sheetSpriteWidth = sheetPixelWidth / spriteSide;
		sheetSpriteHeight = sheetPixelHeight / spriteSide;

		spriteWidth = spriteSide;
		spriteHeight = spriteSide;

		sprites = new Sprite[sheetSpriteWidth * sheetSpriteHeight];

		fillSpritesFromImage(image);
	}

	public SpriteSheet(final String path, final int spriteWidth, final int spriteHeight, final boolean opaqueSheet) {
		BufferedImage image;

		if (opaqueSheet) {
			image = ResourceLoader.loadOpaqueCompatibleImage(path);
		} else {
			image = ResourceLoader.loadTranslucentCompatibleImage(path);
		}

		sheetPixelWidth = image.getWidth();
		sheetPixelHeight = image.getHeight();

		sheetSpriteWidth = sheetPixelWidth / spriteWidth;
		sheetSpriteHeight = sheetPixelHeight / spriteHeight;

		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;

		sprites = new Sprite[sheetSpriteWidth * sheetSpriteHeight];

		fillSpritesFromImage(image);
	}

	private void fillSpritesFromImage(final BufferedImage image) {
		for (int y = 0; y < sheetSpriteHeight; y++) {
			for (int x = 0; x < sheetSpriteWidth; x++) {
				final int xPos = x * spriteWidth;
				final int yPos = y * spriteHeight;

				sprites[x + y * sheetSpriteWidth] = new Sprite(
						image.getSubimage(xPos, yPos, spriteWidth, spriteHeight));
			}
		}
	}

	public Sprite getSprite(final int index) {
		return sprites[index];
	}

	public Sprite getSprite(final int x, final int y) {
		return sprites[x + y * sheetSpriteWidth];
	}
}
