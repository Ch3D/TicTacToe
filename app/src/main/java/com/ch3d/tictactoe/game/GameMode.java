package com.ch3d.tictactoe.game;

import com.ch3d.tictactoe.game.player.Player;

import auto.parcel.AutoParcel;

/**
 * Created by Ch3D on 21.07.2015.
 */
@AutoParcel
public class GameMode {
	private Player mPlayer1;

	private Player mPlayer2;

	public Player getPlayer1() {
		return mPlayer1;
	}

	public void setPlayer1(final Player player1) {
		mPlayer1 = player1;
	}

	public Player getPlayer2() {
		return mPlayer2;
	}

	public void setPlayer2(final Player player2) {
		mPlayer2 = player2;
	}
}
