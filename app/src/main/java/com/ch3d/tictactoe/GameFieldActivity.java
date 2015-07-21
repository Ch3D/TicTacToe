package com.ch3d.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class GameFieldActivity extends AppCompatActivity {

	public static void start(final Context context) {
		context.startActivity(new Intent(context, GameFieldActivity.class));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_field);
	}
}
