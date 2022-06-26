package com.fintechhackathon14.paybus.util;

import static com.fintechhackathon14.paybus.util.Constants.NAME_ONAY;
import static com.fintechhackathon14.paybus.util.Constants.NAME_TOLEM;
import static com.fintechhackathon14.paybus.util.Constants.NAME_TULPAR_CARD;
import static com.fintechhackathon14.paybus.util.Constants.NUMBER_ONAY;
import static com.fintechhackathon14.paybus.util.Constants.NUMBER_TOLEM;
import static com.fintechhackathon14.paybus.util.Constants.NUMBER_TULPAR_CARD;

import java.util.HashMap;
import java.util.Map;

public class ServiceName {

    public static final Map<String, String> SERVICE_NAME = new HashMap<>();

    static {
        SERVICE_NAME.put(NUMBER_ONAY, NAME_ONAY);
        SERVICE_NAME.put(NUMBER_TOLEM, NAME_TOLEM);
        SERVICE_NAME.put(NUMBER_TULPAR_CARD, NAME_TULPAR_CARD);
    }
}
