package com.ch3d.tictactoe.module;

import com.ch3d.tictactoe.GameFieldActivity;
import com.ch3d.tictactoe.MainActivity;
import com.ch3d.tictactoe.TicTacToeApplication;
import com.ch3d.tictactoe.game.BasicGameController;
import com.ch3d.tictactoe.game.GameController;
import com.ch3d.tictactoe.game.GameField;
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
		complete = false)
public class AppModule {
}
