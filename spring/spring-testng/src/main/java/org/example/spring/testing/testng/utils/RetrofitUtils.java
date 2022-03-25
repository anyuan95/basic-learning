package org.example.spring.testing.testng.utils;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author anyuan
 * @date 2022-03-15 21:07
 */
@Slf4j
public class RetrofitUtils {

    @Deprecated
    public static <T> T syncExecute(Call<T> call) {
        try {
            Response<T> response = call.execute();
            log.info("request url:{}", call.request().url());
            if (response.isSuccessful()) {
                return response.body();
            } else {
                String requestStr = call.request().toString();
                log.warn("syncExecute error requestStr:{},response.code:{},body:{},errorBody:{}", requestStr,
                        response.code(), response.body() == null ? null : response.body().toString(),
                        response.errorBody() == null ? null : response.errorBody().string());
            }
        } catch (IOException e) {
            log.error("retrofit2 syncExecute occur IOException", e);
        }
        return null;
    }

    public static <T> T syncExecuteWithAssertion(Call<T> call) {
        try {
            Response<T> response = call.execute();
            log.info("request url:{}", call.request().url());
            if (response.isSuccessful()) {
                return response.body();
            } else {
                String requestStr = call.request().toString();
                log.warn("syncExecute error requestStr:{},response.code:{},body:{},errorBody:{}", requestStr,
                        response.code(), response.body() == null ? null : response.body().toString(),
                        response.errorBody() == null ? null : response.errorBody().string());
            }
        } catch (IOException e) {
            log.error("retrofit2 syncExecute occur IOException", e);
            Assert.assertNull(e, "http请求出现异常，异常信息: [" + e.getMessage() + "]");
        }
        return null;
    }
}
