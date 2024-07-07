package fr.svedel.vcomponent;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

/**
 * This class store functions that are used in all of the
 * package.
 *
 * @author Samuel Vedel
 */
public abstract class UsefulTh {
	
	/**
	 * Return the width of a text.
	 * 
	 * @param text the text
	 * @param g2d the {@code Graphics2D} wich is used to know the font
	 * @return the width of the text
	 */
	protected static int getTextW(String text, Graphics2D g2d) {
		FontMetrics fm = g2d.getFontMetrics();
		Rectangle2D textBounds = fm.getStringBounds(text, g2d);
		return (int) textBounds.getWidth();
	}
	
	/**
	 * <p>
	 * Return the height of a text.
	 * </p>
	 *
	 * <p>
	 * If the font isn't Arial, there can be gap bewteen the value
	 * returned, and the acctual height of the text.
	 * </p>
	 * 
	 * @param text the text
	 * @param g2d the {@code Graphics2D} wich is used to know the font
	 * @return the height of the text
	 */
	protected static int getTextH(String text, Graphics2D g2d) {
		FontMetrics fm = g2d.getFontMetrics();
		Rectangle2D textBounds = fm.getStringBounds(text, g2d);
		return (int) textBounds.getHeight()-g2d.getFont().getSize()*20/50;
	}
	
	/**
	 * Draw a string in a smooth way.
	 *
	 * @param text the text we want to draw
	 * @param x the abscissa of the text
	 * @param y the ordinate of the text
	 * @param g2d the Graphics2D context in which to paint
	 */
	public static void drawString(String text, int x, int y, Graphics2D g2d) {
		Graphics2D g2d2 = (Graphics2D) g2d.create();
		g2d2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d2.drawString(text, x, y);
		g2d2.dispose();
	}
}
