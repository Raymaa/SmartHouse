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
 * Created by 胡泽宇 on 2018/4/25.
 */

public  class MainPresenter<V extends UiInterface>extends BasePresenter<V>{

    public void ReadCo2(final String user){
        if (weakReference.get() != null) {
            //获取Co2信息 Rxjava实现轮询
            //15秒访问一次
            Observable.interval(3,5, TimeUnit.SECONDS)
                    .doOnNext(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Exception {
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
            }).subscribe(new Observer<Long>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Long aLong) {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });


        }
    }
    public void StopTask(){
        DataModel.getInstance().AllTaskCancel();
    }
    public void ReadLedState(final String user){
        if (weakReference.get() != null) {
            //获取Co2信息 Rxjava实现轮询
            //15秒访问一次
            Observable.interval(3,15, TimeUnit.SECONDS)
                    .doOnNext(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            DataModel.getInstance().ReadLedState(user, new DataCallBack() {
                                @Override
                                public void success(List<DataBean> datalist) {
                                    int i=0;
                                    int[] values=new int[datalist.size()];
                                    String[] times=new String[datalist.size()];
                                    for (DataBean d:datalist){
                                        values[i]=d.getValue();
                                        times[i++]= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d.getTime());

                                    }
                                    if (weakReference.get() != null) {
                                        weakReference.get().ReadLedState(values[0],times[0]);

                                    }
                                }

                                @Override
                                public void fail(String error) {
                                    Log.i("Persenter", "Get Co2Values fail: "+error);
                                }
                            });
                        }
                    }).subscribe(new Observer<Long>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Long aLong) {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });

        }
    }

    public void ReadSmog(final String user){
        if (weakReference.get() != null) {
            //获取Co2信息 Rxjava实现轮询
            //15秒访问一次
            Observable.interval(3,5, TimeUnit.SECONDS)
                    .doOnNext(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            DataModel.getInstance().ReadSmog(user, new DataCallBack() {
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
                                     weakReference.get().ReadSmog(values,times);
                                    }
                                }

                                @Override
                                public void fail(String error) {
                                    Log.i("Persenter", "Get Co2Values fail: "+error);
                                }
                            });
                        }
                    }).subscribe(new Observer<Long>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Long aLong) {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });

        }
    }

    public void ReadTemperature(final String user){
        if (weakReference.get() != null) {
            //获取Co2信息 Rxjava实现轮询
            //15秒访问一次
            Observable.interval(3,5, TimeUnit.SECONDS)
                    .doOnNext(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
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
                    }).subscribe(new Observer<Long>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Long aLong) {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });

        }
    }

    public void SetCmd(String type,String state){
        DataModel.getInstance().SetCmd(type, state, new DataCallBack() {
            @Override
            public void success(List<DataBean> datalist) {

            }

            @Override
            public void fail(String error) {
                Log.i("Cmd", "fail: ");
            }
        });
    }
}
