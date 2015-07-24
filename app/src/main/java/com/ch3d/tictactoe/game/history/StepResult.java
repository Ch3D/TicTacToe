package com.ch3d.tictactoe.game.history;

import java.util.Arrays;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class StepResult {

	public static final StepResult NULL = new StepResult(new int[]{-1, -1, -1});

	private final int[] mPositions;

	public StepResult(int[] positions) {
		mPositions = positions;
	}

	@Override
	public String toString() {
		return "StepResult{" +
				"mPositions=" + Arrays.toString(mPositions) +
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

		final StepResult that = (StepResult) o;

		return Arrays.equals(mPositions, that.mPositions);

	}

	@Override
	public int hashCode() {
		return mPositions != null ? Arrays.hashCode(mPositions) : 0;
	}

	public int[] getPositions() {
		return mPositions;
	}
}
