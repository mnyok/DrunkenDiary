package tourplatform.drunkdiary.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import tourplatform.drunkdiary.Font;
import tourplatform.drunkdiary.Fragment.CalendarFragment;
import tourplatform.drunkdiary.Fragment.DiaryFragment;
import tourplatform.drunkdiary.Fragment.StatsFragment;
import tourplatform.drunkdiary.R;

public class MainActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;


    RelativeLayout actionbar;
    TextView text_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignView();

        Font.GOTHAM_BOOK = Typeface.createFromAsset(getAssets(), "Gotham-Book.otf");
        Font.GOTHAM_LIGHT = Typeface.createFromAsset(getAssets(), "Gotham-Light.otf");
        Font.GOTHAM_MIDIUM = Typeface.createFromAsset(getAssets(), "Gotham-Medium.ttf");
        Font.NOTOSANSCJKKR_THIN = Typeface.createFromAsset(getAssets(), "NotoSansCJKkr-Thin.otf");
        Font.NOTOSANSCJKKR_LIGHT = Typeface.createFromAsset(getAssets(), "NotoSansCJKkr-Light.otf");

        text_title.setTypeface(Font.GOTHAM_MIDIUM);

        init();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){


        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realcontent);

        TabHost.TabSpec tabSpec = mTabHost.newTabSpec("tab1");
        ImageView btn = new ImageView(this);
        btn.setClickable(true);
        btn.setImageResource(R.drawable.ic_calendar_xml);
        btn.setScaleType(ImageView.ScaleType.FIT_CENTER);
        tabSpec.setIndicator(btn);
        Bundle b = new Bundle();
        b.putString("name", "calendar");
        mTabHost.addTab(tabSpec, CalendarFragment.class, b);

        tabSpec = mTabHost.newTabSpec("tab2");
        btn = new ImageView(this);
        btn.setClickable(true);
        btn.setImageResource(R.drawable.ic_diary_xml);
        btn.setScaleType(ImageView.ScaleType.FIT_CENTER);
        tabSpec.setIndicator(btn);
        b = new Bundle();
        b.putString("name", "diary");
        mTabHost.addTab(tabSpec, DiaryFragment.class, b);

        tabSpec = mTabHost.newTabSpec("tab3");
        btn = new ImageView(this);
        btn.setClickable(true);
        btn.setImageResource(R.drawable.ic_stats_xml);
        btn.setScaleType(ImageView.ScaleType.FIT_CENTER);
        tabSpec.setIndicator(btn);
        b = new Bundle();
        b.putString("name", "stats");
        mTabHost.addTab(tabSpec, StatsFragment.class, b);
    }

    private void assignView(){
        actionbar = (RelativeLayout) findViewById(R.id.actionbar);
        text_title = (TextView) findViewById(R.id.actionbar_title);

    }
}
