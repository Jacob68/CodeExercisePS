package com.jacob68.packageInstaller;

import java.util.ArrayList;

/**
 * The actual worker class. Contains all static methods with the end result of
 * generating a package install list with dependencies resolved.
 * 
 * @author Jacobus LaFazia
 */
public class DependencyResolver {

	/**
	 * The main method that gets it all done.
	 * 
	 * @param packages
	 *            The array of packages (with dependencies) from which to
	 *            generate a package install list.
	 * 
	 * @return A {@linkplain Result} object containing a list of the resolved
	 *         packages and an error message. If the given package list did not
	 *         contain errors, the resulting list will be non-null and the error
	 *         message will be null. If an error or circular dependency was
	 *         detected, the resulting list will be null and the error message
	 *         will be non-null.
	 */
	public static Result resolveDependencies(String[] packages) {
		ArrayList<Node> nodes = convertPackagesToNodes(packages);
		return computeDependencies(nodes);
	}

	/**
	 * @param nodeName
	 *            The name of the node to get from the nodes list.
	 * @param nodes
	 *            The list of nodes to check in.
	 * 
	 * @return The Node with the given <i>nodeName</i> or <code>null</code> if
	 *         not found in the nodes list.
	 */
	private static Node getNode(String nodeName, ArrayList<Node> nodes) {
		for (Node node : nodes) {
			if (node.name.equals(nodeName)) {
				return node;
			}
		}
		return null;
	}

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
	 * @return The list of <i>Nodes</i> as converted from the package list.
	 */
	public static ArrayList<Node> convertPackagesToNodes(String[] packages) {
		// Create nodes list
		ArrayList<Node> nodes = new ArrayList<Node>();

		// Create a Node object for each package
		for (int i = 0; i < packages.length; i++) {
			String[] parts = packages[i].split(": ");

			// Get/Create the parent node
			Node parent = getNode(parts[0], nodes);
			if (parent == null) {
				// Don't have this node, create it
				parent = new Node(parts[0]);
			}

			// Set dependent node if any
			if (parent.dependent == null && parts.length > 1) {
				// Need to set the dependent
				String name = parts[1];
				// See if we have this node already
				Node dependent = getNode(name, nodes);
				if (dependent != null) {
					// Have this node already,
					// Set the dependent node to the parent node.
					parent.setDependent(dependent);
					// TODO check for cycle here?

				} else {
					// Create the dependent node
					dependent = new Node(name);
					// Add the dependent node to the parent
					parent.setDependent(dependent);
					// Add new dependent node to list
					nodes.add(dependent);
				}
			}

			// Add/update node to list
			addOrUpdateNodeToList(parent, nodes);
		}

		return nodes;
	}

	/**
	 * Adds a node to the given list if it is not found in the list, if found in
	 * list, just updates the node's dependent.
	 * 
	 * @param node
	 *            The node to add/update.
	 * @param nodes
	 *            The list of nodes to add to.
	 */
	private static void addOrUpdateNodeToList(Node node, ArrayList<Node> nodes) {
		for (Node n : nodes) {
			if (n.name.equals(node.name)) {
				// Update dependency
				n.dependent = node.dependent;
				return;
			}
		}
		// Node not found, add it to list
		nodes.add(node);
	}

	/**
	 * Takes the given <i>nodes</i> list and computes all dependencies and
	 * generates a 'resolved' list of the same nodes such that all dependencies
	 * will be resolved.
	 * 
	 * @param nodes
	 *            The list of nodes to compute the dependency list for.
	 * 
	 * @return A {@linkplain Result} object containing a list of the resolved
	 *         packages and an error message. If the given package list did not
	 *         contain errors, the resulting list will be non-null and the error
	 *         message will be null. If an error or circular dependency was
	 *         detected, the resulting list will be null and the error message
	 *         will be non-null.
	 */
	public static Result computeDependencies(ArrayList<Node> nodes) {
		// Keep track of nodes we've already seen to check for circular
		// dependencies.
		ArrayList<Node> seen = new ArrayList<Node>();
		ArrayList<Node> unresolved = new ArrayList<Node>();
		unresolved.addAll(nodes);
		ArrayList<Node> resolved = new ArrayList<Node>();

		try {
			while (unresolved.size() > 0) {
				resolveDependency(unresolved.get(0), seen, unresolved, resolved);
			}

		} catch (Exception e) {
			// Return null to signify circular dependency
			return new Result(null, e.getMessage());
		}

		return new Result(resolved, null);
	}

	/**
	 * Drills down through the dependencies for the given node until the last
	 * dependency has been found or a circular dependency has been detected.
	 * 
	 * @param node
	 *            The node for which to resolve dependencies.
	 * @param seen
	 *            The list of already 'seen' nodes.
	 * @param unresolved
	 *            The list of currently unresolved nodes.
	 * @param resolved
	 *            The list of currently resolved nodes.
	 */
	private static void resolveDependency(Node node, ArrayList<Node> seen,
			ArrayList<Node> unresolved, ArrayList<Node> resolved)
			throws Exception {
		// Add node to the seen list
		seen.add(node);

		// Don't add dependent node if already resolved
		if (node.dependent != null && !resolved.contains(node.dependent)) {
			// Check for circular dependency
			if (seen.contains(node.dependent)) {
				throw new Exception(String.format(
						"Found circular dependency: %s -> %s", node.name,
						node.dependent.name));
			}
			resolveDependency(node.dependent, seen, unresolved, resolved);
		}

		if (!resolved.contains(node)) {
			// add to resolved list
			resolved.add(node);
		}
		// remove from unresolved list
		unresolved.remove(node);
	}

}
