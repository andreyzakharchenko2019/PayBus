package com.fintechhackathon14.paybus.qrscanner.presenter;

import static androidx.core.app.ActivityCompat.requestPermissions;
import static com.fintechhackathon14.paybus.util.Constants.LENGTH_CODE_TRANSPORT_TULPAR_CARD;
import static com.fintechhackathon14.paybus.util.Constants.LOG_NAME;
import static com.fintechhackathon14.paybus.util.Constants.NUMBER_ONAY;
import static com.fintechhackathon14.paybus.util.Constants.NUMBER_TULPAR_CARD;
import static com.fintechhackathon14.paybus.util.Constants.REGEX_CODE_TRANSPORT_TULPAR_CARD;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.fintechhackathon14.paybus.R;
import com.fintechhackathon14.paybus.qrscanner.view.QrScannerViewContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class QrScannerPresenter implements QrScannerPresenterContract {

    private QrScannerViewContract qrScannerViewContract;
    private SmsManager smsManager;
    private Context context;
    private TelephonyManager telephonyManager;
    private Activity activity;

    public QrScannerPresenter(QrScannerViewContract qrScannerViewContract, Context context, Activity activity) {
        this.qrScannerViewContract = qrScannerViewContract;
        this.context = context;
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        this.activity = activity;
    }

    @Override
    public void parseStringFromQr(String qrText) {
        Log.d(LOG_NAME, "getNetworkOperatorName() - " + telephonyManager.getNetworkOperatorName());
        smsManager = SmsManager.getSmsManagerForSubscriptionId(chekMobileOperator());
        String codeTransport;
        if (qrText.contains("c.onay.kz")) {
            codeTransport = qrText.split(".kz/")[1];
            sendSMS(codeTransport, NUMBER_ONAY);
        } else if (qrText.length() == LENGTH_CODE_TRANSPORT_TULPAR_CARD &&
                qrText.matches(REGEX_CODE_TRANSPORT_TULPAR_CARD)) {
            codeTransport = qrText;
            sendSMS(codeTransport, NUMBER_TULPAR_CARD);
        } else {
            qrScannerViewContract.showError(context.getString(R.string.error_qr));
        }
    }

    @Override
    public void sendSMS(String codeTransport, String numberOfOperator) {
        Log.d(LOG_NAME, codeTransport + " " + numberOfOperator);
        smsManager.sendTextMessage(numberOfOperator, null, codeTransport, null, null);
    }

    private int chekMobileOperator() {

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, 0);
        }
        List<SubscriptionInfo> subscriptionInfos;

        subscriptionInfos = SubscriptionManager.from(context).getActiveSubscriptionInfoList();


        Map<String, Integer> subscriptionInfoMap = new HashMap();
        for (SubscriptionInfo subscriptionInfo : subscriptionInfos) {
            subscriptionInfoMap.put(subscriptionInfo.getCarrierName().toString(), subscriptionInfo.getSubscriptionId());

        }
        Log.d(LOG_NAME, subscriptionInfoMap.toString());

        for (Map.Entry<String, Integer> item : subscriptionInfoMap.entrySet()) {

            if (item.getKey().toLowerCase(Locale.ROOT).contains("activ") || item.getKey().toLowerCase(Locale.ROOT).contains("kcell")) {
                return item.getValue();
            }
        }
        return 0;

    }
}
