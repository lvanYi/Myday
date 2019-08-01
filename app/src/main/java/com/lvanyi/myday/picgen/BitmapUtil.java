package com.lvanyi.myday.picgen;

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtil {
    public static final String TAG = "BitmapUtil";


    public static boolean saveImage(Bitmap bitmap, String absPath, String appName) {
        return saveImage(bitmap, absPath, appName, 100);
    }

    public static boolean saveImage(Bitmap bitmap, String absPath, String appName, int quality) {
        if (!createNewFile(absPath,appName)) {
            Log.w(TAG, "create file failed.");
            return false;
        }
        try {
            File outFile = new File(absPath);
            FileOutputStream fos = new FileOutputStream(outFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bitmap.compress(Bitmap.CompressFormat.PNG, quality, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "failed to write image content");
            return false;
        }


        return true;
    }


    private static boolean createNewFile(String path,String appName) {
        Log.d(TAG, "createNewFile: "+appName);
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath() + "/"+appName+"/";
        File filehead = new File(filePath);
        if (!filehead.exists()) {
            filehead.mkdirs();
        }
        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        boolean isOk = false;
        try {
            isOk = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isOk;
    }
}
