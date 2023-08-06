package ir.solid.reports;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "reading_assistant.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_reports";
    private static final String COLUMN_ID = "_id";

    // Date : {year, month, day}
    private static final String COLUMN_DATE = "app_date";

    // Lecture : {like Hesaban, Fizik, Hendese and ...}
    private static final String COLUMN_LECTURE = "app_lecture";

    // Section : {like moadele mosalasati, etehad mosalasati and ...}
    private static final String COLUMN_SECTION = "app_section";

    // Source : {like IQ, Nardebam, Jozve Arian Heidari, Youtube and ...}
    private static final String COLUMN_SOURCE = "app_source";

    private static final String COLUMN_DURATION = "app_duration";
    private static final String COLUMN_PAGES = "app_pages";

    // Specific Detail For Function
    // functions : {Read, Repetition, Test, Problem Solving, Research and so on}
    private static final String COLUMN_FUNCTION = "app_function";


    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_LECTURE + " TEXT, " +
                        COLUMN_SECTION + " TEXT, " +
                        COLUMN_SOURCE + " TEXT, " +
                        COLUMN_DURATION + " TEXT, " +
                        COLUMN_PAGES + " TEXT, " +
                        COLUMN_FUNCTION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addLog(String date, String lecture, String section,
                       String source, String duration, String pages, String function) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_LECTURE, lecture);
        cv.put(COLUMN_SECTION, section);
        cv.put(COLUMN_SOURCE, source);
        cv.put(COLUMN_DURATION, duration);
        cv.put(COLUMN_PAGES, pages);
        cv.put(COLUMN_FUNCTION, function);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateLog(String row_id, String date, String lecture, String section,
                          String source, String duration, String pages, String function) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_LECTURE, lecture);
        cv.put(COLUMN_SECTION, section);
        cv.put(COLUMN_SOURCE, source);
        cv.put(COLUMN_DURATION, duration);
        cv.put(COLUMN_PAGES, pages);
        cv.put(COLUMN_FUNCTION, function);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor search(String date) {
        String query = String.format("SELECT * FROM "
                + TABLE_NAME
                + " WHERE "
                + COLUMN_DATE + " = '%s'", date);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}