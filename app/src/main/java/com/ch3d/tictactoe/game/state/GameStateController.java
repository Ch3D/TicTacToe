package com.ch3d.tictactoe.game.state;

import com.ch3d.tictactoe.game.GameField;
import com.ch3d.tictactoe.game.history.GameHistory;
import com.ch3d.tictactoe.game.history.GameStepO;
import com.ch3d.tictactoe.game.history.GameStepX;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class GameStateController {
	private final GameField mGameField;

	private GameHistory mHistory;

	private GameState mCurrentState;

	public GameStateController(final GameField gameField) {
		mGameField = gameField;
		mHistory = new GameHistory();
		mCurrentState = GameState.START;
	}

	public GameState getState() {
		return mCurrentState;
	}

	public GameState moveX(final int pos) {
		if(mHistory.addStep(new GameStepX(pos))) {
			mCurrentState = GameState.X_WON;
		} else {
			checkAndUpdateState();
		}
		return mCurrentState;
	}

	public GameState moveY(final int pos) {
		if(mHistory.addStep(new GameStepO(pos))) {
			mCurrentState = GameState.O_WON;
		} else {
			checkAndUpdateState();
		}
		return mCurrentState;
	}

	public void checkAndUpdateState() {
		if(mHistory.isFilled()) {
			mCurrentState = GameState.DRAW;
			return;
		}
		if(mCurrentState == GameState.X_WON || mCurrentState == GameState.O_WON) {
			// game ended - do nothing
			return;
		}
		if(mCurrentState == GameState.START || mCurrentState == GameState.X_MOVE) {
			mCurrentState = GameState.O_MOVE;
			return;
		}
		if(mCurrentState == GameState.O_MOVE) {
			mCurrentState = GameState.X_MOVE;
			return;
		}
	}

	public boolean validateStep(final int pos) {
		return !mHistory.isBusy(pos);
	}

	public void clear() {
		mCurrentState = GameState.START;
		mHistory.clear();
	}
}
