package com.ch3d.tictactoe.fragment;

import android.app.Fragment;
import android.os.Bundle;

import com.ch3d.tictactoe.game.controller.GameController;
import com.ch3d.tictactoe.game.state.GameState;

/**
 * Created by Ch3D on 24.07.2015.
 */
public class GameRetainedFragment extends Fragment {
	public static final String TAG = GameRetainedFragment.class.getSimpleName();

	private GameController data;

	private GameState mCurrentState;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	public GameController getData() {
		return data;
	}

	public void setData(GameController data) {
		this.data = data;
	}

	public GameState getCurrentState() {
		return mCurrentState;
	}

	public void setCurrentState(final GameState currentState) {
		mCurrentState = currentState;
	}
}
