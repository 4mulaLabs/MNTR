package com.formulaLabs.mntr;

import java.util.ArrayList;
import java.util.HashMap;

public class Definitions {
    static ArrayList<String> SignalLevels = new ArrayList() {{
        add("UNKNOWN_SIGNAL");
        add("POOR_SIGNAL");
        add("GOOD_SIGNAL");
        add("GREAT_SIGNAL");
    }};

    static HashMap<String, String> MccMncBrands = new HashMap() {{
        put("425001", "Partner");
        put("425002", "Cellcom");
        put("425003", "Pelephone");
        put("425005", "Jawwal");
        put("425006", "Wataniya Mobile");
        put("425007", "Hot Mobile");
        put("425008", "Golan Telecom\t");
        put("425009", "We4G");
        put("425010", "Partner");
        put("425012", "x2one");
        put("425014", "Youphone");
        put("425015", "Home Cellular");
        put("425016", "Rami Levy");
        put("425017", "Sipme");
        put("425018", "Cellact Communications");
        put("425019", "019 Mobile");
        put("425020", "Bezeq");
        put("425021", "Bezeq International\t");
        put("425024", "012 Mobile");
        put("425025", "IMOD");
        put("425026", "Annatel");
    }};
}
