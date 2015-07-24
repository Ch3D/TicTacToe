package com.ch3d.tictactoe.module;

import com.ch3d.tictactoe.activity.GameFieldActivity;
import com.ch3d.tictactoe.activity.MainActivity;
import com.ch3d.tictactoe.TicTacToeApplication;
import com.ch3d.tictactoe.game.GameField;
import com.ch3d.tictactoe.game.controller.BasicGameController;
import com.ch3d.tictactoe.game.controller.GameController;
import com.ch3d.tictactoe.view.GameFieldView;

import dagger.Module;

/**
 * Created by Ch3D on 21.07.2015.
 */
@Module(injects = {
		TicTacToeApplication.class,
		/* activities */
		MainActivity.class, GameFieldActivity.class,
		/* other */
		GameFieldView.class, GameController.class, BasicGameController.class, GameField.class},
		complete = false, library = true)
public class AppModule {
}
