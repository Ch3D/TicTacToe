package com.ch3d.tictactoe.game.history;

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

	private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

	private List<GameStep> mHistory;

	public GameHistory() {
		mHistory = new ArrayList<>();
	}

	public boolean addStep(final GameStep gameStep) {
		mHistory.add(gameStep);
		final int position = gameStep.getPosition();
		int row = (position - 1) / BOARD_SIZE;
		int col = (position - 1) % BOARD_SIZE;
		board[row][col] = gameStep.getValue();
		return checkWinner(row, col, gameStep.getValue());
	}

	private boolean checkWinner(final int row, final int col, final int value) {
		return (board[row][0] == value && board[row][1] == value && board[row][2] == value
				|| board[0][col] == value && board[1][col] == value && board[2][col] == value
				|| row == col && board[0][0] == value && board[1][1] == value && board[2][2] == value
				|| row + col == 2 && board[0][2] == value && board[1][1] == value && board[2][0] == value);
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
			Arrays.fill(board[i], 0);
		}
	}

	public boolean isFilled() {
		return mHistory.size() == BOARD_SIZE * BOARD_SIZE;
	}
}
