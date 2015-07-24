package com.ch3d.tictactoe;

import com.ch3d.tictactoe.game.mark.CellMark;

/**
 * Created by Ch3D on 22.07.2015.
 * <p/>
 * Callback to be notified when some changes appears on a game board
 */
public interface GameHistoryListener {
	void onCellMarked(int pos, CellMark mark);
}