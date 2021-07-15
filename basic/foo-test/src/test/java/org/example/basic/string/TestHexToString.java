package org.example.basic.string;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;

/**
 * @author anyuan
 * @since 2021-05-25 11:13
 */
public class TestHexToString {

    public static void main(String[] args) throws DecoderException {
        final String original = "6a6176612f6c616e672f4f626a656374";
        final String target = new String(Hex.decodeHex(original.toCharArray()), StandardCharsets.UTF_8);
        System.out.println(target);
    }
}
