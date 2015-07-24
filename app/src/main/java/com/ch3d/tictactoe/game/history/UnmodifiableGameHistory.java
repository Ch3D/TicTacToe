package com.ch3d.tictactoe.game.history;

import com.ch3d.tictactoe.game.board.GameBoard;
import com.ch3d.tictactoe.game.history.step.GameStep;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class UnmodifiableGameHistory extends GameHistory {

	public static UnmodifiableGameHistory create(GameBoard board, GameHistory history) {
		return new UnmodifiableGameHistory(board, history.mSteps);
	}

	private UnmodifiableGameHistory(GameBoard board, List<GameStep> history) {
		super(board);
		this.mBoard = board;
		mSteps = Collections.unmodifiableList(history);
	}
}
