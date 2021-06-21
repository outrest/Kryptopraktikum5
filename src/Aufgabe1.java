import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;

public class Aufgabe1 {
	BigInteger p;
	BigInteger q;
	BigInteger d;
	BigInteger verschluesselung;
	int numBits;
	int klartext;

	public Aufgabe1(int numbits, int klartext) {
		numBits = numbits;
		this.klartext = klartext;
		ausfuehren();
	}

	public BigInteger getP() {
		return p;
	}

	public BigInteger getQ() {
		return q;
	}

	public BigInteger getD() {
		return d;
	}

	public int getNumBits() {
		return numBits;
	}

	public int getKlartext() {
		return klartext;
	}

	public BigInteger getVerschluesselung(){
		return verschluesselung;
	}
	public void ausfuehren() {
		//!!!!!!!!!!!!!!!!!!! AUFGABE A !!!!!!!!!!!!!!!!!!!!
		long startZeit = System.currentTimeMillis();
		BigInteger primeCandidate =
				BigInteger.probablePrime(numBits, new Random());
		long dauer = System.currentTimeMillis() - startZeit;

		System.out.println(
				"Aufgabe 1 a)\nGeneriere eine zufällige 1500-Bit " + "Zahl");
		System.out.println(primeCandidate);
		System.out.println("Die Berechnung dauerte: " + dauer + "ms\n");

		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! AUFGABE B
		System.out.println(
				"Aufgabe 1 b)\nGeneriere einen 3000-Bit " + "Schlüssel");


		startZeit = System.currentTimeMillis();
		do {
			p = BigInteger.probablePrime(numBits, new Random());
			q = BigInteger.probablePrime(numBits, new Random());
		} while (isImIntervall(p, numBits) && isImIntervall(q, numBits));


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
		d = e.modPow(BigInteger.valueOf(-1), phi);
		//Termumformung e^-1 mod phi
		System.out.println("\nN: " + n);
		System.out.println("Phi: " + phi);
		System.out.println("d: " + d);

		//VERSCHLÜSSELN!
		System.out.println(
				"\nBerechnung der Verschlüsselung der Zahl " + klartext +
				"\n");
		verschluesselung = BigInteger.valueOf(klartext).modPow(e, n);
		System.out.println("Verschlüsselung=\n" + verschluesselung);
		//ENTSCHLÜSSELUNG!
		BigInteger entschluesselung = verschluesselung.modPow(d, n);
		System.out.println("Entschlüsselung=\n" + entschluesselung);
		System.out.println("\nBEEP BUUP BOOP! ... Fertig.\n");
	}

	public boolean isImIntervall(BigInteger zahl, int numbits) {
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


