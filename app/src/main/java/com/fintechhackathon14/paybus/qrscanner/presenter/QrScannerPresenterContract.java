package com.fintechhackathon14.paybus.qrscanner.presenter;

public interface QrScannerPresenterContract {
    void parseStringFromQr(String qrText);
    void sendSMS(String codeTransport, String numberOfOperator);
}
