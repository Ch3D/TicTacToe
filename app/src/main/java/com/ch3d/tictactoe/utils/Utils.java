package com.ch3d.tictactoe.utils;

import com.ch3d.tictactoe.game.history.GameCell;

import java.util.List;

/**
 * Created by Ch3D on 24.07.2015.
 */
public class Utils {
	public static int getCellPosition(final GameCell cell, int boardSize) {
		return (cell.getColumn() * boardSize) + (cell.getRow() + 1);
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
}
