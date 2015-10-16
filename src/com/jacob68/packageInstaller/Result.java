package com.jacob68.packageInstaller;

import java.util.ArrayList;

/**
 * Wrapper for a result that contains a list of resolved nodes and an error
 * message.
 * 
 * @author Jacobus LaFazia
 */
public class Result {

	private ArrayList<Node> mResolved;
	private String mErrorMsg;

	public Result(ArrayList<Node> resolvedNodes, String errorMsg) {
		mResolved = resolvedNodes;
		mErrorMsg = errorMsg;
	}

	/**
	 * @return The list of dependency resolved nodes or <code>null</code> if an
	 *         error or circular dependency was detected.
	 */
	public ArrayList<Node> getResolvedNodes() {
		return mResolved;
	}

	/**
	 * @return The error message or <code>null</code> if no error occurred.
	 */
	public String getErrorMessage() {
		return mErrorMsg;
	}

}
