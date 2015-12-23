package ms.drunkdiary.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ms.drunkdiary.Alcohol;
import ms.drunkdiary.Condition;
import ms.drunkdiary.DiaryData;
import ms.drunkdiary.Font;
import ms.drunkdiary.R;

/**
 * Created by orc12 on 2015-12-05.
 */
public class ItemActivity extends Activity {
    public static final int CODE = 1;

    ImageButton bt_back;
    TextView text_title;
    TextView text_drink_condition;
    TextView text_note;
    Button bt_soju;
    Button bt_beer;
    Button bt_somac;
    Button bt_makgeolli;
    Button bt_liquor;
    RelativeLayout layout_bottle;
    RelativeLayout layout_glass;
    ImageView image_bottle;
    ImageView image_glass;
    TextView text_bottle;
    TextView text_glass;

    ImageButton bt_calendar;
    ImageButton bt_diary;
    ImageButton bt_stats;

    EditText et_note;

    //true if already written in this day
    Boolean update = false;

    //date of today (yyyy.MM.dd)
    String date;
    Alcohol selected_alcohol = null;
    Condition condition = Condition.OK;
    int bottle;
    int glass;

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
        String dayIncludeWeek = date + " " + getIntent().getExtras().getString("dayOfWeek");
        text_title.setText(dayIncludeWeek);

        text_bottle.setTypeface(Font.GOTHAM_BOOK);
        text_glass.setTypeface(Font.GOTHAM_BOOK);
        bt_soju.performClick();

