package com.ch3d.tictactoe.game.history;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class GameStepX extends GameStep {
	public GameStepX(final int position) {
		super(position);
	}

	@Override
	public int getValue() {
		return 1;
	}
}
