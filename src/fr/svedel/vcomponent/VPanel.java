package fr.svedel.vcomponent;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class VPanel extends VComponent {
	
	private ArrayList<VComponent> vcList = new ArrayList<>();
//	private Vector<VComponent> vcList = new Vector<>();
	
//	private JPanel jp;
	
	private KeyListener kl = new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			for (VComponent vc : vcList) {
				vc.keyTyped(e);
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			for (VComponent vc : vcList) {
				vc.keyReleased(e);
			}
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			for (VComponent vc : vcList) {
				vc.keyPressed(e);
			}
		}
	};
	private MouseListener ml = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			for (VComponent vc : vcList) {
				vc.mouseReleased(e);
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			for (VComponent vc : vcList) {
				vc.mousePressed(e);
			}
//			if (hasFocus() && jp != null) {
//				jp.grabFocus();
//			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			for (VComponent vc : vcList) {
				vc.mouseExited(e);
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			/*for (VComponent vc : vcList) {
				vc.mouseEntered(e);
				}*/
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {}
	};
	private MouseMotionListener mml = new MouseMotionListener() {
		
		@Override
		public void mouseMoved(MouseEvent e) {
			for (VComponent vc : vcList) {
				vc.mouseMoved(e);
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			for (VComponent vc : vcList) {
				vc.mouseDragged(e);
			}
		}
	};
	private MouseWheelListener mwl = new MouseWheelListener() {
		
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			for (int i = 0; i < vcList.size(); ++i) {
				vcList.get(i).mouseWheelMoved(e);
			}
		}
	};
	
	public VPanel(int x, int y, int w, int h, int widthReference, int heightReference) {
		super(x, y, w, h, widthReference, heightReference);
		initListeners();
	}
	
	public VPanel(int x, int y, int w, int h) {
		this(x, y, w, h, 0, 0);
	}
	
	public VPanel(int w, int h) {
		this(0, 0, w, h);
	}
	
	public VPanel() {
		this(0, 0, 0, 0);
	}
	
	private void initListeners() {
		addKeyListener(kl);
		addMouseListener(ml);
		addMouseMotionListener(mml);
		addMouseWheelListener(mwl);
	}
	
//	public void addListenersToAJPanel(JPanel jp) {
//		jp.addKeyListener(this);
//		jp.addMouseListener(this);
//		jp.addMouseMotionListener(this);
//		this.jp = jp;
//	}
	
	public void addMlToAComponent(Component c) {
		c.addMouseListener(this);
	}
	
	public void removeMlToAComponent(Component c) {
		c.removeMouseListener(this);
	}
	
	public void addMmlToAComponent(Component c) {
		c.addMouseMotionListener(this);
	}
	
	public void removeMmlToAComponent(Component c) {
		c.removeMouseMotionListener(this);
	}
	
	public void addKlToAComponent(Component c) {
		c.addKeyListener(this);
	}
	
	public void removeKlToAComponent(Component c) {
		c.removeKeyListener(this);
	}
	
	public void addMwlToAComponent(Component c) {
		c.addMouseWheelListener(this);
	}
	
	public void removeMwlToAComponent(Component c) {
		c.removeMouseWheelListener(this);
	}
	
	public void add(VComponent vc) {
		vcList.add(vc);
		
		//vc.setWidthReference(getWidth());
		vc.getWidthReference().setValue(getWidth().getValue());
		//vc.setHeightReference(getHeight());
		vc.getHeightReference().setValue(getHeight().getValue());
		vc.setAdjustment(ADJUSTMENT_BY_WIDTH_AND_HEIGHT);
		vc.setAlignment(NO_ALIGNMENT);
	}
	
	public void remove(int index) {
		vcList.remove(index);
	}
	
	public void remove(VComponent vc) {
		for (int i = vcList.size()-1; i >= 0; i--) {
			if (vcList.get(i) == vc) {
				vcList.remove(i);
			}
		}
	}
	
	@Override
	public void adjust(int widthRefrence, int heightRefrence) {
		super.adjust(widthRefrence, heightRefrence);
		for (VComponent vc : vcList) {
			vc.adjust(getWidth().getCurrentValue(), getHeight().getCurrentValue());
			vc.getX().setCurrentValue(vc.getX().getCurrentValue()
									  +getX().getCurrentValue());
			vc.getY().setCurrentValue(vc.getY().getCurrentValue()
									  +getY().getCurrentValue());
		}
	}
	
	@Override
	public void display(Graphics2D g2d) {
		for (VComponent vc : vcList) {
			vc.display(g2d);
		}
	}
}
