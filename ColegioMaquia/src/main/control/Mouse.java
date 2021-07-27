package main.control;

import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import main.graphics.DrawingSurface;
import main.tools.ResourceLoader;

public class Mouse extends MouseAdapter{

	private final Cursor cursor;
	private Point position;

	public Mouse() {
		Toolkit configuration = Toolkit.getDefaultToolkit();

		BufferedImage icon = ResourceLoader.loadTranslucentCompatibleImage("/images/icons/CursorIcon.png");

		Point tip = new Point(0, 0);

		this.cursor = configuration.createCustomCursor(icon, tip, null);
		
		position = new Point();
	}
	
	public void update(final DrawingSurface ds) {
		updatePosition(ds);
	}

	public Cursor getCursor() {
		return cursor;
	}
	
	private void updatePosition(final DrawingSurface ds) {
		final Point initialPosition = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(initialPosition, ds);
		position.setLocation(initialPosition.getX(), initialPosition.getY());
	}
	
	public Point getPosition() {
		return position;
	}
}
