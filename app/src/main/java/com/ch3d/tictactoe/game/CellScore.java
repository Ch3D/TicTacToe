package com.ch3d.tictactoe.game;

import com.ch3d.tictactoe.game.board.GameCell;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class CellScore {
	private final int mScore;

	private final GameCell mPoint;

	public CellScore(final int score, final GameCell point) {
		mScore = score;
		mPoint = point;
	}

	public int getScore() {
		return mScore;
	}

	public GameCell getPoint() {
		return mPoint;
	}
}
