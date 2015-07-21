package com.ch3d.tictactoe.game;

import android.os.Parcel;

/**
 * Created by Ch3D on 21.07.2015.
 */
public class ComputerPlayer extends Player {
	public static final Creator<ComputerPlayer> CREATOR = new Creator<ComputerPlayer>() {
		@Override
		public ComputerPlayer createFromParcel(Parcel in) {
			return new ComputerPlayer(in);
		}

		@Override
		public ComputerPlayer[] newArray(int size) {
			return new ComputerPlayer[size];
		}
	};

	protected ComputerPlayer(Parcel in) {
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
