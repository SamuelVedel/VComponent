package tst.vcomponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.svedel.vcomponent.VActionListener;
import fr.svedel.vcomponent.VButton;
import fr.svedel.vcomponent.VComponent;
import fr.svedel.vcomponent.VLabel;
import fr.svedel.vcomponent.VNumberWriter;
import fr.svedel.vcomponent.VPanel;
import fr.svedel.vcomponent.VScrollPane;
import fr.svedel.vcomponent.VSwitch;

public class Test {
	private JFrame jf = new JFrame();
	
	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	
	private VPanel vp = new VPanel(0, 0, WIDTH, HEIGHT, WIDTH, HEIGHT);
	private final int VB_WIDTH = 100;
	private final int VB_HEIGHT = 80;
	private VButton vb = new VButton(WIDTH/2-VB_WIDTH/2, HEIGHT/2-VB_HEIGHT/2, VB_WIDTH, VB_HEIGHT);
	private VSwitch vs = new VSwitch(50, 50, /*110,*/ 50);
	
	private VLabel vl = new VLabel(50, 100, "Hey salut battard");
	
	private VNumberWriter vnw = new VNumberWriter(50, 150, /*50,*/ 30);
	
//	private VScrollPane jsp = new VScrollPane(100, 100, 100, 100, null);
	
	private Painter paint = new Painter();
	
	private VActionListener vActL = new VActionListener() {
		
		@Override
		public void action(VComponent source, MouseEvent e) {
			if (source == vb) {
				vs.setUsable(!vs.isUsable());
			} else if (source == vs) {
				if (vs.getState()) {
					vnw.setFocusable(true);
				} else {
					vnw.setFocusable(false);
				}
			}
		}
	};
	
	public Test() {
		initJf();
		initVc();
		
		while (true) {
			jf.repaint();
			try {
				Thread.sleep(1000/30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void initJf() {
		jf.setTitle("test VComponent");
		jf.setSize(800, 600);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setContentPane(paint);
		jf.setVisible(true);
	}
	
	private void initVc() {
		vp.add(vb);
		vp.add(vs);
		vp.add(vl);
		//vp.setAdjustment(VComponent.ADJUSTMENT_BY_WIDTH);
		//vp.setAdjustment(VComponent.ADJUSTMENT_BY_HEIGHT);
		vp.setAdjustment(VComponent.ADJUSTMENT_BY_THE_SMALLEST);
		//vp.setAdjustment(VComponent.ADJUSTMENT_BY_WIDTH_AND_HEIGHT);
		vp.setAlignment(VComponent.NO_ALIGNMENT);
		//vp.setAlignment(VComponent.CENTER_ALIGNMENT);
		//vp.setAlignment(VComponent.BOTTOM_ALIGNMENT);
		vp.addMlToAComponent(paint);
		vp.addMmlToAComponent(paint);
		vp.addKlToAComponent(jf);
		vb.setText("hey");
		vb.addVActionListener(vActL);
		vs.addVActionListener(vActL);
		vl.getFontSize().setValue(50);
		vl.setColor(Color.BLACK);
//		vp.setFocus(true);
//		paint.grabFocus();
		
		vp.add(vnw);
		vnw.setMaximumNumber(9999);
		vnw.setFocusable(false);
//		vs.setUsable(false);
		
		vb.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode()+" "+e.getKeyChar());
			}
		});
		
		initVsp();
	}
	
	private void initVsp() {
		VPanel vp2 = new VPanel(0, 0, 100, 600, WIDTH, HEIGHT);
		VSwitch[] vss = new VSwitch[10];
		final int vsGap = 5;
		final int vsH = 50;
		for (int i = 0; i < vss.length; i++) {
			vss[i] = new VSwitch(vsGap, vsGap+i*(vsGap+vsH), vsH);
			vp2.add(vss[i]);
		}
		vss[0].addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("hey");
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
//		jsp.setVPanel(vp2);
//		vp.add(jsp);
	}
	
	private class Painter extends JPanel {
		private static final long serialVersionUID = 812081786007386400L;
		
//		public Painter() {
//			JButton jb = new JButton("lksjdclskf");
//			jb.setEnabled(false);
////			setLayout(new BorderLayout());
//			add(jb/*, BorderLayout.NORTH*/);
//		}
		
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			vp.adjust(getWidth(), getHeight());
			vp.display(g2d);
		}
	}
}
