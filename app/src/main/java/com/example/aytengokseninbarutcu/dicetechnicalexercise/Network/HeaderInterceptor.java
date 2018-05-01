package com.example.aytengokseninbarutcu.dicetechnicalexercise.Network;

import android.content.Context;
import android.util.Log;


import com.example.aytengokseninbarutcu.dicetechnicalexercise.App;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by goksenin.barutcu on 07/11/2017.
 */


/**
 * Created by goksenin.barutcu on 07/11/2017.
 */

public class HeaderInterceptor implements Interceptor {
    private Context context;

    public HeaderInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();
        if (App.getAccessToken() != null) {
            requestBuilder.addHeader("Authorization", "bearer " + App.getAccessToken());
            if (BuildConfig.DEBUG) {
                Log.d("Authorization", App.getAccessToken() + "");
            }
        }
        Request request = requestBuilder.method(original.method(), original.body())
                .build();
        return chain.proceed(request);

    }
}
