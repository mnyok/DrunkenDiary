package tourplatform.drunkdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by orc12 on 2015-12-22.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "diaryManager";

    //table name
    private static final String TABLE_ALCOHOL = "alcohol";
    private static final String TABLE_DIARY = "diary";

    //alcohol table attribute name
    private static final String ALCOHOL_DATE = "date";
    private static final String ALCOHOL_KIND = "kind";
    private static final String ALCOHOL_BOTTLE = "bottle";
    private static final String ALCOHOL_GLASS = "glass";

    //diary table attribute name
    private static final String DIARY_DATE = "date";
    private static final String DIARY_CONDITION = "condition";
    private static final String DIARY_NOTE = "note";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create alcohol table
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ALCOHOL + "("
                + ALCOHOL_DATE + " DATE PRIMARY KEY," + ALCOHOL_KIND + " TEXT,"
                + ALCOHOL_BOTTLE + " INT," + ALCOHOL_GLASS + " INT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        //create diary table
        CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DIARY + "("
                + DIARY_DATE + " DATE PRIMARY KEY," + DIARY_CONDITION + " TEXT,"
                + DIARY_NOTE + " INT" + ")";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALCOHOL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIARY);

        // Create tables again
        onCreate(db);
    }

    // insert table
    public void addItem(DiaryData diaryData) {
        SQLiteDatabase db = this.getWritableDatabase();

        //TODO : Think about how to save date
        ContentValues alcoholValues = new ContentValues();
        alcoholValues.put(ALCOHOL_DATE, diaryData.getDateString("-"));
        alcoholValues.put(ALCOHOL_KIND, diaryData.getAlcohol().name());
        alcoholValues.put(ALCOHOL_BOTTLE, diaryData.getBottle());
        alcoholValues.put(ALCOHOL_GLASS, diaryData.getGlass());

        // Inserting Row
        db.insert(TABLE_ALCOHOL, null, alcoholValues);

        //TODO : Think about how to save date
        ContentValues diaryValues = new ContentValues();
        diaryValues.put(DIARY_DATE, diaryData.getDateString("-"));
        diaryValues.put(DIARY_CONDITION, diaryData.getCondition().name());
        diaryValues.put(DIARY_NOTE, diaryData.getNote());

        // Inserting Row
        db.insert(TABLE_DIARY, null, diaryValues);

        db.close(); // Closing database connection
    }

//    public DiaryData getItem(String date) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
//                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
//                new String[] { String.valueOf(date) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
////        DiaryData contact = new DiaryData(Integer.parseInt(cursor.getString(0)),
////                cursor.getString(1), cursor.getString(2));
//        return contact;
//    }
}
