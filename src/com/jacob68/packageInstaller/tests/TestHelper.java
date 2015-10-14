package com.jacob68.packageInstaller.tests;

public class TestHelper {

	public static String[] getValidSmall() {
		return new String[] { "KittenService: CamelCaser", "CamelCaser" };
	}

	public static String[] getValidLarge() {
		return new String[] { "KittenService: ", "Leetmeme: Cyberportal",
				"Cyberportal: Ice", "CamelCaser: KittenService",
				"Fraudstream: Leetmeme", "Ice: " };
	}

	public static String[] getCyclicSmall() {
		return new String[] { "KittenService: CamelCaser",
				"CamelCaser: KittenService" };
	}

	public static String[] getCyclicLarge() {
		return new String[] { "KittenService: ", "Leetmeme: Cyberportal",
				"Cyberportal: Ice", "CamelCaser: KittenService",
				"Fraudstream: ", "Ice: Leetmeme" };
	}

}
