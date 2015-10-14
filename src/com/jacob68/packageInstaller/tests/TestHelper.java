package com.jacob68.packageInstaller.tests;

public class TestHelper {

	// Simple package strings

	public static String[] getValidSimpleSmall() {
		// Output: A, B, C
		return new String[] { "A: ", "B: A", "C: B" };
	}

	public static String[] getValidSimpleLarge() {
		// Output: C, F, D, B, A, E, G
		return new String[] { "A: D", "D: F", "F: C", "B: F", "C: ", "E: A",
				"G: " };
	}

	public static String[] getCyclicSimpleSmall() {
		return new String[] { "A: B", "B: A" };
	}

	public static String[] getCyclicSimpleLarge() {
		return new String[] { "A: D", "D: F", "F: C", "B: F", "C: B", "E: A",
				"G: " };
	}

	// More complex package strings

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
