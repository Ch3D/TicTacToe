package com.ch3d.tictactoe.game;

import android.support.annotation.Nullable;

import com.ch3d.tictactoe.game.board.GameCell;
import com.ch3d.tictactoe.game.history.step.GameStepO;
import com.ch3d.tictactoe.game.history.step.GameStepX;
import com.ch3d.tictactoe.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class MinMaxStrategy {

	public static final int RESULT_X_WON = +1;

	public static final int RESULT_Y_WON = -1;

	public static final int RESULT_NONE = 0;

	public static int getMinIndex(List<CellScore> list) {
		int valueMin = Integer.MAX_VALUE;
		int best = -1;
		for(int i = 0; i < list.size(); ++i) {
			if(valueMin > list.get(i).getScore()) {
				valueMin = list.get(i).getScore();
				best = i;
			}
		}
		return best;
	}

	public static int getMaxIndex(List<CellScore> list) {
		int valueMax = Integer.MIN_VALUE;
		int bestIndex = -1;

		for(int i = 0; i < list.size(); ++i) {
			if(valueMax < list.get(i).getScore()) {
				valueMax = list.get(i).getScore();
				bestIndex = i;
			}
		}
		return bestIndex;
	}

	private final int[][] mBoard;

	private List<CellScore> rootsChildrenScores;

	private int mMaxDepth;

	public MinMaxStrategy(final int[][] board) {
		mBoard = board;
		mMaxDepth = mBoard.length * mBoard.length;
	}

	public List<CellScore> getRootsChildrenScores() {
		return rootsChildrenScores;
	}

	public void callMinimax(int depth, int turn) {
		rootsChildrenScores = new ArrayList<>();
		minimax(depth, turn);
	}

	public boolean hasXWon() {
		return hasWon(GameStepX.VALUE);
	}

	public boolean hasWon(int value) {
		if((mBoard[0][0] == mBoard[1][1] && mBoard[0][0] == mBoard[2][2] && mBoard[0][0] == value) || (mBoard[0][2] == mBoard[1][1]
				&& mBoard[0][2] == mBoard[2][0] && mBoard[0][2] == value)) {
			return true;
		}
		for(int i = 0; i < mBoard.length; ++i) {
			if(((mBoard[i][0] == mBoard[i][1] && mBoard[i][0] == mBoard[i][2] && mBoard[i][0] == value)
					|| (mBoard[0][i] == mBoard[1][i] && mBoard[0][i] == mBoard[2][i] && mBoard[0][i] == value))) {
				return true;
			}
		}
		return false;
	}

	public boolean hasOWon() {
		return hasWon(GameStepO.VALUE);
	}

	public List<GameCell> getAvailableStates() {
		ArrayList<GameCell> availablePoints = new ArrayList<>();
		for(int i = 0; i < mBoard.length; ++i) {
			for(int j = 0; j < mBoard[i].length; ++j) {
				if(mBoard[i][j] == 0) {
					availablePoints.add(new GameCell(i, j));
				}
			}
		}
		return availablePoints;
	}

	public void placeAMove(GameCell point, int player) {
		mBoard[point.getRow()][point.getColumn()] = player;
	}

	public int minimax(int depth, int turn) {
		if(hasXWon()) {
			return RESULT_X_WON * (mMaxDepth - depth);
		}
		if(hasOWon()) {
			return RESULT_Y_WON * (mMaxDepth - depth);
		}

		List<GameCell> pointsAvailable = getAvailableStates();
		if(pointsAvailable.isEmpty()) {
			return RESULT_NONE;
		}

		List<Integer> scores = new ArrayList<>();

		for(int i = 0; i < pointsAvailable.size(); ++i) {
			GameCell cell = pointsAvailable.get(i);

			if(turn == GameStepX.VALUE) {
				placeAMove(cell, GameStepX.VALUE);
				int currentScore = minimax(depth + 1, GameStepO.VALUE);
				scores.add(currentScore);

				if(depth == 0) {
					rootsChildrenScores.add(new CellScore(currentScore, cell));
				}

			} else if(turn == GameStepO.VALUE) {
				placeAMove(cell, GameStepO.VALUE);
				final int currentScore = minimax(depth + 1, GameStepX.VALUE);
				scores.add(currentScore);

				if(depth == 0) {
					rootsChildrenScores.add(new CellScore(currentScore, cell));
				}
			}
			mBoard[cell.getRow()][cell.getColumn()] = 0;
		}
		return turn == GameStepX.VALUE ? Utils.returnMax(scores) : Utils.returnMin(scores);
	}

	@Nullable
	public GameCell returnBestMoveX() {
		if(rootsChildrenScores.isEmpty()) {
			return null;
		}

		int bestIndex = getMaxIndex(rootsChildrenScores);
		return rootsChildrenScores.get(bestIndex).getPoint();
	}

	@Nullable
	public GameCell returnBestMoveO() {
		if(rootsChildrenScores.isEmpty()) {
			return null;
		}

		int best = getMinIndex(rootsChildrenScores);

		return rootsChildrenScores.get(best).getPoint();
	}
}
