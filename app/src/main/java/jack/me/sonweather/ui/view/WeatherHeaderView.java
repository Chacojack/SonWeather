package jack.me.sonweather.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import jack.me.sonweather.R;

/**
 * Created by zjchai on 2016/11/9.
 */
public class WeatherHeaderView extends RelativeLayout{

    private static final String TAG = WeatherHeaderView.class.getSimpleName();

    public WeatherHeaderView(Context context) {
        this(context, null);
    }

    public WeatherHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeatherHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        afterViews(context);
    }

    private void afterViews(Context context) {
        inflate(context, R.layout.view_weather_header,this);
    }

}
