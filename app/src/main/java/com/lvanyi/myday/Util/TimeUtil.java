package com.lvanyi.myday.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具类
 */


public class TimeUtil {
    private static final String TAG = "TimeUtil";

    /**
     * 计算日期差，不能跨年计算
     *
     * @param startDate 起始时间
     * @param endDate   结束时间
     * @return string类型时间差
     */
    public static String getDateDiff(Date startDate, Date endDate) {

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(startDate);

        int day1 = mCalendar.get(Calendar.DAY_OF_YEAR);

        mCalendar.setTime(endDate);

        int day2 = mCalendar.get(Calendar.DAY_OF_YEAR);

        return String.valueOf(day2 - day1);
    }

    /**
     * 计算日期差
     *
     * @param startDate
     * @param endDate
     * @return string类型时间差
     */
    public static String getnewDateDiff(Date startDate, Date endDate) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fromDays = simpleDateFormat.format(startDate);
        String toDayS = simpleDateFormat.format(endDate);

        long fromDate = 0;
        long toDate = 0;

        try {
            fromDate = simpleDateFormat.parse(fromDays).getTime();
            toDate = simpleDateFormat.parse(toDayS).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (fromDate < toDate) {
            int days = (int) ((toDate - fromDate) / (1000 * 60 * 60 * 24));
            return "已过去" + String.valueOf(days)+ "天";
        } else if (fromDate == toDate) {
            return "就是今天";
        } else {
            int days = (int) ((fromDate - toDate) / (1000 * 60 * 60 * 24));
            return "还剩" + String.valueOf(days) + "天";
        }
    }
}
