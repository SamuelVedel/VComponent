package fr.svedel.vcomponent;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

/**
 * This abstract class implement components that can be easily reshaped
 * when the window is resized.
 *
 * @author Samuel Vedel
 */
public abstract class VComponent
	implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	/**
	 * No size adjustment.
	 */
	public static final int NO_ADJUSTMENT = 0;
	/**
	 *Size adjustment with the width and the height of the window.
	 */
	public static final int ADJUSTMENT_BY_WIDTH_AND_HEIGHT = 1;
	/**
	 * Size adjustment width the width of the window.
	 */
	public static final int ADJUSTMENT_BY_WIDTH = 2;
	/**
	 * Size adjustment width the height of the window.
	 */
	public static final int ADJUSTMENT_BY_HEIGHT = 3;
	/**
	 * Size adjusmtent with the smallest between
	 * the width and the height of the window.
	 */
	public static final int ADJUSTMENT_BY_THE_SMALLEST = 4;
	
	/**
	 * No aligment.
	 * Equivalent to top/right alignment.
	 */
	public static final int NO_ALIGNMENT = 0;
	/**
	 * Aligment of the center of the component.
	 */
	public static final int CENTER_ALIGNMENT = 1;
	/**
	 * Aligment with the bottom/left of the component.
	 */
	public static final int BOTTOM_ALIGNMENT = 2;
	
//	public static final int LEFT_ALIGNMENT = 0;
//	public static final int CENTER_ALIGNMENT = 1;
//	public static final int RIGHT_ALIGNMENT = 2;
//	public static final int TOP_ALIGNMENT = LEFT_ALIGNMENT;
//	public static final int BOTTOM_ALIGNMENT = RIGHT_ALIGNMENT;
	
	private VAdjustInt widthReference;
	private VAdjustInt heightReference;
	private int autoAdjustment = NO_ADJUSTMENT;
	private int autoAlignment = NO_ALIGNMENT;
