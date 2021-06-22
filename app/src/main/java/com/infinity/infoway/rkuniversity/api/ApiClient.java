package com.infinity.infoway.rkuniversity.api;

import androidx.multidex.BuildConfig;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {


            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            if (BuildConfig.BUILD_TYPE.contentEquals("debug")) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                logging.setLevel(HttpLoggingInterceptor.Level.NONE);
            }


            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(320, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .connectTimeout(320, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Urls.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
