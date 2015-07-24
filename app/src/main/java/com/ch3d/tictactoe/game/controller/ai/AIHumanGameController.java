package com.ch3d.tictactoe.game.controller.ai;

import com.ch3d.tictactoe.game.MinMaxStrategy;
import com.ch3d.tictactoe.game.board.GameCell;
import com.ch3d.tictactoe.game.history.GameHistory;
import com.ch3d.tictactoe.game.history.step.GameStepX;
import com.ch3d.tictactoe.game.mark.CellMark;
import com.ch3d.tictactoe.game.mark.CellMarkX;
import com.ch3d.tictactoe.game.state.GameState;
import com.ch3d.tictactoe.utils.Utils;
import com.ch3d.tictactoe.view.GameHistoryListener;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class AIHumanGameController extends AIGameController {
	public AIHumanGameController(GameHistoryListener listener) {
		super(listener);
	}

	@Override
	protected void placeMove(final int pos) {
		placeMoveX(pos);
	}

	@Override
	protected int analyze(final GameHistory history) {
		if(history.size() == 0) {
			return (history.getBoardSize() / 2) + 1;
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
