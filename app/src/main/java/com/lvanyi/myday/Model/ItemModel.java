package com.lvanyi.myday.Model;

import java.util.Date;

public class ItemModel {
    private int id;
    private Date startDate;
    private String title;
    private String remark;
    private int type;
    private String dateDiff = "";

    public ItemModel(){

    }

    public ItemModel(int id, Date startDate, String title, String remark, int type) {
        this.id = id;
        this.startDate = startDate;
        this.title = title;
        this.remark = remark;
        this.type = type;
    }

    public ItemModel(Date startDate, String title, String remark) {
        this.startDate = startDate;
        this.title = title;
        this.remark = remark;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static ItemModel getItemModel(Date startDate, String title, String remark){
        return new ItemModel(startDate, title, remark);
    }

    public String getDateDiff() {
        return dateDiff;
    }

    public void setDateDiff(String dateDiff) {
        this.dateDiff = dateDiff;
    }
}
