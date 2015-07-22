package com.ch3d.tictactoe.game.mark;

/**
 * Created by Ch3D on 22.07.2015.
 */
public final class CellMarkX extends CellMark {

	public static final CellMarkX VALUE = new CellMarkX(0, "x");

	private CellMarkX(final int id, final String type) {
		super(id, type);
	}
}
