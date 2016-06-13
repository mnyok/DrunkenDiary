package ms.drunkdiary.Activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import ms.drunkdiary.Alcohol;
import ms.drunkdiary.DBHelper;
import ms.drunkdiary.Font;
import ms.drunkdiary.Fragment.CalendarFragment;
import ms.drunkdiary.Fragment.DiaryFragment;
import ms.drunkdiary.Fragment.StatsFragment;
import ms.drunkdiary.R;

public class MainActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;
    public static DBHelper dbHelper;


    RelativeLayout actionbar;
    TextView text_title;
    public ImageView bt_calendar;
    public ImageView bt_diary;
    public ImageView bt_stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);

        assignView();

        Font.GOTHAM_BOOK = Typeface.createFromAsset(getAssets(), "Gotham-Book.otf");
        Font.GOTHAM_LIGHT = Typeface.createFromAsset(getAssets(), "Gotham-Light.otf");
        Font.GOTHAM_MIDIUM = Typeface.createFromAsset(getAssets(), "Gotham-Medium.ttf");
        Font.NOTOSANSCJKKR_THIN = Typeface.createFromAsset(getAssets(), "NotoSansCJKkr-Thin.otf");
        Font.NOTOSANSCJKKR_LIGHT = Typeface.createFromAsset(getAssets(), "NotoSansCJKkr-Light.otf");
        text_title.setTypeface(Font.GOTHAM_MIDIUM);


        initTabHost();
    }

    private void initTabHost(){
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realcontent);

        bt_calendar = addTab(mTabHost, 1, CalendarFragment.class, R.drawable.ic_calendar_xml);
        bt_diary = addTab(mTabHost, 2, DiaryFragment.class, R.drawable.ic_diary_xml);
        bt_stats = addTab(mTabHost, 3, StatsFragment.class, R.drawable.ic_stats_xml);
    }

    private ImageView addTab(FragmentTabHost tabHost, int position, Class fragment, int imageID){
        TabHost.TabSpec tabSpec = mTabHost.newTabSpec("tab" + position);

        ImageView imageView = new ImageView(this);
        imageView.setClickable(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageResource(imageID);

        String name = CalendarFragment.class.getSimpleName().replace("Fragment", "").toLowerCase();
        Bundle b = new Bundle();
        b.putString("name", name);

        tabSpec.setIndicator(imageView);
        tabHost.addTab(tabSpec, fragment, b);

        return imageView;
    }

    private void assignView(){
        actionbar = (RelativeLayout) findViewById(R.id.actionbar);
        text_title = (TextView) findViewById(R.id.actionbar_title);

    }
}
