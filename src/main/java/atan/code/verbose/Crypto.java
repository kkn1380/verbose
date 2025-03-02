package atan.code.verbose;

//import io.github.novacrypto.base58.Base58;
import org.bouncycastle.crypto.params.*;

import java.util.Base64;

public class Crypto {
    static String hexPrivateKey = "d25137fd5f6678119655ac1f333ff7a6b3b7ec2f7746bed4dfd1fb445eb64084";

//    static String hexPrivateKey = "d25137fd5f6678119655ac1f333ff7a6b3b7ec2f7746bed4dfd1fb445eb64084"; // 256-bit private key

    // implementation 'org.bouncycastle:bcprov-jdk15on:1.70'
    public static void Ed25519() {
        byte[] privateKeyBytes = HexCodec.hexFormatToBytes(hexPrivateKey);
        Ed25519PrivateKeyParameters privateKeyParams = new Ed25519PrivateKeyParameters(privateKeyBytes, 0);
        Ed25519PublicKeyParameters publicKeyParams = privateKeyParams.generatePublicKey();
        byte[] publicKeyBytes = publicKeyParams.getEncoded();

        System.out.println("Extracted Public Key (Base64): " + Base64.getEncoder().encodeToString(publicKeyBytes));
//        System.out.println("Extracted Public Key (Base58): " + Base58.base58Encode(publicKeyBytes));

        String toString = HexCodec.byteArrayToHex(publicKeyBytes);
        System.out.println("Extracted Public Key (Hex): " + toString);
    }

    public static void testCrypto() {
        Ed25519();
    }

}