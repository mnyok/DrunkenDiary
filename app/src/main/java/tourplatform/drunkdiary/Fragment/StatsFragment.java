package tourplatform.drunkdiary.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tourplatform.drunkdiary.Font;
import tourplatform.drunkdiary.R;

/**
 * Created by orc12 on 2015-12-16.
 */
public class StatsFragment extends Fragment {

    TextView text_you_were_drunken;
    TextView text_days_in_a_month;
    TextView text_drink_day;
    TextView text_alcohol[];
    TextView text_stats_title[];
    TextView text_stats_total[];
    TextView text_stats_avr[];


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        assignView(view);


        text_you_were_drunken.setTypeface(Font.GOTHAM_MIDIUM);
        text_days_in_a_month.setTypeface(Font.GOTHAM_MIDIUM);
        text_drink_day.setTypeface(Font.GOTHAM_BOOK);
        for(TextView alcohol:text_alcohol){
            alcohol.setTypeface(Font.NOTOSANSCJKKR_LIGHT);
        }
        for(TextView title: text_stats_title){
            title.setTypeface(Font.GOTHAM_BOOK);
        }
        for(TextView total: text_stats_total){
            total.setTypeface(Font.GOTHAM_BOOK);
        }
        for(TextView avr: text_stats_avr){
            avr.setTypeface(Font.GOTHAM_BOOK);
        }
        return view;
    }

    private void assignView(View view){
        text_you_were_drunken = (TextView) view.findViewById(R.id.stats_text_you_were_drunken);
        text_days_in_a_month = (TextView) view.findViewById(R.id.stats_text_days_in_a_month);
        text_drink_day = (TextView) view.findViewById(R.id.stats_text_drink_day);
        text_alcohol = new TextView[5];
        text_alcohol[0] = (TextView) view.findViewById(R.id.stats_text_soju);
        text_alcohol[1] = (TextView) view.findViewById(R.id.stats_text_beer);
        text_alcohol[2] = (TextView) view.findViewById(R.id.stats_text_somac);
        text_alcohol[3] = (TextView) view.findViewById(R.id.stats_text_liquor);
        text_alcohol[4] = (TextView) view.findViewById(R.id.stats_text_makgeolli);
        text_stats_title = new TextView[10];
        text_stats_title[0] = (TextView) view.findViewById(R.id.stats_text_soju_avr_title);
        text_stats_title[1] = (TextView) view.findViewById(R.id.stats_text_soju_total_title);
        text_stats_title[2] = (TextView) view.findViewById(R.id.stats_text_beer_avr_title);
        text_stats_title[3] = (TextView) view.findViewById(R.id.stats_text_beer_total_title);
        text_stats_title[4] = (TextView) view.findViewById(R.id.stats_text_somac_avr_title);
        text_stats_title[5] = (TextView) view.findViewById(R.id.stats_text_somac_total_title);
        text_stats_title[6] = (TextView) view.findViewById(R.id.stats_text_liquor_avr_title);
        text_stats_title[7] = (TextView) view.findViewById(R.id.stats_text_liquor_total_title);
        text_stats_title[8] = (TextView) view.findViewById(R.id.stats_text_makgeolli_avr_title);
        text_stats_title[9] = (TextView) view.findViewById(R.id.stats_text_makgeolli_total_title);
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
