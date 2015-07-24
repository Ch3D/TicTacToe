package com.ch3d.tictactoe.game.state;

/**
 * Created by Ch3D on 22.07.2015.
 */
public final class GameState {

	public static final GameState X_MOVE = new GameState(0, "X-MOVE");

	public static final GameState O_MOVE = new GameState(1, "O-MOVE");

	public static final GameState X_WON = new GameState(2, "X-WON", true, true);

	public static final GameState O_WON = new GameState(3, "O-WON", true, true);

	public static final GameState START = new GameState(4, "START");

	public static final GameState DRAW = new GameState(5, "DRAW", true, false);

	private final int mId;

	private final String mName;

	private final boolean mFinished;

	private boolean mHasWinner;

	private GameState(int id, String name, boolean finished, boolean hasWinner) {
		mId = id;
		mName = name;
		mFinished = finished;
		mHasWinner = hasWinner;
	}

	private GameState(int id, String name) {
		this(id, name, false, false);
	}

	@Override
	public String toString() {
		return "GameState{" +
				"mId=" + mId +
				", mName='" + mName + '\'' +
				", mFinished=" + mFinished +
				", mHasWinner=" + mHasWinner +
				'}';
	}

	public String getName() {
		return mName;
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

	public boolean hasWinner() {
		return mHasWinner;
	}
}
