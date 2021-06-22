//import numpy as np;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
// <<<<<<<<<<<<<<<<<<<<<<<<<<
// Monaden!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class Aufgabe2 {
	private Aufgabe1 aufg1;
	BigInteger x;
	BigInteger p;
	BigInteger q;
	BigInteger d;
	BigInteger n;
	BigInteger cp;
	BigInteger cq;
	BigInteger ylinks;
	BigInteger yrechts;

	public void ausfuehren() {
		//1.
		BigInteger xp = x.mod(p);
		BigInteger xq = x.mod(q);
		BigInteger dp = d.mod(p.subtract(BigInteger.ONE));
		BigInteger dq = d.mod(q.subtract(BigInteger.ONE));

		//2.
		BigInteger yp = xp.modPow(dp, p);
		BigInteger yq = xq.modPow(dq, q);

		//3.
		ylinks = ylinks.multiply(yp);
		yrechts = yrechts.multiply(yq);
		BigInteger y = ylinks.add(yrechts).mod(n);
		System.out.println(y);
	}

	public Aufgabe2(Aufgabe1 aufgabe1) {
		System.out.println(
				"-----------------------------------------------------------------------------\nFühre Aufgabe 2 "
				+ "aus:\nErgebnis: ");
		this.aufg1 = aufgabe1;
		StartVals();
		ausfuehren();
	}

	public void StartVals() {
		x = aufg1.getVerschluesselung();
		p = aufg1.getP();
		q = aufg1.getQ();
		d = aufg1.getD();
		n = aufg1.getn();
		ylinks = null;
		yrechts = null;
		cp = null;
		cq = null;
		vorberechnen();
	}

	public void vorberechnen() {
		cp = q.modPow(BigInteger.ZERO.subtract(BigInteger.ONE), p);
		cq = p.modPow(BigInteger.ZERO.subtract(BigInteger.ONE), q);

		ylinks = q.multiply(cp);
		yrechts = p.multiply(cq);
	}

	public void zeitMessen(int haeufigkeit) {
		System.out.println(
				"------\nStarte Zeitmessung mit einer Haeufigkeit von " + haeufigkeit);
		List<Long> dauer = new ArrayList<>();
		for (int i = 0; i < haeufigkeit; i++) {
			StartVals();
			long startZeit = System.currentTimeMillis();
			ausfuehren();
			dauer.add(System.currentTimeMillis() - startZeit);
		}
		List<Long> durchschnitt = dauer.stream().reduce(Long::sum).stream()
		                               .collect(Collectors.toList());
		System.out.println("Der Durchschnitt MIT chin. Restsatz " + "betrug "
		                   + durchschnitt.get(0) / haeufigkeit + " ms\n------");
	}
}

