package main.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Keyboard implements KeyListener {

	private final int keyNumber = 256;
	private final boolean[] keys = new boolean[keyNumber];

	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	private boolean run;

	private boolean exit;

	public void update() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		exit = keys[KeyEvent.VK_ESCAPE];
		run = keys[KeyEvent.VK_CONTROL];
	}

	public boolean isUp() {
		return up;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isRun() {
		return run;
	}

	public boolean isExit() {
		return exit;
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
