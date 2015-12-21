package tourplatform.drunkdiary.Activity;

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

import tourplatform.drunkdiary.Alcohol;
import tourplatform.drunkdiary.Font;
import tourplatform.drunkdiary.R;

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

    EditText et_note;

    String date;
    Alcohol selected_alcohol = null;
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
                if (fill) image_bottle.setImageResource(R.drawable.icon_big_liquor_pressed);
                else image_bottle.setImageResource(R.drawable.icon_big_liquor);
                break;
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
                if (fill) image_glass.setImageResource(R.drawable.icon_big_liquor_glass_pressed);
                else image_glass.setImageResource(R.drawable.icon_big_liquor_glass);
                break;
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
                finishWithResult("diary");
                break;
            case R.id.bottombar_bt_stats:
                finishWithResult("calendar");
                break;
            case R.id.bt_save:
                //TODO : save
                finishWithResult("back");
                break;
            case R.id.bt_delete:
                //TODO : delete
                finishWithResult("back");
                break;
            case R.id.item_bt_plus_bottle:
                if (bottle == 0) {
                    setBottleImage(selected_alcohol, true);
                    text_bottle.setVisibility(View.VISIBLE);
                }

                bottle = (bottle + 1) % 10;
                text_bottle.setText(String.valueOf(bottle));
                Log.i("bottle", String.valueOf(bottle));
                if (bottle == 0) {
                    setBottleImage(selected_alcohol, false);
                    text_bottle.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.item_bt_minus_bottle:
                if (bottle == 0) {
                    setBottleImage(selected_alcohol, true);
                    text_bottle.setVisibility(View.VISIBLE);
                }

                if(bottle == 0){
                    bottle = bottle + 9;
                } else {
                    bottle = (bottle - 1) % 10;
                }
                text_bottle.setText(String.valueOf(bottle));
                Log.i("bottle", String.valueOf(bottle));
                if (bottle == 0) {
                    setBottleImage(selected_alcohol, false);
                    text_bottle.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.item_bt_plus_glass:
                if (glass == 0) {
                    setGlassImage(selected_alcohol, true);
                    text_glass.setVisibility(View.VISIBLE);
                }

                glass = (glass + 1) % 10;
                text_glass.setText(String.valueOf(glass));
                Log.i("glass", String.valueOf(glass));
                if (glass == 0) {
                    setGlassImage(selected_alcohol, false);
                    text_glass.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.item_bt_minus_glass:
                if (glass == 0) {
                    setGlassImage(selected_alcohol, true);
                    text_glass.setVisibility(View.VISIBLE);
                }

                if(glass == 0){
                    glass = glass + 9;
                } else {
                    glass = (glass - 1) % 10;
                }
                text_glass.setText(String.valueOf(glass));
                Log.i("glass", String.valueOf(glass));
                if (glass == 0) {
                    setGlassImage(selected_alcohol, false);
                    text_glass.setVisibility(View.INVISIBLE);
                }
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
                layout_glass.setVisibility(View.VISIBLE);
                text_glass.setVisibility(View.INVISIBLE);
                text_bottle.setVisibility(View.INVISIBLE);
                selected_alcohol = Alcohol.LIQUOR;
                image_bottle.setImageResource(R.drawable.icon_big_liquor);
                image_glass.setImageResource(R.drawable.icon_big_liquor_glass);
                bottle = 0;
                glass = 0;
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
    }

}