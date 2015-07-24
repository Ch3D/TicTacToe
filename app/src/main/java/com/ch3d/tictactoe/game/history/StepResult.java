package com.ch3d.tictactoe.game.history;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class StepResult {

	public static final StepResult NULL = new StepResult(new int[]{-1, -1, -1});

	private final int[] mPositions;

	public StepResult(int[] positions) {
		mPositions = positions;
	}

	public int[] getPositions() {
		return mPositions;
	}
}
