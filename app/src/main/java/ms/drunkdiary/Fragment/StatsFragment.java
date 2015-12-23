package ms.drunkdiary.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import ms.drunkdiary.Activity.MainActivity;
import ms.drunkdiary.DiaryData;
import ms.drunkdiary.Font;
import ms.drunkdiary.R;

/**
 * Created by orc12 on 2015-12-16.
 */
public class StatsFragment extends Fragment {

    TextView text_title;
    TextView text_month;
    ImageButton bt_prev;
    ImageButton bt_next;
    TextView text_drink_day;
    ImageButton bt_info;
    TextView text_stats_total[];
    TextView text_stats_avr[];

    Calendar calendar = Calendar.getInstance();
    float average[] = new float[5];
    float total[] = new float[5];
    float count[] = new float[5];


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        assignView(view);
        setTypeface(view);

        text_title.setText("STATISTIC");


        bt_next.setOnClickListener(clickListener);
        bt_prev.setOnClickListener(clickListener);
        bt_info.setOnClickListener(clickListener);

        calculateStats();

        return view;
    }

    private void calculateStats() {
        ArrayList<DiaryData> diaryList;

        for (int i = 0; i < 5; i++) {
            total[i] = 0;
            count[i] = 0;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        diaryList = MainActivity.dbHelper.getAlcoholList(formatter.format(calendar.getTime()));

        for (DiaryData diaryData : diaryList) {
            switch (diaryData.getAlcohol()) {
                case SOJU:
                    count[0]++;
                    total[0] += diaryData.getBottle() + ((float) diaryData.getGlass() / 7);
                    break;
                case BEER:
                    count[1]++;
                    total[1] += diaryData.getBottle() + ((float) diaryData.getGlass() * 2 / 3);
                    break;
                case SOMAC:
                    count[2]++;
                    total[2] += diaryData.getBottle();
                    break;
                case LIQUOR:
                    count[3]++;
                    total[3] += diaryData.getGlass();
                    break;
                case MAKGEOLLI:
                    count[4]++;
                    total[4] += diaryData.getBottle() + ((float) diaryData.getGlass() / 4);
                    break;
            }
        }

        //count average
        for (int i = 0; i < 5; i++) {
            if (count[i] == 0) {
                average[i] = 0;
            } else {
                average[i] = total[i] / count[i];
            }
        }

        //set text

        String monthString = calendar.get(Calendar.YEAR) + " " + getMonthString(calendar);
        text_month.setText(monthString);

        String drink_day;
        if (diaryList.size() < 10) {
            drink_day = "0" + diaryList.size();
            text_drink_day.setText(drink_day);
        } else {
            drink_day = String.valueOf(diaryList.size());
            text_drink_day.setText(drink_day);
        }

        for (int i = 0; i < 5; i++) {
            text_stats_avr[i].setText(String.format("%.1f", average[i]));
            text_stats_total[i].setText(String.format("%.1f", total[i]));
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.stats_bt_prev:
                    if (calendar.get(Calendar.MONTH) == calendar.getActualMinimum(Calendar.MONTH)) {
                        calendar.set((calendar.get(Calendar.YEAR) - 1), calendar.getActualMaximum(Calendar.MONTH), 1);
                    } else {
                        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
                    }
                    calculateStats();
                    break;
                case R.id.stats_bt_next:
                    if (calendar.get(Calendar.MONTH) == calendar.getActualMaximum(Calendar.MONTH)) {
                        calendar.set((calendar.get(Calendar.YEAR) + 1), calendar.getActualMinimum(Calendar.MONTH), 1);
                    } else {
                        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
                    }
                    calculateStats();
                    break;
                case R.id.stats_bt_info:
                    InfoFragment fragment;
                    try {
                        fragment = new InfoFragment();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }

                    Log.i("fragment", "infofragment");
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.realcontent, fragment, "info");
                    ft.addToBackStack("info");
                    ft.commit();
                    getFragmentManager().executePendingTransactions();
                    break;
            }
        }
    };


    private String getMonthString(Calendar cal) {
        String month;
        switch (cal.get(Calendar.MONTH)) {
            case 0:
                month = "JANUARY";
                break;
            case 1:
                month = "FEBRUARY";
                break;
            case 2:
                month = "MARCH";
                break;
            case 3:
                month = "APRIL";
                break;
            case 4:
                month = "MAY";
                break;
            case 5:
                month = "JUNE";
                break;
            case 6:
                month = "JULY";
                break;
            case 7:
                month = "AUGUST";
                break;
            case 8:
                month = "SEPTEMBER";
                break;
            case 9:
                month = "OCTOBER";
                break;
            case 10:
                month = "NOVEMBER";
                break;
            case 11:
                month = "DECEMBER";
                break;
            default:
                month = null;
        }
        return month;
    }

    private void setTypeface(View view){

        //set Typeface
        text_month.setTypeface(Font.GOTHAM_MIDIUM);
        text_drink_day.setTypeface(Font.GOTHAM_BOOK);
        for (TextView total : text_stats_total) {
            total.setTypeface(Font.GOTHAM_BOOK);
        }
        for (TextView avr : text_stats_avr) {
            avr.setTypeface(Font.GOTHAM_BOOK);
        }


        ((TextView) view.findViewById(R.id.stats_text_drink_day_days)).setTypeface(Font.GOTHAM_MIDIUM);
        ((TextView) view.findViewById(R.id.stats_text_you_were_drunken)).setTypeface(Font.GOTHAM_MIDIUM);

        ((TextView) view.findViewById(R.id.stats_text_soju)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);
        ((TextView) view.findViewById(R.id.stats_text_soju_avr_title)).setTypeface(Font.GOTHAM_BOOK);
        ((TextView) view.findViewById(R.id.stats_text_soju_total_title)).setTypeface(Font.GOTHAM_BOOK);
        ((TextView) view.findViewById(R.id.stats_text_soju_avr_measure)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);
        ((TextView) view.findViewById(R.id.stats_text_soju_total_measure)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);

        ((TextView) view.findViewById(R.id.stats_text_beer)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);
        ((TextView) view.findViewById(R.id.stats_text_beer_avr_title)).setTypeface(Font.GOTHAM_BOOK);
        ((TextView) view.findViewById(R.id.stats_text_beer_total_title)).setTypeface(Font.GOTHAM_BOOK);
        ((TextView) view.findViewById(R.id.stats_text_beer_avr_measure)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);
        ((TextView) view.findViewById(R.id.stats_text_beer_total_measure)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);

        ((TextView) view.findViewById(R.id.stats_text_somac)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);
        ((TextView) view.findViewById(R.id.stats_text_somac_avr_title)).setTypeface(Font.GOTHAM_BOOK);
        ((TextView) view.findViewById(R.id.stats_text_somac_total_title)).setTypeface(Font.GOTHAM_BOOK);
        ((TextView) view.findViewById(R.id.stats_text_somac_avr_measure)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);
        ((TextView) view.findViewById(R.id.stats_text_somac_total_measure)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);

        ((TextView) view.findViewById(R.id.stats_text_liquor)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);
        ((TextView) view.findViewById(R.id.stats_text_liquor_avr_title)).setTypeface(Font.GOTHAM_BOOK);
        ((TextView) view.findViewById(R.id.stats_text_liquor_total_title)).setTypeface(Font.GOTHAM_BOOK);
        ((TextView) view.findViewById(R.id.stats_text_liquor_avr_measure)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);
        ((TextView) view.findViewById(R.id.stats_text_liquor_total_measure)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);

        ((TextView) view.findViewById(R.id.stats_text_makgeolli)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);
        ((TextView) view.findViewById(R.id.stats_text_makgeolli_avr_title)).setTypeface(Font.GOTHAM_BOOK);
        ((TextView) view.findViewById(R.id.stats_text_makgeolli_total_title)).setTypeface(Font.GOTHAM_BOOK);
        ((TextView) view.findViewById(R.id.stats_text_makgeolli_avr_measure)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);
        ((TextView) view.findViewById(R.id.stats_text_makgeolli_total_measure)).setTypeface(Font.NOTOSANSCJKKR_LIGHT);

    }

    private void assignView(View view) {
        text_title = (TextView) getActivity().findViewById(R.id.actionbar_title);
        text_month = (TextView) view.findViewById(R.id.stats_text_month);
        bt_prev = (ImageButton) view.findViewById(R.id.stats_bt_prev);
        bt_next = (ImageButton) view.findViewById(R.id.stats_bt_next);
        text_drink_day = (TextView) view.findViewById(R.id.stats_text_drink_day);
        bt_info = (ImageButton) view.findViewById(R.id.stats_bt_info);
        text_stats_total = new TextView[5];
        text_stats_total[0] = (TextView) view.findViewById(R.id.stats_text_soju_total);
        text_stats_total[1] = (TextView) view.findViewById(R.id.stats_text_beer_total);
        text_stats_total[2] = (TextView) view.findViewById(R.id.stats_text_somac_total);
        text_stats_total[3] = (TextView) view.findViewById(R.id.stats_text_liquor_total);
        text_stats_total[4] = (TextView) view.findViewById(R.id.stats_text_makgeolli_total);
        text_stats_avr = new TextView[5];
        text_stats_avr[0] = (TextView) view.findViewById(R.id.stats_text_soju_avr);
        text_stats_avr[1] = (TextView) view.findViewById(R.id.stats_text_beer_avr);
        text_stats_avr[2] = (TextView) view.findViewById(R.id.stats_text_somac_avr);
        text_stats_avr[3] = (TextView) view.findViewById(R.id.stats_text_liquor_avr);
        text_stats_avr[4] = (TextView) view.findViewById(R.id.stats_text_makgeolli_avr);
    }
}
