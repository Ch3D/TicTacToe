package com.ch3d.tictactoe.game.board;

import com.ch3d.tictactoe.game.history.step.GameStep;

/**
 * Created by Ch3D on 24.07.2015.
 */
public interface GameBoard {
	void fill(GameCell gameCell, GameStep gameStep);

	int getCellValue(int row, int col);

	void clear();

	int getSize();

	int getCellsCount();

	int[][] getMatrix();

	boolean isRowFilled(int row, int value);

	boolean isColumnFilled(int col, int value);

	boolean isDiagonalFilled(int row, int col, int value);

	boolean isBackDiagonalFilled(int row, int col, int value);

	boolean isBusy(int pos);

	int[] getColumnIndexes(int column);

	int[] getRowIndexes(int rowStartValue);

	int[] getDiagonalBackIndexes();

	int[] getDiagonalIndexes();
}
