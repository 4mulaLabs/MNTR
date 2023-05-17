package com.formulaLabs.mntr;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

import java.util.List;

public class NetworkInfo {


    private void getNetworkInformation(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        // Check if the required permissions are granted
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // Permissions not granted, handle accordingly
            return;
        }

        // Get the network operator name
        String networkOperatorName = telephonyManager.getNetworkOperatorName();

        // Get the network type (e.g., LTE, HSPA, etc.)
        int networkType = telephonyManager.getNetworkType();
        String networkTypeName = getNetworkTypeName(networkType);

        // Register a signal strength listener to get updates
        telephonyManager.listen(new PhoneStateListener() {
            @Override
            public void onSignalStrengthsChanged(android.telephony.SignalStrength signalStrength) {
                super.onSignalStrengthsChanged(signalStrength);

                // Use the signal strength data as needed
                int gsmSignalStrength = signalStrength.getGsmSignalStrength();
                int cdmaDbm = signalStrength.getCdmaDbm();
                int evdoDbm = signalStrength.getEvdoDbm();
            }
        }, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }

    // Helper method to get the network type name
    private String getNetworkTypeName(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return "2G";
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return "3G";
            case TelephonyManager.NETWORK_TYPE_LTE:
                return "4G";
            case TelephonyManager.NETWORK_TYPE_NR:
                return "5G";
            default:
                return "Unknown";
        }
    }


    private void getAvailableNetworkOperatorNames(Context context) {
        // Check if the required permission is granted
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, handle accordingly
            return;
        }

        TelephonyManager telephonyManager = context.getSystemService(TelephonyManager.class);
        SubscriptionManager subscriptionManager = context.getSystemService(SubscriptionManager.class);

        // Get a list of all available subscriptions
        List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();

        if (subscriptionInfoList != null) {
            for (SubscriptionInfo subscriptionInfo : subscriptionInfoList) {
                // Get the subscription ID
                int subscriptionId = subscriptionInfo.getSubscriptionId();

                // Get the operator name associated with the subscription
                CharSequence operatorName = subscriptionInfo.getCarrierName();

                // Process the operator name as needed
            }
        }
    }
}
