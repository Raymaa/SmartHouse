package com.example.smarthome.Model;

import java.sql.Timestamp;

/**
 * Created by joel.
 * Date: 2019/5/19
 * Time: 10:46
 * Description:
 */
public class DataBean {

    int value;
    Timestamp time;
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp time) {
        this.time = time;
    }
}
