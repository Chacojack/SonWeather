package jack.me.nativetransitionlayoutlibrary;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.IntRange;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by zjchai on 2016/11/14.
 */

public class NativeTransitionLayout extends LinearLayout {

    public static final String TAG = NativeTransitionLayout.class.getSimpleName();

    public static final int ANIMATOR_DURATION = 500;

    private int screenWidth, screenHeight;
    private OnSelectedAnchorListener onSelectedAnchorListener;
    private OnAnimationListener onAnimationListener;
    private OnAnimationEndListener onAnimationEndListener;
    private OnAnimationStartListener onAnimationStartListener;
    private SupportOffset supportOffset;


    public NativeTransitionLayout(Context context) {
        this(context, null);
    }

    public NativeTransitionLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NativeTransitionLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        afterViews();
    }

    private void afterViews() {
        DisplayMetrics dm = new DisplayMetrics();
        if (getContext() instanceof Activity) {
            ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
            screenWidth = dm.widthPixels; //宽度
            screenHeight = dm.heightPixels; //高度
            Log.d(TAG, "afterViews: getScreenSize width:" + screenWidth + "height:" + screenHeight);
        } else {
            Log.d(TAG, "afterViews: getScreenSize fail. context is not instance of Activity.");
        }
    }

    public void startStretchTransition(View anchor) {
        int index = indexOfChild(anchor);
        if (index >= 0) {
            if (onSelectedAnchorListener != null) {
                onSelectedAnchorListener.onSelectedAnchorListener(anchor, index);
            }
            if (getOrientation() == VERTICAL) {
                doVerticalStretchAnimation(anchor, index);
            } else {

            }
        }
    }

    public void startShrinkTransition(@IntRange(from = 0) final int index) {
        if (getChildCount() <= index) {
            return;
        }
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(0, 1);
        valueAnimator.setDuration(ANIMATOR_DURATION);
        final View anchor = getChildAt(index);
        final int top = anchor.getTop();
        final int bottom = anchor.getBottom();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                doAnimator(1 - fraction, index, top, bottom, anchor);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                reset(anchor, top, bottom, VISIBLE);
                if (onAnimationEndListener != null) {
                    onAnimationEndListener.onAnimationEndListener(anchor, true);
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (onAnimationStartListener != null) {
                    onAnimationStartListener.onAnimationStart(true);
                }
                setAllViewToStretchState(index, top, bottom, anchor);
                setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                reset(anchor, top, bottom, VISIBLE);
                if (onAnimationEndListener != null) {
                    onAnimationEndListener.onAnimationEndListener(anchor, true);
                }
            }
        });
        valueAnimator.start();

    }

    private void setAllViewToStretchState(int index, int top, int bottom, View anchor) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (i < index) {
                view.setTranslationY(-top);
            } else if (i > index) {
                view.setTranslationY(bottom);
            }
        }
        anchor.setTop(0);
        anchor.setBottom(screenHeight);
        anchor.setAlpha(0);
    }


    private void doVerticalStretchAnimation(final View anchor, @IntRange(from = 0) final int index) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(0, 1);
        valueAnimator.setDuration(ANIMATOR_DURATION);
        final int top = anchor.getTop();
        final int bottom = anchor.getBottom();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                doAnimator(fraction, index, top, bottom, anchor);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                reset(anchor, top, bottom, INVISIBLE);
                if (onAnimationEndListener != null) {
                    onAnimationEndListener.onAnimationEndListener(anchor, false);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                reset(anchor, top, bottom, INVISIBLE);
                if (onAnimationEndListener != null) {
                    onAnimationEndListener.onAnimationEndListener(anchor, false);
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (onAnimationStartListener != null) {
                    onAnimationStartListener.onAnimationStart(false);
                }
            }
        });
        valueAnimator.start();
    }

    private void doAnimator(float fraction, @IntRange(from = 0) int index, int top, int bottom, View anchor) {
        int offset = 0;
        if (supportOffset != null) {
            offset = supportOffset.getOffset();
        }
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (i < index) { // 在上方的View，要从上方移出
                view.setTranslationY((offset - top) * fraction);
            } else if (i > index) { // 在下方的View，要从下方移出
                view.setTranslationY((screenHeight + offset - bottom) * fraction);
            }
            anchor.setTop(offset + (int) ((top - offset) * (1 - fraction)));
            anchor.setBottom((int) (bottom + (screenHeight + offset - bottom) * fraction));
            anchor.setAlpha(1 - fraction);
            if (onAnimationListener != null) {
                onAnimationListener.onAnimationListener(anchor);
            }
        }
    }

    private void resetAllView(View anchor, int top, int bottom) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            view.setTranslationY(0);
        }
        anchor.setTop(top);
        anchor.setBottom(bottom);
        anchor.setAlpha(1);
    }

    private void reset(View anchor, int top, int bottom, int visibility) {
        setVisibility(visibility);
        resetAllView(anchor, top, bottom);
    }

    public NativeTransitionLayout setOnSelectedAnchorListener(OnSelectedAnchorListener onSelectedAnchorListener) {
        this.onSelectedAnchorListener = onSelectedAnchorListener;
        return this;
    }

    public NativeTransitionLayout setOnAnimationListener(OnAnimationListener onAnimationListener) {
        this.onAnimationListener = onAnimationListener;
        return this;
    }

    public NativeTransitionLayout setOnAnimationEndListener(OnAnimationEndListener onAnimationEndListener) {
        this.onAnimationEndListener = onAnimationEndListener;
        return this;
    }

    public NativeTransitionLayout setOnAnimationStartListener(OnAnimationStartListener onAnimationStartListener) {
        this.onAnimationStartListener = onAnimationStartListener;
        return this;
    }

    public NativeTransitionLayout setSupportOffset(SupportOffset supportOffset) {
        this.supportOffset = supportOffset;
        return this;
    }
}
