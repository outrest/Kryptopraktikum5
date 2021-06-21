import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;

public class Aufgabe1 {
	public static void main(String[] args) {
		int numbits = 1500;

		BigInteger primeCandidate = new BigInteger(1500, rnd);
		long startZeit = System.currentTimeMillis();
		while (!(Prime.isPrime(primeCandidate))) {
			primeCandidate = BigInteger.probablePrime(numbits, new Random());
		}
		long endZeit = System.currentTimeMillis();
		long dauer = endZeit - startZeit;

		System.out.println(
				"Aufgabe 1 a)\nGeneriere eine zufällige 1500-Bit " + "Zahl\n");
		System.out.println(primeCandidate);
		System.out.println("Prüfe ob dies eine Primzahl ist: " + Prime
				.isPrime(primeCandidate));
		System.out.println("Die Berechnung dauerte: " + dauer + "ms\n");

		//!!! AUFGABE B!!!
		System.out.println(
				"Aufgabe 1 b)\nGeneriere einen 3000-Bit " + "Schlüssel");


		startZeit = System.currentTimeMillis();
		BigInteger p = BigInteger.probablePrime(numbits, new Random());

		BigInteger q = BigInteger.probablePrime(numbits, new Random());

		if (p.compareTo(q) > 0) {
			BigInteger tmp = p;
			p = q;
			q = tmp;
		}

		dauer = System.currentTimeMillis() - startZeit;
		System.out.println("Das Finden von zwei Primzahlen mit 1500 Bits "
		                   + "innerhalb des oben angegebenen Intervalls "
		                   + "dauerte " + dauer + " ms");
		System.out.println("P = " + p + "\nQ = " + q);
		//Fölie 19 Step 2
		BigInteger n = p.multiply(q);
		BigInteger phi =
				p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

		BigInteger e = new BigInteger("65537"); //2^16 + 1
		//Fölie 19 Step 4
		BigInteger d = e.modPow(BigInteger.valueOf(-1), phi);
		//Termumformung e^-1 mod phi
		System.out.println("\nN: " + n);
		System.out.println("\nPhi: \n" + phi);
		System.out.println("d: \n" + d);

		//Klartext
		BigInteger x = new BigInteger("4711");

		//VERSCHLÜSSELN!
		System.out.println(
				"\nBerechnung der Verschlüsselung der Zahl " + x + "\n");
		BigInteger verschluesselung = x.modPow(e, n);
		System.out.println("Verschlüsselung=\n" + verschluesselung);
		//ENTSCHLÜSSELUNG!
		BigInteger entschluesselung = verschluesselung.modPow(d, n);
		System.out.println("Entschlüsselung=\n" + entschluesselung);
		System.out.println("BEEP BUUP BOOP! ... Fertig.");

	}

	public static boolean isImIntervall(BigInteger zahl, int numbits) {
		BigDecimal a = new BigDecimal("2");
		BigDecimal redundancy = a.pow(numbits);
		return zahl.compareTo(redundancy
				                      .divide(BigDecimal.valueOf(Math.sqrt(2)),
				                              RoundingMode.HALF_UP)
				                      .toBigInteger()) > 0
		       && zahl.compareTo(redundancy.toBigInteger()) < 0;
	}

	public static BigInteger generatePrime(int numBits) {
		//	MathContext mc = new MathContext(1, RoundingMode.UP);
		BigInteger probablePrime;
		do {
			probablePrime = new BigInteger(numBits, new Random());
		} while (!(Prime.isPrime(probablePrime, 3)) && !(isImIntervall(
				probablePrime, numBits)));
		return probablePrime;
	}
}


