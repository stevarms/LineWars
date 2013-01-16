package com.SS.linewars;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class gameView extends View {

    public enum State {
        UNKNOWN(-3),
        WIN(-2),
        EMPTY(0),
        PLAYER1(1),
        PLAYER2(2);

        private int mValue;

        private State(int value) {
            mValue = value;
        }

        public int getValue() {
            return mValue;
        }

        public static State fromInt(int i) {
            for (State s : values()) {
                if (s.getValue() == i) {
                    return s;
                }
            }
            return EMPTY;
        }
    }
    
    private static final int MARGIN = 4;
    
    private final Rect mSrcRect = new Rect();
    private final Rect mDstRect = new Rect();
	
    private int mSxy;
    private int mOffetX;
    private int mOffetY;
    private Paint mWinPaint;
    private Paint mLinePaint;
    private Paint mBmpPaint;
    private Bitmap mBmpPlayer1;
    private Bitmap mBmpPlayer2;
	private Drawable mDrawableBg;
	
	private ICellListener mCellListener;
	
    /** Contains one of {@link State#EMPTY}, {@link State#PLAYER1} or {@link State#PLAYER2}. */
    private final State[] mData = new State[9];

    private int mSelectedCell = -1;
    private State mSelectedValue = State.EMPTY;
    private State mCurrentPlayer = State.UNKNOWN;
    private State mWinner = State.EMPTY;

    private int mWinCol = -1;
    private int mWinRow = -1;
    private int mWinDiag = -1;

    private boolean mBlinkDisplayOff;
    private final Rect mBlinkRect = new Rect();
	
    public interface ICellListener {
        abstract void onCellSelected();
    }
    
	public gameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        requestFocus();

        //this is the x and the o from tic tac toe probably removeable
        //mDrawableBg = getResources().getDrawable(R.drawable.lib_bg);
        //setBackgroundDrawable(mDrawableBg);

        //mBmpPlayer1 = getResBitmap(R.drawable.lib_cross);
        //mBmpPlayer2 = getResBitmap(R.drawable.lib_circle);

        if (mBmpPlayer1 != null) {
            mSrcRect.set(0, 0, mBmpPlayer1.getWidth() -1, mBmpPlayer1.getHeight() - 1);
        }

        mBmpPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mLinePaint = new Paint();
        mLinePaint.setColor(0xFFFFFFFF);
        mLinePaint.setStrokeWidth(5);
        mLinePaint.setStyle(Style.STROKE);

        mWinPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWinPaint.setColor(0xFFFF0000);
        mWinPaint.setStrokeWidth(10);
        mWinPaint.setStyle(Style.STROKE);

        for (int i = 0; i < mData.length; i++) {
            mData[i] = State.EMPTY;
        }

        if (isInEditMode()) {
            // In edit mode (e.g. in the Eclipse ADT graphical layout editor)
            // we'll use some random data to display the state.
            Random rnd = new Random();
            for (int i = 0; i < mData.length; i++) {
                mData[i] = State.fromInt(rnd.nextInt(3));
            }
        }
    }
	

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int sxy = mSxy;
        int s3  = sxy * 3;
        int x7 = mOffetX;
        int y7 = mOffetY;

        
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Keep the view squared
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int d = w == 0 ? h : h == 0 ? w : w < h ? w : h;
        setMeasuredDimension(d, d);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int sx = (w - 2 * MARGIN) / 3;
        int sy = (h - 2 * MARGIN) / 3;

        int size = sx < sy ? sx : sy;

        mSxy = size;
        mOffetX = (w - 3 * size) / 2;
        mOffetY = (h - 3 * size) / 2;

        mDstRect.set(MARGIN, MARGIN, size - MARGIN, size - MARGIN);
    }
    
    

}
