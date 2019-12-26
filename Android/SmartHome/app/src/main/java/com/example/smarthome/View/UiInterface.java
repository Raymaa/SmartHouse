package com.example.smarthome.View;

/**
 * Created by 胡泽宇 on 2018/4/25.
 */

public interface UiInterface {
    public void ReadCo2(int[] values,String[] times);
    public void ReadLedState(int i,String times);
    public void ReadSmog(int[] values,String[] times);
    public void ReadTemperature(int[] values,String[] times);

}
