package com.example.smarthome.Model;

import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joel.
 * Date: 2019/5/19
 * Time: 10:42
 * Description:
 */
public class DataModel {
    Retrofit restAdapter ;
    DataRecevice service ;

    private static volatile DataModel instance;
    private DataModel(){
        restAdapter = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.43.209:8080/")
                .build();
        service = restAdapter.create(DataRecevice.class);
    }


    public static DataModel getInstance(){
        //防止不必要的锁操作
        if(instance!=null){
            return instance;
        }
        synchronized (DataModel.class) {
            if (instance == null) {
                instance = new DataModel();
            }
            return instance;
        }
    }
    public void AllTaskCancel(){

    }
    public void ReadCo2Value(String username, final DataCallBack callBack) {

        final Call<List<DataBean>> query = service.ReadCo2(username);

        query.enqueue(new Callback<List<DataBean>>() {
            @Override
            public void onResponse(Call<List<DataBean>> call, Response<List<DataBean>> response) {
                List<DataBean> a = response.body();
                callBack.success(a);

            }
            @Override
            public void onFailure(Call<List<DataBean>> call, Throwable t) {
                callBack.fail(t.toString());

            }
        });
    }


    public void ReadLedState(String username, final DataCallBack callBack) {

        final Call<List<DataBean>> query = service.ReadLedState(username);

        query.enqueue(new Callback<List<DataBean>>() {
            @Override
            public void onResponse(Call<List<DataBean>> call, Response<List<DataBean>> response) {
                List<DataBean> a = response.body();

                callBack.success(a);
            }
            @Override
            public void onFailure(Call<List<DataBean>> call, Throwable t) {

                callBack.fail(t.toString());
            }
        });
    }


    public void ReadSmog(String username, final DataCallBack callBack) {

        final Call<List<DataBean>> query = service.ReadSmog(username);

        query.enqueue(new Callback<List<DataBean>>() {
            @Override
            public void onResponse(Call<List<DataBean>> call, Response<List<DataBean>> response) {
                List<DataBean> a = response.body();

                callBack.success(a);
            }
            @Override
            public void onFailure(Call<List<DataBean>> call, Throwable t) {

                callBack.fail(t.toString());

            }
        });
    }

    public void ReadTemperature(String username, final DataCallBack callBack) {

        final Call<List<DataBean>> query = service.ReadTemperature(username);

        query.enqueue(new Callback<List<DataBean>>() {
            @Override
            public void onResponse(Call<List<DataBean>> call, Response<List<DataBean>> response) {
                List<DataBean> a = response.body();

                callBack.success(a);
            }
            @Override
            public void onFailure(Call<List<DataBean>> call, Throwable t) {

                callBack.fail(t.toString());
            }
        });
    }


    /***
     * 上传命令
     * @param type
     * @param state
     * @param callBack
     */
    public void SetCmd(String type,String state, final DataCallBack callBack) {

        final Call<List<DataBean>> query = service.SetCmd(type,state);

        query.enqueue(new Callback<List<DataBean>>() {
            @Override
            public void onResponse(Call<List<DataBean>> call, Response<List<DataBean>> response) {

            }
            @Override
            public void onFailure(Call<List<DataBean>> call, Throwable t) {

                callBack.fail(t.toString());
            }
        });
    }


}
