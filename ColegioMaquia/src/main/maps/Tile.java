package main.maps;

import java.awt.Rectangle;

import main.sprites.Sprite;

public class Tile {

	private final Sprite sprite;
	private final int id;
	private boolean solid;

	public Tile(final Sprite sprite, final int id) {
		this.sprite = sprite;
		this.id = id;
		solid = false;
	}

	public Tile(final Sprite sprite, final int id, final boolean solid) {
		this(sprite, id);
		this.solid = solid;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getId() {
		return id;
	}

	public Rectangle getBounds(final int x, final int y) {
		return new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
	}
}
