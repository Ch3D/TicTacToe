package com.ch3d.tictactoe.game.history.step;

import com.ch3d.tictactoe.game.mark.CellMark;

/**
 * Created by Ch3D on 22.07.2015.
 */
public abstract class GameStep {
	public static final GameStep NULL = new GameStepX(0);

	public static GameStep create(final int player, final int pos) {
		switch(player) {
			case GameStepX.VALUE:
				return new GameStepX(pos);
		}
		return GameStep.NULL;
	}

	private final int position;

	private final CellMark mMark;

	public GameStep(final int position, final CellMark mark) {
		this.position = position;
		mMark = mark;
	}

	public CellMark getMark() {
		return mMark;
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
