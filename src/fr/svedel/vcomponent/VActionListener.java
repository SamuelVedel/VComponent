package fr.svedel.vcomponent;

import java.awt.event.MouseEvent;

/**
 * This interface is use to interact with buttons.
 *
 * @author Samuel Vedel
 * @see VAbstractButton
 */
public interface VActionListener {
	
	/**
	 * This methode is called when the button is clicked
	 *
	 * @param e the event for wich we want to execute the action
	 */
	public void action(VComponent source, MouseEvent e);
}
