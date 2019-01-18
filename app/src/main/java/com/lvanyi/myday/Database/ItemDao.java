package com.lvanyi.myday.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lvanyi.myday.Model.ItemModel;
import com.lvanyi.myday.Util.ValuesTransformUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {
    private static final String TAG = "ItemDao";
    private DBHelper dbHelper;
    private Context context;

    public ItemDao(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
    }

    public List<ItemModel> queryAll(){
        SQLiteDatabase sqLiteDatabase = null;
        List<ItemModel>  list = new ArrayList<ItemModel>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sqLiteDatabase = dbHelper.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.query(Data.DB_TABLE_NAME,null,null,null,null,null,null);
            if(cursor.moveToFirst()){
                do{
                    list.add(new ItemModel(simpleDateFormat.parse(cursor.getString(cursor.getColumnIndex(Data.DB_COLUMN_STARTDAY))),
                            cursor.getString(cursor.getColumnIndex(Data.DB_COLUMN_TITLE)),
                            cursor.getString(cursor.getColumnIndex(Data.DB_COLUMN_REMARK))));
                }while (cursor.moveToNext());
            }
            Log.d(TAG, "queryAll is ok");
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            sqLiteDatabase.close();
        }
    }

    public void insert(ItemModel itemModel){
        SQLiteDatabase sqLiteDatabase = null;
        try {
            sqLiteDatabase = dbHelper.getWritableDatabase();
            sqLiteDatabase.insert(Data.DB_TABLE_NAME,null, ValuesTransformUtil.itemToContentValues(itemModel));
            Log.d(TAG, "insert: add is ok");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (sqLiteDatabase!=null){
                sqLiteDatabase.close();
            }
        }
    }
}
