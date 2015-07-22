package com.ch3d.tictactoe.game.controller;

import com.ch3d.tictactoe.game.state.GameState;
import com.ch3d.tictactoe.game.state.GameStateController;
import com.ch3d.tictactoe.view.GameHistoryListener;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class AIHumanGameController extends BasicGameController {
	public AIHumanGameController() {
		super();
	}

	@Override
	protected void nextStep(final GameStateController stateController, final GameHistoryListener listener) {

	}

	@Override
	protected void processCellClick(final GameHistoryListener listener, final GameState state, final int pos) {
		if(state == GameState.X_MOVE) {
			return;
		}
		super.processCellClick(listener, state, pos);
	}
}
