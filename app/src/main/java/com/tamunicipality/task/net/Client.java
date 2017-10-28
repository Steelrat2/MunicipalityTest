package com.tamunicipality.task.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dmitriy on 19.06.2017.
 */

public class Client {

    private static final String END_POINT = "https://restcountries.eu/";
    private static CountriesApi sCountriesApi;
    private static HttpLoggingInterceptor mLoggingInterceptor;
    static {
        // Init okhttp request logger
        mLoggingInterceptor = new HttpLoggingInterceptor();
        mLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    static <T> T createService(Class<T> apiInterfaceClass){

        // Init okHttp
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(mLoggingInterceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(END_POINT)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(apiInterfaceClass);
    }

    public static CountriesApi getCountriesApi() {
        if(sCountriesApi == null) {
            synchronized (Client.class) {
                if(sCountriesApi == null)
                    sCountriesApi = createService(CountriesApi.class);
            }
        }
        return sCountriesApi;
    }
}
