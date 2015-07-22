package com.ch3d.tictactoe.game.history;

/**
 * Created by Ch3D on 22.07.2015.
 */
public abstract class GameStep {
	private final int position;

	public GameStep(final int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	public abstract int getValue();
}