        //load saved data from database
        DiaryData savedData = MainActivity.dbHelper.getItem(date);
        if (savedData != null) {
            update = true;

            Log.i("item", savedData.getDateString(".") + " " + savedData.getCondition().name()
                    + " " + savedData.getAlcohol().name()
                    + " " + savedData.getBottle()
                    + " " + savedData.getGlass()
                    + " " + savedData.getNote());

            switch (savedData.getAlcohol()) {
                case SOJU:
                    bt_soju.performClick();
                    break;
                case BEER:
                    bt_beer.performClick();
                    break;
                case SOMAC:
                    bt_somac.performClick();
                    break;
                case MAKGEOLLI:
                    bt_makgeolli.performClick();
                    break;
                case LIQUOR:
                    bt_liquor.performClick();
                    break;
            }
            switch (savedData.getCondition()) {
                case OK:
                    findViewById(R.id.radio_ok).performClick();
                    break;
                case SOSO:
                    findViewById(R.id.radio_soso).performClick();
                    break;
                case DISGUSTED:
                    findViewById(R.id.radio_disgusted).performClick();
                    break;
                case VOMIT:
                    findViewById(R.id.radio_vomit).performClick();
                    break;
                case DEAD:
                    findViewById(R.id.radio_dead).performClick();
                    break;
            }
            bottle = savedData.getBottle();
            refreshBottleLayout();
            glass = savedData.getGlass();
            refreshGlassLayout();
            et_note.setText(savedData.getNote());
        }
    }


    @Override
    public void onBackPressed() {
        bt_back.performClick();
    }

    private void finishWithResult(String result) {
        Intent intent = new Intent();
        intent.putExtra("clicked", result);
        setResult(CODE, intent);
        finish();
        //TODO: change animation
        overridePendingTransition(android.R.anim.fade_in, R.anim.slide_out_down);
    }

    public void setBottleImage(Alcohol alcohol, boolean fill) {
        switch (selected_alcohol) {
            case SOJU:
                if (fill) image_bottle.setImageResource(R.drawable.icon_big_soju_pressed);
                else image_bottle.setImageResource(R.drawable.icon_big_soju);
                break;
            case BEER:
                if (fill) image_bottle.setImageResource(R.drawable.icon_big_beer_pressed);
                else image_bottle.setImageResource(R.drawable.icon_big_beer);
                break;
            case SOMAC:
                if (fill) image_bottle.setImageResource(R.drawable.icon_big_somac_pressed);
                else image_bottle.setImageResource(R.drawable.icon_big_somac);
                break;
            case MAKGEOLLI:
                if (fill) image_bottle.setImageResource(R.drawable.icon_big_makgeolli_pressed);
                else image_bottle.setImageResource(R.drawable.icon_big_makgeolli);
                break;
            case LIQUOR:
                if (fill) image_bottle.setImageResource(R.drawable.icon_big_liquor_glass_pressed);
                else image_bottle.setImageResource(R.drawable.icon_big_liquor_glass);
                break;
        }
    }

    public void refreshBottleLayout() {
        text_bottle.setText(String.valueOf(bottle));

        if (bottle == 0) {
            setBottleImage(selected_alcohol, false);
            text_bottle.setVisibility(View.INVISIBLE);
        } else {
            setBottleImage(selected_alcohol, true);
            text_bottle.setVisibility(View.VISIBLE);
        }
    }

    public void setGlassImage(Alcohol alcohol, boolean fill) {
        switch (selected_alcohol) {
            case SOJU:
                if (fill) image_glass.setImageResource(R.drawable.icon_big_soju_glass_pressed);
                else image_glass.setImageResource(R.drawable.icon_big_soju_glass);
                break;
            case BEER:
                if (fill) image_glass.setImageResource(R.drawable.icon_big_beer_glass_pressed);
                else image_glass.setImageResource(R.drawable.icon_big_beer_glass);
                break;
            case SOMAC:
                Log.e("setGlassImage", "somac doesn't have glass");
                break;
            case MAKGEOLLI:
                if (fill) image_glass.setImageResource(R.drawable.icon_big_makgeolli_bowl_pressed);
                else image_glass.setImageResource(R.drawable.icon_big_makgeolli_bowl);
                break;
            case LIQUOR:
                Log.e("setGlassImage", "somac doesn't have glass");
                break;
        }
    }

    public void refreshGlassLayout() {
        text_glass.setText(String.valueOf(glass));

        if (glass == 0) {
            setGlassImage(selected_alcohol, false);
            text_glass.setVisibility(View.INVISIBLE);
        } else {
            setGlassImage(selected_alcohol, true);
            text_glass.setVisibility(View.VISIBLE);
        }
    }

    public void mOnClick(View view) {
        switch (view.getId()) {
            case R.id.bt_back:
                finishWithResult("back");
                break;
            case R.id.bottombar_bt_calendar:
                finishWithResult("calendar");
                break;
            case R.id.bottombar_bt_diary:
                bt_calendar.setImageResource(R.drawable.ic_calendar);
                bt_diary.setImageResource(R.drawable.ic_diary_pressed);
                finishWithResult("diary");
                break;
            case R.id.bottombar_bt_stats:
                bt_calendar.setImageResource(R.drawable.ic_calendar);
                bt_stats.setImageResource(R.drawable.ic_stats_pressed);
                finishWithResult("stats");
                break;
            case R.id.bt_save:
                DiaryData diaryData = new DiaryData(
                        date,
                        condition,
                        selected_alcohol,
                        et_note.getText().toString(),
                        bottle,
                        glass);
                if(update){
                    MainActivity.dbHelper.updateItem(diaryData);
                } else {
                    MainActivity.dbHelper.addItem(diaryData);

                }
                finishWithResult("back");
                break;
            case R.id.bt_delete:
                MainActivity.dbHelper.deleteItem(date);
                finishWithResult("back");
                break;
            case R.id.item_bt_plus_bottle:
                bottle = (bottle + 1) % 10;
                refreshBottleLayout();
                break;
            case R.id.item_bt_minus_bottle:
                if (bottle == 0) {
                    bottle = bottle + 9;
                } else {
                    bottle = (bottle - 1) % 10;
                }
                refreshBottleLayout();
                break;
            case R.id.item_bt_plus_glass:
                glass = (glass + 1) % 10;
                refreshGlassLayout();
                break;
            case R.id.item_bt_minus_glass:

                if (glass == 0) {
                    glass = glass + 9;
                } else {
                    glass = (glass - 1) % 10;
                }
                refreshGlassLayout();
                break;


        }
    }

    public void setAlcoholButtonImageToDefault() {
        bt_soju.setBackgroundResource(R.drawable.item_btn_soju);
        bt_beer.setBackgroundResource(R.drawable.item_btn_beer);
        bt_somac.setBackgroundResource(R.drawable.item_btn_somac);
        bt_makgeolli.setBackgroundResource(R.drawable.item_btn_makgeolli);
        bt_liquor.setBackgroundResource(R.drawable.item_btn_liquor);

    }

    public void mOnAlcoholClick(View view) {
        switch (view.getId()) {
            case R.id.bt_soju:
                setAlcoholButtonImageToDefault();
                bt_soju.setBackgroundResource(R.drawable.item_btn_pressed_soju);
                layout_glass.setVisibility(View.VISIBLE);
                text_glass.setVisibility(View.INVISIBLE);
                text_bottle.setVisibility(View.INVISIBLE);
                selected_alcohol = Alcohol.SOJU;
                image_bottle.setImageResource(R.drawable.icon_big_soju);
                image_glass.setImageResource(R.drawable.icon_big_soju_glass);
                bottle = 0;
                glass = 0;
                break;
            case R.id.bt_beer:
                setAlcoholButtonImageToDefault();
                bt_beer.setBackgroundResource(R.drawable.item_btn_pressed_beer);
                layout_glass.setVisibility(View.VISIBLE);
                text_glass.setVisibility(View.INVISIBLE);
                text_bottle.setVisibility(View.INVISIBLE);
                selected_alcohol = Alcohol.BEER;
                image_bottle.setImageResource(R.drawable.icon_big_beer);
                image_glass.setImageResource(R.drawable.icon_big_beer_glass);
                bottle = 0;
                glass = 0;
                break;
            case R.id.bt_somac:
                setAlcoholButtonImageToDefault();
                bt_somac.setBackgroundResource(R.drawable.item_btn_pressed_somac);
                layout_glass.setVisibility(View.GONE);
                text_bottle.setVisibility(View.INVISIBLE);
                selected_alcohol = Alcohol.SOMAC;
                image_bottle.setImageResource(R.drawable.icon_big_somac);
                bottle = 0;
                glass = 0;
                break;
            case R.id.bt_makgeolli:
                setAlcoholButtonImageToDefault();
                bt_makgeolli.setBackgroundResource(R.drawable.item_btn_pressed_makgeolli);
                layout_glass.setVisibility(View.VISIBLE);
                text_glass.setVisibility(View.INVISIBLE);
                text_bottle.setVisibility(View.INVISIBLE);
                selected_alcohol = Alcohol.MAKGEOLLI;
                image_bottle.setImageResource(R.drawable.icon_big_makgeolli);
                image_glass.setImageResource(R.drawable.icon_big_makgeolli_bowl);
                bottle = 0;
                glass = 0;
                break;
            case R.id.bt_liquor:
                setAlcoholButtonImageToDefault();
                bt_liquor.setBackgroundResource(R.drawable.item_btn_pressed_liquor);
                layout_glass.setVisibility(View.GONE);
                text_bottle.setVisibility(View.INVISIBLE);
                selected_alcohol = Alcohol.LIQUOR;
                image_bottle.setImageResource(R.drawable.icon_big_liquor_glass);
                bottle = 0;
                glass = 0;
                break;
        }
    }

    public void onRadioClick(View view) {
        switch (view.getId()) {
            case R.id.radio_ok:
                condition = Condition.OK;
                break;
            case R.id.radio_soso:
                condition = Condition.SOSO;
                break;
            case R.id.radio_disgusted:
                condition = Condition.DISGUSTED;
                break;
            case R.id.radio_vomit:
                condition = Condition.VOMIT;
                break;
            case R.id.radio_dead:
                condition = Condition.DEAD;
                break;
        }

    }

    private void assignView() {
        bt_back = (ImageButton) findViewById(R.id.bt_back);
        text_title = (TextView) findViewById(R.id.actionbar_title);
        text_drink_condition = (TextView) findViewById(R.id.text_drink_condition);
        text_note = (TextView) findViewById(R.id.text_note);
        et_note = (EditText) findViewById(R.id.et_note);
        bt_soju = (Button) findViewById(R.id.bt_soju);
        bt_beer = (Button) findViewById(R.id.bt_beer);
        bt_somac = (Button) findViewById(R.id.bt_somac);
        bt_makgeolli = (Button) findViewById(R.id.bt_makgeolli);
        bt_liquor = (Button) findViewById(R.id.bt_liquor);
        layout_bottle = (RelativeLayout) findViewById(R.id.layout_bottle);
        layout_glass = (RelativeLayout) findViewById(R.id.layout_glass);
        image_bottle = (ImageView) findViewById(R.id.item_bottle);
        image_glass = (ImageView) findViewById(R.id.item_glass);
        text_bottle = (TextView) findViewById(R.id.text_bottle);
        text_glass = (TextView) findViewById(R.id.text_glass);

        bt_calendar = (ImageButton)findViewById(R.id.bottombar_bt_calendar);
        bt_diary = (ImageButton)findViewById(R.id.bottombar_bt_diary);
        bt_stats = (ImageButton)findViewById(R.id.bottombar_bt_stats);
    }

}