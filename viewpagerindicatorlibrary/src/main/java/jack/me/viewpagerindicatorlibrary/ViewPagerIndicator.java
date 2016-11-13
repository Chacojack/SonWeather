package jack.me.viewpagerindicatorlibrary;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjchai on 2016/11/13.
 */

public class ViewPagerIndicator extends LinearLayout implements ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {

    ViewPager viewPager;
    List<View> viewList = new ArrayList<>();

    ITabInjector tabInjector;
    ViewPagerDateSetObserver dateSetObserver;
    int currentSelected = 0;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        afterViews();
    }

    public void setViewPager(ViewPager vp) {
        onViewPagerChange();
        resetViewPager();
        viewPager = vp;
        initViewPager();
    }

    private void onViewPagerChange() {
        resetIndicator();
    }

    private void resetIndicator() {
        removeAllViews();
        viewList.clear();
        currentSelected = 0;
    }

    private void initViewPager() {
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(this);
            viewPager.addOnAdapterChangeListener(this);
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                adapter.registerDataSetObserver(dateSetObserver);
                int count = adapter.getCount();
                initTabs(count);
            }
        }
    }

    private void resetViewPager() {
        if (viewPager != null) {
            viewPager.removeOnPageChangeListener(this);
            viewPager.removeOnAdapterChangeListener(this);
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                adapter.unregisterDataSetObserver(dateSetObserver);
            }
        }
    }

    private void initTabs(int childCount) {
        for (int i = 0; i < childCount; i++) {
            if (tabInjector != null) {
                View view = tabInjector.onCreateTabView(i);
                view.setSelected(false);
                viewList.add(view);
                addView(view);
            } else {
                View view = inflate(getContext(), R.layout.default_tab, null);
                view.setSelected(false);
                viewList.add(view);
                int viewSize = getResources().getDimensionPixelSize(R.dimen.dimen_8);
                LayoutParams layoutParams = new LayoutParams(viewSize, viewSize);
                int marginSize = getResources().getDimensionPixelSize(R.dimen.dimen_2);
                layoutParams.setMargins(marginSize, marginSize, marginSize, marginSize);
                view.setLayoutParams(layoutParams);
                addView(view);
            }
        }
        selected(currentSelected);
    }

    private void selected(@IntRange(from = 0) int position) {
        if (viewList.size() > position) {
            if (viewList.size() > currentSelected) {
                viewList.get(currentSelected).setSelected(false);
            }
            viewList.get(position).setSelected(true);
            currentSelected = position;
        }
    }


    private void afterViews() {
        dateSetObserver = new ViewPagerDateSetObserver();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        selected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onAdapterChanged(@NonNull ViewPager viewPager, @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {
        resetIndicator();
        if (oldAdapter != null) {
            oldAdapter.unregisterDataSetObserver(dateSetObserver);
        }
        if (newAdapter != null) {
            newAdapter.registerDataSetObserver(dateSetObserver);
            int count = newAdapter.getCount();
            initTabs(count);
        }
    }

    class ViewPagerDateSetObserver extends DataSetObserver {

        @Override
        public void onChanged() {
            super.onChanged();
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter != null) {
                int count = adapter.getCount();
                removeAllViews();
                initTabs(count);
            }
        }

    }


}
