package tourplatform.drunkdiary;

/**
 * Created by orc12 on 2015-12-04.
 */
/*
* Copyright 2011 Lauri Nevala.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.graphics.Color;
import android.widget.BaseAdapter;


import java.util.ArrayList;
import java.util.Calendar;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

public class CalendarAdapter extends BaseAdapter {
    static final int FIRST_DAY_OF_WEEK = 0; // Sunday = 0, Monday = 1

    class ViewHolder {
        private TextView text_day;
        private ImageView image_alcohol;
    }

    private Context mContext;
    private ViewHolder holder;

    private java.util.Calendar month;
    private Calendar selectedDate;
    private ArrayList<DiaryData> diaryList;
    private DiaryData[] drunkenDays;

    public CalendarAdapter(Context c, Calendar monthCalendar) {
        month = monthCalendar;
        selectedDate = (Calendar) monthCalendar.clone();
        mContext = c;
        month.set(Calendar.DAY_OF_MONTH, 1);
        this.diaryList = new ArrayList<>();
        drunkenDays = new DiaryData[32];
        refreshDays();
    }

    //TODO: check
    public void setList(ArrayList<DiaryData> diaryList) {
        drunkenDays = new DiaryData[32];

        for (int i = 0; i != diaryList.size(); i++) {
            int day = Integer.parseInt(diaryList.get(i).getDateString(".").substring(8));
            drunkenDays[day] = diaryList.get(i);
        }

        this.diaryList = diaryList;
    }


    public int getCount() {
        return days.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new view for each item referenced by the Adapter
    // TODO: it's library but... too dirty and complex. change after
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            convertView = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.item_calendar, null);
            holder = new ViewHolder();

            //assign each Views
            assign_views(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text_day.setTypeface(Font.GOTHAM_BOOK);

        // disable empty days from the beginning
        if (days[position].equals("0")) {
            convertView.setClickable(false);
            convertView.setVisibility(View.INVISIBLE);
            convertView.setFocusable(false);
        } else {
            convertView.setVisibility(View.VISIBLE);
            // mark current day as focused
            if (month.get(Calendar.YEAR) == selectedDate.get(Calendar.YEAR) && month.get(Calendar.MONTH) == selectedDate.get(Calendar.MONTH) && days[position].equals("" + selectedDate.get(Calendar.DAY_OF_MONTH))) {
                holder.text_day.setBackgroundResource(R.drawable.ic_today);
            } else {
                holder.text_day.setBackgroundColor(Color.argb(0 ,255, 255, 255));
            }
        }
        holder.text_day.setText(days[position]);

        // create date string for comparison
        int day = Integer.parseInt(days[position]);

        String monthStr = "" + (month.get(Calendar.MONTH) + 1);
        if (monthStr.length() == 1) {
            monthStr = "0" + monthStr;
        }

        // show icon if date is not empty and it exists in the diaryList array
        if (day != 0 && drunkenDays != null
                && drunkenDays[day] != null) {
            holder.image_alcohol.setVisibility(View.VISIBLE);
            switch(drunkenDays[day].getAlcohol()){
                case SOJU:
                    holder.image_alcohol.setImageResource(R.drawable.ic_soju);
                    break;
                case BEER:
                    holder.image_alcohol.setImageResource(R.drawable.ic_beer);
                    break;
                case SOMAC:
                    holder.image_alcohol.setImageResource(R.drawable.ic_somac);
                    break;
                case MAKGEOLLI:
                    holder.image_alcohol.setImageResource(R.drawable.ic_makgeolli);
                    break;
                case LIQUOR:
                    holder.image_alcohol.setImageResource(R.drawable.ic_liquor);
                    break;

            }
        } else {
            holder.image_alcohol.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    public void refreshDays() {
        // clear diaryList
        diaryList.clear();

        int lastDay = month.getActualMaximum(Calendar.DAY_OF_MONTH);
        int firstDay = month.get(Calendar.DAY_OF_WEEK);

        // figure size of the array
        if (firstDay == 1) {
            days = new String[lastDay + (FIRST_DAY_OF_WEEK * 6)];
        } else {
            days = new String[lastDay + firstDay - (FIRST_DAY_OF_WEEK + 1)];
        }

        int j = FIRST_DAY_OF_WEEK;

        // populate empty days before first real day
        if (firstDay > 1) {
            for (j = 0; j < firstDay - FIRST_DAY_OF_WEEK; j++) {
                days[j] = "0";
            }
        } else {
            for (j = 0; j < FIRST_DAY_OF_WEEK * 6; j++) {
                days[j] = "0";
            }
            j = FIRST_DAY_OF_WEEK * 6 + 1; // sunday => 1, monday => 7
        }

        // populate days
        int dayNumber = 1;
        for (int i = j - 1; i < days.length; i++) {
            days[i] = "" + dayNumber;
            dayNumber++;
        }
    }

    // references to diaryList
    public String[] days;

    private void assign_views(View view) {
        holder.text_day = (TextView) view.findViewById(R.id.text_day);
        holder.image_alcohol = (ImageView) view.findViewById(R.id.image_alcohol);
    }
}