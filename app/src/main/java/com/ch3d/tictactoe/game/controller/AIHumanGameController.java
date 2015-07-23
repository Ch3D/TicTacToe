package com.ch3d.tictactoe.game.controller;

import android.graphics.Point;

import com.ch3d.tictactoe.game.CellScore;
import com.ch3d.tictactoe.game.MinMaxStrategy;
import com.ch3d.tictactoe.game.history.GameHistory;
import com.ch3d.tictactoe.game.mark.CellMarkX;
import com.ch3d.tictactoe.game.state.GameState;
import com.ch3d.tictactoe.game.state.GameStateController;
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

		// try to win
		final int winPosition = canWin(history);
		if(winPosition != WRONG_POSITION) {
			return winPosition;
		}

		// find best move
		final MinMaxStrategy minMaxStrategy = new MinMaxStrategy(history.getBoard());
		minMaxStrategy.callMinimax(0, 1);
		for(CellScore cs : minMaxStrategy.getRootsChildrenScores()) {
			System.out.println("Point: " + cs.getPoint() + " Score: " + cs.getScore());
		}
		final Point point = minMaxStrategy.returnBestMoveX();
		if(point == null) {
			return WRONG_POSITION;
		}
		System.err.println("point = " + point);
		minMaxStrategy.placeAMove(point, 1);
		final int pos = (point.x * 3) + (point.y + 1);
		System.out.println("pos: " + pos);
		return pos;
	}

	private int canWin(final GameHistory history) {
		return WRONG_POSITION;
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
