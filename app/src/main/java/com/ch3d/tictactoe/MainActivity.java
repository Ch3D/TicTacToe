package com.ch3d.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GameFieldActivity.start(this);
		finish();

		//setContentView(R.layout.activity_main);
	}
}
