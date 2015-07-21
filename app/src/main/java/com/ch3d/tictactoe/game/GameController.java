package com.ch3d.tictactoe.game;

/**
 * Created by Ch3D on 21.07.2015.
 */
public interface GameController {
	/**
	 * Callback method that will be called when player tapped on a cell
	 */
	void onCellClick(String tag);

	/**
	 * Play again - default case when user taps 'Play again'
	 */
	void replay(String tag);
}
