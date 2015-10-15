package com.jacob68.packageInstaller;

import java.util.ArrayList;

import com.sun.istack.internal.NotNull;

/**
 * This is the package installer that will take a list of packages and determine
 * the correct order of install such that an install won't fail due to a missing
 * dependency.
 * <p>
 * If a circular dependency is detected, an error will be displayed and the
 * application will terminate.
 * </p>
 * 
 * @author Jacobus LaFazia
 */
public class PackageInstaller {

	/**
	 * Converts a String array containing packages into an ArrayList of
	 * {@linkplain Node}s.
	 * <p>
	 * Package String format must be of the form "package: dependsOnPackage"
	 * </p>
	 * 
	 * @param packages
	 *            The String array of packages. Must not be <code>null</code>.
	 * 
	 * @return The list of <i>Nodes</i> as converted from the given list of
	 *         packages.
	 */
	public static ArrayList<Node> convertPackagesToNodes(
			@NotNull String[] packages) {
		// TODO create the nodes list from the packages list

		return null;
	}

	/**
	 * Takes the given <i>nodes</i> list and computes all dependencies and
	 * re-orders the nodes such that all dependencies will be resolved.
	 * 
	 * @param nodes
	 *            The list of nodes to compute the dependency list for.
	 * 
	 * @return The new list of nodes that are re-ordered with dependencies
	 *         resolved or <code>null</code> if a circular dependency was
	 *         detected.
	 */
	public static ArrayList<Node> computeDependencies(
			@NotNull ArrayList<Node> nodes) {
		// TODO go through all nodes, drill down to last dependent, add nodes in
		// reverse order
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TODO Get package list input

		// TODO Create a node list from the package list
		// Node should have a name and dependent node

		// Once have the node list, generate the dependency list

		// TODO Generate output, catch cycles
	}

}
