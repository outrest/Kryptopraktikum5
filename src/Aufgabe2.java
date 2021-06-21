//import numpy as np;
import java.math.BigDecimal;
import java.math.BigInteger;

import static java.util.Arrays.stream;

public class Aufgabe2 {
	public static void main(String[] args) {
		System.out.println("Eat my bat!");



		BigInteger xp;
		BigInteger xq;
		BigInteger dp;
		BigInteger dq;


		BigInteger d = new BigInteger("1");
		BigDecimal anotherD = new BigDecimal(d);
		BigDecimal dMinusOne = BigDecimal.ONE.divide(anotherD);
		d=d.modInverse(d);
		System.out.println(d);



	}



















}
