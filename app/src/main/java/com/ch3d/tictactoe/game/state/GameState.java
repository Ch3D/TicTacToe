package com.ch3d.tictactoe.game.state;

/**
 * Created by Ch3D on 22.07.2015.
 */
public final class GameState {

	public static final GameState X_MOVE = new GameState(0);

	public static final GameState O_MOVE = new GameState(1);

	public static final GameState X_WON = new GameState(2, true);

	public static final GameState O_WON = new GameState(3, true);

	public static final GameState START = new GameState(4);

	public static final GameState DRAW = new GameState(5);

	private final int mId;

	private final boolean mFinished;

	private GameState(int id, boolean finished) {
		mId = id;
		mFinished = finished;
	}

	private GameState(int id) {
		mId = id;
		mFinished = false;
	}

	public int getId() {
		return mId;
	}

	@Override
	public boolean equals(final Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}

		final GameState gameState = (GameState) o;

		return mId == gameState.mId;

	}

	@Override
	public int hashCode() {
		return mId;
	}

	public boolean isGameFinished() {
		return mFinished;
	}
}
