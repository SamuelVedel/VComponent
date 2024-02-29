package fr.svedel.vcomponent;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class VScrollPane extends VComponent {
	
	private VPanel vp;
	
	private int xScroll = 0;
	private int yScroll = 0;
	
	private int defaultScrollWidth = 10;
	private VAdjustInt xScrollWidth = new VAdjustInt(defaultScrollWidth);
	private VAdjustInt yScrollWidth = new VAdjustInt(defaultScrollWidth);
	
	public static final int X_SCROLL_NEVER = 0;
	public static final int X_SCROLL_ALWAYS = 1;
	//public static final int X_SCROLL_AS_NEEDED = 2;
	private int xScrollDisplay = X_SCROLL_ALWAYS;
	
	public static final int Y_SCROLL_NEVER = 3;
	public static final int Y_SCROLL_ALWAYS = 4;
	//public static final int Y_SCROLL_AS_NEEDED = 5;
	private int yScrollDisplay = Y_SCROLL_ALWAYS;
	
	private KeyListener kl = new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			if (vp != null) vp.keyTyped(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			if (vp != null) vp.keyReleased(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			if (vp != null) vp.keyPressed(e);
		}
	};
	private MouseListener ml = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if (!isMouseInScrollBar(e.getX(), e.getY())) {
				if (vp != null) vp.mouseReleased(e);
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (!isMouseInScrollBar(e.getX(), e.getY())) {
				if (vp != null) vp.mousePressed(e);
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			if (!isMouseInScrollBar(e.getX(), e.getY())) {
				if (vp != null) vp.mouseExited(e);
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {}
		
		@Override
		public void mouseClicked(MouseEvent e) {}
	};
	private MouseMotionListener mml = new MouseMotionListener() {
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if (!isMouseInScrollBar(e.getX(), e.getY())
				|| vp != null) vp.mouseMoved(e);
			if (isMouseInScrollBar(e.getX(), e.getY())
				&& vp.isMouseIn()) {
				vp.mouseExited(e);
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (!isMouseInScrollBar(e.getX(), e.getY())
				|| vp != null) vp.mouseDragged(e);
			if (isMouseInScrollBar(e.getX(), e.getY())
				&& vp.isMouseIn()) {
				vp.mouseExited(e);
			}
		}
	};
	
	public VScrollPane(int x, int y, int w, int h, int widthReference,
					   int heightReference, VPanel vp) {
		super(x, y, w, h, widthReference, heightReference);
		setVPanel(vp);
		initListeners();
	}
	
	public VScrollPane(int x, int y, int w, int h, int widthReference, int heightReference) {
		this(x, y, w, h, widthReference, heightReference, null);
	}
	
	public VScrollPane(int x, int y, int w, int h, VPanel vp) {
		this(x, y, w, h, 0, 0, vp);
	}
	
	public VScrollPane(int x, int y, int w, int h) {
		this(x, y, w, h, 0, 0, null);
	}
	
	private void initListeners() {
		addKeyListener(kl);
		addMouseListener(ml);
		addMouseMotionListener(mml);
	}
	
	public VPanel getVPanel() {
		return vp;
	}
	
	public void setVPanel(VPanel vp) {
		this.vp = vp;
		
		if (this.vp != null) {
			vp.getWidthReference().setValue(getWidth().getValue());
			vp.getHeightReference().setValue(getHeight().getValue());
			vp.setAdjustment(ADJUSTMENT_BY_WIDTH_AND_HEIGHT);
			vp.setAlignment(NO_ALIGNMENT);
		}
	}
	
	public int getXScrollDisplay() {
		return xScrollDisplay;
	}
	
	public void setXScrollDisplay(int display) {
		xScrollDisplay = display;
	}
	
	public int getYScrollDisplay() {
		return yScrollDisplay;
	}
	
	public void setYScrollDisplay(int display) {
		yScrollDisplay = display;
	}
	
	public int getDefaultScrollWidth() {
		return defaultScrollWidth;
	}
	
	public void setDefaultScrollWidth(int width) {
		defaultScrollWidth = width;
	}
	
	public VAdjustInt getXScrollWidth() {
		return xScrollWidth;
	}
	
	public VAdjustInt getYScrollWidth() {
		return yScrollWidth;
	}
	
	public int getXScrollLength() {
		if (getXScrollDisplay() != X_SCROLL_ALWAYS
			|| vp == null) {
			return 0;
		}
		int currentWidth = getWidth().getCurrentValue();
		int currentVPWidth = vp.getWidth().getCurrentValue();
		if (currentWidth >= currentVPWidth)
			return currentWidth;
		return currentWidth*currentWidth/currentVPWidth;
	}
	
	public int[] getXScrollCoordinates() {
		int currentX = getX().getCurrentValue();
		int currentY = getY().getCurrentValue();
		int currentWidth = getWidth().getCurrentValue();
		int currentVPWidth = vp.getWidth().getCurrentValue();
		
		int x = currentX+xScroll*currentWidth/currentVPWidth;
		int y = currentY+getHeight().getCurrentValue()-getXScrollWidth().getCurrentValue();
		return new int[] {x, y};
	}
	
	public int getYScrollLength() {
		if (getYScrollDisplay() != Y_SCROLL_ALWAYS
			|| vp == null)
			return 0;
		int currentHeight = getHeight().getCurrentValue();
		int currentVPHeight = vp.getHeight().getCurrentValue();
		if (currentHeight >= currentVPHeight)
			return currentHeight;
		return currentHeight*currentHeight/currentVPHeight;
	}
	
	public int[] getYScrollCoordinates() {
		int currentX = getX().getCurrentValue();
		int currentY = getY().getCurrentValue();
		int currentHeight = getHeight().getCurrentValue();
		int currentVPHeight = vp.getHeight().getCurrentValue();
		
		int x = currentX+getWidth().getCurrentValue()-getYScrollWidth().getCurrentValue();
		int y = currentY+yScroll*currentHeight/currentVPHeight;
		return new int[] {x, y};
	}
	
	private boolean isMouseInScrollBar(int mouseX, int mouseY) {
		return mouseX > getX().getCurrentValue()+
			getWidth().getCurrentValue()-getXScrollWidth().getCurrentValue()
			|| mouseY > getY().getCurrentValue()+
			getHeight().getCurrentValue()-getYScrollWidth().getCurrentValue();
	}
	
	@Override
	public void adjust(int widthRefrence, int heightRefrence) {
		super.adjust(widthRefrence, heightRefrence);
		adjustValue(xScrollWidth);
		adjustValue(yScrollWidth);
		vp.getX().setValue(getX().getValue()+xScroll);
		vp.getY().setValue(getY().getValue()+yScroll);
		vp.adjust(getWidth().getCurrentValue(),
				  getHeight().getCurrentValue());
	}
	
	@Override
	public void display(Graphics2D g2d) {
		if (vp != null) {
			int currentX = getX().getCurrentValue();
			int currentY = getY().getCurrentValue();
			int currentWidth = getWidth().getCurrentValue();
			int currentHeight = getHeight().getCurrentValue();
			
			g2d.setColor(Color.BLACK);
			g2d.drawRect(currentX, currentY, currentWidth, currentHeight);
			
			BufferedImage bi = new BufferedImage(currentWidth, currentHeight,
												 BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2dBi = bi.createGraphics();
			g2dBi.translate(-currentX-xScroll, -currentY-yScroll);
			vp.display(g2dBi);
			g2d.drawImage(bi, currentX, currentY, currentWidth, currentHeight, null);
			g2dBi.dispose();
			
			if (getXScrollDisplay() == X_SCROLL_ALWAYS) {
				int currentXsWidth = getXScrollWidth().getCurrentValue();
				int currentXsLength = getXScrollLength();
				int[] xsCoor = getXScrollCoordinates();
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.fillRect(currentX, currentY+currentHeight-currentXsWidth,
							 currentWidth, currentXsWidth);
				g2d.setColor(Color.DARK_GRAY);
				g2d.fillRect(xsCoor[0], xsCoor[1], currentXsLength, currentXsWidth);
			}
			if (getYScrollDisplay() == Y_SCROLL_ALWAYS) {
				int currentYsWidth = getYScrollWidth().getCurrentValue();
				int currentYsLength = getYScrollLength();
				int[] ysCoor = getYScrollCoordinates();
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.fillRect(currentX+currentWidth-currentYsWidth, currentY,
							 currentYsWidth, currentHeight);
				g2d.setColor(Color.DARK_GRAY);
				g2d.fillRect(ysCoor[0], ysCoor[1], currentYsWidth, currentYsLength);
			}
		}
	}
}
