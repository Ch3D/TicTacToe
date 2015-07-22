package com.ch3d.tictactoe.game.controller;

import com.ch3d.tictactoe.game.history.GameHistory;
import com.ch3d.tictactoe.game.history.GameStep;
import com.ch3d.tictactoe.game.mark.CellMarkO;
import com.ch3d.tictactoe.game.state.GameState;
import com.ch3d.tictactoe.game.state.GameStateController;
import com.ch3d.tictactoe.view.GameHistoryListener;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class HumanAIGameController extends BasicGameController {

	public static final int WRONG_POSITION = -1;

	private final Random mCornerRandom;

	private final Random mEdgeRandom;

	private int[] corners = {1, 3, 7, 9};

	private int[] edges = {2, 4, 6, 8};

	public HumanAIGameController() {
		super();
		mCornerRandom = new Random();
		mEdgeRandom = new Random();
	}

	@Override
	protected void nextStep(final GameStateController stateController, final GameHistoryListener listener) {
		final int pos = analyze(stateController.getHistory().unmodifiable());

		if(!mStateController.validateStep(pos)) {
			nextStep(stateController, listener);
		}

		listener.onCellMarked(pos, CellMarkO.VALUE);
		final GameState gameState = mStateController.moveO(pos);
		if(gameState == GameState.O_WON) {
			notifyWinner(GameState.O_WON);
		} else if(gameState == GameState.DRAW) {
			notifyDraw();
		}
	}

	private int analyze(final GameHistory history) {
		if(history.size() == 1) {
			if(history.cell(1, 1) == 1) {
				final int cornerIndex = mCornerRandom.nextInt(4);
				final int corner = corners[cornerIndex];
				final GameState gameState = mStateController.moveO(corner);
				if(gameState == GameState.O_WON) {
					notifyWinner(GameState.O_WON);
				} else if(gameState == GameState.DRAW) {
					notifyDraw();
				}
				return corner;
			} else {
				// move center
				return 5;
			}
		}

		// try to win
		final int winPosition = canWin(history);
		if(winPosition != WRONG_POSITION) {
			return winPosition;
		}

		// try to block
		final int blockPosition = canBlock(history);
		if(blockPosition != WRONG_POSITION) {
			return blockPosition;
		}

		// otherwise - find best position
		return mCornerRandom.nextInt(9) + 1;
	}

	private int canBlock(final GameHistory history) {
		return WRONG_POSITION;
	}

	private int canWin(final GameHistory history) {
		final List<GameStep> steps = history.getStepsO();
		Collections.sort(steps, new Comparator<GameStep>() {
			@Override
			public int compare(final GameStep lhs, final GameStep rhs) {
				return lhs.getPosition() > rhs.getPosition() ? 1 : -1;
			}
		});
		return WRONG_POSITION;
	}

	@Override
	protected void processCellClick(final GameHistoryListener listener, final GameState state, final int pos) {
		if(state == GameState.O_MOVE) {
			return;
		}
		super.processCellClick(listener, state, pos);
	}
}
