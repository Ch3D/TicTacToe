package com.ch3d.tictactoe.game.history;

import com.ch3d.tictactoe.game.board.GameBoardSimple;
import com.ch3d.tictactoe.game.history.step.GameStep;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class UnmodifiableGameHistory extends GameHistory {

	public static UnmodifiableGameHistory create(GameHistory history) {
		return new UnmodifiableGameHistory(history.mBoard, history.mSteps);
	}

	private UnmodifiableGameHistory(GameBoardSimple board, List<GameStep> history) {
		this.mBoard = board;
		mSteps = Collections.unmodifiableList(history);
	}
}
