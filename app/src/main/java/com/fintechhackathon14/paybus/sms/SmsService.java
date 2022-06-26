package com.fintechhackathon14.paybus.sms;

import static com.fintechhackathon14.paybus.util.Constants.INTENT_SERVICE_NAME;
import static com.fintechhackathon14.paybus.util.Constants.INTENT_SMS_BODY;
import static com.fintechhackathon14.paybus.util.Constants.LOG_NAME;
import static com.fintechhackathon14.paybus.util.Constants.NAME_ONAY;
import static com.fintechhackathon14.paybus.util.Constants.NAME_TULPAR_CARD;
import static com.fintechhackathon14.paybus.util.ServiceName.SERVICE_NAME;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

import com.fintechhackathon14.paybus.MainActivity;
import com.fintechhackathon14.paybus.R;
import com.fintechhackathon14.paybus.entity.Ticket;
import com.fintechhackathon14.paybus.ticket.presenter.TicketPresenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SmsService extends Service {

    private Ticket ticket;
    private TicketPresenter ticketPresenter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ticketPresenter = new TicketPresenter(getBaseContext());
        ticket = new Ticket();
        ticket.setServiceName(SERVICE_NAME.get(intent.getStringExtra(INTENT_SERVICE_NAME)));

        parseTextSms(intent.getStringExtra(INTENT_SMS_BODY), intent.getStringExtra(INTENT_SERVICE_NAME));

        return START_STICKY;
    }

    private void parseTextSms(String smsBody, String numberOperator) {
        if (ticket.getServiceName().equals(NAME_ONAY)) {

        }

        if (ticket.getServiceName().equals(NAME_TULPAR_CARD)) {
            String[] detailSmsBody = smsBody.split("\n");
            ticket.setPriceTicket(detailSmsBody[1].split(" ")[0]);
            ticket.setCodeTransport(detailSmsBody[3]);
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd.MM.yyyy HH:mm");
            Date date;
            try {
                date = format.parse(detailSmsBody[2]);
            } catch (ParseException e) {
                e.printStackTrace();
                date = new Date();
            }
            ticket.setDateTicket(date.getTime());
            ticketPresenter.saveTicket(ticket);
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + numberOperator));
            startActivity(intent);
        }
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
