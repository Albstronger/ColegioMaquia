package main;

import main.control.ControlGestor;
import main.graphics.DrawingSurface;
import main.graphics.Window;
import main.statemachine.StateGestor;

public class MainGestor {
	private boolean running = false;
	private String title;
	private int width;
	private int height;

	private DrawingSurface ds;
	private Window window;
	private StateGestor sg;

	private MainGestor(final String title, final int width, final int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}

	public static void main(String[] args) {
		MainGestor mg = new MainGestor("ColegioMaquia", 640, 360);

		Constants.SCREEN_WIDTH = 640;
		Constants.SCREEN_HEIGHT = 360;

		mg.startGame();
		mg.startMainLoop();
	}

	private void startGame() {
		running = true;
		initialize();
	}

	private void initialize() {
		ds = new DrawingSurface(width, height);
		window = new Window(title, ds);
		sg = new StateGestor();
	}

	private void startMainLoop() {
		int aps = 0;
		int fps = 0;

		final int NS_PER_SECOND = 1000000000;
		final byte TARGET_APS = 60;
		final double NS_PER_UPDATE = NS_PER_SECOND / TARGET_APS;

		long updateReference = System.nanoTime();
		long counterReference = System.nanoTime();

		double timeElapsed;
		double delta = 0;

		while (running) {
			final long inicioBucle = System.nanoTime();

			timeElapsed = inicioBucle - updateReference;
			updateReference = inicioBucle;

			delta += timeElapsed / NS_PER_UPDATE;

			while (delta >= 1) {
				update();
				aps++;
				Constants.APS = aps;
				delta--;
			}

			draw();
			fps++;

			if (System.nanoTime() - counterReference > NS_PER_SECOND) {
				System.out.println("FPS: " + fps + " || APS: " + aps);
				aps = 0;
				Constants.APS = aps;
				fps = 0;
				counterReference = System.nanoTime();
			}
		}
	}

	private void update() {
		ControlGestor.KEYBOARD.update();
		sg.update();
	}

	private void draw() {
		ds.draw(sg);
	}
}
