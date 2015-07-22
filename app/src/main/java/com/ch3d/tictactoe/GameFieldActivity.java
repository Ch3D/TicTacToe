package com.ch3d.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ch3d.tictactoe.game.GameMode;
import com.ch3d.tictactoe.game.controller.GameController;
import com.ch3d.tictactoe.game.controller.GameControllerFactory;
import com.ch3d.tictactoe.game.controller.GameListener;
import com.ch3d.tictactoe.game.history.StepResult;
import com.ch3d.tictactoe.game.state.GameState;
import com.ch3d.tictactoe.utils.EXTRAS;
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

	@Bind(R.id.panel_controls)
	protected View mControlsView;

	protected GameController mGameController;

	private GameMode mMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_field);

		mMode = getIntent().getParcelableExtra(EXTRAS.GAME_MODE);
		mGameController = GameControllerFactory.create(mMode);

		final TicTacToeApplication application = (TicTacToeApplication) getApplicationContext();
		application.inject(this);
		ButterKnife.bind(this);

		mViewGameField.setGameController(mGameController);
		mControlsView.setTranslationY(getResources().getDimensionPixelSize(R.dimen.game_control_height));
		mControlsView.setAlpha(0);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGameController.addGameListener(this);
	}

	@OnClick(R.id.replay)
	protected void onReplay() {
		replay();
	}

	@OnClick(R.id.exit)
	protected void onExit() {
		exit();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mGameController.removeGameListener(this);
	}

	@Override
	public void onGameEndedWinner(final GameState state) {
		final StepResult winCombination = mGameController.getWinCombination();
		mViewGameField.showCombination(winCombination);
		mControlsView.animate().alpha(1f).translationY(0).start();
	}

	private void exit() {
		mGameController.clear();
		finish();
	}

	private void replay() {
		mViewGameField.clear();
		mGameController.replay("");
		mControlsView.animate().alpha(0f).translationY(getResources().getDimensionPixelSize(R.dimen.game_control_height)).start();
	}

	@Override
	public void onGameEndedDraw() {
		mControlsView.animate().alpha(1f).translationY(0).start();
	}
}
