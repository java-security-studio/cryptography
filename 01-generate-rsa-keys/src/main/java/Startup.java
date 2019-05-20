import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.jce.provider.asymmetric.ec.KeyPairGenerator;

import java.math.BigInteger;
import java.security.*;


public class Startup {
    public static void main(String[] args) {
        try {
            java.security.KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            KeyPair key = keyGen.generateKeyPair();
            PrivateKey priv = key.getPrivate();
            PublicKey pub = key.getPublic();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void createRSAKeyPair(int keySizeBits, int strength) throws Exception
    {
        BigInteger publicExponent = BigInteger.valueOf(0x10001);
        SecureRandom rnd = new SecureRandom();
        RSAKeyGenerationParameters p = new RSAKeyGenerationParameters(publicExponent, rnd, keySizeBits, strength);

        RSAKeyPairGenerator g = new RSAKeyPairGenerator();
        g.init(p);

        AsymmetricCipherKeyPair kp = g.generateKeyPair();
        RSAPrivateCrtKeyParameters pri = (RSAPrivateCrtKeyParameters)kp.getPrivate();
        RSAKeyParameters pub = (RSAKeyParameters)kp.getPublic();

        // return new AKeyPair(new APrivateKey(pri), new APublicKey(pub));
    }
}

