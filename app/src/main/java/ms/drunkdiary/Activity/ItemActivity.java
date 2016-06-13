package ms.drunkdiary.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ms.drunkdiary.Condition;
import ms.drunkdiary.DiaryData;
import ms.drunkdiary.Font;
import ms.drunkdiary.R;

/**
 * Created by orc12 on 2015-12-05.
 */
public class ItemActivity extends Activity {
    public static final int CODE = 1;

    public static final int SOJU = 0;
    public static final int BEER = 1;
    public static final int SOMAC = 2;
    public static final int MAKGEOLLI = 3;
    public static final int LIQUOR = 4;


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
    int selected_alcohol = 0;
    Condition condition = Condition.OK;
    int[] bottle = new int[5];
    int[] glass = new int[5];

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
        DiaryData[] savedData = MainActivity.dbHelper.getItemList(date);

        for (int i = 4; i >= 0; i--) {
            if (savedData[i] == null) {
                bottle[i] = 0;
                glass[i] = 0;
            } else {
                update = true;

//            Log.i("item", savedData.getDateString(".") + " " + savedData.getCondition().name()
//                    + " " + savedData.getAlcohol().name()
//                    + " " + savedData.getBottle()
//                    + " " + savedData.getGlass()
//                    + " " + savedData.getNote());


                et_note.setText(savedData[i].getNote());
                switch (savedData[i].getCondition()) {
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

                bottle[i] = savedData[i].getBottle();
                glass[i] = savedData[i].getGlass();

                //click
                switch (savedData[i].getAlcohol()) {
                    case SOJU:
                        bt_soju.performClick();
                        selected_alcohol = 0;
                        break;
                    case BEER:
                        bt_beer.performClick();
                        selected_alcohol = 1;
                        break;
                    case SOMAC:
                        bt_somac.performClick();
                        selected_alcohol = 2;
                        break;
                    case MAKGEOLLI:
                        bt_makgeolli.performClick();
                        selected_alcohol = 3;
                        break;
                    case LIQUOR:
                        bt_liquor.performClick();
                        selected_alcohol = 4;
                        break;

                }
            }

        }
        refreshBottleLayout();
        refreshGlassLayout();
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
        overridePendingTransition(android.R.anim.fade_in, R.anim.slide_out_down);
    }

