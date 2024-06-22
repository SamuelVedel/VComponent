package fr.svedel.vcomponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 * This class implement text that display in one line.
 *
 * @author Samuel Vedel
 */
public class VLabel extends VComponent {
	
	private String text;
	
	private Color color = Color.WHITE;
	
	private String fontName = "ARIAL";
	private int fontStyle = Font.BOLD;
	private VAdjustInt fontSize = new VAdjustInt(10);
	
	/**
	 * Constructor for {@code VLabel}.
	 * The width and height are automaticaly
	 * generated with de font's properties
	 *
	 * @param x the abscissa of the component
	 * @param y the ordinate of the component
	 * @param widthReference the width for wich the
	 * sizes and positions of the component are the same as specified
	 * @param heightReference he height for wich the
	 * sizes and positions of the component are the same as specified
	 * @param text the text of the label
	 */
	public VLabel(int x, int y, int widthReference, int heightReference, String text) {
		super(x, y, 0, 0, widthReference, heightReference);
		this.text = text;
	}
	
	/**
	 * Constructor for {@code VLabel}.
	 * The width and height are automaticaly
	 * generated with de font's properties
	 *
	 * @param x the abscissa of the component
	 * @param y the ordinate of the component
	 * @param widthReference the width for wich the
	 * sizes and positions of the component are the same as specified
	 * @param heightReference he height for wich the
	 * sizes and positions of the component are the same as specified
	 */
	public VLabel(int x, int y, int widthReference, int heightReference) {
		this(x, y, widthReference, heightReference, null);
	}
	
	/**
	 * Constructor for {@code VLabel}.
	 * The width and height are automaticaly
	 * generated with de font's properties
	 *
	 * @param x the abscissa of the component
	 * @param y the ordinate of the component
	 * @param text the text of the label
	 */
	public VLabel(int x, int y, String text) {
		this(x, y, 0, 0, text);
	}
	
	/**
	 * Constructor for {@code VLabel}.
	 * The width and height are automaticaly
	 * generated with de font's properties.
	 *
	 * @param x the abscissa of the component
	 * @param y the ordinate of the component
	 */
	public VLabel(int x, int y) {
		this(x, y, 0, 0);
	}
	
	/**
	 * Return the text of the label
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Set the text of the label.
	 *
	 * @param text the new text for the label
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Return the font's color.
	 *
	 * @return the font's color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Set the font's color.
	 *
	 * @param color the new font's color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Return the font's name (ex: Arial).
	 *
	 * @return the font's name
	 */
	public String getFontName() {
		return fontName;
	}
	
	/**
	 * Set the font's name (ex: Arial).
	 *
	 * @param fontName the new font's name
	 */
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	
	/**
	 * Return the int that descire the font's style.
	 *
	 * @return the int that descire the font's style
	 * @see Font
	 */
	public int getFontStyle() {
		return fontStyle;
	}
	
	/**
	 * Set the int that descire the font's style.
	 *
	 * @param fontStyle the new int that descire the font's style
	 * @see Font
	 */
	public void setFontStyle(int fontStyle) {
		this.fontStyle = fontStyle;
	}
	
	/**
	 * Return the adjustable vairable for the font's size.
	 *
	 * @return the adjustable vairable for the font's size
	 * @see VAdjustInt
	 */
	public VAdjustInt getFontSize() {
		return fontSize;
	}
	
	@Override
	public void adjust(int widthReference, int heightReference) {
		super.adjust(widthReference, heightReference);
		adjustValue(fontSize);
	}
	
	@Override
	public void display(Graphics2D g2d) {
		int currentX = getX().getCurrentValue();
		int currentY = getY().getCurrentValue();
		int currentWidth = getWidth().getCurrentValue();
		int currentHeight = getHeight().getCurrentValue();
		
		g2d.setFont(new Font(fontName, fontStyle, fontSize.getValue()));
		getWidth().setValue(UsefulTh.getTextW(text, g2d));
		getHeight().setValue(UsefulTh.getTextH(text, g2d));
		
		g2d.setColor(color);
		g2d.setFont(new Font(fontName, fontStyle, fontSize.getCurrentValue()));
		UsefulTh.drawString(text, currentX, currentY+currentHeight, g2d);
	}
}
