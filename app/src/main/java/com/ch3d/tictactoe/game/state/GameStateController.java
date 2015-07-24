package com.ch3d.tictactoe.game.state;

import com.ch3d.tictactoe.game.board.GameBoardSimple;
import com.ch3d.tictactoe.game.history.GameHistory;
import com.ch3d.tictactoe.game.history.StepResult;
import com.ch3d.tictactoe.game.history.step.GameStepO;
import com.ch3d.tictactoe.game.history.step.GameStepX;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class GameStateController {
	private GameHistory mHistory;

	private GameState mCurrentState;

	public GameStateController() {
		mHistory = new GameHistory(new GameBoardSimple());
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
		mHistory.dump();
		return mCurrentState;
	}

	public GameState moveO(final int pos) {
		if(mHistory.addStep(new GameStepO(pos))) {
			mCurrentState = GameState.O_WON;
		} else {
			checkAndUpdateState();
		}
		mHistory.dump();
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

	public StepResult getWinCombination() {
		return mHistory.getWinnerCombination();
	}

	public boolean validateStep(final int pos) {
		return !mHistory.isBusy(pos);
	}

	public void clear() {
		mCurrentState = GameState.START;
		mHistory.clear();
	}

	public GameHistory getHistory() {
		return mHistory;
	}
}
