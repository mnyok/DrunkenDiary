package tourplatform.drunkdiary;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends Activity {
    public Calendar month;
    public CalendarAdapter adapter;
    public Handler handler;
    public ArrayList<String> items; // container to store some random calendar items
    GridView gridView;




    RelativeLayout actionbar;
    TextView text_year;
    TextView text_month;
    TextView text_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //noinspection ResourceType
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.actionbar);

        text_year= (TextView) findViewById(R.id.text_year);
        text_month = (TextView) findViewById(R.id.text_month);
        gridView = (GridView) findViewById(R.id.grid_calendar);
        actionbar = (RelativeLayout) findViewById(R.id.actionbar);
        text_title = (TextView) findViewById(R.id.actionbar_title);

        Font.GOTHAM_BOOK = Typeface.createFromAsset(getAssets(), "Gotham-Book.otf");
        Font.GOTHAM_LIGHT = Typeface.createFromAsset(getAssets(), "Gotham-Light.otf");
        Font.NOTOSANSCJKKR_THIN = Typeface.createFromAsset(getAssets(), "NotoSansCJKkr-Thin.otf");

        text_title.setTypeface(Font.GOTHAM_LIGHT);
        text_year.setTypeface(Font.GOTHAM_BOOK);
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
                    Log.i("date",  android.text.format.DateFormat.format("yyyy-MM", month) + "-" + day);
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
}
