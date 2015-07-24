package com.ch3d.tictactoe.game;

import android.support.annotation.Nullable;

import com.ch3d.tictactoe.game.history.GameCell;
import com.ch3d.tictactoe.game.history.GameStepO;
import com.ch3d.tictactoe.game.history.GameStepX;
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
		if((mBoard[0][0] == mBoard[1][1] && mBoard[0][0] == mBoard[2][2] && mBoard[0][0] == 1) || (mBoard[0][2] == mBoard[1][1]
				&& mBoard[0][2] == mBoard[2][0] && mBoard[0][2] == 1)) {
			//System.out.println("X Diagonal Win");
			return true;
		}
		for(int i = 0; i < 3; ++i) {
			if(((mBoard[i][0] == mBoard[i][1] && mBoard[i][0] == mBoard[i][2] && mBoard[i][0] == 1)
					|| (mBoard[0][i] == mBoard[1][i] && mBoard[0][i] == mBoard[2][i] && mBoard[0][i] == 1))) {
				// System.out.println("X Row or Column win");
				return true;
			}
		}
		return false;
	}

	public boolean hasOWon() {
		if((mBoard[0][0] == mBoard[1][1] && mBoard[0][0] == mBoard[2][2] && mBoard[0][0] == 2) || (mBoard[0][2] == mBoard[1][1]
				&& mBoard[0][2] == mBoard[2][0] && mBoard[0][2] == 2)) {
			// System.out.println("O Diagonal Win");
			return true;
		}
		for(int i = 0; i < 3; ++i) {
			if((mBoard[i][0] == mBoard[i][1] && mBoard[i][0] == mBoard[i][2] && mBoard[i][0] == 2)
					|| (mBoard[0][i] == mBoard[1][i] && mBoard[0][i] == mBoard[2][i] && mBoard[0][i] == 2)) {
				//  System.out.println("O Row or Column win");
				return true;
			}
		}

		return false;
	}

	public List<GameCell> getAvailableStates() {
		ArrayList<GameCell> availablePoints = new ArrayList<>();
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
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
			System.err.println("xwon depth = " + depth);
			return RESULT_X_WON * (mMaxDepth - depth);
		}
		if(hasOWon()) {
			System.err.println("owon depth = " + depth);
			return RESULT_Y_WON * (mMaxDepth - depth);
		}

		List<GameCell> pointsAvailable = getAvailableStates();
		if(pointsAvailable.isEmpty()) {
			return RESULT_NONE;
		}

		List<Integer> scores = new ArrayList<>();

		for(int i = 0; i < pointsAvailable.size(); ++i) {
			GameCell point = pointsAvailable.get(i);

			if(turn == GameStepX.VALUE) {
				placeAMove(point, GameStepX.VALUE);
				int currentScore = minimax(depth + 1, GameStepO.VALUE);
				scores.add(currentScore);

				if(depth == 0) {
					rootsChildrenScores.add(new CellScore(currentScore, point));
				}

			} else if(turn == GameStepO.VALUE) {
				placeAMove(point, GameStepO.VALUE);
				final int currentScore = minimax(depth + 1, GameStepX.VALUE);
				scores.add(currentScore);

				if(depth == 0) {
					rootsChildrenScores.add(new CellScore(currentScore, point));
				}
			}
			mBoard[point.getRow()][point.getColumn()] = 0;
		}
		return turn == GameStepX.VALUE ? Utils.returnMax(scores) : Utils.returnMin(scores);
	}

	@Nullable
	public GameCell returnBestMoveX() {
		if(rootsChildrenScores.isEmpty()) {
			return null;
		}

		int valueMax = Integer.MIN_VALUE;
		int bestIndex = -1;

		for(int i = 0; i < rootsChildrenScores.size(); ++i) {
			if(valueMax < rootsChildrenScores.get(i).getScore()) {
				valueMax = rootsChildrenScores.get(i).getScore();
				bestIndex = i;
			}
		}

		return rootsChildrenScores.get(bestIndex).getPoint();
	}

	@Nullable
	public GameCell returnBestMoveO() {
		if(rootsChildrenScores.isEmpty()) {
			return null;
		}

		int MIN = Integer.MAX_VALUE;
		int best = -1;
		for(int i = 0; i < rootsChildrenScores.size(); ++i) {
			if(MIN > rootsChildrenScores.get(i).getScore()) {
				MIN = rootsChildrenScores.get(i).getScore();
				best = i;
			}
		}

		return rootsChildrenScores.get(best).getPoint();
	}
}
