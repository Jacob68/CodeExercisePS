package com.jacob68.packageInstaller.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;

import com.jacob68.packageInstaller.Node;
import com.jacob68.packageInstaller.PackageInstaller;

/**
 * Test class that runs JUnit tests on the {@linkplain PackageInstaller} class
 * methods.
 * 
 * @author Jacobus LaFazia
 */
public class PackageInstallerTest {

	// TODO Since packages can have up to 1 dependent, the input list and the
	// resolved lists should be the same length?
	// TODO create test for length comparison

	@Test
	public void simpleSmallListShouldBeValid() {
		// Get input package list
		String[] input = TestHelper.getValidSimpleSmall();

		// Convert package list to node list
		ArrayList<Node> nodes = PackageInstaller.convertPackagesToNodes(input);

		// Compute dependency list
		ArrayList<Node> resolved = PackageInstaller.computeDependencies(nodes);

		assertNotNull("Package list " + input.toString()
				+ " should generate a Non-Null install order string", resolved);
	}

	// TODO Test all other valid package lists

	@Test
	public void simpleSmallListShouldBeCyclic() {
		// Get input package list
		String[] input = TestHelper.getCyclicSimpleSmall();

		// Convert package list to node list
		ArrayList<Node> nodes = PackageInstaller.convertPackagesToNodes(input);

		// Compute dependency list
		ArrayList<Node> resolved = PackageInstaller.computeDependencies(nodes);

		assertNull("Package list " + input.toString()
				+ " is cyclic and should generate a Null resolved list",
				resolved);
	}
	// TODO Test all other cyclic package lists

}
