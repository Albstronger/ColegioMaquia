package main.statemachine;

import java.awt.Graphics;

import main.statemachine.states.game.GameGestor;

public class StateGestor {

	private GameState[] states;
	private GameState actualState;

	public StateGestor() {
		startStates();
		startActualState();
	}

	private void startStates() {
		states = new GameState[1];
		states[0] = new GameGestor();
		// ADD EVERY NEW STATE
	}

	private void startActualState() {
		actualState = states[0];
	}

	public void update() {
		actualState.update();
	}

	public void draw(final Graphics g) {
		actualState.draw(g);
	}

	public void changeActualState(final int newState) {
		actualState = states[newState];
	}

	public GameState getActualState() {
		return actualState;
	}
}
