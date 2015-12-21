package tourplatform.drunkdiary;

/**
 * Created by orc12 on 2015-12-21.
 */
public class DiaryData {
    private String date;
    private Condition condition;
    private Alcohol alcohol;
    private String note;

    public DiaryData(){

    }
    public DiaryData(String date, Condition condition, Alcohol alcohol, String note){
        this.date = date;
        this.condition = condition;
        this.alcohol = alcohol;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}
