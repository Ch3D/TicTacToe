package com.ch3d.tictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.ch3d.tictactoe.game.controller.GameController;
import com.ch3d.tictactoe.game.controller.GameListener;
import com.ch3d.tictactoe.game.state.GameState;
import com.ch3d.tictactoe.view.GameFieldView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameFieldActivity extends AppCompatActivity implements GameListener {

	public static void start(final Context context) {
		context.startActivity(new Intent(context, GameFieldActivity.class));
	}

	@Bind(R.id.view_game_field)
	protected GameFieldView mViewGameField;

	@Inject
	protected GameController mGameController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_field);
		final TicTacToeApplication application = (TicTacToeApplication) getApplicationContext();
		application.inject(this);
		ButterKnife.bind(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGameController.addGameListener(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mGameController.removeGameListener(this);
	}

	@Override
	public void onGameEndedWinner(final GameState state) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Game over");
		builder.setMessage("Someone won! Do you want to play again?");
		builder.setCancelable(false);
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				replay();
			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				exit();
			}
		});
		builder.show();
	}

	private void exit() {
		mGameController.clear();
		finish();
	}

	private void replay() {
		mViewGameField.clear();
		mGameController.replay("");
	}

	@Override
	public void onGameEndedDraw() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Game over");
		builder.setMessage("No one won! Do you want to play again?");
		builder.setCancelable(false);
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				replay();
			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(final DialogInterface dialog, final int which) {
				exit();
			}
		});
		builder.show();
	}
}
