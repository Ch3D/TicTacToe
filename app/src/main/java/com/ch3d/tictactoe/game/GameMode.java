package com.ch3d.tictactoe.game;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.ch3d.tictactoe.game.player.Player;

import auto.parcel.AutoParcel;

/**
 * Created by Ch3D on 21.07.2015.
 */
@AutoParcel
public abstract class GameMode implements Parcelable {

	public static final int MODE_HUMAN_HUMAN = 0;

	public static final int MODE_HUMAN_AI = 1;

	public static final int MODE_AI_HUMAN = 2;

	public static GameMode create(Player player1, Player player2) {
		return new AutoParcel_GameMode(player1, player2);
	}

	@Nullable
	public abstract Player player1();

	@Nullable
	public abstract Player player2();
}
