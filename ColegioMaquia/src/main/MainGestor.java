package main;

import main.graphics.DrawingSurface;
import main.graphics.Window;
import main.statemachine.StateGestor;

public class MainGestor {
	private boolean running = false;
	private String title;
	private int width, height;

	private DrawingSurface ds;
	@SuppressWarnings("unused")
	private Window window;
	private StateGestor sg;

	private static int tps, fps;

	private MainGestor(final String title, final int width, final int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}

	public static void main(String[] args) {
		MainGestor mg = new MainGestor("ColegioMaquia", Constants.FULLSCREEN_WIDTH, Constants.FULLSCREEN_HEIGHT);

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
		int accumulatedTimes = 0;
		int accumulatedFrames = 0;

		final int NS_PER_SECOND = 1000000000;
		final byte TARGET_TPS = 60;
		final double NS_PER_UPDATE = NS_PER_SECOND / TARGET_TPS;

		long updateReference = System.nanoTime();
		long counterReference = System.nanoTime();

		double timeElapsed;
		double delta = 0;

		while (running) {
			final long loopStart = System.nanoTime();

			timeElapsed = loopStart - updateReference;
			updateReference = loopStart;

			delta += timeElapsed / NS_PER_UPDATE;

			while (delta >= 1) {
				update();
				accumulatedTimes++;
				delta--;
			}

			draw();
			accumulatedFrames++;

			if (System.nanoTime() - counterReference > NS_PER_SECOND) {
				MainGestor.tps = accumulatedTimes;
				MainGestor.fps = accumulatedFrames;
				accumulatedTimes = 0;
				accumulatedFrames = 0;
				counterReference = System.nanoTime();
			}
		}
	}

	private void update() {
		sg.update();
	}

	private void draw() {
		ds.draw(sg);
	}

	public static int getAps() {
		return tps;
	}

	public static int getFps() {
		return fps;
	}
}
