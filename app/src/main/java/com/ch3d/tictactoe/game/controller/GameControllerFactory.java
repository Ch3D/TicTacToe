package com.ch3d.tictactoe.game.controller;

import android.support.annotation.NonNull;

import com.ch3d.tictactoe.game.GameMode;
import com.ch3d.tictactoe.game.controller.ai.AIHumanGameController;
import com.ch3d.tictactoe.game.controller.ai.HumanAIGameController;
import com.ch3d.tictactoe.view.GameHistoryListener;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class GameControllerFactory {
	public static final GameController create(@NonNull final GameMode gameMode, GameHistoryListener listener) {
		if(gameMode.player1().isHuman() && gameMode.player2().isHuman()) {
			return new BasicGameController(listener);
		}
		if(gameMode.player1().isHuman() && !gameMode.player2().isHuman()) {
			return new HumanAIGameController(listener);
		}
		if(!gameMode.player1().isHuman() && gameMode.player2().isHuman()) {
			return new AIHumanGameController(listener);
		}
		return new BasicGameController(listener);
	}
}
