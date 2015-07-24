package com.ch3d.tictactoe.game.history;

import java.util.Arrays;

/**
 * Created by Ch3D on 24.07.2015.
 */
public class GameBoard {
	public static final int BOARD_SIZE = 3;

	protected int[][] mBoard = new int[BOARD_SIZE][BOARD_SIZE];

	public GameCell createCell(final int pos) {
		return GameCell.create(pos, BOARD_SIZE);
	}

	public void fill(final GameCell gameCell, final GameStep gameStep) {
		mBoard[gameCell.getRow()][gameCell.getColumn()] = gameStep.getValue();
	}

	public int getCellValue(final int row, final int col) {
		return mBoard[row][col];
	}

	public void clear() {
		for(int i = 0; i < BOARD_SIZE; i++) {
			Arrays.fill(mBoard[i], 0);
		}
	}

	public int getSize() {
		return BOARD_SIZE;
	}

	public int getCellsCount() {
		return BOARD_SIZE * BOARD_SIZE;
	}

	public void dump() {

		System.err.println(Arrays.toString(mBoard[0]));
		System.err.println(Arrays.toString(mBoard[1]));
		System.err.println(Arrays.toString(mBoard[2]));
	}

	public int[][] getMatrix() {
		//return Utils.clone(mBoard);
		return mBoard;
	}
}
