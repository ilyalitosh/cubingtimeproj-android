package com.litosh.ilya.cubingtimeproj.timeractivity.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.litosh.ilya.cubingtimeproj.R;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.Time;
import com.litosh.ilya.cubingtimeproj.timeractivity.models.TimerThread;

/**
 * Timer
 * Кастомный таймер-вью с слушателем
 * прикосновений
 *
 * @author Ilya Litosh
 */
public class Timer extends View implements View.OnTouchListener {

    private Context mContext;
    private OnTouchTimerListener mOnTouchTimerListener;
    private TimerThread mThread;
    @Dimension
    private Float mTextSize;
    @ColorInt
    private int mTextColor;

    public Timer(Context context) {
        super(context);
        mContext = context;
        initializePaint();
        setOnTouchListener(this);
    }

    public Timer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        setOnTouchListener(this);
        mTextSize = context.obtainStyledAttributes(attrs, R.styleable.Timer)
                .getDimension(R.styleable.Timer_timerTextSize, 14f);
        mTextColor = context.obtainStyledAttributes(attrs, R.styleable.Timer)
                .getColor(R.styleable.Timer_timerTextColor, Color.BLACK);
        initializePaint();
    }

    public Timer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        setOnTouchListener(this);
        mTextSize = context.obtainStyledAttributes(attrs, R.styleable.Timer)
                .getDimension(R.styleable.Timer_timerTextSize, 14f);
        mTextColor = context.obtainStyledAttributes(attrs, R.styleable.Timer)
                .getColor(R.styleable.Timer_timerTextColor, Color.BLACK);
        initializePaint();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Timer(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        setOnTouchListener(this);
        mTextSize = context.obtainStyledAttributes(attrs, R.styleable.Timer)
                .getDimension(R.styleable.Timer_timerTextSize, 14f);
        mTextColor = context.obtainStyledAttributes(attrs, R.styleable.Timer)
                .getColor(R.styleable.Timer_timerTextColor, Color.BLACK);
        initializePaint();
    }

    private boolean isStarted;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (isStarted) {
                    stop();
                } else {
                    start();
                }
                mTextPaint.setColor(mTextColor);
                Timer.this.invalidate();
                return true;
            case MotionEvent.ACTION_DOWN:
                if (!isStarted) {
                    mTextPaint.setColor(Color.argb(255, 43, 196, 75));
                    Timer.this.invalidate();
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                return false;
        }

        return false;
    }

    private String mText = "00:00:00.000";
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(
                mText,
                (getWidth() / 2) - (mTextPaint.measureText(mText) / 2),
                (getHeight() / 2),
                mTextPaint);
    }

    public void setOnTouchTimerListener(OnTouchTimerListener mOnTouchTimerListener) {
        this.mOnTouchTimerListener = mOnTouchTimerListener;
    }


    private Paint mTextPaint;
    private Rect mTextRect;
    private void initializePaint() {
        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);

        mTextRect = new Rect();
    }

    private Time mTime;
    private void start() {
        mThread = new TimerThread(time -> {
            mTime = time;
            mText = time.toString();
            Timer.this.post(new Runnable() {
                @Override
                public void run() {
                    Timer.this.invalidate();
                }
            });
        });
        mThread.start();
        isStarted = true;
    }

    private void stop() {
        mThread.stopThread();
        isStarted = false;
        mOnTouchTimerListener.onDetach(mTime);
    }

    /**
     * OnTouchTimerListener
     *
     * @author Ilya Litosh
     */
    public interface OnTouchTimerListener {

        /**
         * Вызывается при прикосновении к таймеру
         *
         */
        void onTouch();

        /**
         * Вызывается при отпускании пальца от таймера.
         * Если Time - NULL, то прикосновение было для старта
         * таймера, в другом случае для остановки и вернется время с таймера.
         *
         * @param time время таймера
         */
        void onDetach(Time time);

    }

}
