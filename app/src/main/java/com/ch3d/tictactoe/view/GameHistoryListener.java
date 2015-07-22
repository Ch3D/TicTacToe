package com.ch3d.tictactoe.view;

import com.ch3d.tictactoe.game.mark.CellMark;

/**
 * Created by Ch3D on 22.07.2015.
 */
public interface GameHistoryListener {
	public void onCellMarked(int pos, CellMark mark);
}
