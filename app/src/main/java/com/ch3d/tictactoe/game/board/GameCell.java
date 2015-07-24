package com.ch3d.tictactoe.game.board;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class GameCell {
	public static GameCell create(int position, int size) {
		return new GameCell((position - 1) / size, (position - 1) % size);
	}

	private int mRow;

	private int mCol;

	public GameCell(int row, int col) {
		mRow = row;
		mCol = col;
	}

	public int getRow() {
		return mRow;
	}

	public int getColumn() {
		return mCol;
	}

	@Override
	public String toString() {
		return "GameCell{" +
				"mRow=" + mRow +
				", mCol=" + mCol +
				'}';
	}

	@Override
	public boolean equals(final Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || getClass() != o.getClass()) {
			return false;
		}

		final GameCell gameCell = (GameCell) o;

		if(mRow != gameCell.mRow) {
			return false;
		}
		return mCol == gameCell.mCol;

	}

	@Override
	public int hashCode() {
		int result = mRow;
		result = 31 * result + mCol;
		return result;
	}
}
