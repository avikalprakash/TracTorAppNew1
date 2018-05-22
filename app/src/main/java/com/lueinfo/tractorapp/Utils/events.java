package com.lueinfo.tractorapp.Utils;

/**
 * Created by lue on 26-09-2017.
 */

public class events {

    String timeevnt;
    String detailevnt;
    String eventtitle;


    public events() {
    }

    public events(String timeevnt, String detailevnt, String eventtitle) {
        this.timeevnt = timeevnt;
        this.detailevnt = detailevnt;
        this.eventtitle = eventtitle;
    }

    public String getTimeevnt() {
        return timeevnt;
    }

    public void setTimeevnt(String timeevnt) {
        this.timeevnt = timeevnt;
    }

    public String getDetailevnt() {
        return detailevnt;
    }

    public void setDetailevnt(String detailevnt) {
        this.detailevnt = detailevnt;
    }

    public String getEventtitle() {
        return eventtitle;
    }

    public void setEventtitle(String eventtitle) {
        this.eventtitle = eventtitle;
    }
}

