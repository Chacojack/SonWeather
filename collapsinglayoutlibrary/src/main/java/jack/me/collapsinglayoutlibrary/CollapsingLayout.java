package jack.me.collapsinglayoutlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;

/**
 * Created by zjchai on 2016/11/10.
 */

public class CollapsingLayout extends RelativeLayout {

    private static final String TAG = CollapsingLayout.class.getSimpleName();

    public static final int NONE = 0, PIN = 1, PARALLAX = 2;

    @IntDef({NONE, PIN, PARALLAX})
    public @interface ShrinkMode {

    }

    private int appBarLayoutVerticalOffset = 0;
    private int appBarElevationStart = 0;
    private int appBarElevationEnd = 0;

    OffsetChangedListener offsetChangedListener;

    public CollapsingLayout(Context context) {
        this(context, null);
    }

    public CollapsingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollapsingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CollapsingLayout);
        appBarElevationStart = typedArray.getDimensionPixelSize(R.styleable.CollapsingLayout_app_bar_elevation_start, 0);
        appBarElevationEnd = typedArray.getDimensionPixelSize(R.styleable.CollapsingLayout_app_bar_elevation_end, 0);
        typedArray.recycle();
        afterViews();
    }

    private void afterViews() {

    }

    private void initOffsetChangedListener() {
        offsetChangedListener = new OffsetChangedListener();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            ((AppBarLayout) parent).addOnOffsetChangedListener(offsetChangedListener);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initOffsetChangedListener();
    }

    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected RelativeLayout.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected RelativeLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return new LayoutParams(lp);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {

        int shrinkMode;
        float shrinkParallaxMultiplier;
        float alphaStart;
        float alphaEnd;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray typedArray = c.obtainStyledAttributes(attrs, R.styleable.CollapsingLayout_Layout);
            shrinkMode = typedArray.getInt(R.styleable.CollapsingLayout_Layout_layout_shrinkMode, NONE);
            shrinkParallaxMultiplier = typedArray.getFloat(R.styleable.CollapsingLayout_Layout_layout_shrinkParallaxMultiplier, 0.5f);
            alphaStart = typedArray.getFloat(R.styleable.CollapsingLayout_Layout_alphaStart, 1f);
            alphaEnd = typedArray.getFloat(R.styleable.CollapsingLayout_Layout_alphaEnd, 1f);
            typedArray.recycle();
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams setShrinkMode(@ShrinkMode int shrinkMode) {
            this.shrinkMode = shrinkMode;
            return this;
        }

        public LayoutParams setShrinkParallaxMultiplier(float shrinkParallaxMultiplier) {
            this.shrinkParallaxMultiplier = shrinkParallaxMultiplier;
            return this;
        }

        public LayoutParams setAlphaStart(float alphaStart) {
            this.alphaStart = alphaStart;
            return this;
        }

        public LayoutParams setAlphaEnd(float alphaEnd) {
            this.alphaEnd = alphaEnd;
            return this;
        }
    }


    class OffsetChangedListener implements AppBarLayout.OnOffsetChangedListener {

        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            Log.d(TAG, "onOffsetChanged() called with: appBarLayout = [" + appBarLayout + "], verticalOffset = [" + verticalOffset + "]");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                changeAppBarLayout(appBarLayout, verticalOffset);
            }
            for (int i = 0; i < getChildCount(); i++) {
                View view = getChildAt(i);
                changeChild(view, verticalOffset);
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        private void changeAppBarLayout(AppBarLayout appBarLayout, int verticalOffset) {
            float fraction = calculateFraction(verticalOffset);
            float elevation = calculateElevation(appBarElevationStart, appBarElevationEnd, fraction);
            appBarLayout.setElevation(elevation);
        }

        private float calculateElevation(int appBarElevationStart, int appBarElevationEnd, float fraction) {
            return appBarElevationStart + (appBarElevationEnd - appBarElevationStart) * fraction;
        }

        private void changeChild(View view, int verticalOffset) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            changeChildTranslation(view, layoutParams, verticalOffset);
            changeChildAlpha(view, layoutParams, verticalOffset);
        }

        private void changeChildAlpha(View view, LayoutParams layoutParams, int verticalOffset) {
            float alphaStart = layoutParams.alphaStart;
            float alphaEnd = layoutParams.alphaEnd;
            float fraction = calculateFraction(verticalOffset);
            float alpha = calculateAlpha(alphaStart, alphaEnd, fraction);
            view.setAlpha(alpha);
        }

        private float calculateAlpha(float alphaStart, float alphaEnd, float fraction) {
            return alphaStart + (alphaEnd - alphaStart) * fraction;
        }

        private float calculateFraction(int verticalOffset) {
            int all = getHeight() - getMinimumHeight();
            return Math.abs(verticalOffset) * 1f / all;
        }

        private void changeChildTranslation(View view, LayoutParams layoutParams, int verticalOffset) {
            switch (layoutParams.shrinkMode) {
                case NONE:
                    break;
                case PIN:
                    view.setTranslationY(-verticalOffset);
                    break;
                case PARALLAX:
                    doOnShrinkModeIsParallax(view, layoutParams, verticalOffset);
                    break;
            }
        }

        private void doOnShrinkModeIsParallax(View view, LayoutParams layoutParams, int verticalOffset) {
            float multiplier = layoutParams.shrinkParallaxMultiplier;
            view.setTranslationY(-verticalOffset * multiplier);
        }
    }


}
