package com.carvindustries.prayerjournal;

/**
 * Created by rmainard on 6/24/2016.
 */
public class PrayerEntry {
    private String text;
    private String Date;
    private int pid;
    public PrayerEntry(String txt, String dt, int id){
        setText(txt);
        setDate(dt);
        setPid(id);
    }

    @Override
    public String toString() {
        return "PrayerEntry{" +
                "text='" + text + '\'' +
                ", Date='" + Date + '\'' +
                ", pid=" + pid +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
