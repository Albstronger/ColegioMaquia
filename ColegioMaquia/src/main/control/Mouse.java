package main.control;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import main.tools.ResourceLoader;

public class Mouse {

	private final Cursor cursor;

	public Mouse() {
		Toolkit configuration = Toolkit.getDefaultToolkit();

		BufferedImage icon = ResourceLoader.loadTranslucentCompatibleImage("/images/icons/CursorIcon.png");

		Point tip = new Point(0, 0);

		this.cursor = configuration.createCustomCursor(icon, tip, null);
	}

	public Cursor getCursor() {
		return cursor;
	}
}
