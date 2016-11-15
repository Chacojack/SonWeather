package jack.me.sonweather.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

/**
 * Created by zjchai on 2016/11/15.
 */

public class BackgroundDrawable extends Drawable {

    Drawable drawable;
    int color;

    public BackgroundDrawable(Drawable drawable, int color) {
        this.drawable = drawable;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        drawable.draw(canvas);
        canvas.drawColor(color);
    }

    @Override
    public void setAlpha(int alpha) {
        drawable.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        drawable.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return drawable.getOpacity();
    }

    @Override
    public int getIntrinsicWidth() {
        return drawable.getIntrinsicWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return drawable.getIntrinsicHeight();
    }

    @Override
    public int getMinimumWidth() {
        return drawable.getMinimumWidth();
    }

    @Override
    public int getMinimumHeight() {
        return drawable.getMinimumHeight();
    }


}
