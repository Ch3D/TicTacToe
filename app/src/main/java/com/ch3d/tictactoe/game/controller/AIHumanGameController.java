package com.ch3d.tictactoe.game.controller;

import com.ch3d.tictactoe.game.CellScore;
import com.ch3d.tictactoe.game.MinMaxStrategy;
import com.ch3d.tictactoe.game.history.GameCell;
import com.ch3d.tictactoe.game.history.GameHistory;
import com.ch3d.tictactoe.game.history.GameStepX;
import com.ch3d.tictactoe.game.mark.CellMarkX;
import com.ch3d.tictactoe.game.state.GameState;
import com.ch3d.tictactoe.game.state.GameStateController;
import com.ch3d.tictactoe.utils.Utils;
import com.ch3d.tictactoe.view.GameHistoryListener;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class AIHumanGameController extends BasicGameController {
	public AIHumanGameController(GameHistoryListener listener) {
		super(listener);
	}

	@Override
	protected void nextStep(final GameStateController stateController, final GameHistoryListener listener) {
		final int pos = analyze(stateController.getHistory().unmodifiable());

		if(!mStateController.validateStep(pos)) {
			nextStep(stateController, listener);
		}

		listener.onCellMarked(pos, CellMarkX.VALUE);
		final GameState gameState = mStateController.moveX(pos);
		if(gameState == GameState.X_WON) {
			notifyWinner(GameState.X_WON);
		} else if(gameState == GameState.DRAW) {
			notifyDraw();
		}
	}

	private int analyze(final GameHistory history) {
		if(history.size() == 0) {
			return 5;
		}

		// find best move
		final MinMaxStrategy minMaxStrategy = new MinMaxStrategy(history.getBoardMatrix());
		minMaxStrategy.callMinimax(0, GameStepX.VALUE);
		for(CellScore cs : minMaxStrategy.getRootsChildrenScores()) {
			System.out.println("Point: " + cs.getPoint() + " Score: " + cs.getScore());
		}
		final GameCell cell = minMaxStrategy.returnBestMoveX();
		if(cell == null) {
			return WRONG_POSITION;
		}
		System.err.println("cell = " + cell);
		minMaxStrategy.placeAMove(cell, GameStepX.VALUE);
		final int pos = Utils.getCellPosition(cell, history.getBoardSize());
		System.out.println("pos: " + pos);
		return pos;
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
		nextStep(mStateController, mListener);
	}
}
