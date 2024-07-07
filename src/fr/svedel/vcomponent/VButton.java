package fr.svedel.vcomponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * This class implements buttons.
 * This class inherits from {@code VAbstractButton}
 *
 * @author Samuel Vedel
 * @see VAbstractButton
 */
public class VButton extends VAbstractButton {
	
	private String text;
	private VAdjustInt fontSize = new VAdjustInt(50);
	private VAdjustInt round = new VAdjustInt(50);
	// TODO create VAdjustFloat
	private VAdjustInt borderWidth = new VAdjustInt(1);
	
	// Couleur noraml du boutton.
	private Color background = new Color(0, 0, 0, 200);
	private Color foreground = new Color(255, 255, 255, 200);
	private Color border = new Color(255, 255, 255, 200);
	
	// Couleur du boutton quand il est survolé.
	private Color survolBackground = new Color(255, 255, 255, 200);
	private Color survolForeground = new Color(0, 0, 0, 200);
	private Color survolBorder = new Color(255, 255, 255, 200);
	
	/**
	 * Constructor for {@code VButton}.
	 *
	 * @param x the abscissa of the button
	 * @param y the ordinate of the button
	 * @param w the width of the button
	 * @param h the height of the button
	 * @param widthReference the width for wich the
	 * sizes and positions of the button are the same as specified
	 * @param heightReference he height for wich the
	 * sizes and positions of the button are the same as specified
	 * @param text the button's text
	 */
	public VButton(int x, int y, int w, int h, int widthReference,
				   int heightReference, String text) {
		super(x, y, w, h, widthReference, heightReference);
		this.text = text;
	}
	
	/**
	 * Constructor for {@code VButton}.
	 *
	 * @param x the abscissa of the button
	 * @param y the ordinate of the button
	 * @param w the width of the button
	 * @param h the height of the button
	 * @param widthReference the width for wich the
	 * sizes and positions of the button are the same as specified
	 * @param heightReference he height for wich the
	 * sizes and positions of the button are the same as specified
	 */
	public VButton(int x, int y, int w, int h, int widthReference, int heightReference) {
		this(x, y, w, h, widthReference, heightReference, null);
	}
	
	/**
	 * Constructor for {@code VButton}.
	 *
	 * @param x the abscissa of the button
	 * @param y the ordinate of the button
	 * @param w the width of the button
	 * @param h the height of the button
	 * @param text the button's text
	 */
	public VButton(int x, int y, int w, int h, String text) {
		this(x, y, w, h, 0, 0, text);
	}
	
	/**
	 * Constructor for {@code VButton}.
	 *
	 * @param x the abscissa of the button
	 * @param y the ordinate of the button
	 * @param w the width of the button
	 * @param h the height of the button
	 */
	public VButton(int x, int y, int w, int h) {
		this(x, y, w, h, 0, 0);
	}
	
	/**
	 * Constructor for {@code VButton}.
	 *
	 * @param w the width of the button
	 * @param h the height of the button
	 * @param text the button's text
	 */
	public VButton(int w, int h, String text) {
		this(0, 0, w, h, text);
	}
	
	/**
	 * Constructor for {@code VButton}.
	 *
	 * @param w the width of the button
	 * @param h the height of the button
	 */
	public VButton(int w, int h) {
		this(0, 0, w, h);
	}
	
	/**
	 * Constructor for {@code VButton}.
	 *
	 * @param text the button's text
	 */
	public VButton(String text) {
		this(0, 0, text);
	}
	
	/**
	 * Return the button's text.
	 *
	 * @return the button's text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Set the button's text.
	 *
	 * @param text the new button's text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Return the adjustable variable of the
	 * button's font size.
	 *
	 * @return the adjustable variable of the
	 * button's font size
	 */
	public VAdjustInt getFontSize() {
		return fontSize;
	}
	
	/**
	 * Return the adjustable variable of the
	 * button's round.
	 *
	 * @return the adjustable variable of the
	 * button's round
	 */
	public VAdjustInt getRound() {
		return round;
	}
	
