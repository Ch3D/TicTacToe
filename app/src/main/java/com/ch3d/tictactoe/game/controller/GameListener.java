package com.ch3d.tictactoe.game.controller;

import com.ch3d.tictactoe.game.state.GameState;

/**
 * Created by Ch3D on 22.07.2015.
 */
public interface GameListener {
	void onGameEndedWinner(final GameState state);

	void onGameEndedDraw();
}
