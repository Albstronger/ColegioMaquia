package main.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Keyboard implements KeyListener {

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();

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
		}
	}

	public void keyTyped(KeyEvent e) {
	}
}
