package atan.code.verbose;

import org.apache.commons.codec.DecoderException;

import static atan.code.verbose.Crypto.testCrypto;
import static atan.code.verbose.HexCodec.testHexCode;

public class Main {

    public static void main(String[] args) {
        try {
            testHexCode();
            testCrypto();
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }
}