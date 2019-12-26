package com.example.smarthome.Model;

import java.util.List;

/**
 * Created by joel.
 * Date: 2019/5/19
 * Time: 17:16
 * Description:数据回调
 */
public interface DataCallBack {
    public void success(List<DataBean> datalist);
    public void fail(String error);
}
