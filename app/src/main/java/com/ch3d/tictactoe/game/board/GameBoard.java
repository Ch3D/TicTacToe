package com.ch3d.tictactoe.game.board;

import com.ch3d.tictactoe.game.history.step.GameStep;

/**
 * Created by Ch3D on 24.07.2015.
 */
public interface GameBoard {
	/**
	 * Fill certain cell with a value
	 */
	void fill(GameCell gameCell, GameStep gameStep);

	/**
	 * @return cell's current value
	 */
	int getCellValue(int row, int col);

	/**
	 * Clean all the cells
	 */
	void clear();

	/**
	 * @return board size (basic is 3)
	 */
	int getSize();

	/**
	 * @return amount of cells on this board
	 */
	int getCellsCount();

	/**
	 * @return current board cells matrix
	 */
	int[][] getMatrix();

	/**
	 * @return true if specific row is filled with value. Otherwise - false
	 */
	boolean isRowFilled(int row, int value);

	/**
	 * @return true if specific column is filled with value. Otherwise - false
	 */
	boolean isColumnFilled(int col, int value);

	/**
	 * @return true if left to right diagonal is filled with value. Otherwise - false
	 */
	boolean isDiagonalFilled(int row, int col, int value);

	/**
	 * @return true if right to left diagonal is filled with value. Otherwise - false
	 */
	boolean isBackDiagonalFilled(int row, int col, int value);

	/**
	 * @return true is specific position already has filled value
	 */
	boolean isBusy(int pos);

	int[] getColumnIndexes(int column);

	int[] getRowIndexes(int rowStartValue);

	int[] getDiagonalBackIndexes();

	int[] getDiagonalIndexes();

	/**
	 * Turns a position into GameCell
	 */
	GameCell createCell(int position);

	/**
	 * For debug purposes
	 */
	void dump();
}
