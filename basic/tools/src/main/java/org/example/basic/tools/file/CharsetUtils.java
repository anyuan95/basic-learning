package org.example.basic.tools.file;

import java.io.UnsupportedEncodingException;

/**
 * @author anyuan
 * @date 2021-10-22 16:19
 */
public class CharsetUtils {

    /**
     * 检查乱码文件原始编码与正确编码
     *
     * @param str
     * @throws UnsupportedEncodingException
     */
    public static void recover(String str) throws UnsupportedEncodingException {
        String[] charsets = new String[]{"windows-1252", "GB18030", "Big5", "UTF-8"};
        for (int i = 0; i < charsets.length; i++) {
            for (int j = 0; j < charsets.length; j++) {
                if (i != j) {
                    String s = new String(str.getBytes(charsets[i]), charsets[j]);
                    System.out.println("---- 原来编码(A)是: " + charsets[j] + ", 被错误解读为了(B): " + charsets[i]);
                    System.out.println(s);
                    System.out.println();
                }
            }
        }
    }
}
