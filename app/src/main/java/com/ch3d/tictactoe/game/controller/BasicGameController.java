package com.ch3d.tictactoe.game.controller;

import com.ch3d.tictactoe.game.history.StepResult;
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
	protected final GameStateController mStateController;

	protected final GameHistoryListener mListener;

	private ArrayList<GameListener> mListeners = new ArrayList<>();

	public BasicGameController(final GameHistoryListener listener) {
		mListener = listener;
		mStateController = new GameStateController();
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
		final int pos = Integer.parseInt(tag);

		processCellClick(listener, state, pos);
		nextStep(mStateController, listener);
	}

	protected void processCellClick(final GameHistoryListener listener, final GameState state, final int pos) {
		if(state.isGameFinished()) {
			// fail fast - game ended
			return;
		}

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
			final GameState gameState = mStateController.moveO(pos);
			if(gameState == GameState.O_WON) {
				notifyWinner(GameState.O_WON);
			} else if(gameState == GameState.DRAW) {
				notifyDraw();
			}
		}
	}

	protected void nextStep(final GameStateController stateController, final GameHistoryListener listener) {
		// do nothing
	}

	protected void notifyDraw() {
		for(final GameListener gameListener : mListeners) {
			gameListener.onGameEndedDraw();
		}
	}

	protected void notifyWinner(final GameState state) {
		for(final GameListener gameListener : mListeners) {
			gameListener.onGameEndedWinner(state);
		}
	}

	@Override
	public StepResult getWinCombination() {
		return mStateController.getWinCombination();
	}

	@Override
	public void replay(final String tag) {
		mStateController.clear();
		startGame();
	}

	@Override
	public void startGame() {

	}

	@Override
	public void clear() {
		mStateController.clear();
	}

	@Override
	public GameState getState() {
		return mStateController.getState();
	}
}
