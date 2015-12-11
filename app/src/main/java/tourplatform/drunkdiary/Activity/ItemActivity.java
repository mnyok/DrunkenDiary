package tourplatform.drunkdiary.Activity;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

    ImageButton bt_back;
    TextView text_title;
    TextView text_drink_condition;
    TextView text_note;
    EditText et_note;
    FragmentTabHost mTabHost;

    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        assignView();


        bt_back.setVisibility(View.VISIBLE);
        text_drink_condition.setTypeface(Font.GOTHAM_MIDIUM);
        text_note.setTypeface(Font.GOTHAM_MIDIUM);
        text_title.setTypeface(Font.GOTHAM_MIDIUM);
        et_note.setTypeface(Font.NOTOSANSCJKKR_THIN);

        date = getIntent().getExtras().getString("date");
        String dayOfWeek = getIntent().getExtras().getString("dayOfWeek");
        text_title.setText(date + " " + dayOfWeek);
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


    @Override
    public void onBackPressed() {
        bt_back.performClick();
    }

    public void mOnClick(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finish();
                overridePendingTransition(android.R.anim.fade_in, R.anim.slide_out_down);
                break;
        }
    }

    private void assignView() {
        bt_back = (ImageButton) findViewById(R.id.bt_back);
        text_title = (TextView) findViewById(R.id.actionbar_title);
        text_drink_condition = (TextView) findViewById(R.id.text_drink_condition);
        text_note = (TextView) findViewById(R.id.text_note);
        et_note = (EditText) findViewById(R.id.et_note);
    }

}