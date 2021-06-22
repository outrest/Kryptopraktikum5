/**
 * Project: Kryptopraktikum5
 * Package: PACKAGE_NAME
 * Author:  F. Mispelbaum (fmispe2s)
 * Version: 1.0
 * Date:    21.06.2021
 */
public class Main {
	public static void main(String[] args) {
		int klartext = 4711;
		Aufgabe1 AufgabeEins = new Aufgabe1(3096, klartext);
		Aufgabe2 AufgabeZwei = new Aufgabe2(AufgabeEins);
	}
}