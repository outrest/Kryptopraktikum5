import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.KeyGeneratorSpi;
import javax.crypto.NoSuchPaddingException;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Aufgabe1 {
	public static void main(String[] args) {
		// AUFGABE A
		Prime primtester = new Prime();

		Random rseed = new Random(
				new Random((long) (Math.random() * 3.5 + 4711)).nextInt());
		Random rnd = new Random(rseed.nextLong());

		BigInteger p = new BigInteger(1500, rnd);
		long startZeit = System.currentTimeMillis();
		while (!(primtester.isPrime(p))) {
			p = new BigInteger(1500, rnd);
		}
		long endZeit = System.currentTimeMillis();
		long dauer = endZeit - startZeit;

		System.out.println(
				"Aufgabe 1 a)\nGeneriere eine zufällige 1500-Bit " + "Zahl\n");
		System.out.println(p);
		System.out.println(
				"Prüfe ob dies eine Primzahl ist: " + primtester.isPrime(p));
		System.out.println("Die Berechnung dauerte: " + dauer + "ms\n");



		// AUFGABE B

		System.out.println(
				"Aufgabe 1 b)\nGeneriere einen 3000-Bit " + "Schlüssel\n");

		BigInteger q = new BigInteger(1500, rnd);

		while (!(primtester.isPrime(q))) {
			q = new BigInteger(1500, rnd);
		}


		KeyPairGenerator generator = null;
		try {
			generator = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		generator.initialize(3000);
		KeyPair pair = generator.generateKeyPair();

		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();

		System.out.println(privateKey);
		System.out.println(publicKey);


		// AUFGABE C

		System.out.println(
				"\nAufgabe 1 c) Ver- und Entschlüsselung einer " + "Zahl");

		Cipher rsa = null;
		byte[] encryptME = {1,3,1,1,2,1,59,7,8,5,23,4,8,1,5};
		ByteArrayInputStream bais = new ByteArrayInputStream(encryptME);
		byte[] encrypted;
		byte[] decrypted;
		try {
			rsa = Cipher.getInstance("RSA");
			rsa.init(Cipher.ENCRYPT_MODE, publicKey);
			encrypted = rsa.doFinal(encryptME);
			rsa.init(Cipher.DECRYPT_MODE, privateKey);
			decrypted = rsa.doFinal(encrypted);
			System.out.println(Arrays.toString(encryptME));
			System.out.println(Arrays.toString(encrypted));
			System.out.println(Arrays.toString(decrypted));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
