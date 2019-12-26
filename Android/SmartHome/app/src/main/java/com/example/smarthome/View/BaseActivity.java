package com.example.smarthome.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.smarthome.Persent.BasePresenter;


/**
 * Created by 胡泽宇 on 2018/4/25.
 */

public abstract class BaseActivity<V ,T extends BasePresenter<V>> extends AppCompatActivity{

    //表示层的引用
    protected T basepresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basepresenter=choicePresent();
        basepresenter.attchView((V)this);

    }

    @Override
    protected void onStop() {
        basepresenter.unattchView();
        super.onStop();
    }

    @Override
    protected void onResume() {
        basepresenter.attchView((V)this);
        super.onResume();
    }

    protected T getPresenter() {
        return basepresenter;
    }

    public abstract T choicePresent();
    @Override
    protected void onDestroy() {

        basepresenter.unattchView();
        super.onDestroy();
    }
}
