package com.ch3d.tictactoe.game.controller;

import android.support.annotation.NonNull;

import com.ch3d.tictactoe.game.GameMode;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class GameControllerFactory {
	public static final GameController create(@NonNull final GameMode gameMode) {
		if(gameMode.player1().isHuman() && gameMode.player2().isHuman()) {
			return new BasicGameController();
		}
		if(gameMode.player1().isHuman() && !gameMode.player2().isHuman()) {
			return new HumanAIGameController();
		}
		if(!gameMode.player1().isHuman() && gameMode.player2().isHuman()) {
			return new AIHumanGameController();
		}
		return new BasicGameController();
	}
}
