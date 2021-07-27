package main.userinterface;

import java.awt.Color;
import java.awt.Graphics;

import main.Constants;
import main.entities.Player;

public class HUD {
	public static final int RES_BAR_X_POS = 40;
	public static final int BAR_Y_POS = Constants.GAME_HEIGHT - 50;
	public static final int BAR_WIDTH = 100;
	public static final int BAR_HEIGHT = 15;
	public static void drawResBar(Graphics g, int resistance) {
		
		int width = BAR_WIDTH * resistance / Player.MAX_RESISTANCE;
		
		g.setColor(Color.BLACK);
		g.fillRect(RES_BAR_X_POS-1, BAR_Y_POS-1, BAR_WIDTH+1, BAR_HEIGHT+1);
		
		g.setColor(Color.GREEN);
		g.fillRect(RES_BAR_X_POS - 1, BAR_Y_POS - 1, width + 1, BAR_HEIGHT/3 + 1);
		g.setColor(Color.GREEN.darker());
		g.fillRect(RES_BAR_X_POS - 1, BAR_Y_POS + BAR_HEIGHT/3 - 1, width + 1, BAR_HEIGHT - BAR_HEIGHT/3 + 1);
		
		g.setColor(Color.WHITE);
		g.drawRect(RES_BAR_X_POS-1, BAR_Y_POS-1, BAR_WIDTH+1, BAR_HEIGHT+1);
	}
}
