package com.ch3d.tictactoe.game;

import android.graphics.Point;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class MinMaxStrategy {
	private final int[][] mBoard;

	private List<CellScore> rootsChildrenScores;

	public MinMaxStrategy(final int[][] board) {
		mBoard = board;
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

	public List<Point> getAvailableStates() {
		ArrayList<Point> availablePoints = new ArrayList<>();
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				if(mBoard[i][j] == 0) {
					availablePoints.add(new Point(i, j));
				}
			}
		}
		return availablePoints;
	}

	public void placeAMove(Point point, int player) {
		mBoard[point.x][point.y] = player;   //player = 1 for X, 2 for O
	}

	public int minimax(int depth, int turn) {
		if(hasXWon()) {
			System.err.println("xwon depth = " + depth);
			return +1 * (9 - depth);
		}
		if(hasOWon()) {
			System.err.println("owon depth = " + depth);
			return -1 * (9 - depth);
		}

		List<Point> pointsAvailable = getAvailableStates();
		if(pointsAvailable.isEmpty()) {
			return 0;
		}

		List<Integer> scores = new ArrayList<>();

		for(int i = 0; i < pointsAvailable.size(); ++i) {
			Point point = pointsAvailable.get(i);

			if(turn == 1) { //X's turn select the highest from below minimax() call
				placeAMove(point, 1);
				int currentScore = minimax(depth + 1, 2);
				scores.add(currentScore);

				if(depth == 0) {
					rootsChildrenScores.add(new CellScore(currentScore, point));
				}

			} else if(turn == 2) {//O's turn select the lowest from below minimax() call
				placeAMove(point, 2);
				final int currentScore = minimax(depth + 1, 1);
				scores.add(currentScore);

				if(depth == 0) {
					rootsChildrenScores.add(new CellScore(currentScore, point));
				}
			}
			mBoard[point.x][point.y] = 0; //Reset this point
		}
		return turn == 1 ? returnMax(scores) : returnMin(scores);
	}

	public int returnMin(List<Integer> list) {
		int min = Integer.MAX_VALUE;
		int index = -1;
		for(int i = 0; i < list.size(); ++i) {
			if(list.get(i) < min) {
				min = list.get(i);
				index = i;
			}
		}
		return list.get(index);
	}

	public int returnMax(List<Integer> list) {
		int maxValue = Integer.MIN_VALUE;
		int index = -1;
		for(int i = 0; i < list.size(); ++i) {
			if(list.get(i) > maxValue) {
				maxValue = list.get(i);
				index = i;
			}
		}
		return list.get(index);
	}

	@Nullable
	public Point returnBestMoveX() {
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
	public Point returnBestMoveO() {
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
