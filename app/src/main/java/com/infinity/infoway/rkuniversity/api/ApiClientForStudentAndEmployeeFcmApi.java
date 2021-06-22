package com.infinity.infoway.rkuniversity.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientForStudentAndEmployeeFcmApi {

    public static final String ENCODED_KEY_FOR_STUDENT_FCM_REGISTRATION = "$tUDt&o9&Pk4pp]le$Z";
    public static final String ENCODED_KEY_FOR_FACULTY_FCM_REGISTRATION = "#Mpt&o9&Pk4pp]le$Z";


    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Urls.BASE_URL_FOR_STUDENT_AND_EMPLOYEE_SIDE_FCM_API)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .build();

}
