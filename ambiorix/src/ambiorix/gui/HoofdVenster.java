package ambiorix.gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import ambiorix.Systeem;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.PionBasis;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelBasis;
import ambiorix.util.Punt;

public class HoofdVenster extends JFrame implements ActionListener {
	private static HoofdVenster instantie = null; // Even singleton van gemaakt, moet opgelost kunnen worden
	
	private JMenuBar menuBalk;
	private JMenu menuBestand, menuHelp;
	private JMenuItem startSpel, stopSpel;
	private JSplitPane splitOnderkant;
	private JSplitPane splitOnderkantLinks;
	private JScrollPane chatVeldScroll;
	private ChatVeld chatVeld;
	private JTextField chatInvoer;
	private JScrollPane pionnenVeldScroll;
	private PionnenVeld pionnenVeld;
	private JScrollPane tegelVeldScroll;
	private TegelVeld tegelVeld;

	private JSplitPane splitOnderkantLinksNogEenKeer;

	private TegelSelectieVeld tegelSelecteer;

	private JScrollPane tegelSelecteerScroll;
	
	/*
	 * Hier start code van Olivier
	 */
	public static HoofdVenster geefInstantie() {
		if(instantie == null) {
			instantie = new HoofdVenster();
		}
		return instantie;
	}
	
	protected TegelVeld geefTegelVeld() {
		return tegelVeld;
	}
	
	public static void main(String args[])
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HoofdVenster hv = new HoofdVenster();
		/*
		for(int i = 0; i <20; i++)
			for(int y = 0; y <20 ;y++)
				hv.voegTegelToe(i, y, null);
		*/
	}
	public HoofdVenster()
	{	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		splitOnderkant = new JSplitPane();
		splitOnderkantLinks = new JSplitPane();
		splitOnderkantLinksNogEenKeer = new JSplitPane();
		chatVeldScroll = new JScrollPane();
		chatVeld = new ChatVeld();
		chatInvoer = new JTextField();
		pionnenVeldScroll = new JScrollPane();
		pionnenVeld = new PionnenVeld(this);
		tegelVeldScroll = new JScrollPane();
		tegelVeld = new TegelVeld(this);
		tegelSelecteer  = new TegelSelectieVeld();
		tegelSelecteerScroll = new JScrollPane();
		// menubalk
		menuBalk = new JMenuBar();
		menuBestand = new JMenu("Bestand");
		menuHelp = new JMenu("Help");
		startSpel = new JMenuItem("Start");
		startSpel.addActionListener(this);
		stopSpel = new JMenuItem("Stop");
		stopSpel.addActionListener(this);
		menuBestand.add(startSpel);
		menuBestand.add(stopSpel);
		menuBalk.add(menuBestand);
		menuBalk.add(menuHelp);
		
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
		
		splitOnderkant.setRightComponent(splitOnderkantLinksNogEenKeer);
		splitOnderkantLinksNogEenKeer.setRightComponent(pionnenVeldScroll);
		splitOnderkantLinksNogEenKeer.setLeftComponent(tegelSelecteerScroll);
		//splitOnderkant.setRightComponent(pionnenVeldScroll);
		
		//tegelVeld
		tegelSelecteerScroll.setViewportView(tegelSelecteer);
		tegelVeldScroll.setViewportView(tegelVeld);
		tegelVeld.setVisible(true);
		pionnenVeld.setVisible(true);
		//HoofdVenster
		setMinimumSize(new Dimension(800, 600));
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(splitOnderkant, BorderLayout.SOUTH);
		container.add(tegelVeldScroll, BorderLayout.CENTER);
		setJMenuBar(menuBalk);
		this.pack();
		this.setLocationRelativeTo(this.getOwner());

		setVisible(true);
		
		/*for(int i = 0; i <20; i++)
			for(int y = 0; y <20 ;y++)
				voegTegelToe(i, y, null);*/
	}
	public void voegRegelToe(String str)
	{
		chatVeld.voegRegelToe(str);
	}
	@Deprecated
	public void voegTegelToe(int x, int y, Tegel tegel)
	{
		tegelVeld.voegTegelToe(x, y, tegel);
	}
	public void voegTegelToe(Tegel tegel, BordPositie bp)
	{
		tegelVeld.voegTegelToe(tegel, bp);
	}
	public void voegSelectieTegelToe(TegelBasis tegel)
	{
		this.tegelSelecteer.voegTegelToe(tegel);
	}
	public void verwijderSelectieTegel(TegelBasis tegel)
	{
		this.tegelSelecteer.verwijderTegel(tegel);
	}
	public void tekenTerrein(Gebied gebied)
	{
		tegelVeld.tekenTerrein(gebied);
	}
	public void voegPionToe(PionBasis pion)
	{
		this.pionnenVeld.voegPionToe(pion);
	}
	public void voegPionToe(Tegel tegel, Pion pion, Punt pos)
	{
		tegelVeld.voegPionToe(tegel, pion, pos);
	}
	public void voegPionLuisteraarToe(PionLuisteraar pl)
	{
		this.pionnenVeld.addPionLuisteraar(pl);
	}
	public void verwijderPionLuisteraar(PionLuisteraar pl)
	{
		this.pionnenVeld.removePionLuisteraar(pl);
	}
	public void voegSelectieTegelLuisteraarToe(TegelLuisteraar tkl)
	{
		this.tegelSelecteer.addTegelKlikLuisteraar(tkl);
	}
	public void verwijderSelectieTegelLuisteraar(TegelLuisteraar tkl)
	{
		this.tegelSelecteer.removeTegelKlikLuisteraar(tkl);
	}
	public void wisPionnenEnSelectieTegels()
	{
		this.pionnenVeld.ledig();
		this.tegelSelecteer.ledig();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(startSpel)) {
			Systeem.getInstantie().startSpel();
		}
		else if(e.getSource().equals(stopSpel)) {
			Systeem.getInstantie().stopSpel();
		}
	}
}
