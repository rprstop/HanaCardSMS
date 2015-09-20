package com.project.htk.hanacardsms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mOnClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.smsstart:
                intent = new Intent(this, SMSService.class);
                startService(intent);
                break;
            case R.id.smsend:
                intent = new Intent(this, SMSService.class);
                stopService(intent);
                break;
        }
    }
}
