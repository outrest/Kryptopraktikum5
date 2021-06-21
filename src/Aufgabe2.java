//import numpy as np;
import java.math.BigDecimal;
import java.math.BigInteger;

import static java.util.Arrays.stream;

public class Aufgabe2 {
	private Aufgabe1 aufg1;
	BigInteger x;
	BigInteger p;
	BigInteger q;
	BigInteger d;
	int n;

	public void ausfuehren() {
		System.out.println(
				"-----------------------------------------------------------------------------\nFühre Aufgabe 2 "
				+ "aus:\n\n");

		//1.
		BigInteger xp = x.mod(p);
		BigInteger xq = x.mod(q);
		BigInteger dp = d.mod(p.subtract(BigInteger.ONE));
		BigInteger dq = d.mod(q.subtract(BigInteger.ONE));

		//2.
		BigInteger yp = xp.modPow(dp, p);
		BigInteger yq = xq.modPow(dq, q);



	}



















}
