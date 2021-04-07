package org.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author anyuan
 * @since 2021-04-01 16:51
 */
public class HttpTest {

    public static void main(String[] args) throws IOException {
        String spec = "www.baidu.com";
        URLConnection urlConnection = new URL("http", spec, 80, null).openConnection();



    }
}
