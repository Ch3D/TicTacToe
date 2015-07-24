package com.ch3d.tictactoe.game.controller;

import com.ch3d.tictactoe.game.CellScore;
import com.ch3d.tictactoe.game.MinMaxStrategy;
import com.ch3d.tictactoe.game.history.GameCell;
import com.ch3d.tictactoe.game.history.GameHistory;
import com.ch3d.tictactoe.game.history.GameStepO;
import com.ch3d.tictactoe.game.history.GameStepX;
import com.ch3d.tictactoe.game.mark.CellMarkO;
import com.ch3d.tictactoe.game.state.GameState;
import com.ch3d.tictactoe.game.state.GameStateController;
import com.ch3d.tictactoe.view.GameHistoryListener;

import java.util.Random;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class HumanAIGameController extends BasicGameController {

	private final Random mCornerRandom;

	private int[] corners = {1, 3, 7, 9};

	public HumanAIGameController(GameHistoryListener listener) {
		super(listener);
		mCornerRandom = new Random();
	}

	@Override
	protected void nextStep(final GameStateController stateController, final GameHistoryListener listener) {
		final int pos = analyze(stateController.getHistory().unmodifiable());

		if(pos == WRONG_POSITION) {
			return;
		}

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
		// if this is a first step - occupy center or corner
		if(history.size() == 1) {
			if(history.cell(1, 1) == GameStepX.VALUE) {
				final int cornerIndex = mCornerRandom.nextInt(4);
				return corners[cornerIndex];
			} else {
				return 5;
			}
		}

		// find best move
		final MinMaxStrategy minMaxStrategy = new MinMaxStrategy(history.getBoardMatrix());
		minMaxStrategy.callMinimax(0, GameStepO.VALUE);
		for(CellScore cs : minMaxStrategy.getRootsChildrenScores()) {
			System.out.println("Point: " + cs.getPoint() + " Score: " + cs.getScore());
		}
		final GameCell point = minMaxStrategy.returnBestMoveO();
		if(point == null) {
			return WRONG_POSITION;
		}
		System.err.println("point = " + point);
		minMaxStrategy.placeAMove(point, GameStepO.VALUE);
		final int pos = (point.getColumn() * history.getBoardSize()) + (point.getRow() + 1);
		System.out.println("pos: " + pos);
		return pos;
	}

	@Override
	protected void processCellClick(final GameHistoryListener listener, final GameState state, final int pos) {
		if(state == GameState.O_MOVE) {
			return;
		}
		super.processCellClick(listener, state, pos);
	}

	@Override
	public void startGame() {

	}
}