    public void setBottleImage(int alcohol, boolean fill) {
        switch (alcohol) {
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
        text_bottle.setText(String.valueOf(bottle[selected_alcohol]));

        if (bottle[selected_alcohol] == 0) {
            setBottleImage(selected_alcohol, false);
            text_bottle.setVisibility(View.INVISIBLE);
        } else {
            setBottleImage(selected_alcohol, true);
            text_bottle.setVisibility(View.VISIBLE);
        }
    }

    public void setGlassImage(boolean fill) {
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
        text_glass.setText(String.valueOf(glass[selected_alcohol]));

        if (glass[selected_alcohol] == 0) {
            setGlassImage(false);
            text_glass.setVisibility(View.INVISIBLE);
        } else {
            setGlassImage(true);
            text_glass.setVisibility(View.VISIBLE);
        }
    }

    public void mOnClick(View view) {
        try {
            //close keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("Keyboard error", "Could not execute method of the activity");
        }
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
                for (int i = 0; i < 5; i++) {
                    DiaryData diaryData = new DiaryData(
                            date,
                            condition,
                            i,
                            et_note.getText().toString(),
                            bottle[i],
                            glass[i]);
                    if (update) {
                        MainActivity.dbHelper.updateItem(diaryData);
                    } else {
                        MainActivity.dbHelper.addItem(diaryData);

                    }
                }
                finishWithResult("back");
                break;
            case R.id.bt_delete:
                MainActivity.dbHelper.deleteItem(date);
                finishWithResult("back");
                break;
            case R.id.item_bt_plus_bottle:
                bottle[selected_alcohol] = (bottle[selected_alcohol] + 1) % 10;
                refreshBottleLayout();
                break;
            case R.id.item_bt_minus_bottle:
                if (bottle[selected_alcohol] == 0) {
                    bottle[selected_alcohol] = bottle[selected_alcohol] + 9;
                } else {
                    bottle[selected_alcohol] = (bottle[selected_alcohol] - 1) % 10;
                }
                refreshBottleLayout();
                break;
            case R.id.item_bt_plus_glass:
                glass[selected_alcohol] = (glass[selected_alcohol] + 1) % 10;
                refreshGlassLayout();
                break;
            case R.id.item_bt_minus_glass:

                if (glass[selected_alcohol] == 0) {
                    glass[selected_alcohol] = glass[selected_alcohol] + 9;
                } else {
                    glass[selected_alcohol] = (glass[selected_alcohol] - 1) % 10;
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
        try {
            //close keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("Keyboard error", "Could not execute method of the activity");
        }
        switch (view.getId()) {
            case R.id.bt_soju:
                setAlcoholButtonImageToDefault();
                bt_soju.setBackgroundResource(R.drawable.item_btn_pressed_soju);
                layout_glass.setVisibility(View.VISIBLE);
                text_glass.setVisibility(View.INVISIBLE);
                text_bottle.setVisibility(View.INVISIBLE);
                selected_alcohol = 0;
                image_bottle.setImageResource(R.drawable.icon_big_soju);
                image_glass.setImageResource(R.drawable.icon_big_soju_glass);
//                bottle[0] = 0;
//                glass[0] = 0;
                break;
            case R.id.bt_beer:
                setAlcoholButtonImageToDefault();
                bt_beer.setBackgroundResource(R.drawable.item_btn_pressed_beer);
                layout_glass.setVisibility(View.VISIBLE);
                text_glass.setVisibility(View.INVISIBLE);
                text_bottle.setVisibility(View.INVISIBLE);
                selected_alcohol = 1;
                image_bottle.setImageResource(R.drawable.icon_big_beer);
                image_glass.setImageResource(R.drawable.icon_big_beer_glass);
//                bottle[1] = 0;
//                glass[1] = 0;
                break;
            case R.id.bt_somac:
                setAlcoholButtonImageToDefault();
                bt_somac.setBackgroundResource(R.drawable.item_btn_pressed_somac);
                layout_glass.setVisibility(View.GONE);
                text_bottle.setVisibility(View.INVISIBLE);
                selected_alcohol = 2;
                image_bottle.setImageResource(R.drawable.icon_big_somac);
//                bottle[2] = 0;
//                glass[2] = 0;
                break;
            case R.id.bt_makgeolli:
                setAlcoholButtonImageToDefault();
                bt_makgeolli.setBackgroundResource(R.drawable.item_btn_pressed_makgeolli);
                layout_glass.setVisibility(View.VISIBLE);
                text_glass.setVisibility(View.INVISIBLE);
                text_bottle.setVisibility(View.INVISIBLE);
                selected_alcohol = 3;
                image_bottle.setImageResource(R.drawable.icon_big_makgeolli);
                image_glass.setImageResource(R.drawable.icon_big_makgeolli_bowl);
//                bottle[3] = 0;
//                glass[3] = 0;
                break;
            case R.id.bt_liquor:
                setAlcoholButtonImageToDefault();
                bt_liquor.setBackgroundResource(R.drawable.item_btn_pressed_liquor);
                layout_glass.setVisibility(View.GONE);
                text_bottle.setVisibility(View.INVISIBLE);
                selected_alcohol = 4;
                image_bottle.setImageResource(R.drawable.icon_big_liquor_glass);
//                bottle[4] = 0;
//                glass[4] = 0;
                break;
        }
    }

    public void onRadioClick(View view) {
        try {
            //close keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("Keyboard error", "Could not execute method of the activity");
        }
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

        bt_calendar = (ImageButton) findViewById(R.id.bottombar_bt_calendar);
        bt_diary = (ImageButton) findViewById(R.id.bottombar_bt_diary);
        bt_stats = (ImageButton) findViewById(R.id.bottombar_bt_stats);
    }

}