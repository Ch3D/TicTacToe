package com.ch3d.tictactoe.game.history.step;

import com.ch3d.tictactoe.game.mark.CellMarkO;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class GameStepO extends GameStep {
	public static final int VALUE = 2;

	public GameStepO(final int position) {
		super(position, CellMarkO.VALUE);
	}

	@Override
	public int getValue() {
		return VALUE;
	}
}
