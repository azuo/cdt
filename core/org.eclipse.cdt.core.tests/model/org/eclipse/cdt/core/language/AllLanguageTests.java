/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.language;

import org.eclipse.cdt.internal.index.tests.IndexTests;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author crecoskie
 *
 */
public class AllLanguageTests extends TestSuite {
	public static Test suite() {
		TestSuite suite = new IndexTests();
		
		suite.addTest(LanguageInheritanceTests.suite());
		
		return suite;
	}
}
