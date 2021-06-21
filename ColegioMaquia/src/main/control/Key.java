package main.control;

public class Key {

	private boolean pressed = false;
	private long lastPress = System.nanoTime();

	public void keyPressed() {
		pressed = true;
		lastPress = System.nanoTime();
	}

	public void keyReleased() {
		pressed = false;
	}

	public boolean isPressed() {
		return pressed;
	}

	public long getLastPress() {
		return lastPress;
	}
}
