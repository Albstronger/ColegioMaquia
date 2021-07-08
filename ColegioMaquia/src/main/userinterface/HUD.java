package main.userinterface;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	public static void drawResBar(Graphics g, int resistance) {
		int width = 100 * resistance / 600;
		Color darkRed = new Color(153, 0, 0);

		g.setColor(Color.BLACK);
		g.fillRect(19, 99, 102, 17);
		g.setColor(Color.WHITE);
		g.drawRect(19, 99, 102, 17);

		g.setColor(Color.red);
		g.fillRect(20, 100, width, 5);
		g.setColor(darkRed);
		g.fillRect(20, 105, width, 10);
	}
}
