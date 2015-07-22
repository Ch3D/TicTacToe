package com.ch3d.tictactoe.game.mark;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class CellMarkO extends CellMark {

	public static final CellMarkO VALUE = new CellMarkO(0, "o");

	public CellMarkO(final int id, final String type) {
		super(id, type);
	}
}
