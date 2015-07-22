package com.ch3d.tictactoe.game.history;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class UnmodifiableGameHistory extends GameHistory {

	public static UnmodifiableGameHistory create(GameHistory history) {
		return new UnmodifiableGameHistory(history.mBoard, history.mHistory);
	}

	private UnmodifiableGameHistory(int[][] board, List<GameStep> history) {
		this.mBoard = board;
		mHistory = Collections.unmodifiableList(history);
	}
}
