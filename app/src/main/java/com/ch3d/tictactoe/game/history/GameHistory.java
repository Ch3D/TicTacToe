package com.ch3d.tictactoe.game.history;

import com.ch3d.tictactoe.game.board.GameBoard;
import com.ch3d.tictactoe.game.board.GameCell;
import com.ch3d.tictactoe.game.history.step.GameStep;
import com.ch3d.tictactoe.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class GameHistory {
	protected GameBoard mBoard;

	protected List<GameStep> mSteps;

	public GameHistory(GameBoard board) {
		mSteps = new ArrayList<>();
		mBoard = board;
	}

	public boolean addStep(final GameStep gameStep) {
		mSteps.add(gameStep);
		GameCell gameCell = mBoard.createCell(gameStep.getPosition());
		mBoard.fill(gameCell, gameStep);
		return checkWinner(gameCell, gameStep.getValue());
	}

	private boolean checkWinner(final GameCell cell, final int value) {
		return isRowFilled(cell, value) || isColumnFilled(cell, value)
				|| isDiagonalFilled(cell, value) || isBackDiagonalFilled(cell, value);
	}

	public boolean isRowFilled(final GameCell cell, final int value) {
		return mBoard.isRowFilled(cell.getRow(), value);
	}

	public boolean isColumnFilled(final GameCell cell, final int value) {
		return mBoard.isColumnFilled(cell.getColumn(), value);
	}

	public boolean isDiagonalFilled(final GameCell cell, final int value) {
		return mBoard.isDiagonalFilled(cell.getRow(), cell.getColumn(), value);
	}

	public boolean isBackDiagonalFilled(final GameCell cell, final int value) {
		return mBoard.isBackDiagonalFilled(cell.getRow(), cell.getColumn(), value);
	}

	public boolean isBusy(final int pos) {
		return mBoard.isBusy(pos);
	}

	/**
	 * Clear history and game board
	 */
	public void clear() {
		mSteps.clear();
		mBoard.clear();
	}

	public boolean isFilled() {
		return mSteps.size() == mBoard.getCellsCount();
	}

	/**
	 * @return combination which led to victory
	 **/
	public StepResult getWinnerCombination() {
		final GameStep gameStep = Utils.getLast(mSteps);
		final int position = gameStep.getPosition();
		final GameCell gameCell = mBoard.createCell(position);
		final int value = gameStep.getValue();
		if(isRowFilled(gameCell, value)) {
			final int row = gameCell.getRow();
			final int rowStartValue = row * mBoard.getSize();
			return new StepResult(mBoard.getRowIndexes(rowStartValue));
		} else if(isColumnFilled(gameCell, value)) {
			final int column = gameCell.getColumn();
			return new StepResult(mBoard.getColumnIndexes(column));
		} else if(isDiagonalFilled(gameCell, value)) {
			return new StepResult(mBoard.getDiagonalIndexes());
		} else if(isBackDiagonalFilled(gameCell, value)) {
			return new StepResult(mBoard.getDiagonalBackIndexes());
		}
		return StepResult.NULL;
	}

	public GameHistory unmodifiable() {
		return UnmodifiableGameHistory.create(mBoard, this);
	}

	public int cell(final int row, final int col) {
		return mBoard.getCellValue(row, col);
	}

	/**
	 * @return steps count
	 */
	public int size() {
		return mSteps.size();
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

	public List<GameStep> getMoves() {
		return Collections.unmodifiableList(mSteps);
	}
}
