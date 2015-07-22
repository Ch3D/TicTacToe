package com.ch3d.tictactoe.game.history;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class StepResult {

	public static final StepResult NULL = new StepResult(new int[]{-1, -1, -1}, false);

	private final int[] mPositions;

	private final boolean mWon;

	public StepResult(int[] positions) {
		mPositions = positions;
		mWon = true;
	}

	public StepResult(int[] positions, boolean won) {
		mPositions = positions;
		mWon = won;
	}

	public boolean isWon() {
		return mWon;
	}

	public int[] getPositions() {
		return mPositions;
	}
}
