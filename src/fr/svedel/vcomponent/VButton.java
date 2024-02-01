package fr.svedel.vcomponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class VButton extends VAbstractButton {
	
	private String text;
	private int[] fontSize = {50, 0};
	private int[] round = {50, 0};
	
	// Couleur noraml du boutton.
	private Color background = new Color(0, 0, 0, 200);
	private Color foreground = new Color(255, 255, 255, 200);
	private Color border = new Color(255, 255, 255, 200);
	
	// Couleur du boutton quand il est survolé.
	private Color survolBackground = new Color(255, 255, 255, 200);
	private Color survolForeground = new Color(0, 0, 0, 200);
	private Color survolBorder = new Color(255, 255, 255, 200);
	
	public VButton(int x, int y, int w, int h, int widthReference, int heightReference, String text) {
		super(x, y, w, h, widthReference, heightReference);
		this.text = text;
	}
	
	public VButton(int x, int y, int w, int h, int widthReference, int heightReference) {
		this(x, y, w, h, widthReference, heightReference, null);
	}
	
	public VButton(int x, int y, int w, int h, String text) {
		this(x, y, w, h, 0, 0, text);
	}
	
	public VButton(int x, int y, int w, int h) {
		this(x, y, w, h, 0, 0);
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getFontSize() {
		return fontSize[0];
	}
	
	public void setFontSize(int fontSize) {
		this.fontSize[0] = fontSize;
	}
	
	public int getActualFontSize() {
		return fontSize[1];
	}
	
	public void setActualFontSize(int fontSize) {
		this.fontSize[1] = fontSize;
	}
	
	public int getRound() {
		return round[0];
	}
	
	public void setRound(int round) {
		this.round[0] = round;
	}
	
	public int getActualRound() {
		return round[1];
	}
	
	public void setActualRound(int round) {
		this.round[1] = round;
	}
	
	public Color getBackground() {
		return background;
	}
	
	public void setBackground(Color background) {
		this.background = background;
	}
	
	public Color getForeground() {
		return foreground;
	}
	
	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}
	
	public Color getBorder() {
		return border;
	}
	
	public void setBorder(Color border) {
		this.border = border;
	}
	
	public Color getSurvolBackground() {
		return survolBackground;
	}
	
	public void setSurvolBackground(Color survolBackground) {
		this.survolBackground = survolBackground;
	}
	
	public Color getSurvolForeground() {
		return survolForeground;
	}
	
	public void setSurvolForeground(Color survolForeground) {
		this.survolForeground = survolForeground;
	}
	
	public Color getSurvolBorder() {
		return survolBorder;
	}
	
	public void setSurvolBorder(Color survolBorder) {
		this.survolBorder = survolBorder;
	}
	
	@Override
	public void adjust(int widthReference, int heightReference) {
		super.adjust(widthReference, heightReference);
		switch (getAdjustment()) {
		case NO_ADJUSTMENT :
			fontSize[1] = fontSize[0];
			round[1] = round[0];
			break;
		case ADJUSTMENT_BY_WIDTH_AND_HEIGHT :
			fontSize[1] = getActualWidthReference()*fontSize[0]/getWidthReference();
			round[1] = getActualWidthReference()*round[0]/getWidthReference();
			break;
		case ADJUSTMENT_BY_WIDTH :
			fontSize[1] = getActualWidthReference()*fontSize[0]/getWidthReference();
			round[1] = getActualWidthReference()*round[0]/getWidthReference();
			break;
		case ADJUSTMENT_BY_HEIGHT :
			fontSize[1] = getActualHeightReference()*fontSize[0]/getHeightReference();
			round[1] = getActualHeightReference()*round[0]/getHeightReference();
			break;
		default :
//			if (widthReference[0]*w[0]/widthReference[1] >= heightReference[0]*w[0]/heightReference[1]) {
//				autoAdjustment = PitiButton.ADJUSTMENT_BY_WIDTH;
//				adjust(width, height);
//			} else {
//				autoAdjustment = PitiButton.ADJUSTMENT_BY_HEIGHT;
//				adjust(width, height);
//			}
//			autoAdjustment = PitiButton.ADJUSTMENT_BY_THE_SMALLEST;
		}
	}
	
	@Override
	public void display(Graphics2D g2d) {
		g2d.setColor(!isMouseIn()? background : survolBackground);
		g2d.fillRoundRect(getActualX(), getActualY(), getActualWidth(), getActualHeight(), round[1], round[1]);
		
		g2d.setColor(isMouseIn()? border : survolBorder);
		g2d.drawRoundRect(getActualX(), getActualY(), getActualWidth(), getActualHeight(), round[1], round[1]);
		
		if (text != null) {
			g2d.setColor(!isMouseIn()? foreground : survolForeground);
			g2d.setFont(new Font("ARIAL", Font.BOLD, fontSize[1]));
			int textW = UsefulTh.getTextW(text, g2d);
			int textH = UsefulTh.getTextH(text, g2d);
			UsefulTh.drawString(text, getActualX()+getActualWidth()/2-textW/2, getActualY()+getActualHeight()/2+textH/2, g2d);
		}
	}
}
