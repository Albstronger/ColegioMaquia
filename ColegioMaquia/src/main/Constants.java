package main;

import java.awt.Toolkit;

public class Constants {

	public static final int SPRITE_SIDE = 32;

	public static final int TILE_SIDE = 32;

	public static final int GAME_WIDTH = 960;
	public static final int GAME_HEIGHT = 540;
	
	public static final int FULLSCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int FULLSCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public static final double HORIZONTAL_SCALING_FACTOR = (double) FULLSCREEN_WIDTH / (double) GAME_WIDTH;
	public static final double VERTICAL_SCALING_FACTOR = (double) FULLSCREEN_HEIGHT / (double) GAME_HEIGHT;

	public static final int WINDOW_X_CENTER = GAME_WIDTH/2;
	public static final int WINDOW_Y_CENTER = GAME_HEIGHT/2;
}
