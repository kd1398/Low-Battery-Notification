package com.example.lowbattery;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyNotify extends Service {

    private static final int NOTIFICATION_ID = 100;
    String title = "Low Battery :(";
    String detail = "Please put your phone in charging :)";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // register our receiver
        this.registerReceiver(this.batteryChangeReceiver, new IntentFilter(
                Intent.ACTION_BATTERY_CHANGED));
        return super.onStartCommand(intent, flags, startId);
    }

        private final BroadcastReceiver batteryChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, final Intent intent) {
            checkBatteryLevel(intent);
        }
    };

        private void checkBatteryLevel(Intent batteryChangeIntent) {
            // some calculations
            final int currLevel = batteryChangeIntent.getIntExtra(
                    BatteryManager.EXTRA_LEVEL, -1);
            final int maxLevel = batteryChangeIntent.getIntExtra(
                    BatteryManager.EXTRA_SCALE, -1);
            final int percentage = (int) Math.round((currLevel * 100.0) / maxLevel);

            Log.d("TAG", "current battery level: " + percentage);

            // low battery
            if (percentage <= 20) {
                Log.d("TAG", "low battery");
                Intent myintent = new Intent(this,MyNotifyActivity.class);
                myintent.putExtra("title",title);
                myintent.putExtra("detail",detail);
                PendingIntent pintent = PendingIntent.getActivity(this,100,myintent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                MyNotificationManager.addNotification(title,detail,this,pintent);
            }
        }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
