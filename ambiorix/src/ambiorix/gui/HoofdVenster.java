package ambiorix.gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class HoofdVenster extends JFrame{
	private JToolBar menuBalk;
	private JButton knopBestand;
	private JButton knopHelp;
	private JSplitPane splitOnderkant;
	private JSplitPane splitOnderkantLinks;
	private JScrollPane chatVeldScroll;
	private JTextArea chatVeld;
	private JTextField chatInvoer;
	private JScrollPane pionnenVeldScroll;
	private PionnenVeld pionnenVeld;
	private JScrollPane tegelVeldScroll;
	private TegelVeld tegelVeld;
	
	
	
	public static void main(String args[])
	{
		new HoofdVenster();
	}
	HoofdVenster()
	{
		
		menuBalk = new JToolBar();
		knopBestand = new JButton();
		knopHelp = new JButton();
		splitOnderkant = new JSplitPane();
		splitOnderkantLinks = new JSplitPane();
		chatVeldScroll = new JScrollPane();
		chatVeld = new JTextArea();
		chatInvoer = new JTextField();
		pionnenVeldScroll = new JScrollPane();
		pionnenVeld = new PionnenVeld();
		tegelVeldScroll = new JScrollPane();
		tegelVeld = new TegelVeld();
		
		//knoppen
		knopBestand.setText("Bestand");
		menuBalk.add(knopBestand);
		knopHelp.setText("help");
		menuBalk.add(knopHelp);
		
		//splitOnderkant
		splitOnderkant.setMinimumSize(new Dimension(100, 160));
		splitOnderkant.setPreferredSize(new Dimension(800, 160));
		
		//splitOnderkantLinks
		splitOnderkantLinks.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitOnderkant.setLeftComponent(splitOnderkantLinks);
		
		//chatVeldScroll
		chatVeldScroll.setMinimumSize(new Dimension(120, 126));
		chatVeldScroll.setPreferredSize(new Dimension(200, 126));
		splitOnderkantLinks.setTopComponent(chatVeldScroll);
		
		//chatVeld
		//chatVeld.setMinimumSize(new Dimension(120, 20));
		//chatVeld.setPreferredSize(new Dimension(200, 20));
		chatVeld.setEditable(false);
		chatVeldScroll.setViewportView(chatVeld);
		
		//chatInvoer
		chatInvoer.setMaximumSize(new Dimension(3000, 20));
		chatInvoer.setPreferredSize(new Dimension(200, 20));
		chatInvoer.setMinimumSize(new Dimension(200, 20));
		splitOnderkantLinks.setBottomComponent(chatInvoer);
		
		//pionnenVeldScroll
		pionnenVeldScroll.setViewportView(pionnenVeld);
		splitOnderkant.setRightComponent(pionnenVeldScroll);
		
		//tegelVeld
		tegelVeldScroll.setViewportView(tegelVeld);
		
		//HoofdVenster
		setMinimumSize(new Dimension(800, 600));
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(menuBalk, BorderLayout.NORTH);
		container.add(splitOnderkant, BorderLayout.SOUTH);
		container.add(tegelVeldScroll, BorderLayout.CENTER);
		this.pack();
		this.setLocationRelativeTo(this.getOwner());
		setVisible(true);
	}
	
}
