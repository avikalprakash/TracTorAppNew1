package com.lueinfo.tractorapp.Utils;

/**
 * Created by lue on 12-09-2017.
 */

public class PromotionModel {

    String image;
    String detailtxt;
    String timetxt;
    String caltxt;
    String datemain;
    String itemid;
    String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public static final int HEADER_ITEM_TYPE = 0;
    public static final int GRID_ITEM_TYPE = 1;

    public PromotionModel() {

    }


    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public PromotionModel(String image, String detailtxt, String timetxt, String caltxt, String datemain, String itemid) {
        this.image = image;
        this.detailtxt = detailtxt;
        this.timetxt = timetxt;
        this.caltxt = caltxt;
        this.datemain = datemain;
        this.itemid = itemid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetailtxt() {
        return detailtxt;
    }

    public void setDetailtxt(String detailtxt) {
        this.detailtxt = detailtxt;
    }

    public String getTimetxt() {
        return timetxt;
    }

    public void setTimetxt(String timetxt) {
        this.timetxt = timetxt;
    }

    public String getCaltxt() {
        return caltxt;
    }

    public void setCaltxt(String caltxt) {
        this.caltxt = caltxt;
    }

    public String getDatemain() {
        return datemain;
    }

    public void setDatemain(String datemain) {
        this.datemain = datemain;
    }


}
