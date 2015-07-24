package com.ch3d.tictactoe.game.controller.ai;

import com.ch3d.tictactoe.GameHistoryListener;
import com.ch3d.tictactoe.game.MinMaxStrategy;
import com.ch3d.tictactoe.game.board.GameCell;
import com.ch3d.tictactoe.game.history.GameHistory;
import com.ch3d.tictactoe.game.history.step.GameStepX;
import com.ch3d.tictactoe.game.mark.CellMark;
import com.ch3d.tictactoe.game.mark.CellMarkX;
import com.ch3d.tictactoe.game.state.GameState;
import com.ch3d.tictactoe.utils.Utils;

import java.util.Random;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class AIHumanGameController extends AIGameController {
	private final Random mCornerRandom;

	private int[] corners = {1, 3, 5, 7, 9};

	public AIHumanGameController(GameHistoryListener listener) {
		super(listener);
		mCornerRandom = new Random();
	}

	@Override
	protected void placeMove(final int pos) {
		placeMoveX(pos);
	}

	@Override
	protected int analyze(final GameHistory history) {
		if(history.size() == 0) {
			final int cornerIndex = mCornerRandom.nextInt(5);
			return corners[cornerIndex];
		}

		// find best move
		final MinMaxStrategy minMaxStrategy = new MinMaxStrategy(history.getBoardMatrix());
		minMaxStrategy.callMinimax(0, GameStepX.VALUE);
		final GameCell cell = minMaxStrategy.returnBestMoveX();
		if(cell == null) {
			return WRONG_POSITION;
		}
		minMaxStrategy.placeAMove(cell, GameStepX.VALUE);
		return Utils.getCellPosition(cell, history.getBoardSize());
	}

	@Override
	protected CellMark getMark() {
		return CellMarkX.VALUE;
	}

	@Override
	protected void processCellClick(final GameHistoryListener listener, final GameState state, final int pos) {
		if(state == GameState.X_MOVE) {
			return;
		}
		super.processCellClick(listener, state, pos);
	}

	@Override
	public void startGame() {
		nextMove(mStateController, mListener);
	}
}
