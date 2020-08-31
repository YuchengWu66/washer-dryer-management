package com.laioffer.washerdrymanagement.Network;

import android.content.Context;
import android.webkit.WebSettings;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackendClient {
    private static final String BASE_URL = "http://10.0.2.2:8088/washer/";
    private static Context context = null;
    private static Retrofit instance = null;
    public static String Cookie = "JSESSIONID=A5FEA3DF3DEA64F58469057635F56271";
    public static Retrofit newInstance(Context context) {
        if (instance == null) {
            BackendClient.context = context;
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .build();
            instance = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return instance;
    }
    private static class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request request = original
                    .newBuilder()
                    .removeHeader("User-Agent").addHeader("User-Agent", "okhttp/3.14.7").
                            removeHeader("Connection").addHeader("Connection", "keep-alive").
                            removeHeader("Accept").addHeader("Accept", "*/*")
                            .removeHeader("Cookie").addHeader("Cookie", Cookie)
                    .build();
            return chain.proceed(request);
        }
    }
}
