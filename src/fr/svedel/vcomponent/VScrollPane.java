package fr.svedel.vcomponent;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class VScrollPane extends VComponent {
	
	private VPanel vp;
	
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
			if (vp != null) vp.mouseReleased(e);
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (vp != null) vp.mousePressed(e);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {}
		
		@Override
		public void mouseEntered(MouseEvent e) {}
		
		@Override
		public void mouseClicked(MouseEvent e) {}
	};
	private MouseMotionListener mml = new MouseMotionListener() {
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if (vp != null) vp.mouseMoved(e);
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (vp != null) vp.mouseDragged(e);
		}
	};
	
	public VScrollPane(int x, int y, int w, int h, int widthReference, int heightReference, VPanel vp) {
		super(x, y, w, h, widthReference, heightReference);
		setVPanel(vp);
		initListeners();
	}
	
	public VScrollPane(int x, int y, int w, int h, VPanel vp) {
		this(x, y, w, h, 0, 0, vp);
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
			vp.setWidthReference(getWidth());
			vp.setHeightReference(getHeight());
			vp.setAdjustment(ADJUSTMENT_BY_WIDTH_AND_HEIGHT);
			vp.setAlignment(NO_ALIGNMENT);
		}
	}
	
	@Override
	public void adjust(int widthRefrence, int heightRefrence) {
		super.adjust(widthRefrence, heightRefrence);
		vp.setActualX(vp.getActualX()+getActualX());
		vp.setActualY(vp.getActualY()+getActualY());
		vp.adjust(getActualWidth(), getActualHeight());
	}
	
	@Override
	public void display(Graphics2D g2d) {
		if (vp != null) {
			BufferedImage bi = new BufferedImage(getActualWidth(), getActualHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2dBi = bi.createGraphics();
			
			vp.display(g2dBi);
			g2d.drawImage(bi, getActualX(), getActualY(), getActualWidth(), getActualHeight(), null);
			g2dBi.dispose();
		}
	}

}
