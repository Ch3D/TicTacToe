package com.ch3d.tictactoe.game.history;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class GameCell {
	public static GameCell create(int position, int size) {
		return new GameCell((position - 1) / size, (position - 1) % size);
	}

	private int mRow;

	private int mCol;

	private GameCell(int row, int col) {
		mRow = row;
		mCol = col;
	}

	public int getRow() {
		return mRow;
	}

	public int getColumn() {
		return mCol;
	}
}
