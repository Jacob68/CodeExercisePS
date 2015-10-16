package com.jacob68.packageInstaller;

import java.util.Scanner;

import com.jacob68.packageInstaller.tests.TestHelper;

/**
 * This is the package installer that will take a list of packages and determine
 * the correct order of install such that an install won't fail due to a missing
 * dependency.
 * <p>
 * If a circular dependency is detected, an error will be displayed and the
 * application will terminate.
 * </p>
 * <p>
 * Pass the package list to this class as arguments.
 * </p>
 * 
 * @author Jacobus LaFazia
 */
public class PackageInstaller {

	public static void main(String[] args) {

		System.out.println("Welcome to the package installer");
		System.out.println("--------------------------------");
		System.out.println("Enter a comma separated list of packages to start");
		System.out
				.println("Each package must have the format, 'name: dependency'");
		System.out.println("Example input: 'A: B, B: C, C: '");
		System.out.println("Hit the 'return' key when finished.");

		// Get package list input
		Scanner input = new Scanner(System.in);
		String entry = "";
		String[] packages;

		do {
			System.out.println("Enter: ");

			entry = input.nextLine();

			// Parse input into package array
			packages = entry.split(", ");

			System.out.println("Packages: "
					+ TestHelper.convertArrayToString(packages));

			System.out.println("Is this correct? (y/n)");
			if (input.nextLine().startsWith("y")) {
				// Ready to run the program
				break;
			}

		} while (true);

		// Close the input
		input.close();

		// Run the code
		System.out.println("Running...");
		Result result = DependencyResolver.resolveDependencies(packages);

		// Print out the results
		String msg = result.getErrorMessage();
		if (msg != null) {
			// Failed
			System.out.println("Error: " + msg);

		} else {
			msg = TestHelper.convertNodeListToString(result.getResolvedNodes(),
					false);
			System.out.println("Package install order: " + msg);
		}
	}

}
