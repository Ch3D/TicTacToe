package com.ch3d.tictactoe.game.controller.ai;

import com.ch3d.tictactoe.game.MinMaxStrategy;
import com.ch3d.tictactoe.game.board.GameCell;
import com.ch3d.tictactoe.game.history.GameHistory;
import com.ch3d.tictactoe.game.history.step.GameStepO;
import com.ch3d.tictactoe.game.history.step.GameStepX;
import com.ch3d.tictactoe.game.mark.CellMark;
import com.ch3d.tictactoe.game.mark.CellMarkO;
import com.ch3d.tictactoe.game.state.GameState;
import com.ch3d.tictactoe.utils.Utils;
import com.ch3d.tictactoe.view.GameHistoryListener;

import java.util.Random;

/**
 * Created by Ch3D on 22.07.2015.
 */
public class HumanAIGameController extends AIGameController {

	private final Random mCornerRandom;

	private int[] corners = {1, 3, 7, 9};

	public HumanAIGameController(GameHistoryListener listener) {
		super(listener);
		mCornerRandom = new Random();
	}

	@Override
	protected void placeMove(final int pos) {
		placeMoveO(pos);
	}

	@Override
	protected int analyze(final GameHistory history) {
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
		final GameCell point = minMaxStrategy.returnBestMoveO();
		if(point == null) {
			return WRONG_POSITION;
		}
		minMaxStrategy.placeAMove(point, GameStepO.VALUE);
		return Utils.getCellPosition(point, history.getBoardSize());
	}

	@Override
	protected CellMark getMark() {
		return CellMarkO.VALUE;
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
		// do nothing
	}
}
