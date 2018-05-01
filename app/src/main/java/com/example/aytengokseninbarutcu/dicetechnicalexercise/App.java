package com.example.aytengokseninbarutcu.dicetechnicalexercise;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.aytengokseninbarutcu.dicetechnicalexercise.Network.ConnectionInterceptor;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Network.HeaderInterceptor;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Utils.PreferenceHelper;
import com.example.aytengokseninbarutcu.dicetechnicalexercise.Utils.TLSSocketFactoryCompat;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static Retrofit retrofitInstance;
    private static App applicationInstance;
    private static Context context;
    private static int successfullLoginCount;
    private final String BASE_URL = "https://api.tronalddump.io/";

    /**
     * Application class is singleton. can be reached to Application class itself via this method.
     *
     * @return
     */
    public static App getInstance() {
        return applicationInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationInstance = this;
        App.context = getApplicationContext();
        setLogger();
    }


    public Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    if (isJSONValid(message)) {
                        Logger.json(message);
                    } else {
                        Log.d("Retrofit", message);
                    }
                }
            });
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(interceptor);
            builder.addInterceptor(new HeaderInterceptor(getApplicationContext()));
            builder.addInterceptor(new ConnectionInterceptor(getApplicationContext()));
            X509TrustManager trustManager = null;
            try {
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init((KeyStore) null);
                TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
                if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                    throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
                }
                trustManager = (X509TrustManager) trustManagers[0];
                SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, new TrustManager[]{trustManager}, null);
                SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            } catch (Exception e) {

            }
            TLSSocketFactoryCompat tlsSocketFactoryCompat = null;
            try {
                tlsSocketFactoryCompat = new TLSSocketFactoryCompat();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            OkHttpClient client = null;
            client = builder.connectTimeout(2, TimeUnit.MINUTES).writeTimeout(2, TimeUnit.MINUTES)
                    .sslSocketFactory(tlsSocketFactoryCompat, trustManager)
                    .readTimeout(2, TimeUnit.MINUTES).build();


            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofitInstance;
    }

    private void setLogger() {
        Logger.init().logLevel(getLogLevel());
    }

    private LogLevel getLogLevel() {
        if (BuildConfig.DEBUG) {
            return LogLevel.FULL;
        }
        return LogLevel.NONE;

    }

    /**
     * @param string parametresi ile verilen stringin json olup olmadiginni control eder.
     * @return
     */
    public boolean isJSONValid(String string) {
        try {
            new JSONObject(string);
        } catch (JSONException ex) {
            try {
                new JSONArray(string);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }

    public static String getAccessToken() {

       /* PreferenceHelper mPreferenceHelper = new PreferenceHelper();
        String accessToken = mPreferenceHelper.GetCustomPreferences(context).getString(CustomEnums.ACCESS_TOKEN, "");*/
        return "";
    }

    public static void setAccessToken(String accessToken) {

//        PreferenceHelper mPreferenceHelper = new PreferenceHelper();
//        mPreferenceHelper.GetCustomPreferencesEditor(context).putString(CustomEnums.ACCESS_TOKEN, accessToken).commit();
    }


}
