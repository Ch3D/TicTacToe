package com.ch3d.tictactoe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ch3d.tictactoe.R;
import com.ch3d.tictactoe.TicTacToeApplication;
import com.ch3d.tictactoe.game.controller.GameController;
import com.ch3d.tictactoe.game.mark.CellMark;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ch3D on 21.07.2015.
 */
public class GameFieldView extends LinearLayout implements View.OnClickListener, GameHistoryListener {

	public static final String EMPTY_STRING = "";

	@Inject
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
		mGameController.onCellClick((String) v.getTag(), this);
	}

	@Override
	public void onCellMarked(final int pos, final CellMark mark) {
		final Button view = (Button) getViewForPosition(pos);
		view.setText(mark.getType());
	}

	private View getViewForPosition(final int pos) {
		return findViewWithTag(String.valueOf(pos));
	}

	public void clear() {
		btnCell1.setText(EMPTY_STRING);
		btnCell2.setText(EMPTY_STRING);
		btnCell3.setText(EMPTY_STRING);
		btnCell4.setText(EMPTY_STRING);
		btnCell5.setText(EMPTY_STRING);
		btnCell6.setText(EMPTY_STRING);
		btnCell7.setText(EMPTY_STRING);
		btnCell8.setText(EMPTY_STRING);
		btnCell9.setText(EMPTY_STRING);
	}
}
