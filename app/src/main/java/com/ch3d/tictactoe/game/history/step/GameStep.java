package com.ch3d.tictactoe.game.history.step;

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

	@Override
	public String toString() {
		return "GameStep{" +
				"value=" + getValue() +
				"position=" + position +
				'}';
	}

	@Override
	public boolean equals(final Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}

		final GameStep gameStep = (GameStep) o;
		return position == gameStep.position && getValue() == gameStep.getValue();

	}

	@Override
	public int hashCode() {
		return position;
	}
}
