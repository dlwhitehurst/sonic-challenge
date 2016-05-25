/**
 * 
 */
package com.sonic.domain;

import java.io.Serializable;

/**
 * This class provides the Java Object overriden methods recommended by Joshua J. Bloch,
 * author of Effective Java, Second Edition
 * 
 * @author <a href="mailto:david@ciwise.com">David L. Whitehurst</a>
 */
public abstract class BaseObject implements Serializable {

	/**
	 * unique serial class identifier
	 */
	private static final long serialVersionUID = -9157212094150650829L;

	/**
     * Return a multi-line String with key=value pairs.
     * @return a String representation of this class.
     */
    public abstract String toString();

    /**
     * Compares object equality. 
     * @param o object to compare to
     * @return true/false based on equality tests
     */
    public abstract boolean equals(Object o);

    /**
     * When you override equals, you should override hashCode. See "Why are
     * equals() and hashCode() importation" for more information:
     * http://www.hibernate.org/109.html
     * @return hashCode
     */
    public abstract int hashCode();
}
