package com.ch3d.tictactoe.utils;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import com.ch3d.tictactoe.game.CellScore;
import com.ch3d.tictactoe.game.board.GameCell;

import java.util.List;

/**
 * Created by Ch3D on 24.07.2015.
 */
public class Utils {
	public static final String EMPTY_STRING = "";

	public static final float SCALE_INVISIBLE = 0f;

	public static final float SCALE_VISIBLE = 1f;

	public static final float ALPHA_TRANSPARENT = 0f;

	public static final float ALPHA_VISIBLE = 1f;

	/**
	 * Convert cell into index(position)
	 */
	public static int getCellPosition(final GameCell cell, int boardSize) {
		return (cell.getColumn() + 1) + (cell.getRow() * boardSize);
	}

	public static int[][] clone(final int[][] src) {
		int[][] dst = new int[src.length][];
		for(int i = 0; i < src.length; i++) {
			int[] aMatrix = src[i];
			int aLength = aMatrix.length;
			dst[i] = new int[aLength];
			System.arraycopy(aMatrix, 0, dst[i], 0, aLength);
		}
		return dst;
	}

	public static int returnMin(List<Integer> list) {
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

	public static int returnMax(List<Integer> list) {
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
	public static <T> T getLast(List<T> list) {
		if(list == null | list.isEmpty()) {
			return null;
		}
		return list.get(list.size() - 1);
	}

	public static void setBackground(final View view, final int color) {
		if(view == null) {
			return;
		}
		view.setBackgroundColor(color);
	}

	public static void clearDrawable(final ImageButton view) {
		if(view == null) {
			return;
		}
		view.setImageDrawable(null);
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

	public static int getMixIndex(List<CellScore> list) {
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
}
