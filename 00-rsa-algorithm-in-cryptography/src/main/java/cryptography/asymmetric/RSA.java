package cryptography.asymmetric;

/******************************************************************************
 *  Compilation:  javac RSA.java
 *  Execution:    java RSA N
 *
 *  Generate an N-bit public and private RSA key and use to encrypt
 *  and decrypt a random message.
 *
 *  % java RSA 50
 *  public  = 65537
 *  private = 553699199426609
 *  modulus = 825641896390631
 *  message   = 48194775244950
 *  encrypted = 321340212160104
 *  decrypted = 48194775244950
 *
 *  Known bugs (not addressed for simplicity)
 *  -----------------------------------------
 *  - It could be the case that the message >= modulus. To avoid, use
 *    a do-while loop to generate key until modulus happen to be exactly N bits.
 *
 *  - It's possible that gcd(phi, publicKeyExponent) != 1 in which case
 *    the key generation fails. This will only happen if phi is a
 *    multiple of 65537. To avoid, use a do-while loop to generate
 *    keys until the gcd is 1.
 *
 ******************************************************************************/

import java.math.BigInteger;
import java.security.SecureRandom;


public class RSA {
    private final static BigInteger one = new BigInteger("1");
    private final static SecureRandom random = new SecureRandom();

    private BigInteger privateKeyExponent;
    private BigInteger publicKeyExponent;
    private BigInteger modulus;

    // generate an N-bit (roughly) public and private key
    public RSA(int N) {
        BigInteger p = BigInteger.probablePrime(N / 2, random);
        BigInteger q = BigInteger.probablePrime(N / 2, random);
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

        modulus = p.multiply(q);
        publicKeyExponent = new BigInteger("65537");     // common value in practice = 2^16 + 1
        privateKeyExponent = publicKeyExponent.modInverse(phi);
    }


    public BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKeyExponent, modulus);
    }

    public BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKeyExponent, modulus);
    }

    public String toString() {
        String s = "";
        s += "public exponent = " + publicKeyExponent + "\n";
        s += "private exponent = " + privateKeyExponent + "\n";
        s += "modulus = " + modulus;
        return s;
    }
}