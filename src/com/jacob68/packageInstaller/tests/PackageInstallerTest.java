package com.jacob68.packageInstaller.tests;

import static org.junit.Assert.*;

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
	public void testInputOutputListSizesOnConvertPackages() {
		PackageInstaller installer = new PackageInstaller();

		// Get input package list
		String[] input = TestHelper.getValidLarge();
		// Print out the input
		System.out
				.print("\nSTART Convert Packages Input & Output list size Test:\n");
		System.out.print("Input: " + TestHelper.convertArrayToString(input));

		// Convert package list to node list
		installer.convertPackagesToNodes(input);
		ArrayList<Node> nodes = installer.getNodes();
		// Print out the nodes list
		System.out.print("\nComputed Nodes: "
				+ TestHelper.convertNodeListToString(nodes, true));

		// Print pass or fail
		System.out.print("\n Input list size = " + input.length
				+ ", output node list size = " + nodes.size());
		if (input.length == nodes.size()) {
			System.out.print("\n    PASSED    \n");

		} else {
			System.out.print("\n    FAIL    ");
		}

		assertEquals("Input list size (" + input.length
				+ ") must equal output list size (" + nodes.size() + ")",
				input.length, nodes.size());

		System.out.print("\nEND\n");
	}

	@Test
	public void inputListSizeShouldMatchOutputListSize() {
		PackageInstaller installer = new PackageInstaller();

		// Get input package list
		String[] input = TestHelper.getValidLarge();

		// Convert package list to node list
		installer.convertPackagesToNodes(input);
		ArrayList<Node> nodes = installer.getNodes();

		// Compute dependency list
		ArrayList<Node> resolved = installer.computeDependencies(nodes);

		System.out.println(String.format("Input size = %d, output size = %d",
				input.length, resolved.size()));
		assertEquals("Input list size (" + input.length
				+ ") must equal output list size (" + resolved.size() + ")",
				input.length, resolved.size());
	}

	@Test
	public void simpleSmallListShouldBeValid() {
		PackageInstaller installer = new PackageInstaller();

		// Get input package list
		String[] input = TestHelper.getValidSimpleSmall();

		// Convert package list to node list
		installer.convertPackagesToNodes(input);
		ArrayList<Node> nodes = installer.getNodes();

		// Compute dependency list
		ArrayList<Node> resolved = installer.computeDependencies(nodes);

		assertNotNull("Package list " + input.toString()
				+ " should generate a Non-Null install order string", resolved);
	}

	// TODO Test all other valid package lists

	@Test
	public void simpleSmallListShouldBeCyclic() {
		PackageInstaller installer = new PackageInstaller();

		// Get input package list
		String[] input = TestHelper.getCyclicSimpleSmall();

		// Convert package list to node list
		installer.convertPackagesToNodes(input);
		ArrayList<Node> nodes = installer.getNodes();

		// Compute dependency list
		ArrayList<Node> resolved = installer.computeDependencies(nodes);

		assertNull("Package list " + input.toString()
				+ " is cyclic and should generate a Null resolved list",
				resolved);
	}
	// TODO Test all other cyclic package lists

}
