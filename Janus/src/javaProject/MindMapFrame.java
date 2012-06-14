package pro;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class MindMapFrame extends JFrame {
	public LeftPanelA lpa;
	public LeftPanelB lpb;
	public RightPanel rp;
	public ToolBar tb;
	public MenuBar mb;
	public Container cp;
	public JPanel ap, tp;
	public Graphics gd;
	public JLabel rootNode;
	public 	Node currentSel;
	public JFrame myframe;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MindMapFrame mp = new MindMapFrame();
	}

	public MindMapFrame() { // init
		setTitle("마인드맵이당.....");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(890, 400);
		// container
		cp = getContentPane();
		cp.setBackground(Color.black);
		// attribute Panel
		ap = new JPanel(new GridLayout(2, 1));
		ap.setPreferredSize(new Dimension(250, 0));
		lpa = new LeftPanelA();
		lpb = new LeftPanelB();
		ap.add(lpa);
		ap.add(lpb);
		cp.add(ap, BorderLayout.WEST);
		// right Panel
		rp = new RightPanel();
		rp.setPreferredSize(new Dimension(635, 0));

		cp.add(rp, BorderLayout.EAST);
		// top Panel for menu and tool bar
		JPanel tP = new JPanel(new GridLayout(2, 1));
		tP.setPreferredSize(new Dimension(250, 40));
		tb = new ToolBar();
		mb = new MenuBar();
		tP.add(mb);
		tP.add(tb);
		cp.add(tP, BorderLayout.NORTH);

		setVisible(true);
	}

	public void uPanel() {
		rp.updateUI();
		ap.updateUI();
		tp.updateUI();

	}

	// //////////////RightPanel
	class RightPanel extends JPanel {

		public RightPanel() {

			setLayout(null);
			setPreferredSize(new Dimension(625, 300));
			setBorder(BorderFactory.createLineBorder(Color.blue, 2));
			setVisible(true);

		}

	}

	// //////////////LeftPanelA
	class LeftPanelA extends JPanel {

		public JLabel l1;
		public JLabel l2;
		public JLabel l3;
		public JLabel l4;
		public JLabel l5;
		public JTextField t1;
		public JTextField t2;
		public JTextField t3;
		public JTextField t4;
		public JTextField t5;

		String[] tmp = new String[5];

		public LeftPanelA() {

			// setters and getters

			// TODO Auto-generated constructor stub
			super(new GridLayout(5, 2));
			setBackground(Color.lightGray);
			setBorder(BorderFactory.createTitledBorder(""));
			setSize(200, 400);
			l1 = new JLabel();
			l2 = new JLabel();
			l3 = new JLabel();
			l4 = new JLabel();
			l5 = new JLabel();
			t1 = new JTextField();
			t2 = new JTextField();
			t3 = new JTextField();
			t4 = new JTextField();
			t5 = new JTextField();

			l1.setText(" x 좌표");
			t1.setText("-1");
			add(l1);
			add(t1);

			l2.setText(" y 좌표");
			t2.setText("-1");
			add(l2);
			add(t2);

			l3.setText(" 너비");
			t3.setText("-1");
			add(l3);
			add(t3);

			l4.setText(" 높이");
			t4.setText("-1");
			add(l4);
			add(t4);

			l5.setText(" 텍스트");
			t5.setText("-1");
			add(l5);
			add(t5);

			setSize(180, 10);
			setVisible(true);

		}

		public void cordUpdate(Node node) {
			tmp[0] = new Integer(node.getX()).toString();
			tmp[1] = new Integer(node.getY()).toString();
			tmp[2] = new Integer(node.getW()).toString();
			tmp[3] = new Integer(node.getH()).toString();
			tmp[4] = new String(node.getText());

			t1.setText(tmp[0]);
			t2.setText(tmp[1]);
			t3.setText(tmp[2]);
			t4.setText(tmp[3]);
			t5.setText(tmp[4]);
		}
	}

	// //////////////LeftPanelB
	class LeftPanelB extends JPanel {
		public LeftPanelB() {
			super(new BorderLayout());
			setBackground(Color.lightGray);
			setBorder(BorderFactory.createTitledBorder(""));
			JButton btn = new JButton("적용");
			ApplyButtonListener listener = new ApplyButtonListener();
			btn.addActionListener(listener);
			add(btn, BorderLayout.CENTER);

			setVisible(true);
		}

	}

	// ////////////////TOOLBAR
	class ToolBar extends JToolBar {
		JButton newButton;
		JButton oldButton;
		JButton connectButton;

		public ToolBar() {
			// TODO Auto-generated constructor stub
			newButton = new JButton(); // 노드 추가 하는 버튼
			newButton.setPreferredSize(new Dimension(100, 100));
			newButton.setText("NEW NODE");
			newButton.setBackground(Color.red);
			NewNodeAction nna = new NewNodeAction();
			newButton.addActionListener(nna);

			oldButton = new JButton(); // 노드 추가 하는 버튼
			oldButton.setPreferredSize(new Dimension(100, 100));
			oldButton.setText("NEW NODE2");
			oldButton.setBackground(Color.green);
			NewNodeAction nna1 = new NewNodeAction();
			oldButton.addActionListener(nna1);

			connectButton = new JButton(); // 노드 연결 하는 버튼 
			connectButton.setPreferredSize(new Dimension(100, 100));
			connectButton.setText("CONNECT NODE");
			connectButton.setBackground(Color.blue);
			connectButton.addActionListener(new NewNodeAction());

			add(newButton);
			add(oldButton);
			add(connectButton);
		}
	}

	// ////////////////MenuBar
	public class MenuBar extends JMenuBar {
		JMenu fileMenu;
		JMenu EditMenu;
		JMenu HelpMenu;
		JMenuItem newAction;
		JMenuItem saveAction;
		JMenuItem saveAsAction;
		JMenuItem openAction;
		JMenuItem exitAction;
		JMenuItem cutAction;
		JMenuItem copyAction;
		JMenuItem pasteAction;
		JMenuItem aboutAction;

		public MenuBar() {
			JMenu FileMenu = new JMenu("File");
			JMenu EditMenu = new JMenu("Edit");
			JMenu HelpMenu = new JMenu("Help");

			JMenuItem newAction = new JMenuItem("New");
			JMenuItem saveAction = new JMenuItem("Save");
			JMenuItem saveAsAction = new JMenuItem("Save As");
			JMenuItem openAction = new JMenuItem("Open");
			JMenuItem exitAction = new JMenuItem("Exit");

			JMenuItem cutAction = new JMenuItem("Cut");
			JMenuItem copyAction = new JMenuItem("Copy");
			JMenuItem pasteAction = new JMenuItem("Paste");
			JMenuItem aboutAction = new JMenuItem("About");

			FileMenu.add(newAction);
			FileMenu.add(saveAction);
			FileMenu.add(saveAsAction);
			FileMenu.add(openAction);
			FileMenu.addSeparator();
			FileMenu.add(exitAction);
			EditMenu.add(copyAction);
			EditMenu.add(cutAction);
			EditMenu.add(pasteAction);
			HelpMenu.add(aboutAction);
			add(FileMenu);
			add(EditMenu);
			add(HelpMenu);
			newAction.addActionListener(new ActionListener() { // 새로 파일 만들기 엑션
						public void actionPerformed(ActionEvent arg0) {
							MindMapFrame mf = new MindMapFrame();

						}
					});
			saveAction.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
				}
			});
			openAction.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("HelloMyWorld");
				}
			});
			exitAction.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("BYE");
					System.exit(0);
				}
			});
			copyAction.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("HelloMyWorld");
				}
			});
			cutAction.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("HelloMyWorld");
				}
			});
			pasteAction.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("HelloMyWorld");
				}
			});
			aboutAction.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Created by Spencer Jang");
				}
			});

		}

	}

	static int cnt = 0;
	ArrayList<Node> nodeList = new ArrayList<Node>();

	// ////////////////Node
	public class Node extends JLabel {
		Graphics g = null;
		private int x = 0;
		private int y = 0;
		private int w = 0;
		private int h = 5;
		private String text = "GOOD";

		public Node() { // init Node
			cnt++;
			nodeList.add(this);
		}

		public JLabel createNode() { // Make new node JLABEL 이네
			if (cnt == 1) {
				setFont(new Font("serif", Font.BOLD, 20));
				setOpaque( true );
				setText("Root");
				setLocation(300, 150);
				setX(300);
				setY(150);
			} else {
				RandomGenerator rg = new RandomGenerator();
				setFont(new Font("serif", Font.BOLD, 20));
				setText("node" + cnt);
				setOpaque( true );
				int x = rg.xrand();
				int y = rg.yrand();
				setLocation(x, y);
				setX(x);
				setY(y);
			}
			setSize(getPreferredSize());
			setW(getWidth());
			setH(getHeight());
			return this;
		}

		public void deleteNode() {

		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getW() {
			return w;
		}

		public void setW(int w) {
			this.w = w;
		}

		public int getH() {
			return h;
		}

		public void setH(int h) {
			this.h = h;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

	}

	// ////////////////RandomGenerator
	public class RandomGenerator {
		int maxx = 600;
		int minx = 20;
		int maxy = 280;
		int miny = 20;
		Random rn = new Random();
		int rangex = maxx - minx + 1;
		int rangey = maxy - miny + 1;
		int ranx;
		int rany;

		public RandomGenerator() {
			ranx = rn.nextInt(rangex) + minx;
			rany = rn.nextInt(rangey) + miny;
			// TODO Auto-generated constructor stub
		}

		public int xrand() {
			return ranx;
		}

		public int yrand() {
			return rany;
		}
	}
	int tmpx,tmpy;
	// ////////////////NewNodeAction
	public class NewNodeAction implements ActionListener{ // 새로운 노드 추가 버튼 눌렀을 때

		public void actionPerformed(ActionEvent e) {

			// TODO Auto-generated method stub
			JButton j = (JButton) e.getSource();
			if ((j.getText().equals("NEW NODE")||j.getText().equals("NEW NODE2"))) {
				JLabel node = new Node().createNode(); // 레이블
				
				node.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						currentSel = (Node) e.getSource();
						currentSel.setBackground(Color.green);
						tmpx = currentSel.getX();
						tmpy = currentSel.getY();
						lpa.cordUpdate(currentSel);

					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
					}

					@Override
					public void mousePressed(MouseEvent arg0) {
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						currentSel.setBackground(null);
					}

				});
				node.addMouseMotionListener(new MouseMotionListener(){

					@Override
					public void mouseDragged(MouseEvent e) {
						currentSel.setX(e.getX()+tmpx);
						currentSel.setY(e.getY()+tmpy);
						lpa.cordUpdate(currentSel);
						//rp.updateUI();
						
					}
					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				rp.add(node);
				rp.updateUI();
				
				
			} 
		}

		
	}
	




	public class ApplyButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			currentSel.setX(Integer.parseInt(lpa.t1.getText()));
			currentSel.setY(Integer.parseInt(lpa.t2.getText()));
			currentSel.setW(Integer.parseInt(lpa.t3.getText()));
			currentSel.setH(Integer.parseInt(lpa.t4.getText()));

			currentSel.setLocation(currentSel.getX(), currentSel.getY());
			currentSel.setSize(currentSel.getW(), currentSel.getWidth());
			currentSel.setText(lpa.t5.getText());
			rp.updateUI();

		}
	}
}
