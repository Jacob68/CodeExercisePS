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

	private ArrayList<Node> mNodes = new ArrayList<Node>();
	private ArrayList<Node> orderedNodes = new ArrayList<Node>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TODO Get package list input

		// TODO Create a node list from the package list
		// Node should have a name and dependent node

		// Once have the node list, generate the dependency list

		// TODO Generate output, catch cycles
	}

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
	 * 
	 * @return The list of <i>Nodes</i> as converted from the given list of
	 *         packages.
	 */
	public void convertPackagesToNodes(@NotNull String[] packages) {
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
			// mNodes.add(parent);
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
	public ArrayList<Node> computeDependencies(@NotNull ArrayList<Node> nodes) {
		// TODO go through all nodes, drill down to last dependent, add nodes in
		// reverse order
		return null;
	}

}