//	private int horizontalAlignment = LEFT_ALIGNMENT;
//	private int verticalAlignment = TOP_ALIGNMENT;
	
	private VAdjustInt width;
	private VAdjustInt height;
	private VAdjustInt x;
	private VAdjustInt y;
	
	private ArrayList<KeyListener> keyLs = new ArrayList<>();
	private ArrayList<MouseListener> mouseLs = new ArrayList<>();
	private ArrayList<MouseMotionListener> mouseMotionLs = new ArrayList<>();
	private ArrayList<MouseWheelListener> mouseWheelLs = new ArrayList<>();
	
	private boolean focus = false;
	private boolean focusable = true;
	private boolean mouseIn = false;
	private boolean pressed = false;

	/**
	 * Constructor for {@code VComponent}.
	 *
	 * @param x the abscissa of the component
	 * @param y the ordinate of the component
	 * @param w the width of the component
	 * @param h the height of the component
	 * @param widthReference the width for wich the
	 * sizes and positions of the component are the same as specified
	 * @param heightReference he height for wich the
	 * sizes and positions of the component are the same as specified
	 */
	public VComponent(int x, int y, int w, int h, int widthReference, int heightReference) {
		this.x = new VAdjustInt(x);
		this.y = new VAdjustInt(y);
		this.width = new VAdjustInt(w);
		this.height = new VAdjustInt(h);
		this.widthReference = new VAdjustInt(widthReference);
		this.heightReference = new VAdjustInt(heightReference);
	}
	
	/**
	 * Constructor for {@code VComponent}.
	 *
	 * @param x The abscissa of the component
	 * @param y The ordinate of the component
	 * @param w The width of the component
	 * @param h The height of the component
	 */
	public VComponent(int x, int y, int w, int h) {
		this(x, y, w, h, 0, 0);
	}
	
	/**
	 * Constructor for {@code VComponent}.
	 *
	 * @param w The width of the component
	 * @param h The height of the component
	 */
	public VComponent(int w, int h) {
		this(0, 0, w, h);
	}
	
	/**
	 * Constructor for {@code VComponent}.
	 *
	 * @param x The abscissa of the component
	 * @param y The ordinate of the component
	 * @param w The width of the component
	 * @param h The height of the component
	 */
	public VComponent() {
		this(0, 0);
	}
	
	/**
	 * Get the adjustable x value.
	 *
	 * @return The adjustable x value
	 */
	public VAdjustInt getX() {
		return x;
	}
	
	/**
	 * Get the adjustable y value.
	 *
	 * @return The adjustable y value
	 */
	public VAdjustInt getY() {
		return y;
	}
	
	/**
	 * Get the adjustable width value.
	 *
	 * @return The adjustable width value
	 */
	public VAdjustInt getWidth() {
		return width;
	}
	
	/**
	 * Get the adjustable height value.
	 *
	 * @return The adjustable height value
	 */
	public VAdjustInt getHeight() {
		return height;
	}
	
	/**
	 * Get the adjustable width reference value.
	 *
	 * @return The adjustable width reference value
	 */
	public VAdjustInt getWidthReference() {
		return widthReference;
	}
	
	/**
	 * Get the adjustable height reference value.
	 *
	 * @return The adjustable height reference value
	 */
	public VAdjustInt getHeightReference() {
		return heightReference;
	}
	
	/**
	 * Get the adjustment value.
	 * <p>
	 * The adjustment can be:
	 * <ul>
	 * <li> {@code NO_ADJUSTMENT} </li>
	 * <li> {@code ADJUSTMENT_BY_WIDTH_AND_HEIGHT} </li>
	 * <li> {@code ADJUSTMENT_BY_WIDTH} </li>
	 * <li> {@code ADJUSTMENT_BY_HEIGHT} </li>
	 * <li> {@code ADJUSTMENT_BY_THE_SMALLEST} </li>
	 * </ul>
	 *
	 * @return The adjusment value
	 */
	public int getAdjustment() {
		return autoAdjustment;
	}
	
	/**
	 * Set the adjustment value.
	 * <p>
	 * The adjustment can be:
	 * <ul>
	 * <li> {@code NO_ADJUSTMENT} </li>
	 * <li> {@code ADJUSTMENT_BY_WIDTH_AND_HEIGHT} </li>
	 * <li> {@code ADJUSTMENT_BY_WIDTH} </li>
	 * <li> {@code ADJUSTMENT_BY_HEIGHT} </li>
	 * <li> {@code ADJUSTMENT_BY_THE_SMALLEST} </li>
	 * </ul>
	 *
	 * @param autoAdjusment The new adjustment value
	 */
	public void setAdjustment(int autoAdjustment) {
		this.autoAdjustment = autoAdjustment;
	}
	
	/**
	 * Get the alignment value.
	 * <p>
	 * The alignment can be:
	 * <ul>
	 * <li> {@code NO_ALIGNMENT} </li>
	 * <li> {@code CENTER_ALIGNMENT} </li>
	 * <li> {@code BOTTOM_ALIGNMENT} </li>
	 * </ul>
	 *
	 * @return The alignment value
	 */
	public int getAlignment() {
		return autoAlignment;
	}
	
	/**
	 * Set the alignment value.
	 * <p>
	 * The alignment can be:
	 * <ul>
	 * <li> {@code NO_ALIGNMENT} </li>
	 * <li> {@code CENTER_ALIGNMENT} </li>
	 * <li> {@code BOTTOM_ALIGNMENT} </li>
	 * </ul>
	 *
	 * @param autoAlignment The new alignment value
	 */
	public void setAlignment(int autoAlignment) {
		this.autoAlignment = autoAlignment;
	}
	
