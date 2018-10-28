package com.litosh.ilya.cubingtimeproj.timeractivity.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ilya.litosh.RubiksCubeGraph;
import com.litosh.ilya.cubingtimeproj.R;

/**
 * ScrambleGraph
 * Компонента представляющая собой
 * отрисованный скрамбл
 *
 * @author Ilya Litosh
 */
public class ScrambleGraph extends View {

    private RubiksCubeGraph mRubiksCubeGraph;

    public ScrambleGraph(Context context) {
        super(context);
        initializePaints();
    }

    public ScrambleGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializePaints();
    }

    public ScrambleGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializePaints();
    }

    public void setRubiksCubeGraph(RubiksCubeGraph mRubiksCubeGraph) {
        this.mRubiksCubeGraph = mRubiksCubeGraph;
        this.invalidate();
    }

    private Paint mRedPaint;
    private Paint mGreenPaint;
    private Paint mBluePaint;
    private Paint mWhitePaint;
    private Paint mYellowPaint;
    private Paint mOrangePaint;
    private Paint mBorderPaint;
    private static final int COLOR_ORANGE = Color.argb(255, 255, 128, 0);
    private void initializePaints() {
        mRedPaint = new Paint();
        mRedPaint.setColor(Color.RED);
        mGreenPaint = new Paint();
        mGreenPaint.setColor(Color.GREEN);
        mBluePaint = new Paint();
        mBluePaint.setColor(Color.BLUE);
        mWhitePaint = new Paint();
        mWhitePaint.setColor(Color.WHITE);
        mYellowPaint = new Paint();
        mYellowPaint.setColor(Color.YELLOW);
        mOrangePaint = new Paint();
        mOrangePaint.setColor(COLOR_ORANGE);
        mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setColor(getResources().getColor(R.color.colorMain));
        mBorderPaint.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRubiksCubeGraph != null) {
            computeData();

            drawUp(canvas);
            drawDown(canvas);
            drawLeft(canvas);
            drawFront(canvas);
            drawRight(canvas);
            drawBack(canvas);
        }

    }

    private void drawBorder(Canvas canvas, int x, int y, int x1, int y1) {
        canvas.drawRect(x, y, x1, y1, mBorderPaint);
    }

    private int mSideSize;
    private int mTileWidth;
    private int mTileHeight;
    private void computeData() {
        mSideSize = mRubiksCubeGraph.getUp().get(0).size();
        mTileHeight = getHeight() / 9;
        mTileWidth = getWidth() / 12;
    }

    private void drawUp(Canvas canvas) {
        int startX = mSideSize * mTileWidth;
        int startY = 0;
        for (int i = 0; i < mRubiksCubeGraph.getUp().size(); i++) {
            for (int j = 0; j < mRubiksCubeGraph.getUp().get(0).size(); j++) {
                drawBorder(canvas, startX, startY, startX + mTileWidth, startY + mTileHeight);
                canvas.drawRect(
                        startX,
                        startY,
                        startX + mTileWidth,
                        startY + mTileHeight,
                        getNeededPaint(mRubiksCubeGraph.getUp().get(i).get(j)));
                startX += mTileWidth;
            }
            startX = mSideSize * mTileWidth;
            startY += mTileHeight;
        }
    }

    private void drawDown(Canvas canvas) {
        int startX = mSideSize * mTileWidth;
        int startY = mSideSize * mTileHeight * 2;
        for (int i = 0; i < mRubiksCubeGraph.getDown().size(); i++) {
            for (int j = 0; j < mRubiksCubeGraph.getDown().get(0).size(); j++) {
                drawBorder(canvas, startX, startY, startX + mTileWidth, startY + mTileHeight);
                canvas.drawRect(
                        startX,
                        startY,
                        startX + mTileWidth,
                        startY + mTileHeight,
                        getNeededPaint(mRubiksCubeGraph.getDown().get(i).get(j)));
                startX += mTileWidth;
            }
            startX = mSideSize * mTileWidth;
            startY += mTileHeight;
        }
    }

    private void drawLeft(Canvas canvas) {
        int startX = 0;
        int startY = mSideSize * mTileHeight;
        for (int i = 0; i < mRubiksCubeGraph.getLeft().size(); i++) {
            for (int j = 0; j < mRubiksCubeGraph.getLeft().get(0).size(); j++) {
                drawBorder(canvas, startX, startY, startX + mTileWidth, startY + mTileHeight);
                canvas.drawRect(
                        startX,
                        startY,
                        startX + mTileWidth,
                        startY + mTileHeight,
                        getNeededPaint(mRubiksCubeGraph.getLeft().get(i).get(j)));
                startX += mTileWidth;
            }
            startX = 0;
            startY += mTileHeight;
        }
    }

    private void drawFront(Canvas canvas) {
        int startX = mSideSize * mTileWidth;
        int startY = mSideSize * mTileHeight;
        for (int i = 0; i < mRubiksCubeGraph.getFront().size(); i++) {
            for (int j = 0; j < mRubiksCubeGraph.getFront().get(0).size(); j++) {
                drawBorder(canvas, startX, startY, startX + mTileWidth, startY + mTileHeight);
                canvas.drawRect(
                        startX,
                        startY,
                        startX + mTileWidth,
                        startY + mTileHeight,
                        getNeededPaint(mRubiksCubeGraph.getFront().get(i).get(j)));
                startX += mTileWidth;
            }
            startX = mSideSize * mTileWidth;
            startY += mTileHeight;
        }
    }

    private void drawRight(Canvas canvas) {
        int startX = mSideSize * mTileWidth * 2;
        int startY = mSideSize * mTileHeight;
        for (int i = 0; i < mRubiksCubeGraph.getRight().size(); i++) {
            for (int j = 0; j < mRubiksCubeGraph.getRight().get(0).size(); j++) {
                drawBorder(canvas, startX, startY, startX + mTileWidth, startY + mTileHeight);
                canvas.drawRect(
                        startX,
                        startY,
                        startX + mTileWidth,
                        startY + mTileHeight,
                        getNeededPaint(mRubiksCubeGraph.getRight().get(i).get(j)));
                startX += mTileWidth;
            }
            startX = mSideSize * mTileWidth * 2;
            startY += mTileHeight;
        }
    }

    private void drawBack(Canvas canvas) {
        int startX = mSideSize * mTileWidth * 3;
        int startY = mSideSize * mTileHeight;
        for (int i = 0; i < mRubiksCubeGraph.getBack().size(); i++) {
            for (int j = 0; j < mRubiksCubeGraph.getBack().get(0).size(); j++) {
                drawBorder(canvas, startX, startY, startX + mTileWidth, startY + mTileHeight);
                canvas.drawRect(
                        startX,
                        startY,
                        startX + mTileWidth,
                        startY + mTileHeight,
                        getNeededPaint(mRubiksCubeGraph.getBack().get(i).get(j)));
                startX += mTileWidth;
            }
            startX = mSideSize * mTileWidth * 3;
            startY += mTileHeight;
        }
    }

    private Paint getNeededPaint(String colorString) {
        if (colorString.equals("R")) {
            return mRedPaint;
        } else if (colorString.equals("G")) {
            return mGreenPaint;
        } else if (colorString.equals("B")) {
            return mBluePaint;
        } else if (colorString.equals("W")) {
            return mWhitePaint;
        } else if (colorString.equals("Y")) {
            return mYellowPaint;
        } else {
            return mOrangePaint;
        }
    }

}
