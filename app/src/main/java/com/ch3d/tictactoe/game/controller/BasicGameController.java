package com.ch3d.tictactoe.game.controller;

import com.ch3d.tictactoe.TicTacToeApplication;
import com.ch3d.tictactoe.game.GameField;
import com.ch3d.tictactoe.game.mark.CellMarkO;
import com.ch3d.tictactoe.game.mark.CellMarkX;
import com.ch3d.tictactoe.game.state.GameState;
import com.ch3d.tictactoe.game.state.GameStateController;
import com.ch3d.tictactoe.view.GameHistoryListener;

import java.util.ArrayList;

/**
 * Created by Ch3D on 21.07.2015.
 */
public class BasicGameController implements GameController {
	private final TicTacToeApplication mApp;

	private final GameStateController mStateController;

	private ArrayList<GameListener> mListeners = new ArrayList<>();

	public BasicGameController(final TicTacToeApplication application) {
		mApp = application;
		mStateController = new GameStateController(new GameField());
	}

	@Override
	public void addGameListener(GameListener listener) {
		mListeners.add(listener);
	}

	@Override
	public void removeGameListener(GameListener listener) {
		mListeners.remove(listener);
	}

	@Override
	public void onCellClick(final String tag, final GameHistoryListener listener) {
		final GameState state = mStateController.getState();
		if(state.isGameFinished()) {
			// fail fast - game ended
			return;
		}
		final int pos = Integer.parseInt(tag);
		if(!mStateController.validateStep(pos)) {
			// cell is already used - skip
			return;
		}
		if(state == GameState.START || state == GameState.X_MOVE) {
			listener.onCellMarked(pos, CellMarkX.VALUE);
			final GameState gameState = mStateController.moveX(pos);
			if(gameState == GameState.X_WON) {
				notifyWinner(GameState.X_WON);
			} else if(gameState == GameState.DRAW) {
				notifyDraw();
			}
		} else {
			listener.onCellMarked(pos, CellMarkO.VALUE);
			final GameState gameState = mStateController.moveY(pos);
			if(gameState == GameState.O_WON) {
				notifyWinner(GameState.O_WON);
			} else if(gameState == GameState.DRAW) {
				notifyDraw();
			}
		}
	}

	private void notifyDraw() {
		for(final GameListener gameListener : mListeners) {
			gameListener.onGameEndedDraw();
		}
	}

	private void notifyWinner(final GameState state) {
		for(final GameListener gameListener : mListeners) {
			gameListener.onGameEndedWinner(state);
		}
	}

	@Override
	public void replay(final String tag) {
		mStateController.clear();
	}

	@Override
	public void clear() {
		mStateController.clear();
	}
}
