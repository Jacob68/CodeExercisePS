package com.jacob68.packageInstaller;

import java.util.ArrayList;

public class Result {

	private ArrayList<Node> mResolved;
	private String mErrorMsg;

	public Result(ArrayList<Node> resolvedNodes, String errorMsg) {
		mResolved = resolvedNodes;
		mErrorMsg = errorMsg;
	}

	public ArrayList<Node> getResolvedNodes() {
		return mResolved;
	}

	public String getErrorMessage() {
		return mErrorMsg;
	}

}
