package com.ch3d.tictactoe.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ch3d.tictactoe.R;
import com.ch3d.tictactoe.TicTacToeApplication;
import com.ch3d.tictactoe.fragment.GameRetainedFragment;
import com.ch3d.tictactoe.game.GameListener;
import com.ch3d.tictactoe.game.GameMode;
import com.ch3d.tictactoe.game.controller.GameController;
import com.ch3d.tictactoe.game.controller.GameControllerFactory;
import com.ch3d.tictactoe.game.history.StepResult;
import com.ch3d.tictactoe.game.state.GameState;
import com.ch3d.tictactoe.utils.EXTRAS;
import com.ch3d.tictactoe.utils.Utils;
import com.ch3d.tictactoe.view.GameFieldView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameFieldActivity extends AppCompatActivity implements GameListener {

	public static void start(final Context context, final GameMode mode) {
		final Intent intent = new Intent(context, GameFieldActivity.class);
		intent.putExtra(EXTRAS.GAME_MODE, mode);
		context.startActivity(intent);
	}

	@Bind(R.id.view_game_field)
	protected GameFieldView mViewGameField;

	@Bind(R.id.btn_replay)
	protected View mControlsView;

	protected GameController mGameController;

	private GameMode mMode;

	private GameRetainedFragment dataFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_field);

		mMode = getIntent().getParcelableExtra(EXTRAS.GAME_MODE);

		final TicTacToeApplication application = (TicTacToeApplication) getApplicationContext();
		application.inject(this);
		ButterKnife.bind(this);

		mControlsView.setTranslationY(getResources().getDimensionPixelSize(R.dimen.game_control_height));
		mControlsView.setAlpha(0);
		initRetainData();

	}

	private void initRetainData() {
		dataFragment = (GameRetainedFragment) getFragmentManager().findFragmentByTag(GameRetainedFragment.TAG);
		if(dataFragment == null) { // clear start
			dataFragment = new GameRetainedFragment();
			getFragmentManager().beginTransaction().add(dataFragment, GameRetainedFragment.TAG).commit();
			mGameController = GameControllerFactory.create(mMode, mViewGameField);
			mViewGameField.setGameController(mGameController);
			dataFragment.setData(mGameController);
			mGameController.startGame();
		} else { // retain data after screen rotation
			mGameController = dataFragment.getData();
			final GameState state = mGameController.getState();
			if(state != null) {
				if(state.isGameFinished()) {
					animateGameOver();
					if(state.hasWinner()) {
						displayWinCombination();
					}
				}
			}

			mViewGameField.prepare(mGameController);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGameController.addGameListener(this);
	}

	@OnClick(R.id.btn_replay)
	protected void onReplay() {
		mViewGameField.clear();
		mGameController.replay(Utils.EMPTY_STRING);
		mControlsView.animate().alpha(Utils.ALPHA_TRANSPARENT)
		             .translationY(getResources().getDimensionPixelSize(
				             R.dimen.game_control_height))
		             .start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mGameController.removeGameListener(this);
	}

	@Override
	public void onGameEndedWinner(final GameState state) {
		displayWinCombination();
		animateGameOver();
		dataFragment.setCurrentState(state);
	}

	private void displayWinCombination() {
		final StepResult winCombination = mGameController.getWinCombination();
		mViewGameField.showCombination(winCombination);
	}

	@Override
	public void onGameEndedDraw() {
		animateGameOver();
		dataFragment.setCurrentState(GameState.DRAW);
	}

	private void animateGameOver() {
		mControlsView.animate().alpha(Utils.ALPHA_VISIBLE).translationY(0).start();
	}
}
