package main.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Keyboard implements KeyListener {

	private final static int KEY_NUMBER = 256;
	private final boolean[] keys = new boolean[KEY_NUMBER];

	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public boolean exit;
	public boolean run;

	public void update() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		exit = keys[KeyEvent.VK_ESCAPE];
		run = keys[KeyEvent.VK_CONTROL];
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}

}
