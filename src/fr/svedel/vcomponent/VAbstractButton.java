package fr.svedel.vcomponent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * This absctact class is the base of all clickable component
 * of this package. And can be use to create new clickable component.
 * <p>
 * We can add {@code VActionListener} to a {@code VAbstractButton} to
 * interact with it
 *
 * @author Samuel Vedel
 * @see VActionListener
 */
public abstract class VAbstractButton extends VComponent {
	
	private ArrayList<VActionListener> vActLs = new ArrayList<>();
	
	private boolean usable = true;
	
	private MouseListener ml = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (!usable) setPressed(false);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {}
		
		@Override
		public void mouseEntered(MouseEvent e) {}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (usable) executeVActLs(e);
		}
	};
	
	/**
	 * Constructor for {@code VAbstractButton}.
	 *
	 * @param x the abscissa of the button
	 * @param y the ordinate of the button
	 * @param w the width of the button
	 * @param h the height of the button
	 * @param widthReference The width for wich the
	 * sizes and positions of the component are the same as specified
	 * @param heightReference The height for wich the
	 * sizes and positions of the component are the same as specified
	 */
	public VAbstractButton(int x, int y, int w, int h,
						   int widthReference, int heightReference) {
		super(x, y, w, h, widthReference, heightReference);
		addMouseListener(ml);
	}
	
	/**
	 * Constructor for {@code VAbstractButton}.
	 *
	 * @param x the abscissa of the button
	 * @param y the ordinate of the button
	 * @param w the width of the button
	 * @param h the height of the button
	*/
	public VAbstractButton(int x, int y, int w, int h) {
		this(x, y, w, h, 0, 0);
	}
	
	/**
	 * To know if the button is usale
	 *
	 * @return a boolean that says if the button
	 * is usable or not
	 */
	public boolean isUsable() {
		return usable;
	}
	
	/**
	 * To decide if the button is usable or not
	 *
	 * @param usable the boolean that says
	 * if you want to make the button usable or not
	 */
	public void setUsable(boolean usable) {
		this.usable = usable;
	}
	
	/**
	 * Add a {@code VActionListener} to the button.
	 * {@code VActionListener} are used to interact with
	 * buttons
	 *
	 * @param val the {@code VActionListener} you want to add
	 * @see VActionListener
	 */
	public void addVActionListener(VActionListener val) {
		vActLs.add(val);
	}
	
	/**
	 * Remove a {@code VActionListener} from the button.
	 * {@code VActionListener} are used to interact with
	 * buttons
	 *
	 * @param val the {@code VActionListener} you want to remove
	 * @see VActionListener
	 */
	public void removeVActionListener(VActionListener val) {
		for (int i = vActLs.size()-1; i >= 0; i--) {
			if (vActLs.get(i) == val) {
				vActLs.remove(i);
			}
		}
	}
	
	/**
	 * Remove all {@code VActionListener} from the button.
	 * {@code VActionListener} are used to interact with
	 * buttons
	 *
	 * @see VActionListener
	 */
	public void removeAllVActionListener() {
		vActLs.removeAll(vActLs);
	}
	
	/**
	 * Call every {@code action} methodes from all this button's {@code VActionListener}
	 *
	 * @param e the event for wich we want to execute the action
	 * @see VActionListener
	 */
	public void executeVActLs(MouseEvent e) {
		for (VActionListener val : vActLs) {
			val.action(this, e);
		}
	}
	
	/**
	 * Supprime le {@code MouseListener} qui execute les {@code VActionListener}
	 * quand on clique sur le boutton
	 *
	 * Remove the {@code MouseListener} that is used to call all of the
	 * {@code VActionListener} when the button is clicked
	 */
	protected void removeMLForVActLs() {
		removeMouseListener(ml);
	}
}
