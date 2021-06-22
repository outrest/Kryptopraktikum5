import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Aufgabe1 {
	BigInteger p;
	BigInteger q;
	BigInteger d;
	BigInteger verschluesselung;
	int numBits;
	int klartext;
	BigInteger n;

	public Aufgabe1(int numbits, int klartext) {
		numBits = numbits;
		this.klartext = klartext;
		ausfuehren();
	}

	public BigInteger getn() {
		return n;
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

	public BigInteger getVerschluesselung() {
		return verschluesselung;
	}

	public void ausfuehren() {
		//!!!!!!!!!!!!!!!!!!! AUFGABE A !!!!!!!!!!!!!!!!!!!!
		long startZeit = System.currentTimeMillis();
		BigInteger primeCandidate =
				BigInteger.probablePrime(numBits, new Random());
		long dauer = System.currentTimeMillis() - startZeit;

		System.out.println(
				"Aufgabe 1 a)\nGeneriere eine zufällige " +numBits+ "-Bit "
				+ "Primzahl");
		System.out.println(primeCandidate);
		System.out.println("Die Berechnung dauerte: " + dauer + "ms\n");

		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! AUFGABE B
		System.out.println(
				"Aufgabe 1 b)\nGeneriere zwei " +numBits/2+ "-Bit Primszahlen"
				+ " für einen "+numBits+"-Bit Schlüssel");

		startZeit = System.currentTimeMillis();
		do {
			p = BigInteger.probablePrime(numBits/2, new Random());
			q = BigInteger.probablePrime(numBits/2, new Random());
		} while (isImIntervall(p, numBits) && isImIntervall(q, numBits));
		dauer = System.currentTimeMillis() - startZeit;
		if (p.compareTo(q) > 0) {
			BigInteger tmp = p;
			p = q;
			q = tmp;
		}
		System.out.println("Das Finden von zwei Primzahlen mit "+numBits/2+
		                   " Bits "
		                   + "innerhalb des vom BSI angegebenen Intervalls "
		                   + "dauerte " + dauer + " ms");
		System.out.println("P = " + p + "\nQ = " + q);
		//Folie 19 Step 2
		n = p.multiply(q);
		BigInteger phi =
				p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

		BigInteger e = new BigInteger("65537"); //2^16 + 1
		//Folie 19 Step 4
		d = e.modPow(BigInteger.valueOf(-1), phi);
		//Termumformung e^-1 mod phi
		System.out.println("\nn: " + n);
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

	public void zeitMessen(int haeufigkeit) {
		System.out.println(
				"------\nStarte Zeitmessung mit einer Haeufigkeit von " + haeufigkeit);
		List<Long> dauer = new ArrayList<>();
		for (int i = 0; i < haeufigkeit; i++) {
			long startZeit = System.currentTimeMillis();
			BigInteger entschluesselung = verschluesselung.modPow(d, n);
			dauer.add(System.currentTimeMillis() - startZeit);
			System.out.println(entschluesselung);
		}
		List<Long> durchschnitt = dauer.stream().reduce(Long::sum).stream()
		                               .collect(Collectors.toList());
		System.out.println("Der Durchschnitt OHNE chin. Restsatz " + "betrug "
		                   + durchschnitt.get(0) / haeufigkeit + " ms\n------");

	}
}


