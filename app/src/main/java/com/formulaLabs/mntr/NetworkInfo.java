package com.formulaLabs.mntr;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.CellInfo;
import android.telephony.CellSignalStrength;
import android.telephony.TelephonyManager;
import androidx.core.app.ActivityCompat;

import java.util.List;
import java.util.Map;

public class NetworkInfo {

    public static Operator getCurrentOperator(Context context) throws SecurityException {
        // Check if the required permission is granted
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, handle accordingly
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE}, 0);

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                throw new SecurityException("No permission to read phone state");
            }
        }

        // Check if the required permission is granted
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, handle accordingly
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                throw new SecurityException("No permission to read phone state");
            }
        }

        Operator operator = new Operator();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        List<CellInfo> cellInfoList = telephonyManager.getAllCellInfo();
        if (cellInfoList != null) {
            for (CellInfo cellInfo : cellInfoList) {
                if (cellInfo.isRegistered()) {
                    CellSignalStrength signalStrength = cellInfo.getCellSignalStrength();
                    String signalStrengthLevel = Definitions.SignalLevels.get(signalStrength.getLevel());
                    String operatorCode = cellInfo.getCellIdentity().getOperatorAlphaShort().toString();
                    String brandName = operatorCode;
                    if (Definitions.MccMncBrands.containsKey(operatorCode)) {
                        brandName = Definitions.MccMncBrands.get(operatorCode);
                    }
                    if (Definitions.MccMncBrands.containsValue(operatorCode)) {
                        operatorCode = getKeyByValue(Definitions.MccMncBrands, operatorCode);
                    }
                    int signalStrengthDbm = signalStrength.getDbm();
                    operator.setBrandName(brandName);
                    operator.setOperatorCode(operatorCode);
                    operator.setSignalStrengthLevel(signalStrengthLevel);
                    operator.setSignalStrengthDbm(signalStrengthDbm);
                }
            }
        }

        return operator;
    }

    public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null; // Return null if value not found
    }
}