package com.ch3d.tictactoe.game;

import android.os.Parcel;

/**
 * Created by Ch3D on 21.07.2015.
 */
public class HumanPlayer extends Player {
	public static final Creator<HumanPlayer> CREATOR = new Creator<HumanPlayer>() {
		@Override
		public HumanPlayer createFromParcel(Parcel in) {
			return new HumanPlayer(in);
		}

		@Override
		public HumanPlayer[] newArray(int size) {
			return new HumanPlayer[size];
		}
	};

	protected HumanPlayer(Parcel in) {
		super(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(final Parcel dest, final int flags) {
	}
}
