package com.lvanyi.myday.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.lvanyi.myday.Database.Data.*;

public class DBHelper extends SQLiteOpenHelper {
    public static final String CREATE_TABLE = "create table "+DB_TABLE_NAME
            +"("+DB_COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +DB_COLUMN_DAYID+ " TEXT,"
            +DB_COLUMN_STARTDAY+" TEXT,"
            +DB_COLUMN_TITLE+" TEXT,"
            +DB_COLUMN_REMARK+" TEXT,"
            +DB_COLUMN_TYPE+" INT)";
    private static final String TAG = "DBHelper";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d(TAG, "----------------------onCreate----------------------");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("UPDATE test SET ");
    }
}
