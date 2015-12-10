package tourplatform.drunkdiary.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import org.w3c.dom.Text;

import tourplatform.drunkdiary.Font;
import tourplatform.drunkdiary.Fragment.CalendarFragment;
import tourplatform.drunkdiary.Fragment.DiaryFragment;
import tourplatform.drunkdiary.R;

/**
 * Created by orc12 on 2015-12-05.
 */
public class ItemActivity extends Activity {

    TextView text_title;
    FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        assignView();


        text_title.setTypeface(Font.GOTHAM_MIDIUM);
    }
//
//    private void init(){
//
//
//        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
//        mTabHost.setup(this, getSupportFragmentManager(), R.id.realcontent);
//
//        TabHost.TabSpec tabSpec = mTabHost.newTabSpec("tab1");
//        ImageView btn = new ImageView(this);
//        btn.setClickable(true);
//        btn.setImageResource(R.drawable.ic_calendar_xml);
//        btn.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        tabSpec.setIndicator(btn);
//        Bundle b = new Bundle();
//        b.putString("name", "calendar");
//        mTabHost.addTab(tabSpec, CalendarFragment.class, b);
//
//        tabSpec = mTabHost.newTabSpec("tab2");
//        btn = new ImageView(this);
//        btn.setClickable(true);
//        btn.setImageResource(R.drawable.ic_diary_xml);
//        btn.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        tabSpec.setIndicator(btn);
//        b = new Bundle();
//        b.putString("name", "diary");
//        mTabHost.addTab(tabSpec, DiaryFragment.class, b);
//
//        tabSpec = mTabHost.newTabSpec("tab3");
//        btn = new ImageView(this);
//        btn.setClickable(true);
//        btn.setImageResource(R.drawable.ic_stats_xml);
//        btn.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        tabSpec.setIndicator(btn);
//        b = new Bundle();
//        b.putString("name", "report");
//        mTabHost.addTab(tabSpec, CalendarFragment.class, b);
//    }


    private void assignView(){
        text_title = (TextView) findViewById(R.id.actionbar_title);
    }
}