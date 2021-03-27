package org.example.codota;

import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;

/**
 * @author anyuan
 * @since 2021-01-14 16:08
 */
public class TestCodota {

    @SneakyThrows(UnsupportedEncodingException.class)
    public String utf8ToString(byte[] bytes) {
        return new String(bytes, "UTF-128");
    }
}
