package tourplatform.drunkdiary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by orc12 on 2015-12-21.
 */
public class DiaryAdapter extends BaseAdapter {

    class ViewHolder {
        private TextView text_note;
        private TextView text_date;
        private ImageView image_emoticon;
        private ImageView image_alcohol;
    }

    private Context mContext;
    private ViewHolder holder;
    private DiaryData listElement;
    private ArrayList<DiaryData> eventData;

    public DiaryAdapter(Context context) {
        super();
        eventData = new ArrayList<>();
        mContext = context;
    }

    public void add(DiaryData data) {
        eventData.add(data);
    }

    public void addAll(ArrayList<DiaryData> datas) {
        eventData = datas;
    }

    public void setList(ArrayList<DiaryData> datas) {
        eventData = datas;
    }

    @Override
    public int getCount() {
        return eventData.size();
    }

    @Override
    public DiaryData getItem(int position) {
        return eventData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.item_diary, parent, false);

            holder = new ViewHolder();
            //assign each Views
            assign_views(convertView);

            //insert wish and like icons
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        listElement = getItem(position);
        if (listElement != null) {
            holder.text_date.setText(listElement.getDateWeekString("."));
            holder.text_date.setTypeface(Font.GOTHAM_MIDIUM);
            holder.text_note.setText(listElement.getNote());
            holder.text_note.setTypeface(Font.NOTOSANSCJKKR_LIGHT);

            switch(listElement.getCondition()) {
                case OK:
                    holder.image_emoticon.setImageResource(R.drawable.diary_ic_ok);
                    break;
                case SOSO:
                    holder.image_emoticon.setImageResource(R.drawable.diary_ic_soso);
                    break;
                case DISGUSTED:
                    holder.image_emoticon.setImageResource(R.drawable.diary_ic_disgusted);
                    break;
                case VOMIT:
                    holder.image_emoticon.setImageResource(R.drawable.diary_ic_vomit);
                    break;
                case DEAD:
                    holder.image_emoticon.setImageResource(R.drawable.diary_ic_dead);
                    break;
                default:
                    Log.e("diary error", "wrong condition");
            }

            switch(listElement.getAlcohol()) {
                case SOJU:
                    holder.image_alcohol.setImageResource(R.drawable.diary_ic_soju);
                    break;
                case BEER:
                    holder.image_alcohol.setImageResource(R.drawable.diary_ic_beer);
                    break;
                case SOMAC:
                    holder.image_alcohol.setImageResource(R.drawable.diary_ic_somac);
                    break;
                case MAKGEOLLI:
                    holder.image_alcohol.setImageResource(R.drawable.diary_ic_makgeolli);
                    break;
                case LIQUOR:
                    holder.image_alcohol.setImageResource(R.drawable.diary_ic_liquor);
                    break;
                default:
                    Log.e("diary error", "wrong alcohol");
            }

        }
        return convertView;
    }

    private void assign_views(View view) {
        holder.text_date = (TextView) view.findViewById(R.id.diary_text_date);
        holder.text_note = (TextView) view.findViewById(R.id.diary_text_note);
        holder.image_alcohol = (ImageView) view.findViewById(R.id.diary_image_alcohol);
        holder.image_emoticon = (ImageView) view.findViewById(R.id.diary_image_emoticon);
    }

}
