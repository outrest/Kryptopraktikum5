//import numpy as np;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.util.Arrays.stream;// GIMME MY MONAAAADEN
// <<<<<<<<<<<<<<<<<<<<<<<<<<
// Monaden!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class Aufgabe2 {
	private Aufgabe1 aufg1;
	BigInteger x;
	BigInteger p;
	BigInteger q;
	BigInteger d;
	BigInteger n;

	public void ausfuehren() {
		System.out.println(
				"-----------------------------------------------------------------------------\nFühre Aufgabe 2 "
				+ "aus:\nErgebnis: ");

		//1.
		BigInteger xp = x.mod(p);
		BigInteger xq = x.mod(q);
		BigInteger dp = d.mod(p.subtract(BigInteger.ONE));
		BigInteger dq = d.mod(q.subtract(BigInteger.ONE));
		//RICHTIG

		//2.
		BigInteger yp = xp.modPow(dp, p);
		BigInteger yq = xq.modPow(dq, q);
		boolean gleich = yp.compareTo(yq) == 0;
		System.out.println("Yp und Yq sind gleich: " + gleich);

		//3.
		BigInteger cp = q.modPow(BigInteger.ZERO.subtract(BigInteger.ONE), p);
		BigInteger cq = p.modPow(BigInteger.ZERO.subtract(BigInteger.ONE), q);

		BigInteger ylinks = q.multiply(cp).multiply(yp);
		BigInteger yrechts = p.multiply(cq).multiply(yq);
		BigInteger y = ylinks.add(yrechts).mod(n);
		System.out.println(y);
	}

	public Aufgabe2(Aufgabe1 aufgabe1) {
		this.aufg1 = aufgabe1;
		x = aufg1.getVerschluesselung();
		p = aufg1.getP();
		q = aufg1.getQ();
		d = aufg1.getD();
		n = aufg1.getn();

		ausfuehren();
	}
}

