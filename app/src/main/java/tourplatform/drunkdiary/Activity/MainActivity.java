package tourplatform.drunkdiary.Activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import tourplatform.drunkdiary.Font;
import tourplatform.drunkdiary.Fragment.CalendarFragment;
import tourplatform.drunkdiary.R;

public class MainActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;

    RelativeLayout home;

    RelativeLayout actionbar;
    TextView text_title;
    ImageButton bt_calendar;
    ImageButton bt_diary;
    ImageButton bt_stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignView();

        Font.GOTHAM_BOOK = Typeface.createFromAsset(getAssets(), "Gotham-Book.otf");
        Font.GOTHAM_LIGHT = Typeface.createFromAsset(getAssets(), "Gotham-Light.otf");
        Font.GOTHAM_MIDIUM = Typeface.createFromAsset(getAssets(), "Gotham-Medium.ttf");
        Font.NOTOSANSCJKKR_THIN = Typeface.createFromAsset(getAssets(), "NotoSansCJKkr-Thin.otf");

        text_title.setTypeface(Font.GOTHAM_MIDIUM);

        init();


    }

    private void init(){


        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realcontent);

        TabHost.TabSpec tabSpec = mTabHost.newTabSpec("tab1");


        TabWidget tabWidget = (TabWidget) findViewById(R.id.tabs);
        tabWidget.removeAllViews();
        mTabHost.removeView(bt_calendar);
        mTabHost.removeView(bt_diary);
        mTabHost.removeView(bt_stats);

        tabSpec.setIndicator(bt_calendar);
        Bundle b = new Bundle();
        b.putString("name", "calendar");
        mTabHost.addTab(tabSpec, CalendarFragment.class, b);

        tabSpec = mTabHost.newTabSpec("tab2");
        tabSpec.setIndicator(bt_diary);
        b = new Bundle();
        b.putString("name", "diary");
        mTabHost.addTab(tabSpec, CalendarFragment.class, b);

        tabSpec = mTabHost.newTabSpec("tab3");
        tabSpec.setIndicator(bt_stats);
        b = new Bundle();
        b.putString("name", "report");
        mTabHost.addTab(tabSpec, CalendarFragment.class, b);
    }

    private void assignView(){
        actionbar = (RelativeLayout) findViewById(R.id.actionbar);
        text_title = (TextView) findViewById(R.id.actionbar_title);
        bt_calendar = (ImageButton) findViewById(R.id.bt_calendar);
        bt_diary = (ImageButton) findViewById(R.id.bt_diary);
        bt_stats = (ImageButton) findViewById(R.id.bt_stats);

    }
}
