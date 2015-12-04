package tourplatform.drunkdiary.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import tourplatform.drunkdiary.Activity.ItemActivity;
import tourplatform.drunkdiary.Activity.MainActivity;
import tourplatform.drunkdiary.CalendarAdapter;
import tourplatform.drunkdiary.Font;
import tourplatform.drunkdiary.R;

/**
 * Created by orc12 on 2015-12-05.
 */
public class CalendarFragment extends Fragment {
    public Calendar month;
    public CalendarAdapter adapter;
    public Handler handler;
    public ArrayList<String> items; // container to store some random calendar items
    GridView gridView;

    RelativeLayout actionbar;
    TextView text_year;
    TextView text_month;
    TextView text_title;
    TextView[] text_week;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        assignView();

        Font.GOTHAM_BOOK = Typeface.createFromAsset(getAssets(), "Gotham-Book.otf");
        Font.GOTHAM_LIGHT = Typeface.createFromAsset(getAssets(), "Gotham-Light.otf");
        Font.GOTHAM_MIDIUM = Typeface.createFromAsset(getAssets(), "Gotham-Medium.ttf");
        Font.NOTOSANSCJKKR_THIN = Typeface.createFromAsset(getAssets(), "NotoSansCJKkr-Thin.otf");

        text_title.setTypeface(Font.GOTHAM_MIDIUM);
        text_year.setTypeface(Font.GOTHAM_BOOK);
        text_month.setTypeface(Font.GOTHAM_LIGHT);
        for(TextView aText_week: text_week){
            aText_week.setTypeface(Font.GOTHAM_LIGHT);
        }

        month = Calendar.getInstance();
//        getActivity().onNewIntent(getActivity().getIntent());
        items = new ArrayList<String>();
        adapter = new CalendarAdapter(getActivity(), month);
        gridView.setAdapter(adapter);
        handler = new Handler();
        handler.post(calendarUpdater);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TextView date = (TextView) v.findViewById(R.id.text_day);
                if (date instanceof TextView && !date.getText().equals("")) {

                    Intent intent = new Intent(getActivity(), ItemActivity.class);
                    String day = date.getText().toString();
                    if (day.length() == 1) {
                        day = "0" + day;
                    }
                    // return chosen date as string format
                    Log.i("date", android.text.format.DateFormat.format("yyyy-MM", month) + "-" + day);
                    intent.putExtra("date", android.text.format.DateFormat.format("yyyy-MM", month) + "-" + day);
//                    setResult(RESULT_OK, intent);
//                    finish();
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_in_up, android.R.anim.fade_out);
                }

            }
        });

        return view;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignView();

        Font.GOTHAM_BOOK = Typeface.createFromAsset(getAssets(), "Gotham-Book.otf");
        Font.GOTHAM_LIGHT = Typeface.createFromAsset(getAssets(), "Gotham-Light.otf");
        Font.GOTHAM_MIDIUM = Typeface.createFromAsset(getAssets(), "Gotham-Medium.ttf");
        Font.NOTOSANSCJKKR_THIN = Typeface.createFromAsset(getAssets(), "NotoSansCJKkr-Thin.otf");

        text_title.setTypeface(Font.GOTHAM_MIDIUM);
        text_year.setTypeface(Font.GOTHAM_BOOK);
        text_month.setTypeface(Font.GOTHAM_LIGHT);
        for(TextView aText_week: text_week){
            aText_week.setTypeface(Font.GOTHAM_LIGHT);
        }

        month = Calendar.getInstance();
        onNewIntent(getIntent());
        items = new ArrayList<String>();
        adapter = new CalendarAdapter(this, month);
        gridView.setAdapter(adapter);
        handler = new Handler();
        handler.post(calendarUpdater);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                TextView date = (TextView) v.findViewById(R.id.text_day);
                if (date instanceof TextView && !date.getText().equals("")) {

                    Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                    String day = date.getText().toString();
                    if (day.length() == 1) {
                        day = "0" + day;
                    }
                    // return chosen date as string format
                    Log.i("date", android.text.format.DateFormat.format("yyyy-MM", month) + "-" + day);
                    intent.putExtra("date", android.text.format.DateFormat.format("yyyy-MM", month) + "-" + day);
//                    setResult(RESULT_OK, intent);
//                    finish();
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_up, android.R.anim.fade_out);
                }

            }
        });
    }

    public void refreshCalendar()
    {

        adapter.refreshDays();
        adapter.notifyDataSetChanged();
        handler.post(calendarUpdater); // generate some random calendar items

        String tmpMonth = android.text.format.DateFormat.format("yyyy", month).toString();
        StringBuilder s = new StringBuilder();
        s.append(tmpMonth.substring(0,1));
        s.append(" ");
        s.append(tmpMonth.substring(1,2));
        s.append(" ");
        s.append(tmpMonth.substring(2,3));
        s.append(" ");
        s.append(tmpMonth.substring(3,4));

        text_year.setText(s.toString());
        text_month.setText(android.text.format.DateFormat.format("M", month));
    }

    public Runnable calendarUpdater = new Runnable() {

        @Override
        public void run() {
            items.clear();
            // format random values. You can implement a dedicated class to provide real values
            for(int i=0;i<31;i++) {
                Random r = new Random();

                if(r.nextInt(10)>6)
                {
                    items.add(Integer.toString(i));
                }
            }

            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    };

    public void mOnClick(View view){
        switch (view.getId()){
            case R.id.bt_left:
                if(month.get(Calendar.MONTH)== month.getActualMinimum(Calendar.MONTH)) {
                    month.set((month.get(Calendar.YEAR)-1),month.getActualMaximum(Calendar.MONTH),1);
                } else {
                    month.set(Calendar.MONTH,month.get(Calendar.MONTH)-1);
                }
                refreshCalendar();
                return;
            case R.id.bt_right:
                if(month.get(Calendar.MONTH)== month.getActualMaximum(Calendar.MONTH)) {
                    month.set((month.get(Calendar.YEAR)+1),month.getActualMinimum(Calendar.MONTH),1);
                } else {
                    month.set(Calendar.MONTH,month.get(Calendar.MONTH)+1);
                }
                refreshCalendar();
        }
    }

    private void assignView(){
        text_year= (TextView) findViewById(R.id.text_year);
        text_month = (TextView) findViewById(R.id.text_month);
        gridView = (GridView) findViewById(R.id.grid_calendar);
        actionbar = (RelativeLayout) findViewById(R.id.actionbar);
        text_title = (TextView) findViewById(R.id.actionbar_title);

        text_week = new TextView[7];
        text_week[0] = (TextView) findViewById(R.id.text_week_0);
        text_week[1] = (TextView) findViewById(R.id.text_week_1);
        text_week[2] = (TextView) findViewById(R.id.text_week_2);
        text_week[3] = (TextView) findViewById(R.id.text_week_3);
        text_week[4] = (TextView) findViewById(R.id.text_week_4);
        text_week[5] = (TextView) findViewById(R.id.text_week_5);
        text_week[6] = (TextView) findViewById(R.id.text_week_6);
    }
}
