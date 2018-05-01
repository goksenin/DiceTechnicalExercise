package com.example.aytengokseninbarutcu.dicetechnicalexercise.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by goksenin.barutcu on 07/11/2017.
 */

public final class NetworkUtil {

    private NetworkUtil() {
    }

    public static boolean isConnectedToNet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        boolean connected = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Network[] networkInfoList = connectivityManager.getAllNetworks();
            if (networkInfoList == null || networkInfoList.length == 0) {
                return false;
            }
            for (Network network : networkInfoList) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                if (networkInfo != null) {
                    connected = networkInfo.isConnected();
                    if (connected) {
                        return true;
                    }
                }
            }
        } else {
            NetworkInfo[] networkInfoList = connectivityManager.getAllNetworkInfo();
            if (networkInfoList == null || networkInfoList.length == 0) {
                return false;
            }
            for (NetworkInfo networkInfo : networkInfoList) {
                if (networkInfo != null) {
                    connected = networkInfo.isConnected();
                    if (connected) {
                        return true;
                    }
                }
            }
        }
        return connected;
    }
}
