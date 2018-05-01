package com.example.aytengokseninbarutcu.dicetechnicalexercise.Network;
import android.util.Log;

import com.example.aytengokseninbarutcu.dicetechnicalexercise.App;

/**
 * Created by goksenin.barutcu on 07/11/2017.
 */

public class NetworkBuilder {

    public static AppNetwork getAppNetwork() {

        Log.e("App.getInstance()" , App.getInstance() + " !!!!");
        return App.getInstance().getRetrofitInstance().create(AppNetwork.class);
    }
}
