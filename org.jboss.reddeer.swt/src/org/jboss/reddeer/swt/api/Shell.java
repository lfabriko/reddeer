package org.jboss.reddeer.swt.api;


/**
 * API for Shell manipulation
 * @author Jiri Peterka
 *
 */
public interface Shell {
	

	/**
	 * Return frame title of a Shell 
	 * @return
	 */	
	String getText();
	

	/**
	 * Closes shell
	 */
	void close();
			
}
