import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
public class Frame extends JFrame{
	public Frame(){
		super("Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
				
		JMenuBar menuBar=new JMenuBar();
		JMenuItem menuItem=new JMenuItem("100ms");
		JMenuItem menuItem2=new JMenuItem("500ms");
		JMenuItem menuItem3=new JMenuItem("1s");
		JMenuItem menuItem4=new JMenuItem("No");
		JMenu ballposition=new JMenu("Click Generator Speed");
				
		ballposition.add(menuItem4);
		ballposition.add(menuItem);
		ballposition.add(menuItem2);
		ballposition.add(menuItem3);
		ballposition.setForeground(Color.white);
		ballposition.setBackground(Color.BLACK);
		
		menuBar.add(ballposition);
		menuBar.setBackground(Color.black);
		
		setJMenuBar(menuBar);
				
		JRadioButton circle=new JRadioButton("Circles");
		circle.setSelected(true);
		JRadioButton square=new JRadioButton("Squares");
		ButtonGroup group=new ButtonGroup();
		group.add(circle);
		group.add(square);
		circle.setForeground(Color.white);
		circle.setBackground(Color.BLACK);
		square.setForeground(Color.white);
		square.setBackground(Color.black);
		
		JCheckBox red=new JCheckBox("Red");
		red.setForeground(Color.white);
		red.setBackground(Color.BLACK);
		JCheckBox blue=new JCheckBox("Blue");
		blue.setForeground(Color.white);
		blue.setBackground(Color.BLACK);
		JCheckBox green=new JCheckBox("Green");
		green.setForeground(Color.white);
		green.setBackground(Color.BLACK);
		
		
		JComboBox ballcolor=new JComboBox();
		ballcolor.addItem("Random");
		ballcolor.addItem("Black,White");
		ballcolor.setEditable(false);
		ballcolor.setForeground(Color.WHITE);
		ballcolor.setBackground(Color.BLACK);
		
		JPanel radio=new JPanel();
		radio.add(circle);
		radio.add(square);
		radio.setBorder(new TitledBorder(new EtchedBorder(),"Shape"));
		radio.setOpaque(false);
		
		JPanel checkbox=new JPanel();
		checkbox.add(red);
		checkbox.add(blue);
		checkbox.add(green);
		checkbox.setBorder(new TitledBorder(new EtchedBorder(),"Background Color"));
		checkbox.setOpaque(false);
		
		JPanel combo=new JPanel();
		combo.add(ballcolor);
		combo.setBorder(new TitledBorder(new EtchedBorder(),"Ball Color"));
		combo.setOpaque(false);
		
		JPanel main=new JPanel();
		main.setLayout(new GridLayout(1,3));
		
		DrawComponent component=new DrawComponent();
		component.setPreferredSize(new Dimension(500,500));
		
		class MyListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				int sh;
				int bc1;
				int bc2;
				int bc3;
				int cb;
				if(circle.isSelected()) sh=1;
				else sh=2;
				if(red.isSelected()) bc1=1; else bc1=0;
				if(blue.isSelected()) bc2=1; else bc2=0;
				if(green.isSelected()) bc3=1; else bc3=0;
				cb=ballcolor.getSelectedIndex();
				component.gather(sh,bc1,bc2,bc3,cb);
			}
		}
		MyListener listener=new MyListener();
		Timer timer=new Timer(20,listener);
		timer.start();
		
		class Mouse implements MouseListener{
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent e) {
				component.mouse(e.getX(),e.getY());
			}
			public void mouseReleased(MouseEvent arg0) {}
		}
		Mouse mouse=new Mouse();
		component.addMouseListener(mouse);
		
		class MenuListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==menuItem)	component.setSpeed(5);
				else if(e.getSource()==menuItem2) component.setSpeed(25);
				else if(e.getSource()==menuItem3) component.setSpeed(50);
				else if(e.getSource()==menuItem4) component.setSpeed(1);
			}
		}
		MenuListener l2=new MenuListener();
		menuItem.addActionListener(l2);
		menuItem2.addActionListener(l2);
		menuItem3.addActionListener(l2);
		menuItem4.addActionListener(l2);
		
		
		setLayout(new BorderLayout());
		add(component,BorderLayout.CENTER);
		add(main,BorderLayout.SOUTH);
		main.add(radio);
		main.add(checkbox);
		main.add(combo);
		main.setBackground(Color.black);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		Frame frame=new Frame();

	}

}
