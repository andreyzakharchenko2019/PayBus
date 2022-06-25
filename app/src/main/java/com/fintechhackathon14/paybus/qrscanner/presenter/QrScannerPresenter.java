package com.fintechhackathon14.paybus.qrscanner.presenter;

import static com.fintechhackathon14.paybus.util.Constants.LOG_NAME;
import static com.fintechhackathon14.paybus.util.Constants.NUMBER_ONAY;

import android.telephony.SmsManager;
import android.util.Log;

import com.fintechhackathon14.paybus.qrscanner.view.QrScannerViewContract;

public class QrScannerPresenter implements QrScannerPresenterContract {

    private QrScannerViewContract qrScannerViewContract;
    private SmsManager smsManager;

    public QrScannerPresenter(QrScannerViewContract qrScannerViewContract) {
        this.qrScannerViewContract = qrScannerViewContract;
        smsManager = SmsManager.getDefault();
    }

    @Override
    public void parseStringFromQr(String qrText) {
        String codeTransport = "";
        if (qrText.contains("c.onay.kz")) {
            codeTransport = qrText.split(".kz/")[1];
            sendSMS(codeTransport, NUMBER_ONAY);
        }

    }

    @Override
    public void sendSMS(String codeTransport, String numberOfOperator) {
        Log.d(LOG_NAME, codeTransport + " " + numberOfOperator);
        smsManager.sendTextMessage(numberOfOperator, null, codeTransport, null, null);
    }
}
