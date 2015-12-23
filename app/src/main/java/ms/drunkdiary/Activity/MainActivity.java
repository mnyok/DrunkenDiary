package ms.drunkdiary.Activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

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

        init();


    }



    private void init(){


        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realcontent);

        TabHost.TabSpec tabSpec = mTabHost.newTabSpec("tab1");
        bt_calendar = new ImageView(this);
        bt_calendar.setClickable(true);
        bt_calendar.setImageResource(R.drawable.ic_calendar_xml);
        bt_calendar.setScaleType(ImageView.ScaleType.FIT_CENTER);
        tabSpec.setIndicator(bt_calendar);
        Bundle b = new Bundle();
        b.putString("name", "calendar");
        mTabHost.addTab(tabSpec, CalendarFragment.class, b);

        tabSpec = mTabHost.newTabSpec("tab2");
        bt_diary = new ImageView(this);
        bt_diary.setClickable(true);
        bt_diary.setImageResource(R.drawable.ic_diary_xml);
        bt_diary.setScaleType(ImageView.ScaleType.FIT_CENTER);
        tabSpec.setIndicator(bt_diary);
        b = new Bundle();
        b.putString("name", "diary");
        mTabHost.addTab(tabSpec, DiaryFragment.class, b);

        tabSpec = mTabHost.newTabSpec("tab3");
        bt_stats = new ImageView(this);
        bt_stats.setClickable(true);
        bt_stats.setImageResource(R.drawable.ic_stats_xml);
        bt_stats.setScaleType(ImageView.ScaleType.FIT_CENTER);
        tabSpec.setIndicator(bt_stats);
        b = new Bundle();
        b.putString("name", "stats");
        mTabHost.addTab(tabSpec, StatsFragment.class, b);
    }

    private void assignView(){
        actionbar = (RelativeLayout) findViewById(R.id.actionbar);
        text_title = (TextView) findViewById(R.id.actionbar_title);

    }
}
