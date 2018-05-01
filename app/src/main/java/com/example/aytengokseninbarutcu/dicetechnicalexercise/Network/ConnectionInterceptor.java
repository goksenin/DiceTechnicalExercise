package com.example.aytengokseninbarutcu.dicetechnicalexercise.Network;

import android.content.Context;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Utils.NoConnectivityException;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by goksenin.barutcu on 07/11/2017.
 */

public class ConnectionInterceptor implements Interceptor {
    private Context mcontext;

    public ConnectionInterceptor(Context context) {
        this.mcontext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!NetworkUtil.isConnectedToNet(mcontext)) {
            throw new NoConnectivityException();
        }
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}
