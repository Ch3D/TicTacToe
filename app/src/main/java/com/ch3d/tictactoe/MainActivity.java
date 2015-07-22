package com.ch3d.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ch3d.tictactoe.game.GameMode;
import com.ch3d.tictactoe.game.player.Player;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

	@OnClick(R.id.btn_human_human)
	protected void onHumanVHuman() {
		finish();
		GameFieldActivity.start(this, GameMode.create(Player.create(true), Player.create(true)));
	}

	@OnClick(R.id.btn_human_ai)
	protected void onHumanVAi() {
		finish();
		GameFieldActivity.start(this, GameMode.create(Player.create(true), Player.create(false)));
	}

	@OnClick(R.id.btn_ai_human)
	protected void onAiVHuman() {
		finish();
		GameFieldActivity.start(this, GameMode.create(Player.create(false), Player.create(true)));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
	}
}
