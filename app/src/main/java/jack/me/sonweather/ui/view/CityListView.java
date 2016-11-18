package jack.me.sonweather.ui.view;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.util.Pools;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jack.me.fixdrawsizerelativelayoutlibrary.FixDrawSizeRelativeLayout;
import jack.me.nativetransitionlayoutlibrary.NativeTransitionLayout;
import jack.me.nativetransitionlayoutlibrary.OnAnimationStartListener;
import jack.me.nativetransitionlayoutlibrary.SupportOffset;
import jack.me.sonweather.R;
import jack.me.sonweather.model.ActualWeather;
import jack.me.sonweather.utils.LogUtils;

/**
 * Created by zjchai on 2016/11/16.
 */

public class CityListView extends ScrollView {

    private static final String TAG = CityListView.class.getSimpleName();

    private int layoutRes;
    private ViewPager viewPager;
    private FixDrawSizeRelativeLayout target;
    private Pools.Pool<View> viewPool = new Pools.SimplePool<>(10);
    private List<ActualWeather> actualWeathers = new ArrayList<>();
    private OnBindDataListener onBindDataListener;
    private NativeTransitionLayout nativeTransitionLayout;

    public CityListView(Context context) {
        this(context, null);
    }

    public CityListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CityListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        nativeTransitionLayout = new NativeTransitionLayout(context);
        nativeTransitionLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        nativeTransitionLayout.setOrientation(LinearLayout.VERTICAL);
        addView(nativeTransitionLayout);
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
        nativeTransitionLayout.setOnSelectedAnchorListener((anchor, position) -> {
            viewPager.setCurrentItem(position, false);
            LogUtils.dFormat(TAG, "init : on Selected position :%d, anchor height:%d,container height:%d", position, anchor.getHeight(), target.getHeight());
        });
        nativeTransitionLayout.setOnAnimationListener(anchor -> {
            target.setFixTop(anchor.getTop() - getScrollY());
            target.setFixBottom(anchor.getBottom() - getScrollY());
        });
        nativeTransitionLayout.setOnAnimationEndListener((anchor, isShrink) -> {
            LogUtils.dFormat(TAG, "init : on animator end isShrink :%s", isShrink);
            if (isShrink) {
                target.setFixTop(anchor.getTop() - getScrollY());
                target.setFixBottom(anchor.getBottom() - getScrollY());
            } else {
                ViewGroup.LayoutParams layoutParams = target.getLayoutParams();
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                target.requestLayout();
                setVisibility(INVISIBLE);
            }
        });
        nativeTransitionLayout.setOnAnimationStartListener(isShrink -> {
            LogUtils.dFormat(TAG, "init : on animation start is shrink :%s", isShrink);
            if (isShrink) {
                setVisibility(VISIBLE);
            }
        });
        nativeTransitionLayout.setSupportOffset(this::getScrollY);
        return this;
    }


    public void notifyRefreshAllList() {
        recyclerAllView();
        for (int i = 0; i < actualWeathers.size(); i++) {
            View view = viewPool.acquire();
            if (view == null) {
                view = inflate(getContext(), layoutRes, null);
                view.setOnClickListener(v -> nativeTransitionLayout.startStretchTransition(v));
            }
            view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.dimen_80)));
            if (onBindDataListener != null) {
                onBindDataListener.onBind(view, actualWeathers.get(i), i);
            }
            nativeTransitionLayout.addView(view);
        }
    }

    private void recyclerAllView() {
        for (int i = 0; i < nativeTransitionLayout.getChildCount(); i++) {
            View view = nativeTransitionLayout.getChildAt(i);
            viewPool.release(view);
        }
        nativeTransitionLayout.removeAllViews();
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

    public void startShrinkTransition(int currentItem) {
        nativeTransitionLayout.startShrinkTransition(currentItem);
    }

    public interface OnBindDataListener {

        void onBind(View view, ActualWeather actualWeather, int position);

    }


}
