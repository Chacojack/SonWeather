package jack.me.showweatherviewlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by zjchai on 2016/11/17.
 */

public class ShowWeatherView extends ImageView {

    public ShowWeatherView(Context context) {
        this(context, null);
    }

    public ShowWeatherView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShowWeatherView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        afterViews();
    }

    private void afterViews() {
        setImageDrawable(new SunDrawable(getContext()));

    }

}
