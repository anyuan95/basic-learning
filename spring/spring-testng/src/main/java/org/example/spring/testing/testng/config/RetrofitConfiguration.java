package org.example.spring.testing.testng.config;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.example.spring.testing.testng.config.converter.EnumRetrofitConverterFactory;
import org.example.spring.testing.testng.retrofit.ones.OnesApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.time.LocalDateTime;

/**
 * @author anyuan
 * @date 2022-03-15 20:48
 */
@SpringBootConfiguration
public class RetrofitConfiguration {

    private static final HttpLoggingInterceptor LOGGING_INTERCEPTOR =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    @Bean
    public OnesApi onesApi(@Value("${ones.url}") String url,
                           @Value("${ones.token}") String onesToken) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create())
                .addConverterFactory(new EnumRetrofitConverterFactory())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(LOGGING_INTERCEPTOR)
                        .addInterceptor(chain -> {
                            Request original = chain.request();
                            String date = LocalDateTime.now().toString();
                            Request.Builder requestBuilder = original.newBuilder()
                                    .header("Date", date)
                                    .header("Authorization", "Basic " + onesToken)
                                    .method(original.method(), original.body());
                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }).build())
                .build()
                .create(OnesApi.class);
    }

}
