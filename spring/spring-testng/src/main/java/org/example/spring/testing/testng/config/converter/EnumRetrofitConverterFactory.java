package org.example.spring.testing.testng.config.converter;

import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class EnumRetrofitConverterFactory extends Converter.Factory {

    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        Converter<?, String> converter = null;
        if (type instanceof Class && ((Class<?>) type).isEnum()) {
            converter = value -> ((Enum) value).name();
        }
        return converter;
    }
}