package com.ch3d.tictactoe.game.controller.ai;

import android.os.AsyncTask;
import android.os.SystemClock;

import com.ch3d.tictactoe.GameHistoryListener;
import com.ch3d.tictactoe.game.controller.BasicGameController;
import com.ch3d.tictactoe.game.history.GameHistory;
import com.ch3d.tictactoe.game.mark.CellMark;
import com.ch3d.tictactoe.game.state.GameStateController;

/**
 * Created by Ch3D on 24.07.2015.
 */
public abstract class AIGameController extends BasicGameController {

	public static final int DEFAULT_AI_DELAY = 500;

	private class FakeDelayAsyncTask extends AsyncTask<Integer, Void, Void> {
		private final int mPos;

		private final GameHistoryListener mListener;

		public FakeDelayAsyncTask(int pos, GameHistoryListener listener) {
			mPos = pos;
			mListener = listener;
		}

		@Override
		protected Void doInBackground(final Integer... params) {
			SystemClock.sleep(DEFAULT_AI_DELAY);
			return null;
		}

		@Override
		protected void onPostExecute(final Void aVoid) {
			mListener.onCellMarked(mPos, getMark());
			placeMove(mPos);
		}
	}

	public AIGameController(final GameHistoryListener listener) {
		super(listener);
	}

	protected abstract int analyze(final GameHistory history);

	protected boolean checkPosition(int pos) {
		return pos != WRONG_POSITION;
	}

	@Override
	protected void nextMove(final GameStateController stateController, final GameHistoryListener listener) {
		final int pos = analyze(stateController.getHistory().unmodifiable());
		if(!checkPosition(pos)) {
			return;
		}
		if(!mStateController.validateStep(pos)) {
			nextMove(stateController, listener);
		}
		new FakeDelayAsyncTask(pos, mListener).execute();
	}

	protected abstract CellMark getMark();

	protected abstract void placeMove(final int pos);
}
