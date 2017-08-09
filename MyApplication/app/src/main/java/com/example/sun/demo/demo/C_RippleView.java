package com.example.sun.demo.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.example.sun.demo.R;

/**
 * Created by sun on 17/7/29.
 */


public class C_RippleView extends LinearLayout {

    private int WIDTH;
    private int HEIGHT;
    private int frameRate = 10;
    private int rippleDuration = 300;
    private int rippleAlpha = 90;
    private Handler canvasHandler;
    private float radiusMax = 0;
    private boolean animationRunning = false;
    private int timer = 0;
    private int timerEmpty = 0;
    private int durationEmpty = -1;
    private float x = -1;
    private float y = -1;
    private int zoomDuration;
    private float zoomScale;
    private ScaleAnimation scaleAnimation;
    private Boolean hasToZoom;
    private Boolean isCentered;
    private Integer rippleType;
    private Paint paint;
    private Bitmap originBitmap;
    private int rippleColor;
    private int ripplePadding;
    private GestureDetector gestureDetector;
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    private Runnable completeRunnable;

    private OnRippleCompleteListener onCompletionListener;

    public C_RippleView(Context context) {
        super(context);
    }

    public C_RippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public C_RippleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    /**
     * Method that initializes all fields and sets listeners
     *
     * @param context Context used to create this view
     * @param attrs Attribute used to initialize fields
     */
    private void init(final Context context, final AttributeSet attrs) {
        if (isInEditMode())
            return;

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleView);
        rippleColor = typedArray.getColor(R.styleable.RippleView_rv_color, getResources().getColor(R.color.rippelColor));
        rippleType = typedArray.getInt(R.styleable.RippleView_rv_type, 0);
        hasToZoom = typedArray.getBoolean(R.styleable.RippleView_rv_zoom, false);
        isCentered = typedArray.getBoolean(R.styleable.RippleView_rv_centered, false);
        rippleDuration = typedArray.getInteger(R.styleable.RippleView_rv_rippleDuration, rippleDuration);
        frameRate = typedArray.getInteger(R.styleable.RippleView_rv_framerate, frameRate);
        rippleAlpha = typedArray.getInteger(R.styleable.RippleView_rv_alpha, rippleAlpha);
        ripplePadding = typedArray.getDimensionPixelSize(R.styleable.RippleView_rv_ripplePadding, 0);
        canvasHandler = new Handler();
        zoomScale = typedArray.getFloat(R.styleable.RippleView_rv_zoomScale, 1.03f);
        zoomDuration = typedArray.getInt(R.styleable.RippleView_rv_zoomDuration, 200);
        typedArray.recycle();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(rippleColor);
        paint.setAlpha(rippleAlpha);
        this.setWillNotDraw(false);

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent event) {
                super.onLongPress(event);
                animateRipple(event);
                completeRunnable = new Runnable() {
                    @Override
                    public void run() {
                        sendClickEvent(true);
                    }
                };
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        this.setDrawingCacheEnabled(true);
        this.setClickable(true);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (animationRunning) {
            canvas.save();
            if (rippleDuration <= timer * frameRate) {
                animationRunning = false;
                timer = 0;
                durationEmpty = -1;
                timerEmpty = 0;
                // There is problem on Android M where canvas.restore() seems to be called automatically
                // For now, don't call canvas.restore() manually on Android M (API 23)
                if(Build.VERSION.SDK_INT != 23) {
                    canvas.restore();
                }
                invalidate();
                onComplete();
                return;
            } else
                canvasHandler.postDelayed(runnable, frameRate);

            if (timer == 0)
                canvas.save();


            canvas.drawCircle(x, y, (radiusMax * (((float) timer * frameRate) / rippleDuration)), paint);

            paint.setColor(Color.parseColor("#ffff4444"));

            if (rippleType == 1 && originBitmap != null && (((float) timer * frameRate) / rippleDuration) > 0.4f) {
                if (durationEmpty == -1)
                    durationEmpty = rippleDuration - timer * frameRate;

                timerEmpty++;
                final Bitmap tmpBitmap = getCircleBitmap((int) ((radiusMax) * (((float) timerEmpty * frameRate) / (durationEmpty))));
                canvas.drawBitmap(tmpBitmap, 0, 0, paint);
                tmpBitmap.recycle();
            }

            paint.setColor(rippleColor);

            if (rippleType == 1) {
                if ((((float) timer * frameRate) / rippleDuration) > 0.6f)
                    paint.setAlpha((int) (rippleAlpha - ((rippleAlpha) * (((float) timerEmpty * frameRate) / (durationEmpty)))));
                else
                    paint.setAlpha(rippleAlpha);
            }
            else
                paint.setAlpha((int) (rippleAlpha - ((rippleAlpha) * (((float) timer * frameRate) / rippleDuration))));

            timer++;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        WIDTH = w;
        HEIGHT = h;

        scaleAnimation = new ScaleAnimation(1.0f, zoomScale, 1.0f, zoomScale, w / 2, h / 2);
        scaleAnimation.setDuration(zoomDuration);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setRepeatCount(1);
    }

    /**
     * Launch Ripple animation for the current view with a MotionEvent
     *
     * @param event MotionEvent registered by the Ripple gesture listener
     */
    public void animateRipple(MotionEvent event) {
        createAnimation(event.getX(), event.getY());
    }

    /**
     * Launch Ripple animation for the current view centered at x and y position
     *
     * @param x Horizontal position of the ripple center
     * @param y Vertical position of the ripple center
     */
    public void animateRipple(final float x, final float y) {
        createAnimation(x, y);
    }

    /**
     * Create Ripple animation centered at x, y
     *
     * @param x Horizontal position of the ripple center
     * @param y Vertical position of the ripple center
     */
    private void createAnimation(final float x, final float y) {
        if (this.isEnabled() && !animationRunning) {
            if (hasToZoom)
                this.startAnimation(scaleAnimation);

            radiusMax = Math.max(WIDTH, HEIGHT);

            if (rippleType != 2)
                radiusMax /= 2;

            radiusMax -= ripplePadding;

            if (isCentered || rippleType == 1) {
                this.x = getMeasuredWidth() / 2;
                this.y = getMeasuredHeight() / 2;
            } else {
                this.x = x;
                this.y = y;
            }

            animationRunning = true;

            if (rippleType == 1 && originBitmap == null)
                originBitmap = getDrawingCache(true);

            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector != null && gestureDetector.onTouchEvent(event)) {
            animateRipple(event);
            completeRunnable = new Runnable() {
                @Override
                public void run() {
                    sendClickEvent(false);
                }
            };
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        //this.onTouchEvent(event);
        return super.onInterceptTouchEvent(event);
    }

    /**
     * Send a click event if parent view is a Listview instance
     *
     * @param isLongClick Is the event a long click ?
     */
    private void sendClickEvent(final Boolean isLongClick) {
        if(getParent() == null){
            return;
        }
        if (getParent() instanceof C_OptionItemView) {
            final C_OptionItemView optionViewItem = (C_OptionItemView) getParent();
            if (optionViewItem != null) {
                if (!isLongClick) {
                    optionViewItem.onClick(this);
                }else {
                    optionViewItem.onLongClick(this);
                }
            }
        }
    }

    private Bitmap getCircleBitmap(final int radius) {
        final Bitmap output = Bitmap.createBitmap(originBitmap.getWidth(), originBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect((int)(x - radius), (int)(y - radius), (int)(x + radius), (int)(y + radius));

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(x, y, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(originBitmap, rect, rect, paint);

        return output;
    }

    private void onComplete() {
        if(completeRunnable != null){
            completeRunnable.run();
            completeRunnable = null;
        }

        if (onCompletionListener != null) {
            onCompletionListener.onComplete(this);
        }
    }

    /**
     * Defines a callback called at the end of the Ripple effect
     */
    public interface OnRippleCompleteListener {
        void onComplete(C_RippleView rippleView);
    }

}
