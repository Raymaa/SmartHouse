package com.sgxy.smarthome.conn;

import java.sql.Timestamp;

public class DataBean {
	int value;
	Timestamp  time;
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
