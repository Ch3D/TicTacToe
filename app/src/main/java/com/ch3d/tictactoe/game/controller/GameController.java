package com.ch3d.tictactoe.game.controller;

import com.ch3d.tictactoe.GameHistoryListener;
import com.ch3d.tictactoe.game.GameListener;
import com.ch3d.tictactoe.game.history.StepResult;
import com.ch3d.tictactoe.game.state.GameState;

/**
 * Created by Ch3D on 21.07.2015.
 */
public interface GameController {
	int WRONG_POSITION = -1;

	void addGameListener(GameListener listener);

	void removeGameListener(GameListener listener);

	/**
	 * Callback method that will be called when player tapped on a cell
	 */
	void onCellClick(String tag, final GameHistoryListener listener);

	/**
	 * @return combination that led to victory
	 */
	StepResult getWinCombination();

	/**
	 * Play again - default case when user taps 'Play again'
	 */
	void replay(String tag);

	void startGame();

	/**
	 * Clear game and all the data
	 */
	void clear();

	/**
	 * @return current game state
	 */
	GameState getState();

	void prepare(GameHistoryListener listener);

	void addGameHistoryListener(GameHistoryListener listener);
}
