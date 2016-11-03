package jack.me.sonweather.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by zjchai on 2016/11/3.
 */

public class AwesomeFontView extends TextView {

    public AwesomeFontView(Context context) {
        this(context, null);
    }

    public AwesomeFontView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AwesomeFontView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        afterViews();
    }

    private void afterViews() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"fontawesome-webfont.ttf");
        setTypeface(typeface);
        setIncludeFontPadding(false);
    }
}
