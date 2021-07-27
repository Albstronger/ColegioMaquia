package main.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Keyboard implements KeyListener {

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public boolean running = false;
	public boolean debug = false;

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			up.keyPressed();
			break;
		case KeyEvent.VK_S:
			down.keyPressed();
			break;
		case KeyEvent.VK_A:
			left.keyPressed();
			break;
		case KeyEvent.VK_D:
			right.keyPressed();
			break;
		case KeyEvent.VK_CONTROL:
			running = true;
			break;
		case KeyEvent.VK_F3:
			debug = !debug;
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			up.keyReleased();
			break;
		case KeyEvent.VK_S:
			down.keyReleased();
			break;
		case KeyEvent.VK_A:
			left.keyReleased();
			break;
		case KeyEvent.VK_D:
			right.keyReleased();
			break;
		case KeyEvent.VK_CONTROL:
			running = false;
			break;
		}
	}

	public void keyTyped(KeyEvent e) {}
}
