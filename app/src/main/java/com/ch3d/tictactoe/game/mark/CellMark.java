package com.ch3d.tictactoe.game.mark;

/**
 * Created by Ch3D on 22.07.2015.
 */
public abstract class CellMark {
	private final int mId;

	private final String mType;

	public CellMark(int id, String type) {
		mId = id;
		mType = type;
	}

	public int getId() {
		return mId;
	}

	public String getType() {
		return mType;
	}
}
