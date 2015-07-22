package com.ch3d.tictactoe.module;

import com.ch3d.tictactoe.TicTacToeApplication;
import com.ch3d.tictactoe.game.controller.BasicGameController;
import com.ch3d.tictactoe.game.controller.GameController;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ch3D on 21.07.2015.
 */
@Module(library = true)
public class GameModule {
	private final TicTacToeApplication application;

	public GameModule(TicTacToeApplication app) {
		application = app;
	}

	@Provides
	@Singleton
	GameController provideGameController() {
		return new BasicGameController(application);
	}

}
