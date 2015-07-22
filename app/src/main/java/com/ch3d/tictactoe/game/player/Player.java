package com.ch3d.tictactoe.game.player;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import auto.parcel.AutoParcel;

/**
 * Created by Ch3D on 21.07.2015.
 */
@AutoParcel
public abstract class Player implements Parcelable {

	public static Player create(boolean isHuman) {
		return new AutoParcel_Player(isHuman);
	}

	@Nullable
	public abstract boolean isHuman();
}
