package com.ch3d.tictactoe.game.history.step;

import com.ch3d.tictactoe.game.mark.CellMarkX;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class GameStepX extends GameStep {

	public static final int VALUE = 1;

	public GameStepX(final int position) {
		super(position, CellMarkX.VALUE);
	}

	@Override
	public int getValue() {
		return VALUE;
	}
}
