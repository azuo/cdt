/*******************************************************************************
 * Copyright (c) 2006 Wind River Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Markus Schorn - initial API and implementation
 *******************************************************************************/ 

package org.eclipse.cdt.internal.ui.callhierarchy;

import java.util.Comparator;

public class CHReferenceInfo {
	public static final Comparator COMPARE_OFFSET = new Comparator() {
		public int compare(Object o1, Object o2) {
			CHReferenceInfo r1= (CHReferenceInfo) o1;
			CHReferenceInfo r2= (CHReferenceInfo) o2;
			return r1.fOffset - r2.fOffset;
		}
	};
	private int fOffset;
	private int fLength;

	public CHReferenceInfo(int offset, int length) {
		fOffset= offset;
		fLength= length;
	}
	
	public int getOffset() {
		return fOffset;
	}

	public int getLength() {
		return fLength;
	}
}
