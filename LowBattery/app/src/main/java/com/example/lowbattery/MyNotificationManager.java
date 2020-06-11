package com.example.lowbattery;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import java.util.Date;

class MyNotificationManager {
    private static final String CHANNEL = "channelID";
    private static NotificationChannel channel;
    public static void addNotification(String title, String message,
                                       Context ctx, PendingIntent pintent)
    {
        createChannel(ctx);
        NotificationCompat.Builder builder = null;
        builder =
                new NotificationCompat.Builder(ctx,CHANNEL)
                        .setSmallIcon(R.drawable.ic_power)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setContentIntent(pintent).setLights(Color.parseColor("#FFFFFF"), 5000, 5000);


        NotificationManager notificationManager = (NotificationManager)
                ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        MediaPlayer player = MediaPlayer.create(ctx,R.raw.plucky);
        player.start();

        /*if (Build.VERSION.SDK_INT >= 26) {
            channel.enableLights(true);
        }*/
        int notificationId = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        notificationManager.notify(notificationId, builder.build());

    }

    public static void createChannel(Context ctx)
    {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        else
        { //26 or higher
            NotificationManager notificationManager =
                    (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
            channel = new NotificationChannel(CHANNEL,"name",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Description");

            notificationManager.createNotificationChannel(channel);
        }
    }
}
