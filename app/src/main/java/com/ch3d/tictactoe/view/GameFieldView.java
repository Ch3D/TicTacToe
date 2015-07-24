package com.ch3d.tictactoe.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ch3d.tictactoe.R;
import com.ch3d.tictactoe.TicTacToeApplication;
import com.ch3d.tictactoe.game.controller.GameController;
import com.ch3d.tictactoe.game.history.StepResult;
import com.ch3d.tictactoe.game.mark.CellMark;
import com.ch3d.tictactoe.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ch3D on 21.07.2015.
 */
public class GameFieldView extends LinearLayout implements View.OnClickListener, GameHistoryListener {

	public static final float CELL_SCALE_FACTOR = 1.1f;

	public static final int CELL_BG_COLOR = Color.argb(77, 255, 0, 0);

	public static final float SCALE_FACTOR_DEFAULT = 1.0f;

	private static final String TAG = GameFieldView.class.getSimpleName();

	protected GameController mGameController;

	@Bind(R.id.cell_1)
	protected Button btnCell1;

	@Bind(R.id.cell_2)
	protected Button btnCell2;

	@Bind(R.id.cell_3)
	protected Button btnCell3;

	@Bind(R.id.cell_4)
	protected Button btnCell4;

	@Bind(R.id.cell_5)
	protected Button btnCell5;

	@Bind(R.id.cell_6)
	protected Button btnCell6;

	@Bind(R.id.cell_7)
	protected Button btnCell7;

	@Bind(R.id.cell_8)
	protected Button btnCell8;

	@Bind(R.id.cell_9)
	protected Button btnCell9;

	public GameFieldView(final Context context) {
		super(context);
		init();
	}

	public GameFieldView(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public GameFieldView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		final TicTacToeApplication application = (TicTacToeApplication) getContext().getApplicationContext();
		application.inject(this);

		setOrientation(VERTICAL);
		LayoutInflater.from(getContext()).inflate(R.layout.view_game_field, this, true);
		ButterKnife.bind(this);

		clear();

		btnCell1.setOnClickListener(this);
		btnCell2.setOnClickListener(this);
		btnCell3.setOnClickListener(this);
		btnCell4.setOnClickListener(this);
		btnCell5.setOnClickListener(this);
		btnCell6.setOnClickListener(this);
		btnCell7.setOnClickListener(this);
		btnCell8.setOnClickListener(this);
		btnCell9.setOnClickListener(this);
	}

	@Override
	public void onClick(final View v) {
		if(mGameController.getState().isGameFinished()) {
			// fail fast
			return;
		}
		mGameController.onCellClick((String) v.getTag(), this);
	}

	@Override
	public void onCellMarked(final int pos, final CellMark mark) {
		final Button view = (Button) getViewForPosition(pos);
		if(view != null) {
			view.setText(mark.getType());
		}
	}

	private View getViewForPosition(final int pos) {
		return findViewWithTag(String.valueOf(pos));
	}

	public void clear() {
		clearView(btnCell1);
		clearView(btnCell2);
		clearView(btnCell3);
		clearView(btnCell4);
		clearView(btnCell5);
		clearView(btnCell6);
		clearView(btnCell7);
		clearView(btnCell8);
		clearView(btnCell9);
	}

	private void clearView(final Button view) {
		view.setText(Utils.EMPTY_STRING);
		view.animate().scaleX(SCALE_FACTOR_DEFAULT).scaleY(SCALE_FACTOR_DEFAULT).start();
		view.setBackgroundColor(Color.TRANSPARENT);
	}

	public void showCombination(final StepResult winCombination) {
		if(winCombination == StepResult.NULL) {
			// skip null combination
			return;
		}
		Log.d(TAG, "showCombination = " + winCombination);
		for(final int i : winCombination.getPositions()) {
			Log.d(TAG, "findViewWithTag = " + i);
			final View viewWithTag = findViewWithTag(Integer.toString(i));
			viewWithTag.animate().scaleX(CELL_SCALE_FACTOR).scaleY(CELL_SCALE_FACTOR).start();
			viewWithTag.setBackgroundColor(CELL_BG_COLOR);
		}
	}

	public void setGameController(final GameController gameController) {
		mGameController = gameController;
	}

	public void prepare(final GameController gameController) {
		setGameController(gameController);
		gameController.prepare(this);
	}
}
