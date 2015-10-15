package com.jacob68.packageInstaller;

/**
 * A <i>Node</i> represents a package.<br>
 * Each has a package 'name' and up to one dependent package/node.
 * 
 * @author Jacobus LaFazia
 */
public class Node {

	public String name;
	public Node dependent;

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            The package name.
	 */
	public Node(String name) {
		this.name = name;
	}

	/**
	 * Sets a dependent node to this node.
	 * 
	 * @param dependent
	 *            The node this node depends on or <code>null</code> to remove
	 *            the dependent.
	 */
	public void setDependent(Node dependent) {
		this.dependent = dependent;
	}

	@Override
	public String toString() {
		String depName = this.dependent == null ? "" : this.dependent.name;
		return "\"" + this.name + ": " + depName + "\"";
	}

}
