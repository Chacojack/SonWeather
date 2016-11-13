package jack.me.viewpagerindicatorlibrary;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by zjchai on 2016/11/13.
 */

public interface ITabInjector {

    @NonNull
    View onCreateTabView(ViewPagerIndicator viewPagerIndicator, @IntRange(from = 0) int position);

}
