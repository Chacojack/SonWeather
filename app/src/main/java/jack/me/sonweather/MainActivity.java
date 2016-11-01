package jack.me.sonweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import jack.me.sonweather.net.INetHandler;
import jack.me.sonweather.net.NetHandler;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    INetHandler netHandler;

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        afterInject();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        afterViews();


    }

    private void inject() {
        netHandler = NetHandler.getInstance();
    }

    private void afterInject() {

    }

    private void afterViews() {
        button.setOnClickListener(v -> netHandler.getCityList().subscribe(city -> Log.d(TAG, "afterViews: city:"+city)));
    }


}
