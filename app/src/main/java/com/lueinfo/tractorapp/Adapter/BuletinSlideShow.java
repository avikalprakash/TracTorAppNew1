package com.lueinfo.tractorapp.Adapter;

/**
 * Created by Fujitsu on 08-09-2017.
 */

public class BuletinSlideShow {
    String item_id;
    String title;
    String sliderImage;
    String sort_order;
    String status;

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSliderImage(String sliderImage) {
        this.sliderImage = sliderImage;
    }

    public void setSort_order(String sort_order) {
        this.sort_order = sort_order;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItem_id() {

        return item_id;
    }

    public String getTitle() {
        return title;
    }

    public String getSliderImage() {
        return sliderImage;
    }

    public String getSort_order() {
        return sort_order;
    }

    public String getStatus() {
        return status;
    }
}
