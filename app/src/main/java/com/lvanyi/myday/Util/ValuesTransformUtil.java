package com.lvanyi.myday.Util;

import android.content.ContentValues;

import com.lvanyi.myday.Database.Data;
import com.lvanyi.myday.Model.ItemModel;

import java.text.SimpleDateFormat;

public class ValuesTransformUtil {


    public static ContentValues itemToContentValues(ItemModel itemModel) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ContentValues contentValues = new ContentValues();
        contentValues.put(Data.DB_COLUMN_DAYID, String.valueOf(itemModel.getId()));
        contentValues.put(Data.DB_COLUMN_STARTDAY, simpleDateFormat.format(itemModel.getStartDate()));
        contentValues.put(Data.DB_COLUMN_TITLE, itemModel.getTitle());
        contentValues.put(Data.DB_COLUMN_REMARK, itemModel.getRemark());
        contentValues.put(Data.DB_COLUMN_TYPE, itemModel.getType());
        return contentValues;
    }


}
