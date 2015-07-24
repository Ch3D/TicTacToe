package com.ch3d.tictactoe.game.controller.ai;

import com.ch3d.tictactoe.game.controller.BasicGameController;
import com.ch3d.tictactoe.game.history.GameHistory;
import com.ch3d.tictactoe.game.mark.CellMark;
import com.ch3d.tictactoe.game.state.GameStateController;
import com.ch3d.tictactoe.view.GameHistoryListener;

/**
 * Created by Ch3D on 24.07.2015.
 */
public abstract class AIGameController extends BasicGameController {
	public AIGameController(final GameHistoryListener listener) {
		super(listener);
	}

	protected abstract int analyze(final GameHistory history);

	protected boolean checkPosition(int pos) {
		return pos != WRONG_POSITION;
	}

	@Override
	protected void nextMove(final GameStateController stateController, final GameHistoryListener listener) {
		final int pos = analyze(stateController.getHistory().unmodifiable());
		if(!checkPosition(pos)) {
			return;
		}
		if(!mStateController.validateStep(pos)) {
			nextMove(stateController, listener);
		}
		listener.onCellMarked(pos, getMark());
		placeMove(pos);
	}

	protected abstract CellMark getMark();

	protected abstract void placeMove(final int pos);
}
