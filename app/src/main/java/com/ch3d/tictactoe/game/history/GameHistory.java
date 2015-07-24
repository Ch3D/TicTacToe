package com.ch3d.tictactoe.game.history;

import java.util.ArrayList;
import java.util.List;

import auto.parcel.AutoParcel;

/**
 * Created by Ch3D on 22.07.2015.
 */
@AutoParcel
public class GameHistory {
	protected GameBoard mBoard;

	protected List<GameStep> mHistory;

	public GameHistory() {
		mHistory = new ArrayList<>();
		mBoard = new GameBoard();
	}

	public boolean addStep(final GameStep gameStep) {
		mHistory.add(gameStep);
		GameCell gameCell = mBoard.createCell(gameStep.getPosition());
		mBoard.fill(gameCell, gameStep);
		return checkWinner(gameCell, gameStep.getValue());
	}

	private boolean checkWinner(final GameCell cell, final int value) {
		return isRowFilled(cell, value) || isColumnFilled(cell, value)
				|| isDiagonalFilled(cell, value) || isBackDiagonalFilled(cell, value);
	}

	public boolean isRowFilled(final GameCell cell, final int value) {
		final int row = cell.getRow();
		return mBoard.getCellValue(row, 0) == value && mBoard.getCellValue(row, 1) == value && mBoard.getCellValue(row, 2) == value;
	}

	public boolean isColumnFilled(final GameCell cell, final int value) {
		final int col = cell.getColumn();
		return mBoard.getCellValue(0, col) == value && mBoard.getCellValue(1, col) == value && mBoard.getCellValue(2, col) == value;
	}

	public boolean isDiagonalFilled(final GameCell cell, final int value) {
		final int col = cell.getColumn();
		final int row = cell.getRow();
		return row == col && mBoard.getCellValue(0, 0) == value && mBoard.getCellValue(1, 1) == value &&
				mBoard.getCellValue(2, 2) == value;
	}

	public boolean isBackDiagonalFilled(final GameCell cell, final int value) {
		final int col = cell.getColumn();
		final int row = cell.getRow();
		return row + col == 2 && mBoard.getCellValue(0, 2) == value && mBoard.getCellValue(1, 1) == value &&
				mBoard.getCellValue(2, 0) == value;
	}

	public boolean isBusy(final int pos) {
		for(final GameStep gameStep : mHistory) {
			if(gameStep.getPosition() == pos) {
				return true;
			}
		}
		return false;
	}

	public void clear() {
		mHistory.clear();
		mBoard.clear();

	}

	public boolean isFilled() {
		return mHistory.size() == mBoard.getCellsCount();
	}

	public StepResult getWinnerCombination() {
		final GameStep gameStep = mHistory.get(mHistory.size() - 1);
		final int position = gameStep.getPosition();
		final GameCell gameCell = mBoard.createCell(position);
		final int value = gameStep.getValue();
		if(isRowFilled(gameCell, value)) {
			final int row = gameCell.getRow();
			final int rowStartValue = row * mBoard.getSize();
			return new StepResult(new int[]{rowStartValue + 1, rowStartValue + 2, rowStartValue + 3});
		} else if(isColumnFilled(gameCell, value)) {
			final int column = gameCell.getColumn();
			return new StepResult(new int[]{column + 1, column + 1 + mBoard.getSize(), column + 1 + mBoard.getSize() + mBoard.getSize()});
		} else if(isDiagonalFilled(gameCell, value)) {
			return new StepResult(new int[]{1, 5, 9});
		} else if(isBackDiagonalFilled(gameCell, value)) {
			return new StepResult(new int[]{3, 5, 7});
		}
		return StepResult.NULL;
	}

	public GameHistory unmodifiable() {
		return UnmodifiableGameHistory.create(this);
	}

	public int cell(final int row, final int col) {
		return mBoard.getCellValue(row, col);
	}

	/**
	 * @return
	 */
	public int size() {
		return mHistory.size();
	}

	public void dump() {
		System.err.println("==== " + size() + " ====");
		mBoard.dump();
	}

	/**
	 * Returns actual game board.
	 * Actually, for safety reason it return clone of actual game board.
	 */
	public int[][] getBoardMatrix() {
		return mBoard.getMatrix();
	}

	public int getBoardSize() {
		return mBoard.getSize();
	}
}
