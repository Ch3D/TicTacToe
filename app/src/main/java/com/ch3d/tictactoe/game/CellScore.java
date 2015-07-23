package com.ch3d.tictactoe.game;

import android.graphics.Point;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class CellScore {
	private final int mScore;

	private final Point mPoint;

	public CellScore(final int score, final Point point) {
		mScore = score;
		mPoint = point;
	}

	public int getScore() {
		return mScore;
	}

	public Point getPoint() {
		return mPoint;
	}
}
