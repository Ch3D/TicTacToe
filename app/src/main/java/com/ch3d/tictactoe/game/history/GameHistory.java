package com.ch3d.tictactoe.game.history;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import auto.parcel.AutoParcel;

/**
 * Created by Ch3D on 22.07.2015.
 */
@AutoParcel
public class GameHistory {
	private static final int BOARD_SIZE = 3;

	protected int[][] mBoard = new int[BOARD_SIZE][BOARD_SIZE];

	protected List<GameStep> mHistory;

	public GameHistory() {
		mHistory = new ArrayList<>();
	}

	public boolean addStep(final GameStep gameStep) {
		mHistory.add(gameStep);
		final GameCell gameCell = GameCell.create(gameStep.getPosition(), BOARD_SIZE);
		mBoard[gameCell.getRow()][gameCell.getColumn()] = gameStep.getValue();
		return checkWinner(gameCell, gameStep.getValue());
	}

	private boolean checkWinner(final GameCell cell, final int value) {
		return isRowFilled(cell, value) || isColumnFilled(cell, value)
				|| isDiagonalFilled(cell, value) || isBackDiagonalFilled(cell, value);
	}

	public boolean isRowFilled(final GameCell cell, final int value) {
		final int row = cell.getRow();
		return mBoard[row][0] == value && mBoard[row][1] == value && mBoard[row][2] == value;
	}

	public boolean isColumnFilled(final GameCell cell, final int value) {
		final int col = cell.getColumn();
		return mBoard[0][col] == value && mBoard[1][col] == value && mBoard[2][col] == value;
	}

	public boolean isDiagonalFilled(final GameCell cell, final int value) {
		final int col = cell.getColumn();
		final int row = cell.getRow();
		return row == col && mBoard[0][0] == value && mBoard[1][1] == value && mBoard[2][2] == value;
	}

	public boolean isBackDiagonalFilled(final GameCell cell, final int value) {
		final int col = cell.getColumn();
		final int row = cell.getRow();
		return row + col == 2 && mBoard[0][2] == value && mBoard[1][1] == value && mBoard[2][0] == value;
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
		for(int i = 0; i < BOARD_SIZE; i++) {
			Arrays.fill(mBoard[i], 0);
		}
	}

	public boolean isFilled() {
		return mHistory.size() == BOARD_SIZE * BOARD_SIZE;
	}

	public StepResult getWinnerCombination() {
		final GameStep gameStep = mHistory.get(mHistory.size() - 1);
		final int position = gameStep.getPosition();
		final GameCell gameCell = GameCell.create(position, BOARD_SIZE);
		final int value = gameStep.getValue();
		if(isRowFilled(gameCell, value)) {
			final int row = gameCell.getRow();
			final int rowStartValue = row * BOARD_SIZE;
			return new StepResult(new int[]{rowStartValue + 1, rowStartValue + 2, rowStartValue + 3});
		} else if(isColumnFilled(gameCell, value)) {
			final int column = gameCell.getColumn();
			return new StepResult(new int[]{column + 1, column + 1 + BOARD_SIZE, column + 1 + BOARD_SIZE + BOARD_SIZE});
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
		return mBoard[row][col];
	}

	public int size() {
		return mHistory.size();
	}

	public void dump() {
		System.err.println("==== " + size() + " ====");
		System.err.println(Arrays.toString(mBoard[0]));
		System.err.println(Arrays.toString(mBoard[1]));
		System.err.println(Arrays.toString(mBoard[2]));
	}

	public List<GameStep> getStepsO() {
		return getGameSteps(GameStepO.VALUE);
	}

	public List<GameStep> getStepsX() {
		return getGameSteps(GameStepX.VALUE);
	}

	@NonNull
	private List<GameStep> getGameSteps(int value) {
		ArrayList<GameStep> result = new ArrayList<>();
		for(final GameStep gameStep : mHistory) {
			if(gameStep.getValue() == value) {
				result.add(gameStep);
			}
		}
		return result;
	}

	public int[][] getBoard() {
		return mBoard;
	}
}
