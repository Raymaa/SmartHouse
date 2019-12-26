package com.example.smarthome.Ui.Activity;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;


import com.example.smarthome.R;

/**
 * Created by joel.
 * Date: 2019/6/8
 * Time: 19:23
 * Description:
 */
public class Aty_Setting  extends PreferenceActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_setting);
        addPreferencesFromResource(R.xml.aty_setting);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aty_Setting.this.finish();
            }
        });

    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        Log.i("SettingAty", "onPreferenceTreeClick: "+preference.getKey());
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
