package com.fintechhackathon14.paybus.sms;

import static com.fintechhackathon14.paybus.util.Constants.INTENT_SERVICE_NAME;
import static com.fintechhackathon14.paybus.util.Constants.INTENT_SMS_BODY;
import static com.fintechhackathon14.paybus.util.Constants.LOG_NAME;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

import com.fintechhackathon14.paybus.MainActivity;
import com.fintechhackathon14.paybus.R;
import com.fintechhackathon14.paybus.entity.Ticket;

public class SmsService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Ticket ticket = new Ticket();
        ticket.setServiceName(intent.getStringExtra(INTENT_SERVICE_NAME));

        parseTextSms(intent.getStringExtra(INTENT_SMS_BODY));

        return START_STICKY;
    }

    private void parseTextSms(String smsBody) {

    }

    private void showNotification(String text) {
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Context context = getApplicationContext();
        Notification.Builder builder = new Notification.Builder(context)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText(text)
                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(0, builder.build());
    }


}
