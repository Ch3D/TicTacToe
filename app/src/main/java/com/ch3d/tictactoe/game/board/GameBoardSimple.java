package com.ch3d.tictactoe.game.board;

import com.ch3d.tictactoe.game.history.step.GameStep;
import com.ch3d.tictactoe.utils.Utils;

import java.util.Arrays;

/**
 * Created by Ch3D on 24.07.2015.
 */
public class GameBoardSimple implements GameBoard {
	public static final int BOARD_SIZE = 3;

	public static final int EMPTY_VALUE = 0;

	public static final int[] INDEXES_BACK_DIAGONAL = new int[]{3, 5, 7};

	public static final int[] INDEXES_DIAGONAL = new int[]{1, 5, 9};

	protected int[][] mBoard = new int[BOARD_SIZE][BOARD_SIZE];

	@Override
	public GameCell createCell(final int pos) {
		return GameCell.create(pos, BOARD_SIZE);
	}

	@Override
	public void fill(final GameCell gameCell, final GameStep gameStep) {
		mBoard[gameCell.getRow()][gameCell.getColumn()] = gameStep.getValue();
	}

	@Override
	public int getCellValue(final int row, final int col) {
		return mBoard[row][col];
	}

	@Override
	public void clear() {
		for(int i = 0; i < BOARD_SIZE; i++) {
			Arrays.fill(mBoard[i], EMPTY_VALUE);
		}
	}

	@Override
	public int getSize() {
		return BOARD_SIZE;
	}

	@Override
	public int getCellsCount() {
		return BOARD_SIZE * BOARD_SIZE;
	}

	public void dump() {
		System.err.println(Arrays.toString(mBoard[0]));
		System.err.println(Arrays.toString(mBoard[1]));
		System.err.println(Arrays.toString(mBoard[2]));
	}

	@Override
	public int[][] getMatrix() {
		return Utils.clone(mBoard);
	}

	@Override
	public boolean isRowFilled(final int row, final int value) {
		return mBoard[row][0] == value && mBoard[row][1] == value && mBoard[row][2] == value;
	}

	@Override
	public boolean isColumnFilled(final int col, final int value) {
		return mBoard[0][col] == value && mBoard[1][col] == value && mBoard[2][col] == value;
	}

	@Override
	public boolean isDiagonalFilled(final int row, final int col, final int value) {
		return row == col && mBoard[0][0] == value && mBoard[1][1] == value && mBoard[2][2] == value;
	}

	@Override
	public boolean isBackDiagonalFilled(final int row, final int col, final int value) {
		return row + col == 2 && mBoard[0][2] == value && mBoard[1][1] == value && mBoard[2][0] == value;
	}

	@Override
	public boolean isBusy(final int pos) {
		final GameCell cell = GameCell.create(pos, BOARD_SIZE);
		return mBoard[cell.getRow()][cell.getColumn()] != EMPTY_VALUE;
	}

	@Override
	public int[] getColumnIndexes(final int column) {
		return new int[]{column + 1, column + 1 + getSize(), column + 1 + getSize() + getSize()};
	}

	@Override
	public int[] getRowIndexes(final int rowStartValue) {
		return new int[]{rowStartValue + 1, rowStartValue + 2, rowStartValue + 3};
	}

	@Override
	public int[] getDiagonalBackIndexes() {
		return INDEXES_BACK_DIAGONAL;
	}

	@Override
	public int[] getDiagonalIndexes() {
		return INDEXES_DIAGONAL;
	}
}
