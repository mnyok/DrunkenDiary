package tourplatform.drunkdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by orc12 on 2015-12-22.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
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
        Log.i("SQLite", "Create DB");

        //create alcohol table
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ALCOHOL + "("
                + ALCOHOL_DATE + " DATE PRIMARY KEY," + ALCOHOL_KIND + " TEXT,"
                + ALCOHOL_BOTTLE + " INT," + ALCOHOL_GLASS + " INT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        //create diary table
        CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DIARY + "("
                + DIARY_DATE + " DATE PRIMARY KEY," + DIARY_CONDITION + " TEXT,"
                + DIARY_NOTE + " INT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

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

        ContentValues alcoholValues = new ContentValues();
        alcoholValues.put(ALCOHOL_DATE, diaryData.getDateString("-"));
        alcoholValues.put(ALCOHOL_KIND, diaryData.getAlcohol().name());
        alcoholValues.put(ALCOHOL_BOTTLE, diaryData.getBottle());
        alcoholValues.put(ALCOHOL_GLASS, diaryData.getGlass());

        // Inserting Row
        db.insert(TABLE_ALCOHOL, null, alcoholValues);

        ContentValues diaryValues = new ContentValues();
        diaryValues.put(DIARY_DATE, diaryData.getDateString("-"));
        diaryValues.put(DIARY_CONDITION, diaryData.getCondition().name());
        diaryValues.put(DIARY_NOTE, diaryData.getNote());

        // Inserting Row
        db.insert(TABLE_DIARY, null, diaryValues);

        db.close(); // Closing database connection
    }

    public DiaryData getItem(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        date = date.replace(".", "-");
        Log.i("SQLite", "getItem : " + date);

        Cursor alcoholCursor = db.query(TABLE_ALCOHOL,
                new String[]{ALCOHOL_DATE, ALCOHOL_KIND, ALCOHOL_BOTTLE, ALCOHOL_GLASS},
                ALCOHOL_DATE + "=?",
                new String[]{String.valueOf(date)}, null, null, null, null);
        if (alcoholCursor != null)
            alcoholCursor.moveToFirst();

        Cursor diaryCursor = db.query(TABLE_DIARY,
                new String[]{DIARY_DATE, DIARY_CONDITION, DIARY_NOTE},
                DIARY_DATE + "=?",
                new String[]{String.valueOf(date)}, null, null, null, null);
        if (diaryCursor != null) {
            diaryCursor.moveToFirst();

        }

        if (diaryCursor.getCount() != 0 && alcoholCursor.getCount() != 0) {
            return new DiaryData(alcoholCursor.getString(0),
                    Condition.valueOf(diaryCursor.getString(1)),
                    Alcohol.valueOf(alcoholCursor.getString(1)),
                    diaryCursor.getString(2),
                    alcoholCursor.getInt(2),
                    alcoholCursor.getInt(3));
        }
        return null;
    }

    public ArrayList<DiaryData> getAlcoholList(String month) {
        SQLiteDatabase db = this.getReadableDatabase();
        month = month.replace(".", "-");
        Log.i("SQLite", "getAlcoholList : " + month);

        Cursor alcoholCursor = db.query(TABLE_ALCOHOL,
                new String[]{ALCOHOL_DATE, ALCOHOL_KIND, ALCOHOL_BOTTLE, ALCOHOL_GLASS},
                "substr(" + ALCOHOL_DATE + ", 1, 7)" + "=?",
                new String[]{String.valueOf(month)}, null, null, null, null);
        if (alcoholCursor != null) {
            alcoholCursor.moveToFirst();
        } else {
            //no data? db error?
            return new ArrayList<>();
        }

        ArrayList<DiaryData> diaryList = new ArrayList<>();
        while (!alcoholCursor.isAfterLast()) {
            Log.i("SQLite", alcoholCursor.getString(0) + " " + alcoholCursor.getString(1)
                    + " " + alcoholCursor.getInt(2) + " " + alcoholCursor.getInt(3));
            diaryList.add(new DiaryData(alcoholCursor.getString(0),
                    null,
                    Alcohol.valueOf(alcoholCursor.getString(1)),
                    null,
                    alcoholCursor.getInt(2),
                    alcoholCursor.getInt(3)));

            alcoholCursor.moveToNext();
        }
        return diaryList;
    }

    public ArrayList<DiaryData> getItemList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.i("SQLite", "getItemList");

        Cursor alcoholCursor = db.query(TABLE_ALCOHOL,
                new String[]{ALCOHOL_DATE, ALCOHOL_KIND, ALCOHOL_BOTTLE, ALCOHOL_GLASS},
                null, null, null, null, DIARY_DATE + " DESC", null);

        if (alcoholCursor != null) {
            alcoholCursor.moveToFirst();
        } else {
            //no data? db error?
            return new ArrayList<>();
        }


        Cursor diaryCursor = db.query(TABLE_DIARY,
                new String[]{DIARY_DATE , DIARY_CONDITION, DIARY_NOTE},
                null, null, null, null, DIARY_DATE + " DESC", null);
        if (diaryCursor != null) {
            diaryCursor.moveToFirst();
        } else {
            //no data? db error?
            return new ArrayList<>();
        }

        ArrayList<DiaryData> diaryList = new ArrayList<>();
        while (!alcoholCursor.isAfterLast() && !diaryCursor.isAfterLast()) {
            Log.i("SQLite", alcoholCursor.getString(0) + " " + alcoholCursor.getString(1)
                    + " " + diaryCursor.getString(1) + " " + diaryCursor.getString(2)
                    + " " + alcoholCursor.getInt(2) + " " + alcoholCursor.getInt(3));

            diaryList.add(new DiaryData(alcoholCursor.getString(0),
                    Condition.valueOf(diaryCursor.getString(1)),
                    Alcohol.valueOf(alcoholCursor.getString(1)),
                    diaryCursor.getString(2),
                    alcoholCursor.getInt(2),
                    alcoholCursor.getInt(3)));

            alcoholCursor.moveToNext();
            diaryCursor.moveToNext();
        }

        alcoholCursor.close();
        diaryCursor.close();
        db.close();
        return diaryList;
    }

    public void updateItem(DiaryData diaryData) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("SQLite", "update " + diaryData.getDateString("-"));

        // update alcohol
        ContentValues alcoholValues = new ContentValues();
        alcoholValues.put(ALCOHOL_DATE, diaryData.getDateString("-"));
        alcoholValues.put(ALCOHOL_KIND, diaryData.getAlcohol().name());
        alcoholValues.put(ALCOHOL_BOTTLE, diaryData.getBottle());
        alcoholValues.put(ALCOHOL_GLASS, diaryData.getGlass());

        db.update(TABLE_ALCOHOL, alcoholValues, ALCOHOL_DATE + " = ?",
                new String[]{String.valueOf(diaryData.getDateString("-"))});


        // update diary
        ContentValues diaryValues = new ContentValues();
        diaryValues.put(DIARY_DATE, diaryData.getDateString("-"));
        diaryValues.put(DIARY_CONDITION, diaryData.getCondition().name());
        diaryValues.put(DIARY_NOTE, diaryData.getNote());

        db.update(TABLE_DIARY, diaryValues, DIARY_DATE + " = ?",
                new String[]{String.valueOf(diaryData.getDateString("-"))});

        db.close();
    }

    public void deleteItem(String date) {
        //replace date expression
        date = date.replace(".", "-");
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ALCOHOL, ALCOHOL_DATE + " = ?",
                new String[]{String.valueOf(date)});

        db.delete(TABLE_DIARY, ALCOHOL_DATE + " = ?",
                new String[]{String.valueOf(date)});
        db.close();
    }
}
