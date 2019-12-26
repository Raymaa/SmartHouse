package com.example.smarthome.Persent;

import android.util.Log;

import com.example.smarthome.Model.DataBean;
import com.example.smarthome.Model.DataCallBack;
import com.example.smarthome.Model.DataModel;
import com.example.smarthome.View.UiInterface;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by joel.
 * Date: 2019/6/26
 * Time: 11:03
 * Description:
 */
public class DataPresenter<V extends UiInterface>extends BasePresenter<V>{

    public void ReadCo2(final String user){
        if (weakReference.get() != null) {

            DataModel.getInstance().ReadCo2Value(user, new DataCallBack() {
                @Override
                public void success(List<DataBean> datalist) {
                    int i=0;
                    int[] values=new int[datalist.size()];
                    String[] times=new String[datalist.size()];
                    for (DataBean d:datalist){
                        values[i]=d.getValue();
                        times[i++]= new SimpleDateFormat("MM-dd HH:mm").format(d.getTime());

                    }
                    if (weakReference.get() != null) {
                        weakReference.get().ReadCo2(values, times);
                    }
                }

                @Override
                public void fail(String error) {
                    Log.i("Persenter", "Get Co2Values fail: "+error);
                }
            });

        }
    }


    public void ReadSmog(final String user){
        if (weakReference.get() != null) {
            DataModel.getInstance().ReadSmog(user, new DataCallBack() {
                @Override
                public void success(List<DataBean> datalist) {
                    int i = 0;
                    int[] values = new int[datalist.size()];
                    String[] times = new String[datalist.size()];
                    for (DataBean d : datalist) {
                        values[i] = d.getValue();
                        times[i++] = new SimpleDateFormat("MM-dd HH:mm").format(d.getTime());

                    }
                    if (weakReference.get() != null) {
                        weakReference.get().ReadSmog(values, times);
                    }
                }

                @Override
                public void fail(String error) {
                    Log.i("Persenter", "Get Co2Values fail: " + error);
                }
            });
        }
    }

    public void ReadTemperature(final String user){
        DataModel.getInstance().ReadTemperature(user, new DataCallBack() {
            @Override
            public void success(List<DataBean> datalist) {
                int i=0;
                int[] values=new int[datalist.size()];
                String[] times=new String[datalist.size()];
                for (DataBean d:datalist){
                    values[i]=d.getValue();
                    times[i++]= new SimpleDateFormat("MM-dd HH:mm").format(d.getTime());

                }
                if (weakReference.get() != null) {
                    weakReference.get().ReadTemperature(values,times);
                }
            }

            @Override
            public void fail(String error) {
                Log.i("Persenter", "ReadTemperature fail: "+error);
            }
        });
    }


}
