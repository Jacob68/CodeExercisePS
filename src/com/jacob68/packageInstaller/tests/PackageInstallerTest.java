package com.jacob68.packageInstaller.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.jacob68.packageInstaller.PackageInstaller;

public class PackageInstallerTest {

	// TODO maybe test correct output order?

	@Test
	public void packageListsShouldBeValidAndPass() {
		PackageInstaller installer = new PackageInstaller();

		String[] input1 = TestHelper.getValidSimpleSmall();
		assertNotNull("Package list " + input1.toString()
				+ " should generate a Non-Null install order string",
				installer.generateInstallOrder(input1));

		// TODO Test all other valid package lists
	}

	@Test
	public void packageListsShouldBeCyclicAndFail() {
		PackageInstaller installer = new PackageInstaller();

		String[] input1 = TestHelper.getCyclicSimpleSmall();
		assertNull("Package list " + input1.toString()
				+ " is cyclic and should generate a Null response",
				installer.generateInstallOrder(input1));

		// TODO Test all other cyclic package lists
	}

}
