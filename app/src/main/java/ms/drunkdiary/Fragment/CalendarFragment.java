package ms.drunkdiary.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ms.drunkdiary.Activity.ItemActivity;
import ms.drunkdiary.Activity.MainActivity;
import ms.drunkdiary.CalendarAdapter;
import ms.drunkdiary.DiaryData;
import ms.drunkdiary.Font;
import ms.drunkdiary.R;

/**
 * Created by orc12 on 2015-12-05.
 */
public class CalendarFragment extends Fragment {
    public Calendar month;
    public CalendarAdapter adapter;
    public Handler handler;
    public ArrayList<DiaryData> diaryList; // container to show calendar icons(alcohols)
    GridView gridView;

    TextView text_title;
    TextView text_year;
    TextView text_month;
    TextView[] text_week;

    ImageButton bt_left;
    ImageButton bt_right;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        assignView(view);

        text_title.setText("CALENDAR");
        text_year.setTypeface(Font.GOTHAM_BOOK);
        text_month.setTypeface(Font.GOTHAM_LIGHT);
        bt_left.setOnClickListener(onClickListener);
        bt_right.setOnClickListener(onClickListener);


        for (TextView aText_week : text_week) {
            aText_week.setTypeface(Font.GOTHAM_LIGHT);
        }

        month = Calendar.getInstance();
        diaryList = new ArrayList<>();
        adapter = new CalendarAdapter(getActivity(), month);
        gridView.setAdapter(adapter);
        handler = new Handler();
        handler.post(calendarUpdater);
        text_month.setText(android.text.format.DateFormat.format("M", month));

        gridView.setOnItemClickListener(onItemClickListener);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("ActivityResult data", data.getStringExtra("clicked"));

        if (requestCode == ItemActivity.CODE) {
            switch (data.getStringExtra("clicked")) {
                case "diary":
                    ((MainActivity) getActivity()).bt_diary.performClick();
                    break;
                case "stats":
                    ((MainActivity) getActivity()).bt_stats.performClick();
                    break;
                default:
                    refreshCalendar();

            }
        }
    }


    public void refreshCalendar() {

        adapter.refreshDays();
        adapter.notifyDataSetChanged();
        handler.post(calendarUpdater); // generate some random calendar diaryList

        String tmpMonth = android.text.format.DateFormat.format("yyyy", month).toString();
        StringBuilder s = new StringBuilder();
        s.append(tmpMonth.substring(0, 1));
        s.append(" ");
        s.append(tmpMonth.substring(1, 2));
        s.append(" ");
        s.append(tmpMonth.substring(2, 3));
        s.append(" ");
        s.append(tmpMonth.substring(3, 4));

        text_year.setText(s.toString());
        text_month.setText(android.text.format.DateFormat.format("M", month));
    }

    public Runnable calendarUpdater = new Runnable() {

        @Override
        public void run() {
            diaryList.clear();

            diaryList = MainActivity.dbHelper.getAlcoholList(
                    android.text.format.DateFormat.format("yyyy-MM", month).toString());

            adapter.setList(diaryList);
            adapter.notifyDataSetChanged();
        }
    };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_left:
                    if (month.get(Calendar.MONTH) == month.getActualMinimum(Calendar.MONTH)) {
                        month.set((month.get(Calendar.YEAR) - 1), month.getActualMaximum(Calendar.MONTH), 1);
                    } else {
                        month.set(Calendar.MONTH, month.get(Calendar.MONTH) - 1);
                    }
                    refreshCalendar();
                    break;
                case R.id.bt_right:
                    if (month.get(Calendar.MONTH) == month.getActualMaximum(Calendar.MONTH)) {
                        month.set((month.get(Calendar.YEAR) + 1), month.getActualMinimum(Calendar.MONTH), 1);
                    } else {
                        month.set(Calendar.MONTH, month.get(Calendar.MONTH) + 1);
                    }
                    refreshCalendar();
            }
        }
    };

    //달력에서 날짜 클릭했을 때 실행
    //날짜 정보를 전달하면서 액티비티 실행
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView date = (TextView) view.findViewById(R.id.text_day);
            if (date != null && !date.getText().equals("")) {

                Intent intent = new Intent(getActivity(), ItemActivity.class);
                String day = date.getText().toString();
                if (day.length() == 1) {
                    day = "0" + day;
                }
                // return chosen date as string format
                Log.i("Clicked date", android.text.format.DateFormat.format("yyyy.MM", month) + "." + day);
                intent.putExtra("date", android.text.format.DateFormat.format("yyyy.MM", month) + "." + day);

                //요일 계산
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
                Date nDate = null;
                try {
                    nDate = dateFormat.parse(android.text.format.DateFormat.format("yyyy.MM.dd", month) + "." + day);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar cal = Calendar.getInstance();
                assert nDate != null;
                cal.setTime(nDate);
                int dayNum = cal.get(Calendar.DAY_OF_WEEK);

                String dayOfWeek = null;
                switch (dayNum) {
                    case 1:
                        dayOfWeek = "SUN";
                        break;
                    case 2:
                        dayOfWeek = "MON";
                        break;
                    case 3:
                        dayOfWeek = "TUE";
                        break;
                    case 4:
                        dayOfWeek = "WEN";
                        break;
                    case 5:
                        dayOfWeek = "THU";
                        break;
                    case 6:
                        dayOfWeek = "FRI";
                        break;
                    case 7:
                        dayOfWeek = "SAT";
                        break;
                }
                intent.putExtra("dayOfWeek", dayOfWeek);

                startActivityForResult(intent, ItemActivity.CODE);
                getActivity().overridePendingTransition(R.anim.slide_in_up, android.R.anim.fade_out);
            }
        }
    };


    private void assignView(View view) {
        text_title = (TextView) getActivity().findViewById(R.id.actionbar_title);

        bt_left = (ImageButton) view.findViewById(R.id.bt_left);
        bt_right = (ImageButton) view.findViewById(R.id.bt_right);
        text_year = (TextView) view.findViewById(R.id.text_year);
        text_month = (TextView) view.findViewById(R.id.text_month);
        gridView = (GridView) view.findViewById(R.id.grid_calendar);

        text_week = new TextView[7];
        text_week[0] = (TextView) view.findViewById(R.id.text_week_0);
        text_week[1] = (TextView) view.findViewById(R.id.text_week_1);
        text_week[2] = (TextView) view.findViewById(R.id.text_week_2);
        text_week[3] = (TextView) view.findViewById(R.id.text_week_3);
        text_week[4] = (TextView) view.findViewById(R.id.text_week_4);
        text_week[5] = (TextView) view.findViewById(R.id.text_week_5);
        text_week[6] = (TextView) view.findViewById(R.id.text_week_6);
    }
}