	/**
	 * Return the adjustable variable of the
	 * button's border width.
	 *
	 * @return the adjustable variable of the
	 * button's border width
	 */
	public VAdjustInt getBorderWidth() {
		return borderWidth;
	}
	
	/**
	 * Return the button's background color.
	 *
	 * @return the button's background color
	 */
	public Color getBackground() {
		return background;
	}
	
	/**
	 * Set the button's background color.
	 *
	 * @param background the new button's background color
	 */
	public void setBackground(Color background) {
		this.background = background;
	}
	
	/**
	 * Return the button's foreground color.
	 *
	 * @return the button's foreground color
	 */
	public Color getForeground() {
		return foreground;
	}
	
	/**
	 * Set the button's foreground color.
	 *
	 * @param foreground the new button's foreground color
	 */
	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}
	
	/**
	 * Return the button's border color.
	 *
	 * @return the button's border color
	 */
	public Color getBorder() {
		return border;
	}
	
	/**
	 * Set the button's border color.
	 *
	 * @param border the new button's border color
	 */
	public void setBorder(Color border) {
		this.border = border;
	}
	
	/**
	 * Return the button's background color
	 * when the mouse hovers it.
	 *
	 * @return the button's background color
	 * when the mouse hovers it
	 */
	public Color getSurvolBackground() {
		return survolBackground;
	}
	
	/**
	 * Set the button's background color when the mouse
	 * hovers it.
	 *
	 * @param survolBackground the new button's background color when the mouse
	 * hovers it
	 */
	public void setSurvolBackground(Color survolBackground) {
		this.survolBackground = survolBackground;
	}
	
	/**
	 * Return the button's foreground color
	 * when the mouse hovers it.
	 *
	 * @return the button's foreground color
	 * when the mouse hovers it
	 */
	public Color getSurvolForeground() {
		return survolForeground;
	}
	
	/**
	 * Set the button's foreground color when the mouse
	 * hovers it.
	 *
	 * @param survolForegorund the new button's foreground color when the mouse
	 * hovers it
	 */
	public void setSurvolForeground(Color survolForeground) {
		this.survolForeground = survolForeground;
	}
	
	/**
	 * Return the button's border color
	 * when the mouse hovers it.
	 *
	 * @return the button's border color
	 * when the mouse hovers it
	 */
	public Color getSurvolBorder() {
		return survolBorder;
	}
	
	/**
	 * Set the button's border color when the mouse
	 * hovers it.
	 *
	 * @param survolBorder the new button's border color when the mouse
	 * hovers it
	 */
	public void setSurvolBorder(Color survolBorder) {
		this.survolBorder = survolBorder;
	}
	
	@Override
	public void adjust(int widthReference, int heightReference) {
		super.adjust(widthReference, heightReference);
		adjustValue(fontSize);
		adjustValue(round);
		adjustValue(borderWidth);
	}
	
	@Override
	public void display(Graphics2D g2d) {
		int currentX = getX().getCurrentValue();
		int currentY = getY().getCurrentValue();
		int currentWidth = getWidth().getCurrentValue();
		int currentHeight = getHeight().getCurrentValue();
		
		g2d.setColor(!isMouseIn()? background : survolBackground);
		g2d.fillRoundRect(currentX, currentY, currentWidth, currentHeight,
						  round.getCurrentValue(), round.getCurrentValue());

		g2d.setColor(isMouseIn()? survolBorder: border);
		g2d.setStroke(new BasicStroke(borderWidth.getCurrentValue()));
		g2d.drawRoundRect(currentX, currentY, currentWidth, currentHeight,
						  round.getCurrentValue(), round.getCurrentValue());;
		g2d.setStroke(new BasicStroke(1));
		
		if (text != null) {
			g2d.setColor(!isMouseIn()? foreground : survolForeground);
			g2d.setFont(new Font("ARIAL", Font.BOLD, fontSize.getCurrentValue()));
			int textW = UsefulTh.getTextW(text, g2d);
			int textH = UsefulTh.getTextH(text, g2d);
			UsefulTh.drawString(text, currentX+currentWidth/2-textW/2,
								currentY+currentHeight/2+textH/2, g2d);
		}
	}
}
