package com.ch3d.tictactoe;

import android.app.Application;

import com.ch3d.tictactoe.module.AppModule;
import com.ch3d.tictactoe.module.GameModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Ch3D on 21.07.2015.
 */
public class TicTacToeApplication extends Application {
	private ObjectGraph objectGraph;

	protected List<Object> getModules() {
		return Arrays.asList(new AppModule(), new GameModule(this));
	}

	@Override
	public void onCreate() {
		super.onCreate();
		objectGraph = ObjectGraph.create(getModules().toArray());
		objectGraph.inject(this);
	}

	public void inject(Object o) {
		objectGraph.inject(o);
	}
}
