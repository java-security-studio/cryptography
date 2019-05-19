import cryptography.asymmetric.RSA;

import java.math.BigInteger;

public class Startup {
    public static void main(String[] args) {

        int N = 1024;
        RSA key = new RSA(N);
        System.out.println(key);

        // create random message, encrypt and decrypt
        // BigInteger message = new BigInteger(N - 1, random);

        // create message by converting string to integer
        String s = "test";
        byte[] bytes = s.getBytes();
        BigInteger message = new BigInteger(bytes);

        BigInteger encrypt = key.encrypt(message);
        BigInteger decrypt = key.decrypt(encrypt);
        System.out.println("message   = " + message);
        System.out.println("encrypted = " + encrypt);
        System.out.println(String.format("decrypted = %s (clear text: %s)", decrypt, new String(decrypt.toByteArray())));
    }
}
