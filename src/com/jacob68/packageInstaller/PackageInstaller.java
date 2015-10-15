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
 * <p>
 * TODO Could maybe simplify the process by removing the Node class and
 * combining the convertPackagesToNodes list and the computeDependencies list?
 * </p>
 * 
 * @author Jacobus LaFazia
 */
public class PackageInstaller {

	private ArrayList<Node> mNodes = new ArrayList<Node>();
	private String mErrorMsg;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TODO Get package list input

		// TODO Create a node list from the package list
		// Node should have a name and dependent node

		// Once have the node list, generate the dependency list

		// TODO Generate output, catch cycles
	}

	public String getErrorMessage() {
		return mErrorMsg;
	}

	/**
	 * @param nodeName
	 *            The name of the node to get from the nodes list.
	 * 
	 * @return The Node with the given <i>nodeName</i> or <code>null</code> if
	 *         not found in the nodes list.
	 */
	private Node getNode(String nodeName) {
		for (Node node : mNodes) {
			if (node.name.equals(nodeName)) {
				return node;
			}
		}
		return null;
	}

	/**
	 * Must call method
	 * {@linkplain PackageInstaller#convertPackagesToNodes(String[])} first to
	 * ensure the nodes list has been generated.
	 * 
	 * @return The list of <i>Nodes</i> as converted from the package list.
	 */
	public ArrayList<Node> getNodes() {
		return mNodes;
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
	 */
	public void convertPackagesToNodes(@NotNull String[] packages) {
		// Clear current nodes list
		mNodes.clear();

		// Create a Node object for each package
		for (int i = 0; i < packages.length; i++) {
			String[] parts = packages[i].split(": ");

			// Get/Create the parent node
			Node parent = getNode(parts[0]);
			if (parent == null) {
				// Don't have this node, create it
				parent = new Node(parts[0]);
			}

			// Set dependent node if any
			if (parent.dependent == null && parts.length > 1) {
				// Need to set the dependent
				String name = parts[1];
				// See if we have this node already
				Node dependent = getNode(name);
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
					mNodes.add(dependent);
				}
			}

			// Add/update node to list
			addOrUpdateNodeToList(parent);
		}
	}

	private void addOrUpdateNodeToList(Node node) {
		for (Node n : mNodes) {
			if (n.name.equals(node.name)) {
				// Update dependency
				n.dependent = node.dependent;
				return;
			}
		}
		// Node not found, add it to list
		mNodes.add(node);
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
	public ArrayList<Node> computeDependencies() {
		// Keep track of nodes we've already seen to check for circular
		// dependencies.
		ArrayList<Node> seen = new ArrayList<Node>();
		ArrayList<Node> resolved = new ArrayList<Node>();

		try {
			while (mNodes.size() > 0) {
				resolveDependency(mNodes.get(0), seen, mNodes, resolved);
			}

		} catch (Exception e) {
			// Save error message
			mErrorMsg = e.getMessage();
			return null;
		}

		return resolved;
	}

	/**
	 * Drills down through the dependencies of the given node until the last
	 * dependency has been found or a circular dependency has been detected.
	 * 
	 * @param node
	 *            The node for which to resolve dependencies.
	 * @param resolved
	 *            The list of currently resolved nodes.
	 */
	private void resolveDependency(Node node, ArrayList<Node> seen,
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
