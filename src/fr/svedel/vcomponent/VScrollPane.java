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
	
	private boolean xScrollPressed = false;
	private VAdjustInt xScroll = new VAdjustInt(0);
	private int xScrollWhenPressed;
	private boolean yScrollPressed = false;
	private VAdjustInt yScroll = new VAdjustInt(0);
	private int yScrollWhenPressed;
	private int[] mouseCoorWhenPressed = new int[2];
	
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
	
	private MouseListener scrollMl = new MouseListener() {
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (!xScrollPressed) {
				int currentXSWidth = getXScrollWidth().getCurrentValue();
				int currentXSBLength = getXScrollBarLength();
				int[] xSBCoor = getXScrollBarCoordinates();
				if (e.getX() > xSBCoor[0] && e.getX() < xSBCoor[0]+currentXSBLength
					&& e.getY() > xSBCoor[1] && e.getX() < xSBCoor[1]+currentXSWidth) {
					xScrollPressed = true;
					mouseCoorWhenPressed[0] = e.getX();
					mouseCoorWhenPressed[1] = e.getY();
					xScrollWhenPressed = xScroll.getCurrentValue();
				}
			}
			if (!yScrollPressed) {
				int currentYSWidth = getYScrollWidth().getCurrentValue();
				int currentYSBLength = getYScrollBarLength();
				int[] ySBCoor = getYScrollBarCoordinates();
				if (e.getX() > ySBCoor[0] && e.getX() < ySBCoor[0]+currentYSWidth
					&& e.getY() > ySBCoor[1] && e.getY() < ySBCoor[1]+currentYSBLength) {
					yScrollPressed = true;
					mouseCoorWhenPressed[0] = e.getX();
					mouseCoorWhenPressed[1] = e.getY();
					yScrollWhenPressed = yScroll.getCurrentValue();
				}
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if (xScrollPressed) xScrollPressed = false;
			if (yScrollPressed) yScrollPressed = false;
		}
		
		@Override
		public void mouseExited(MouseEvent e) {}
		
		@Override
		public void mouseEntered(MouseEvent e) {}
		
		@Override
		public void mouseClicked(MouseEvent e) {}
	};
	
	private MouseMotionListener scrollMml = new MouseMotionListener() {
		
		@Override
		public void mouseMoved(MouseEvent e) {}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (xScrollPressed) {
				int currentXSBLength = getXScrollBarLength();
				int currentXSRLength = getXScrollRailLength();
				int currentWidth = getWidth().getCurrentValue();
				int currentVpWidth = vp.getWidth().getCurrentValue();
				int deltaMouseX = e.getX()-mouseCoorWhenPressed[0];
				
				xScroll.setCurrentValue(xScrollWhenPressed+deltaMouseX
										*currentVpWidth/currentXSRLength);
				if (xScroll.getCurrentValue() < 0) {
					xScroll.setCurrentValue(0);
				} else if (xScroll.getCurrentValue() > currentVpWidth-currentWidth) {
					xScroll.setCurrentValue(currentVpWidth-currentWidth);
				}
				xScroll.reverseAdjust(getWidthReference());
			}
			if (yScrollPressed) {
				int currentYSBLength = getYScrollBarLength();
				int currentYSRLength = getYScrollRailLength();
				int currentHeight = getHeight().getCurrentValue();
				int currentVpHeight = vp.getHeight().getCurrentValue();
				int deltaMouseY = e.getY()-mouseCoorWhenPressed[1];
				
				yScroll.setCurrentValue(yScrollWhenPressed+deltaMouseY
										*currentVpHeight/currentYSRLength);
				if (yScroll.getCurrentValue() < 0) {
					yScroll.setCurrentValue(0);
				} else if (yScroll.getCurrentValue() > currentVpHeight-currentHeight) {
					yScroll.setCurrentValue(currentVpHeight-currentHeight);
				}
				yScroll.reverseAdjust(getWidthReference());
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
		addMouseListener(scrollMl);
		addMouseMotionListener(scrollMml);
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
	
	public int getXScrollBarLength() {
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
	
	public int[] getXScrollBarCoordinates() {
		if (getXScrollDisplay() != X_SCROLL_ALWAYS
			|| vp == null) {
			return new int[] {0, 0};
		}
		
		int currentX = getX().getCurrentValue();
		int currentY = getY().getCurrentValue();
		int currentWidth = getWidth().getCurrentValue();
		int currentVPWidth = vp.getWidth().getCurrentValue();
		
		int x = currentX+xScroll.getCurrentValue()*currentWidth/currentVPWidth;
		int y = currentY+getHeight().getCurrentValue()-getXScrollWidth().getCurrentValue();
		return new int[] {x, y};
	}
	
	public int getXScrollRailLength() {
		if (getXScrollDisplay() != X_SCROLL_ALWAYS
			|| vp == null) {
			return 0;
		}
		
		if (getYScrollDisplay() == Y_SCROLL_ALWAYS) {
			return getWidth().getCurrentValue()
				-yScrollWidth.getCurrentValue();
		} else {
			return getWidth().getCurrentValue();
		}
	}
	
	public int[] getXScrollRailCoordinates() {
		if (getXScrollDisplay() != X_SCROLL_ALWAYS
			|| vp == null) {
			return new int[] {0, 0};
		}
		
		return new int[] {
			getX().getCurrentValue(),
			getY().getCurrentValue()+getHeight().getCurrentValue()
			-xScrollWidth.getCurrentValue()
		};
	}
	
	public int getYScrollBarLength() {
		if (getYScrollDisplay() != Y_SCROLL_ALWAYS
			|| vp == null) {
			return 0;
		}
		
		int currentHeight = getHeight().getCurrentValue();
		int currentVPHeight = vp.getHeight().getCurrentValue();
		if (currentHeight >= currentVPHeight)
			return currentHeight;
		return currentHeight*currentHeight/currentVPHeight;
	}
	
	public int[] getYScrollBarCoordinates() {
		if (getYScrollDisplay() != Y_SCROLL_ALWAYS
			|| vp == null) {
			return new int[] {0, 0};
		}
		
		int currentX = getX().getCurrentValue();
		int currentY = getY().getCurrentValue();
		int currentHeight = getHeight().getCurrentValue();
		int currentVPHeight = vp.getHeight().getCurrentValue();
		
		int x = currentX+getWidth().getCurrentValue()-getYScrollWidth().getCurrentValue();
		int y = currentY+yScroll.getCurrentValue()*currentHeight/currentVPHeight;
		return new int[] {x, y};
	}
	
	public int getYScrollRailLength() {
		if (getYScrollDisplay() != Y_SCROLL_ALWAYS
			|| vp == null) {
			return 0;
		}
		
		if (getXScrollDisplay() == X_SCROLL_ALWAYS) {
			return getHeight().getCurrentValue()
				-xScrollWidth.getCurrentValue();
		} else {
			return getHeight().getCurrentValue();
		}
	}
	
	public int[] getYScrollRailCoordinates() {
		if (getYScrollDisplay() != Y_SCROLL_ALWAYS
			|| vp == null) {
			return new int[] {0, 0};
		}
		
		return new int[] {
			getX().getCurrentValue()+getWidth().getCurrentValue()
			-yScrollWidth.getCurrentValue(),
			getY().getCurrentValue()
		};
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
		
		// ajustement des dimension de scrolling
		if (getXScrollDisplay() == X_SCROLL_ALWAYS) {
			adjustValue(xScroll);
			adjustValue(xScrollWidth);
		} else {
			xScrollWidth.setCurrentValue(0);
		}
		if (getYScrollDisplay() == Y_SCROLL_ALWAYS) {
			adjustValue(yScroll);
			adjustValue(yScrollWidth);
		} else {
			yScrollWidth.setCurrentValue(0);
		}
		
		// positionnement et ajustement du VPanel
		vp.getX().setValue(getX().getValue()-xScroll.getValue());
		vp.getY().setValue(getY().getValue()-yScroll.getValue());
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
			g2dBi.translate(-currentX/*-xScroll.getCurrentValue()*/,
							-currentY/*-yScroll.getCurrentValue()*/);
			vp.display(g2dBi);
			g2d.drawImage(bi, currentX, currentY, currentWidth, currentHeight, null);
			g2dBi.dispose();
			
			if (getXScrollDisplay() == X_SCROLL_ALWAYS) {
				int currentXSWidth = getXScrollWidth().getCurrentValue();
				int currentXSBLength = getXScrollBarLength();
				int[] xSBCoor = getXScrollBarCoordinates();
				int currentXSRLength = getXScrollRailLength();
				int[] xSRCoor = getXScrollRailCoordinates();
				
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.fillRect(xSRCoor[0], xSRCoor[1],
							 currentXSRLength, currentXSWidth);
				g2d.setColor(Color.DARK_GRAY);
				g2d.fillRect(xSBCoor[0], xSBCoor[1], currentXSBLength, currentXSWidth);
			}
			if (getYScrollDisplay() == Y_SCROLL_ALWAYS) {
				int currentYSWidth = getYScrollWidth().getCurrentValue();
				int currentYSBLength = getYScrollBarLength();
				int[] ySBCoor = getYScrollBarCoordinates();
				int currentYSRLength = getYScrollRailLength();
				int[] ySRCoor = getYScrollRailCoordinates();
				
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.fillRect(ySRCoor[0], ySRCoor[1],
							 currentYSWidth, currentYSRLength);
				g2d.setColor(Color.DARK_GRAY);
				g2d.fillRect(ySBCoor[0], ySBCoor[1], currentYSWidth, currentYSBLength);
			}
			if (getXScrollDisplay() == X_SCROLL_ALWAYS
				&& getYScrollDisplay() == Y_SCROLL_ALWAYS) {
				int currentXSWidth = getXScrollWidth().getCurrentValue();
				int currentYSWidth = getYScrollWidth().getCurrentValue();
				g2d.setColor(Color.DARK_GRAY);
				g2d.fillRect(currentX+currentWidth-currentXSWidth,
							 currentY+currentHeight-currentYSWidth,
							 currentXSWidth, currentYSWidth);
			}
		}
	}
}
