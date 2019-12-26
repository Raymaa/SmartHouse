package com.example.smarthome.Ui.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;


import com.example.smarthome.Persent.MainPresenter;
import com.example.smarthome.R;

import com.example.smarthome.View.BaseActivity;
import com.example.smarthome.View.UiInterface;
import com.hanks.htextview.evaporate.EvaporateTextView;

/**
 * Created by joel.
 * Date: 2019/6/2
 * Time: 14:59
 * Description:
 */
public class Aty_Main extends BaseActivity<UiInterface, MainPresenter<UiInterface>>implements UiInterface {
    EvaporateTextView etv;
    Toolbar toolbar;
    DrawerLayout dl;
    NavigationView nv;
    ImageButton ibtn_menu;
    ImageButton ibtn_setting;

    CardView mtem;
    CardView msmog;
    CardView mwet;

    //数据刷新
    EvaporateTextView evt_wet;
    EvaporateTextView evt_wet_tim;

    EvaporateTextView evt_smog;
    EvaporateTextView evt_smog_tim;

    EvaporateTextView evt_tem;
    EvaporateTextView evt_tem_tim;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("");
        init();
        Events();
        getPresenter().ReadCo2("hzy");
        //  getPresenter().ReadLedState("hzy");
        getPresenter().ReadSmog("hzy");
        getPresenter().ReadTemperature("hzy");
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onStop() {
    //    getPresenter().StopTask();
        super.onStop();
    }

    private void init() {
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("");
        this.setSupportActionBar(toolbar);
        ibtn_menu=toolbar.findViewById(R.id.menu_show);
        dl=findViewById(R.id.aty_main_drawerlayout);
        nv=findViewById(R.id.aty_main_navigationView);
        ibtn_setting=toolbar.findViewById(R.id.menu_setting);
        etv=findViewById(R.id.etv_tem);
        msmog=findViewById(R.id.aty_main_smog);
        mtem=findViewById(R.id.aty_main_tem);
        mwet=findViewById(R.id.aty_main_wet);

        evt_wet=findViewById(R.id.etv_wet);
        evt_wet_tim=findViewById(R.id.etv_wet_tim);


        evt_tem=findViewById(R.id.etv_tem);
        evt_tem_tim=findViewById(R.id.etv_tem_tim);


        evt_smog=findViewById(R.id.etv_smog);
        evt_smog_tim=findViewById(R.id.etv_smog_tim);
    }
    private void Events() {

        ibtn_menu .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dl.openDrawer(nv);
            }
        });
        ibtn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aty_Main.this.startActivity(new Intent(Aty_Main.this,Aty_Setting.class));
            }
        });
        msmog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Main", "onClick: somg");
                startActivity(new Intent(Aty_Main.this, Aty_ShowSmogData.class));
            }
        });
        mwet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Main", "onClick: wet");
                startActivity(new Intent(Aty_Main.this, Aty_ShowWetData.class));

            }
        });
        mtem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Main", "onClick: mtem");
                startActivity(new Intent(Aty_Main.this, Aty_ShowTemData.class));

            }
        });

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


//                    item.setChecked(true);设置后分组中的按钮按下后会消失
                switch (item.getItemId()) {
                    case R.id.btn_cmd:
                        //发送命令
                        Aty_Main.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //
                                ShowCreateTitle();
                            }
                        });


                        break;



                }
                dl.closeDrawers();
                return true;
            }
        });



    }



    @Override
    public MainPresenter choicePresent() {
        return new MainPresenter();
    }

    @Override
    public void ReadCo2(int[] values, String[] times) {
        Log.i("MainAty", "ReadCo2: "+values.length+" "+times.length);
        evt_wet.animateText(values[0]+"");
        evt_wet_tim.animateText(times[0]);

    }

    @Override
    public void ReadLedState(int i, String times) {
        Log.i("MainAty", "ReadLedState: "+i+" "+times);
    }

    @Override
    public void ReadSmog(int[] values, String[] times) {
        Log.i("MainAty", "ReadSmog: "+values.length+" "+times.length);
        evt_smog.animateText(values[0]+"");
        evt_smog_tim.animateText(times[0]);
    }

    @Override
    public void ReadTemperature(int[] values, String[] times) {
        Log.i("MainAty", "ReadTemperature: "+values.length+" "+times.length);

        evt_tem.animateText(values[0]+"");
        evt_tem_tim.animateText(times[0]);
    }

    private void ShowCreateTitle() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("发送指令");
        final View view = View.inflate(this, R.layout.send_cmd, null);

        builder.setView(view);
        final EditText editText = view.findViewById(R.id.type);
        final EditText editText2 = view.findViewById(R.id.state);
        final Dialog dialog = builder.create();

        view.findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type= editText.getText().toString();
                String state= editText2.getText().toString();
                getPresenter().SetCmd(type,state);
                dialog.dismiss();
            }
        });
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.alpha = 1f;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }

}
