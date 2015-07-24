package com.ch3d.tictactoe.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.ch3d.tictactoe.GameHistoryListener;
import com.ch3d.tictactoe.R;
import com.ch3d.tictactoe.TicTacToeApplication;
import com.ch3d.tictactoe.game.controller.GameController;
import com.ch3d.tictactoe.game.history.StepResult;
import com.ch3d.tictactoe.game.mark.CellMark;
import com.ch3d.tictactoe.game.mark.CellMarkX;
import com.ch3d.tictactoe.utils.Utils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Ch3D on 21.07.2015.
 */
public class GameFieldView extends LinearLayout implements View.OnClickListener, GameHistoryListener {

	public static final float CELL_SCALE_FACTOR = 1.1f;

	public static final int CELL_BG_WIN = Color.argb(77, 255, 0, 0);

	public static final float SCALE_FACTOR_DEFAULT = 1.0f;

	public static final int ANIMATION_DELAY_MARK = 150;

	public static final int ANIMATION_DELAY_WIN = 500;

	private static final String TAG = GameFieldView.class.getSimpleName();

	protected GameController mGameController;

	@Bind(R.id.cell_1)
	protected ImageButton btnCell1;

	@Bind(R.id.cell_2)
	protected ImageButton btnCell2;

	@Bind(R.id.cell_3)
	protected ImageButton btnCell3;

	@Bind(R.id.cell_4)
	protected ImageButton btnCell4;

	@Bind(R.id.cell_5)
	protected ImageButton btnCell5;

	@Bind(R.id.cell_6)
	protected ImageButton btnCell6;

	@Bind(R.id.cell_7)
	protected ImageButton btnCell7;

	@Bind(R.id.cell_8)
	protected ImageButton btnCell8;

	@Bind(R.id.cell_9)
	protected ImageButton btnCell9;

	@Bind(value = {R.id.cell_1, R.id.cell_2, R.id.cell_3,
			R.id.cell_4, R.id.cell_5, R.id.cell_6,
			R.id.cell_7, R.id.cell_8, R.id.cell_9})
	protected List<ImageButton> buttons;

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

		for(final View button : buttons) {
			button.setOnClickListener(this);
		}
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
		final ImageButton view = (ImageButton) getViewForPosition(pos);
		if(view != null) {
			view.setImageResource(mark == CellMarkX.VALUE ? R.drawable.mark_x : R.drawable.mark_o);
			view.setScaleX(Utils.ALPHA_TRANSPARENT);
			view.setScaleY(Utils.ALPHA_TRANSPARENT);
			view.animate()
			    .scaleX(Utils.ALPHA_VISIBLE)
			    .scaleY(Utils.ALPHA_VISIBLE)
			    .setStartDelay(ANIMATION_DELAY_MARK)
			    .start();
		}
	}

	private View getViewForPosition(final int pos) {
		return findViewWithTag(String.valueOf(pos));
	}

	public void clear() {
		for(final ImageButton button : buttons) {
			clearView(button);
		}
	}

	private void clearView(final ImageButton view) {
		view.animate()
		    .scaleX(Utils.ALPHA_TRANSPARENT)
		    .scaleY(Utils.ALPHA_TRANSPARENT)
		    .alpha(Utils.ALPHA_TRANSPARENT)
		    .withEndAction(new Runnable() {
			    @Override
			    public void run() {
				    view.animate()
				        .scaleX(SCALE_FACTOR_DEFAULT)
				        .scaleY(SCALE_FACTOR_DEFAULT)
				        .alpha(Utils.ALPHA_VISIBLE)
				        .start();
				    view.setBackgroundColor(Color.TRANSPARENT);
				    view.setImageDrawable(null);
			    }
		    }).start();
	}

	public void showCombination(final StepResult winCombination) {
		if(winCombination == StepResult.NULL) {
			// skip null combination
			return;
		}
		postDelayed(new Runnable() {
			@Override
			public void run() {
				for(final int i : winCombination.getPositions()) {
					final View viewWithTag = findViewWithTag(Integer.toString(i));
					viewWithTag.animate().scaleX(CELL_SCALE_FACTOR).scaleY(CELL_SCALE_FACTOR).start();
					viewWithTag.setBackgroundColor(CELL_BG_WIN);
				}
			}
		}, ANIMATION_DELAY_WIN);
	}

	public void setGameController(final GameController gameController) {
		mGameController = gameController;
	}

	public void prepare(final GameController gameController) {
		setGameController(gameController);
		gameController.prepare(this);
	}
}
