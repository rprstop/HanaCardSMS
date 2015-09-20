package com.project.htk.hanacardsms;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.telephony.SmsMessage;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static android.widget.Toast.LENGTH_SHORT;


public class SMSService extends Service {

    boolean mQuit;
    SMSService mParent;
    Handler mHandler;

    public SMSService(SMSService parent, Handler handler) {
        mParent = parent;
        mHandler = handler;
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiverBR);
        Toast.makeText(this, "Service End", LENGTH_SHORT).show();
        mQuit = true;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        registerReceiver(mReceiverBR, new IntentFilter(
                "android.provider.Telephony.SMS_RECEIVED"));
        Toast.makeText(this, "Hana Card SMS Service Start!", LENGTH_SHORT).show();
        mQuit = false;
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    BroadcastReceiver mReceiverBR = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = "";
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdus.length; i++) {
                    SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    result += "from" + msg.getOriginatingAddress() + " => " +
                            msg.getMessageBody() + "\n";
                }
//                mResult.setText("message : " + result);
//                Toast.makeText(this, result, LENGTH_SHORT).show();

            }
        }
    };

}
//
//    public void setNotification(String result) {
//        Intent intent = new Intent(SMSService.this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//        Notification.Builder builder = new Notification.Builder(this);
//
//        // 작은 아이콘 이미지.
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//
//        // 알림이 출력될 때 상단에 나오는 문구.
//        builder.setTicker("HanaCard SMS Service");
//
//        // 알림 출력 시간.
//        builder.setWhen(System.currentTimeMillis());
//
//        // 알림 제목.
//        builder.setContentTitle("HanaCard SMS Service!");
//
//        // 알림 내용.
//        builder.setContentText(result);
//
//        // 알림시 사운드, 진동, 불빛을 설정 가능.
//        builder.setDefaults(Notification.DEFAULT_LIGHTS);
//
//        // 알림 터치시 반응.
//        builder.setContentIntent(pendingIntent);
//
//        // 알림 터치시 반응 후 알림 삭제 여부.
//        builder.setAutoCancel(true);
//
//        // 행동 최대3개 등록 가능.
//        builder.addAction(R.mipmap.ic_launcher, "Show", pendingIntent);
//        builder.addAction(R.mipmap.ic_launcher, "Hide", pendingIntent);
//        builder.addAction(R.mipmap.ic_launcher, "Remove", pendingIntent);
//
//
//        // 고유ID로 알림을 생성.
//        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        nm.notify(123456, builder.build());
//    }
//}

//
//
//    public void onCreate() {
//        super.onCreate();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        registerReceiver(mReceiverBR, new IntentFilter(
//                "android.provider.Telephony.SMS_RECEIVED"));
//    }
//
//    protected void onPause() {
//        super.onPause();
//        unregisterReceiver(mReceiverBR);
//    }
//
//    BroadcastReceiver mReceiverBR = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String result = "";
//            Bundle bundle = intent.getExtras();
//            if(bundle != null) {
//                Object[] pdus = (Object[])bundle.get("pdus");
//                for(int i = 0; i < pdus.length; i++) {
//                    SmsMessage msg = SmsMessage.createFromPdu((byte[])pdus[i]);
//                    result += "from" + msg.getOriginatingAddress() + " => " +
//                            msg.getMessageBody() + "\n";
//                }
//                mResult.setText("message : " + result);
//
//            }
//        }
//    };
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}
