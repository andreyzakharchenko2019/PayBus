package com.fintechhackathon14.paybus.ticket.view;

public class RecyclerHistoryViewItem {

    private String nameService;
    private String codeTransport;
    private String date;
    private String price;
    private String urlQr;

    public RecyclerHistoryViewItem(String nameService, String codeTransport, String date, String price) {
        this.nameService = nameService;
        this.codeTransport = codeTransport;
        this.date = date;
        this.price = price;
    }

    public String getNameService() {
        return nameService;
    }

    public String getCodeTransport() {
        return codeTransport;
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }

    public String getUrlQr() {
        return urlQr;
    }
}