//	public int getHorizontalAlignment() {
//		return horizontalAlignment;
//	}
//
//	public void setHorizontalAlignment(int horizontalAlignment) {
//		this.horizontalAlignment = horizontalAlignment;
//	}
//
//	public int getVerticalAlignment() {
//		return verticalAlignment;
//	}
//
//	public void setVerticalAlignment(int verticalAlignment) {
//		this.verticalAlignment = verticalAlignment;
//	}
	
	/**
	 * Add a KeyListener to the component.
	 *
	 * @param kl The KeyListener we want to add
	 */
	public void addKeyListener(KeyListener kl) {
		keyLs.add(kl);
	}
	
	/**
	 * Remove a KeyListener from the component.
	 *
	 * @param kl The KeyListener we want to removes
	 */
	public void removeKeyListener(KeyListener kl) {
		for (int i = keyLs.size()-1; i >= 0; i--) {
			if (keyLs.get(i) == kl) {
				keyLs.remove(i);
			}
		}
	}
	
	/**
	 * Remove all KeyListener from this component.
	 */
	public void removeAllKeyListeners() {
		keyLs.removeAll(keyLs);
	}
	
	/**
	 * Add a MouseListener to the component.
	 *
	 * @param ml The MouseListener we want to add
	 */
	public void addMouseListener(MouseListener ml) {
		mouseLs.add(ml);
	}
	
	/**
	 * Remove a MouseListener from the component.
	 *
	 * @param kl The MouseListener we want to remove
	 */
	public void removeMouseListener(MouseListener ml) {
		for (int i = mouseLs.size()-1; i >= 0; i--) {
			if (mouseLs.get(i) == ml) {
				mouseLs.remove(i);
			}
		}
	}
	
	/**
	 * Remove all MouseListener from this component.
	 */
	public void removeAllMouseListeners() {
		mouseLs.removeAll(mouseLs);
	}
	
	/**
	 * Add a MouseMotionListener to the component.
	 *
	 * @param mml The MouseMotionListener we want to add
	 */
	public void addMouseMotionListener(MouseMotionListener mml) {
		mouseMotionLs.add(mml);
	}
	
	/**
	 * Remove a MouseMotionListener from the component.
	 *
	 * @param mml The MouseMotionListener we want to remove
	 */
	public void removeMouseMotionListener(MouseMotionListener mml) {
		for (int i = mouseMotionLs.size()-1; i >= 0; i--) {
			if (mouseMotionLs.get(i) == mml) {
				mouseMotionLs.remove(i);
			}
		}
	}
	
	/**
	 * Remove all moueMotionListener from this component.
	 */
	public void removeAllMouseMotionListeners() {
		mouseMotionLs.removeAll(mouseMotionLs);
	}
	
	/**
	 * Add a MouseWheelListener to the component.
	 *
	 * @param mwl The MouseWheelListener we want to add
	 */
	public void addMouseWheelListener(MouseWheelListener mwl) {
		mouseWheelLs.add(mwl);
	}
	
	/**
	 * Remove a MouseWheelListener from the component.
	 *
	 * @param mwll The mouseWheelListener we want to remove
	 */
	public void removeMouseWheelListener(MouseWheelListener mwl) {
		for (int i = mouseWheelLs.size()-1; i >= 0; i--) {
			if (mouseWheelLs.get(i) == mwl) {
				mouseWheelLs.remove(i);
			}
		}
	}
	
	/**
	 * Remove all MouseWheelListener from this component.
	 */
	public void removeAllMouseWheelListener() {
		mouseWheelLs.removeAll(mouseWheelLs);
	}
	
	/**
	 * Say if the component has the focus.
	 * To use keyboard event width a component
	 * the focus is required.
	 *
	 * @return A boolean that says if the component has the focus
	 */
	public boolean hasFocus() {
		return focus;
	}
	
	/**
	 * Set the focus on this component.
	 * To use keyboard event width a component
	 * the focus is required.
	 *
	 * @param focus The new focus of the component
	 */
	public void setFocus(boolean focus) {
		this.focus = focus;
	}
	
	/**
	 * Says if the component is focusable.
	 * To use keyboard event width a component
	 * the focus is required.
	 *
	 * @return A boolean that says if the component is focusable
	 */
	public boolean isFocusable() {
		return focusable;
	}
	
	/**
	 * To decide if the component is focusable of not.
	 * To use keyboard event width a component
	 * the focus is required.
	 *
	 * @param fucusable A boolean that says if we
	 * want the comonent to be focusable of not
	 */
	public void setFocusable(boolean focusable) {
		this.focusable = focusable;
		if (focusable == false) focus = false;
	}
	
	/**
	 * Says if the mouse is in this component.
	 *
	 * @return A boolean that says if the mouse is in this component
	 */
	public boolean isMouseIn() {
		return mouseIn;
	}
	
	/**
	 * Says if a key is pressed with the focus on this component.
	 *
	 * @return A boolean that says if a key is pressed with the
	 * focus on this component
	 */
	public boolean isPressed() {
		return pressed;
	}
	
	/**
	 * Set a variable that describe if a key is pressed when the focus
	 * is on this component
	 *
	 * @param pressed The new value of this variable
	 */
	protected void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
	
	// fonctions des listeners --------------
	// KeyListener
	@Override
	public void keyTyped(KeyEvent e) {
		if (focus) {
			for (KeyListener kl : keyLs) {
				kl.keyTyped(e);
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (focus) {
			for (KeyListener kl : keyLs) {
				kl.keyPressed(e);
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (focus) {
			for (KeyListener kl : keyLs) {
				kl.keyReleased(e);
			}
		}
	}
	
	//MouseListener
	@Override
	public void mouseClicked(MouseEvent e) {
		for (MouseListener ml : mouseLs) {
			ml.mouseClicked(e);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (mouseIn) {
			pressed = true;
			if (focusable) focus = true;
			for (MouseListener ml : mouseLs) {
				ml.mousePressed(e);
			}
		} else {
			focus = false;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (pressed) {
			pressed = false;
			for (MouseListener ml : mouseLs) {
				ml.mouseReleased(e);
			}
			if (mouseIn) {
				mouseClicked(e);
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if (!mouseIn && isCoorIn(e.getX(), e.getY())) {
			mouseIn = true;
			for (MouseListener ml : mouseLs) {
				ml.mouseEntered(e);
			}
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		hardMouseExited(e);
	}
	
	// MouseMotionListener
	@Override
	public void mouseDragged(MouseEvent e) {
		checkForEnteredAndExited(e);
		if (pressed && mouseIn) {
			for (MouseMotionListener mml : mouseMotionLs) {
				mml.mouseDragged(e);
			}
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		checkForEnteredAndExited(e);
		if (!pressed && mouseIn) {
			for (MouseMotionListener mml : mouseMotionLs) {
				mml.mouseMoved(e);
			}
		}
	}
	
	/**
	 * Call the {@code mouseExited} function of the MouseListeners
	 * added to this component,
	 * even if the mouse is in this component.
	 *
	 * @param e the event to be processed
	 */
	public void hardMouseExited(MouseEvent e) {
		if (mouseIn) {
			mouseIn = false;
			for (MouseListener ml : mouseLs) {
				ml.mouseExited(e);
			}
		}
	}
	
	/**
	 * Call the {@code mouseExited} function of the MouseListeners
	 * added to this component
	 * if the mouse isn't in this component.
	 *
	 * @param e the event to be processed
	 */
	public void softMouseExited(MouseEvent e) {
		if (mouseIn && !isCoorIn(e.getX(), e.getY())) {
			mouseIn = false;
			for (MouseListener ml : mouseLs) {
				ml.mouseExited(e);
			}
		}
	}
	
	/**
	 * Check if the coordinates {@code x} and {@code y} are
	 * in the component.
	 *
	 * @param x point's abscissa
	 * @param y point's ordinate
	 * @return the boolean that describe if the point is in the component
	 */
	private boolean isCoorIn(int x, int y) {
		return x > this.x.getCurrentValue()
			&& x < this.x.getCurrentValue()+width.getCurrentValue()
			&& y > this.y.getCurrentValue()
			&& y < this.y.getCurrentValue()+height.getCurrentValue();
	}
	
	private void checkForEnteredAndExited(MouseEvent e) {
		/*if (isCoorIn(e.getX(), e.getY())) {
			if (!mouseIn) {
				mouseEntered(e);
			}
		} else if (mouseIn) {
			mouseExited(e);
			}*/
		mouseEntered(e);
		softMouseExited(e);
	}
	
	// MouseWheelListener
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (mouseIn) {
			for (int i = 0; i < mouseWheelLs.size(); ++i) {
				mouseWheelLs.get(i).mouseWheelMoved(e);
			}
		}
	}
	
	// fin des fonctions des listeners ------
	
	/**
	 * Adjust a {@code VAdjustInt} with the component's caracteristics
	 *
	 * @param value the adjustable value we want to adjust
	 * @param preferenceForWidth if the adjustment is
	 * {@code ADJUSTMENT_BY_WIDTH_AND_HEIGHT}, this boolean chooses
	 * between width and height
	 */
	public void adjustValue(VAdjustInt value, boolean preferenceForWidth) {
		switch (getAdjustment()) {
		case NO_ADJUSTMENT :
			value.adjust(null);
			break;
		case ADJUSTMENT_BY_WIDTH_AND_HEIGHT :
			if (preferenceForWidth) {
				value.adjust(widthReference);
			} else {
				value.adjust(heightReference);
			}
			break;
		case ADJUSTMENT_BY_WIDTH :
			value.adjust(widthReference);
			break;
		case ADJUSTMENT_BY_HEIGHT :
			value.adjust(heightReference);
			break;
		case ADJUSTMENT_BY_THE_SMALLEST :
			if (widthReference.getValue()*width.getValue()/widthReference.getCurrentValue()
				>= heightReference.getValue()*width.getValue()/heightReference.getCurrentValue()) {
				autoAdjustment = ADJUSTMENT_BY_WIDTH;
				adjustValue(value, preferenceForWidth);
			} else {
				autoAdjustment = ADJUSTMENT_BY_HEIGHT;
				adjustValue(value, preferenceForWidth);
			}
			autoAdjustment = ADJUSTMENT_BY_THE_SMALLEST;
		}
	}
	
	/**
	 * Adjust a {@code VAdjustInt} with the component's caracteristics.
	 * If the adjustment is {@code ADJUSTMENT_BY_WIDTH_AND_HEIGHT},
	 * it will be adjusted by width.
	 *
	 * @param value the adjustable value we want to adjust
	 */
	public void adjustValue(VAdjustInt value) {
		adjustValue(value, true);
	}
	
	/**
	 * Adjust all the adjustable values of the component
	 *
	 * @param widthReference the current width reference,
	 * for exemple the current width of a {@code JPanel}
	 * @param heightReference the current height reference,
	 * for exemple the current height of a {@code JPanel}
	 */
	public void adjust(int widthRefrence, int heightRefrence) {
		this.widthReference.setCurrentValue(widthRefrence);
		this.heightReference.setCurrentValue(heightRefrence);
		
		switch (autoAdjustment) {
		case NO_ADJUSTMENT :
			width.adjust(null);
			height.adjust(null);
			
			x.adjust(null);
			y.adjust(null);
			break;
		case ADJUSTMENT_BY_WIDTH_AND_HEIGHT :
			width.adjust(widthReference);
			height.adjust(heightReference);
			
			x.adjust(widthReference);
			y.adjust(heightReference);
			break;
		case ADJUSTMENT_BY_WIDTH :
			width.adjust(widthReference);
			height.adjust(widthReference);
			
			x.adjust(widthReference);
			if (autoAlignment == NO_ALIGNMENT) {
				y.adjust(widthReference);
			} else if (autoAlignment == CENTER_ALIGNMENT) {
				y.adjust(y.getValue()+height.getValue()/2, heightReference);
				y.setCurrentValue(y.getCurrentValue()-height.getCurrentValue()/2);
			} else {
				y.adjust(heightReference.getValue()-(y.getValue()+height.getValue()),
						 widthReference);
				y.setCurrentValue(heightReference.getCurrentValue()
								  -y.getCurrentValue()-height.getCurrentValue());
			}
			break;
		case ADJUSTMENT_BY_HEIGHT :
			width.adjust(heightReference);
			height.adjust(heightReference);
			
			if (autoAlignment == NO_ALIGNMENT) {
				x.adjust(heightReference);
			} else if (autoAlignment == CENTER_ALIGNMENT) {
				x.adjust(x.getValue()+width.getValue()/2, widthReference);
				x.setCurrentValue(x.getCurrentValue()-width.getCurrentValue()/2);
			} else if (autoAlignment == BOTTOM_ALIGNMENT) {
				x.adjust(widthReference.getValue()-(x.getValue()+width.getValue()),
						 heightReference);
				x.setCurrentValue(widthReference.getCurrentValue()
								  -x.getCurrentValue()-width.getCurrentValue());
			}
			y.adjust(heightReference);
			break;
		case ADJUSTMENT_BY_THE_SMALLEST :
			if (widthReference.getValue()*width.getValue()/widthReference.getCurrentValue()
				>= heightReference.getValue()
				   *width.getValue()/heightReference.getCurrentValue()) {
				autoAdjustment = ADJUSTMENT_BY_WIDTH;
				adjust(widthRefrence, heightRefrence);
			} else {
				autoAdjustment = ADJUSTMENT_BY_HEIGHT;
				adjust(widthRefrence, heightRefrence);
			}
			autoAdjustment = ADJUSTMENT_BY_THE_SMALLEST;
			break;
		}
	}
	
	/**
	 * Affiche le composant
	 *
	 * @param g2d the Graphics2D context in which to paint
	 */
	public abstract void display(Graphics2D g2d);
}
