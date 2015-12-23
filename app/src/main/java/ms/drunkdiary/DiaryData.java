package ms.drunkdiary;

import java.util.Calendar;

/**
 * Created by orc12 on 2015-12-21.
 */
public class DiaryData {
    private Calendar date = Calendar.getInstance();
    private Condition condition;
    private Alcohol alcohol;
    private String note;
    private int bottle;
    private int glass;
    //make alcohol, bottle, glass to ArrayList if want to multiple select

    public DiaryData() {

    }

    public DiaryData(String dateString, Condition condition, Alcohol alcohol, String note, int bottle, int glass) {

        //calendar's month is start at 0
        dateString = dateString.replace(".", "-");
        date.set(Integer.parseInt(dateString.split("[-]")[0]),
                Integer.parseInt(dateString.split("[-]")[1]) - 1,
                Integer.parseInt(dateString.split("[-]")[2]));

        this.condition = condition;
        this.alcohol = alcohol;
        this.note = note;
        this.bottle = bottle;
        this.glass = glass;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Calendar getDate() {
        return date;
    }

    public String getDateString(String divider) {
        //요일 계산
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH) + 1;
        int day = date.get(Calendar.DAY_OF_MONTH);

        if(month >= 10){
            return year + divider + month + divider + day;
        } else {
            return year + divider + "0" + month + divider + day;
        }

    }

    public String getDateWeekString(String divider){
        int dayNum = date.get(Calendar.DAY_OF_WEEK);
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

        return getDateString(divider) + " " + dayOfWeek;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Alcohol getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Alcohol alcohol) {
        this.alcohol = alcohol;
    }

    public int getBottle() {
        return bottle;
    }

    public void setBottle(int bottle) {
        this.bottle = bottle;
    }

    public int getGlass() {
        return glass;
    }

    public void setGlass(int glass) {
        this.glass = glass;
    }
}
