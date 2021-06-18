package main.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import main.control.Keyboard;
import main.control.Mouse;
import main.statemachine.StateGestor;

public class DrawingSurface extends Canvas {

	private static final long serialVersionUID = 1L;

	private int width;
	private int height;

	private Keyboard keyboard;
	private Mouse mouse;

	public DrawingSurface(final int width, final int height) {
		this.width = width;
		this.height = height;

		keyboard = new Keyboard();
		mouse = new Mouse();

		setIgnoreRepaint(true);
		setCursor(mouse.getCursor());
		setPreferredSize(new Dimension(width, height));
		addKeyListener(keyboard);
		setFocusable(true);
		requestFocus();
	}

	public void draw(final StateGestor sg) {
		BufferStrategy buffer = getBufferStrategy();

		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = buffer.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);

		sg.draw(g);

		Toolkit.getDefaultToolkit().sync();

		g.dispose();

		buffer.show();
	}

	public Keyboard getKeyboard() {
		return keyboard;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
