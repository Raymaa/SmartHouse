package com.example.smarthome.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by joel.
 * Date: 2019/5/19
 * Time: 10:45
 * Description:
 */
public interface DataRecevice {

    @GET("SmartHome/ReadCo2")
    Call< List<DataBean>> ReadCo2(@Query("user")String user);

    @GET("SmartHome/ReadLedState")
    Call< List<DataBean>> ReadLedState(@Query("user")String user);

    @GET("SmartHome/ReadSmog")
    Call< List<DataBean>> ReadSmog(@Query("user")String user);

    @GET("SmartHome/ReadTemperature")
    Call< List<DataBean>> ReadTemperature(@Query("user")String user);

    @GET("SmartHome/SetCmd")
    Call< List<DataBean>> SetCmd(@Query("type")String user,@Query("state")String state);

}
