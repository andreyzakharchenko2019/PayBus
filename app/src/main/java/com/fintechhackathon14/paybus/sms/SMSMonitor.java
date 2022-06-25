package com.fintechhackathon14.paybus.sms;

import static android.provider.ContactsContract.Intents.Insert.ACTION;

import static com.fintechhackathon14.paybus.util.Constants.LOG_NAME;
import static com.fintechhackathon14.paybus.util.Constants.NUMBER_ONAY;
import static com.fintechhackathon14.paybus.util.Constants.NUMBER_TOLEM;
import static com.fintechhackathon14.paybus.util.Constants.NUMBER_TULPAR_CARD;
import static com.fintechhackathon14.paybus.util.Constants.SMS_BODY;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class SMSMonitor extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null) {
            Object[] pduArray = (Object[]) intent.getExtras().get("pdus");
            SmsMessage[] messages = new SmsMessage[pduArray.length];
            for (int i = 0; i < pduArray.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pduArray[i]);
            }
            chekSender(messages, context);
        }
    }

    private void chekSender(SmsMessage[] messages, Context context) {
        String smsFrom = messages[0].getDisplayOriginatingAddress();
        Log.d(LOG_NAME, "getDisplayOriginatingAddress() " + smsFrom);
        if (smsFrom.equalsIgnoreCase(NUMBER_ONAY) || smsFrom.equalsIgnoreCase(NUMBER_TOLEM)
                || smsFrom.equalsIgnoreCase(NUMBER_TULPAR_CARD)) {
            StringBuilder bodyText = new StringBuilder();
            for (SmsMessage message : messages) {
                bodyText.append(message.getMessageBody());
            }
            String body = bodyText.toString();
            Log.d(LOG_NAME, body);
            startService(body, context);
        }
    }

    private void startService(String smsBody, Context context) {
        Intent mIntent = new Intent(context, SmsService.class);
        mIntent.putExtra(SMS_BODY, smsBody);
        context.startService(mIntent);
    }

}
