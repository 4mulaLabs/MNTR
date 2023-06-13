package com.formulaLabs.mntr;

import static androidx.core.content.ContextCompat.getMainExecutor;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class NetworkScanner {
    private final Context context;
    private final List<String> networkList;
    private final TelephonyManager telephonyManager;

    public NetworkScanner(Context context) {
        this.context = context;
        networkList = new ArrayList<>();
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public List<String> getAvailableNetworks() {
        return networkList;
    }

    public void startNetworkScan() {
        // Check if the required permission is granted
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, handle accordingly
            ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.READ_PHONE_STATE}, 0);

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                throw new SecurityException("No permission to read phone state");
            }
        }
        telephonyManager.requestCellInfoUpdate(getMainExecutor(context), new TelephonyManager.CellInfoCallback() {
            @Override
            public void onCellInfo(List<CellInfo> cellInfoList) {
                networkList.clear();
                for (CellInfo cellInfo : cellInfoList) {
                    networkList.add(cellInfo.getCellIdentity().getOperatorAlphaLong().toString());
                }
            }
        });
    }

    public void stopNetworkScan() {
        // Check if the required permission is granted
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, handle accordingly
            ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.READ_PHONE_STATE}, 0);

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                throw new SecurityException("No permission to read phone state");
            }
        }

        telephonyManager.requestCellInfoUpdate(null, null);
    }
}
