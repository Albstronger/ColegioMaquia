package main.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import main.Constants;
import main.control.ControlGestor;
import main.statemachine.StateGestor;

public class DrawingSurface extends Canvas {

	private static final long serialVersionUID = 1L;

	private int width;
	private int height;

	public DrawingSurface(final int width, final int height) {
		this.width = width;
		this.height = height;
		
		setIgnoreRepaint(true);
		setCursor(ControlGestor.MOUSE.getCursor());
		setPreferredSize(new Dimension(width, height));
		addKeyListener(ControlGestor.KEYBOARD);
		addMouseListener(ControlGestor.MOUSE);
		setFocusable(true);
		requestFocus();
	}

	public void draw(final StateGestor sg) {
		BufferStrategy buffer = getBufferStrategy();

		if (buffer == null) {
			createBufferStrategy(4);
			return;
		}

		Graphics2D g = (Graphics2D) buffer.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constants.FULLSCREEN_WIDTH, Constants.FULLSCREEN_HEIGHT);
		
		if(Constants.HORIZONTAL_SCALING_FACTOR != 1.0 || Constants.VERTICAL_SCALING_FACTOR != 1.0) {
			g.scale(Constants.HORIZONTAL_SCALING_FACTOR, Constants.VERTICAL_SCALING_FACTOR);
		}

		sg.draw(g);

		Toolkit.getDefaultToolkit().sync();

		g.dispose();

		buffer.show();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
