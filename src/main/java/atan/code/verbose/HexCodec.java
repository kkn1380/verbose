package atan.code.verbose;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import com.google.common.io.BaseEncoding;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HexFormat;

public class HexCodec {
    public static byte[] hexFormatToBytes(String hexString) {
        // Java 17
        return HexFormat.of().parseHex(hexString);
    }

    public static String hexFormatToString(byte[] hexBytes) {
        // Java 17
        return HexFormat.of().formatHex(hexBytes);
    }

    /* s must be an even-length string. */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            /*
            첫번째 값을 radix로 해석 시 해당 하는 값 반환.
            Character.digit('A', 16) -> 10
            Character.digit('F', 16) -> 15
             */
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String byteArrayToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            /*
            %x : b값을 16진수(hex) 소문자로 출력
            02 : 2자리 숫자로 맞추되, 한 자리 숫자일 경우 앞에 0을 추가
            b : 변환할 byte 값
            ex) String.format("%02x", 255) -> 결과 : ff
            ex) String.format("%02x", 1) -> 결과 : 01
             */
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public static byte[] apacheHexFormatToBytes(String hexString) throws DecoderException {
        return Hex.decodeHex(hexString);
    }

    public static String apacheHexFormatToString(byte[] hexBytes) {
        return Hex.encodeHexString(hexBytes);
    }

    public static byte[] guavaHexFormatToBytes(String hexString) throws DecoderException {
        return BaseEncoding.base16().decode(hexString);
    }

    public static String guavaHexFormatToString(byte[] hexBytes) {
        return BaseEncoding.base16().encode(hexBytes);
    }

    public static byte[] bigIntHexFormatToBytes(String hexString) throws DecoderException {
        return new BigInteger(hexString, 16).toByteArray();
    }

    public static void testHexCode() throws DecoderException {
        String hexString = "00A0BF";
        byte[] hexBytes = {0x00, (byte) 0xA0, (byte) 0xBf};

        // HexFormat -->
        byte[] resultInBytes = hexFormatToBytes(hexString);
        Utils.assertIfFalse(Arrays.equals(hexBytes, resultInBytes));

        String resultInString = hexFormatToString(hexBytes);
        Utils.assertIfFalse(hexString.equals(resultInString.toUpperCase()));
        // HexFormat <--

        // native -->
        resultInBytes = hexStringToByteArray(hexString);
        Utils.assertIfFalse(Arrays.equals(hexBytes, resultInBytes));

        resultInString = byteArrayToHex(hexBytes);
        Utils.assertIfFalse(hexString.equals(resultInString.toUpperCase()));
        // native <--

        // apache -->
        // implementation 'commons-codec:commons-codec:1.18.0'
        resultInBytes = apacheHexFormatToBytes(hexString);
        Utils.assertIfFalse(Arrays.equals(hexBytes, resultInBytes));

        resultInString = apacheHexFormatToString(hexBytes);
        Utils.assertIfFalse(hexString.equals(resultInString.toUpperCase()));
        // apache <--

        // guava -->
        // implementation 'com.google.guava:guava:18.0'
        resultInBytes = guavaHexFormatToBytes(hexString);
        Utils.assertIfFalse(Arrays.equals(hexBytes, resultInBytes));

        resultInString = guavaHexFormatToString(hexBytes);
        Utils.assertIfFalse(hexString.equals(resultInString.toUpperCase()));
        // guava <--

        // bigInt -->
        resultInBytes = bigIntHexFormatToBytes(hexString);
        Utils.assertIfFalse(Arrays.equals(hexBytes, resultInBytes));
        // bigInt <--
    }
}
