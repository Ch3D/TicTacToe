package com.ch3d.tictactoe.game;

import android.widget.Toast;

import com.ch3d.tictactoe.TicTacToeApplication;

/**
 * Created by Ch3D on 21.07.2015.
 */
public class BasicGameController implements GameController {
	private final TicTacToeApplication mApp;

	private final GameField mField;

	public BasicGameController(final TicTacToeApplication application) {
		mApp = application;
		mField = new GameField();
	}

	@Override
	public void onCellClick(final String tag) {
		Toast.makeText(mApp, "Click tag = " + tag, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void replay(final String tag) {

	}
}
