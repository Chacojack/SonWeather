package jack.me.fixdrawsizerelativelayoutlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by zjchai on 2016/11/16.
 */

public class FixDrawSizeRelativeLayout extends RelativeLayout {

    private int fixLeft, fixTop, fixRight, fixBottom;
    private boolean fixL = false, fixT = false, fixR = false, fixB = false;

    public FixDrawSizeRelativeLayout(Context context) {
        this(context, null);
    }

    public FixDrawSizeRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FixDrawSizeRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        afterViews();
    }

    private void afterViews() {

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (fixL) setLeft(fixLeft);
        if (fixT) setTop(fixTop);
        if (fixR) setRight(fixRight);
        if (fixB) setBottom(fixBottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public int getFixLeft() {
        return fixLeft;
    }

    public FixDrawSizeRelativeLayout setFixLeft(int fixLeft) {
        this.fixLeft = fixLeft;
        setLeft(fixLeft);
        fixL = true;
        return this;
    }

    public int getFixTop() {
        return fixTop;
    }

    public FixDrawSizeRelativeLayout setFixTop(int fixTop) {
        this.fixTop = fixTop;
        setTop(fixTop);
        fixT = true;
        return this;
    }

    public int getFixRight() {
        return fixRight;
    }

    public FixDrawSizeRelativeLayout setFixRight(int fixRight) {
        this.fixRight = fixRight;
        setRight(fixRight);
        fixR = true;
        return this;
    }

    public int getFixBottom() {
        return fixBottom;
    }

    public FixDrawSizeRelativeLayout setFixBottom(int fixBottom) {
        this.fixBottom = fixBottom;
        setBottom(fixBottom);
        fixB = true;
        return this;
    }

    public boolean isFixL() {
        return fixL;
    }

    public FixDrawSizeRelativeLayout setFixL(boolean fixL) {
        this.fixL = fixL;
        return this;
    }

    public boolean isFixT() {
        return fixT;
    }

    public FixDrawSizeRelativeLayout setFixT(boolean fixT) {
        this.fixT = fixT;
        return this;
    }

    public boolean isFixR() {
        return fixR;
    }

    public FixDrawSizeRelativeLayout setFixR(boolean fixR) {
        this.fixR = fixR;
        return this;
    }

    public boolean isFixB() {
        return fixB;
    }

    public FixDrawSizeRelativeLayout setFixB(boolean fixB) {
        this.fixB = fixB;
        return this;
    }
}
