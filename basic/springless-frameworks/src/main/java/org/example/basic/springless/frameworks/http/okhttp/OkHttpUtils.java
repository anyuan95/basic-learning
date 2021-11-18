package org.example.basic.springless.frameworks.http.okhttp;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author anyuan
 * @date 2021-11-17 20:18
 */
public final class OkHttpUtils {

    private static ConnectionPool pool() {
        return new ConnectionPool(20, 5, TimeUnit.MINUTES);
    }

    private static OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectionPool(pool())
                .connectTimeout(5L, TimeUnit.SECONDS)
                .readTimeout(5L, TimeUnit.SECONDS)
                .writeTimeout(5L, TimeUnit.SECONDS)
                .build();
    }

    private static OkHttpClient singleton;

    public static OkHttpClient getClientInstance() {
        if (singleton == null) {
            synchronized (OkHttpUtils.class) {
                if (singleton == null) {
                    singleton = okHttpClient();
                }
            }
        }
        return singleton;
    }





}
