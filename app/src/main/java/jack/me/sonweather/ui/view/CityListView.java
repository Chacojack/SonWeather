package jack.me.sonweather.ui.view;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.util.Pools;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jack.me.fixdrawsizerelativelayoutlibrary.FixDrawSizeRelativeLayout;
import jack.me.nativetransitionlayoutlibrary.NativeTransitionLayout;
import jack.me.sonweather.R;
import jack.me.sonweather.model.ActualWeather;
import jack.me.sonweather.utils.LogUtils;

/**
 * Created by zjchai on 2016/11/16.
 */

public class CityListView extends NativeTransitionLayout {

    private int layoutRes;
    private ViewPager viewPager;
    private FixDrawSizeRelativeLayout target;
    private Pools.Pool<View> viewPool = new Pools.SimplePool<>(10);
    private List<ActualWeather> actualWeathers = new ArrayList<>();
    private OnBindDataListener onBindDataListener;


    public CityListView(Context context) {
        this(context, null);
    }

    public CityListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CityListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOrientation(VERTICAL);
    }

    /**
     * @param layoutRes
     * @param viewPager
     * @param target
     * @return
     */
    public CityListView init(int layoutRes, @NonNull ViewPager viewPager, @NonNull FixDrawSizeRelativeLayout target) {
        this.layoutRes = layoutRes;
        this.viewPager = viewPager;
        this.target = target;
        setOnSelectedAnchorListener((anchor, position) -> {
            viewPager.setCurrentItem(position, false);
            LogUtils.dFormat(TAG, "afterViews : on Selected position :%d, anchor height:%d,container height:%d", position, anchor.getHeight(), target.getHeight());
        });
        setOnAnimationListener(anchor -> {
            LogUtils.dFormat(TAG, "afterViews : container height:%d ", target.getHeight());
            target.setFixTop(anchor.getTop());
            target.setFixBottom(anchor.getBottom());
        });
        setOnAnimationEndListener((anchor, isShrink) -> {
            LogUtils.dFormat(TAG, "afterViews : on animator end isShrink :%s", isShrink);
            if (isShrink) {
                target.setFixTop(anchor.getTop());
                target.setFixBottom(anchor.getBottom());
            } else {
                ViewGroup.LayoutParams layoutParams = target.getLayoutParams();
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                target.requestLayout();
            }
        });
        return this;
    }


    public void notifyRefreshAllList() {
        recyclerAllView();
        for (int i = 0; i < actualWeathers.size(); i++) {
            View view = viewPool.acquire();
            if (view == null) {
                view = inflate(getContext(), layoutRes, null);
                view.setOnClickListener(this::startStretchTransition);
            }
            view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.dimen_80)));
            if (onBindDataListener != null) {
                onBindDataListener.onBind(view, actualWeathers.get(i), i);
            }
            addView(view);
        }
    }

    private void recyclerAllView() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            viewPool.release(view);
        }
        removeAllViews();
    }

    public void addData(@NonNull ActualWeather actualWeather) {
        actualWeathers.add(actualWeather);
    }

    public void addAllData(@NonNull List<ActualWeather> actualWeathers) {
        this.actualWeathers.addAll(actualWeathers);
    }

    public void addAllData(@NonNull ActualWeather[] actualWeathers) {
        this.actualWeathers.addAll(Arrays.asList(actualWeathers));
    }

    public void updateData(@NonNull ActualWeather actualWeather, @IntRange(from = 0) int position) {
        if (actualWeathers.size() <= position) {
            throw new IllegalArgumentException(String.format("position is cannot > actualWeathers.size().now size is %d,position is %d", actualWeathers.size(), position));
        }
        actualWeathers.remove(position);
        actualWeathers.add(position, actualWeather);
    }

    public CityListView setOnBindDataListener(OnBindDataListener onBindDataListener) {
        this.onBindDataListener = onBindDataListener;
        return this;
    }

    public interface OnBindDataListener {

        void onBind(View view, ActualWeather actualWeather, int position);

    }


}
