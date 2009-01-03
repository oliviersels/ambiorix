package ambiorix.gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
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
import ambiorix.spelers.Speler;
import ambiorix.util.Punt;

public class HoofdVenster extends JFrame implements ActionListener, WindowListener {
	private static HoofdVenster instantie = null; // Even singleton van gemaakt, moet opgelost kunnen worden
	
	private Invoer invoer;
	private Uitvoer uitvoer;
	
	private JMenuBar menuBalk;
	private JMenu menuBestand, menuHelp;
	private JMenuItem stopSpel;
	private JSplitPane splitOnderkant;
	private JSplitPane splitOnderkantLinks;
	private JSplitPane splitVoorOnderkant;
	private JScrollPane chatVeldScroll;
	private ChatVeld chatVeld;
	private JTextField chatInvoer;
	private JScrollPane pionnenVeldScroll;
	private PionnenVeld pionnenVeld;
	private JScrollPane tegelVeldScroll;
	private TegelVeld tegelVeld;
	private ScoreVeld scoreVeld;
	private JSplitPane splitOnderkantLinksNogEenKeer;
	private JButton knop_undo;
	private JButton knop_volgendeSpeler;
	private JPanel knoppenPanel;
	private TegelSelectieVeld tegelSelecteer;

	private JScrollPane tegelSelecteerScroll;
	
	private Speler actieveSpeler = null;
	private Vector <Speler_Gui> spelers; 
	private Vector <HoofdVensterLuisteraar> hoofdVensterLuisteraars = new Vector <HoofdVensterLuisteraar>();
	@Deprecated
	public static HoofdVenster geefInstantie() {
		if(instantie == null) {
			instantie = new HoofdVenster();
		}
		return instantie;
	}
	
	public Invoer getInvoer() {
		return invoer;
	}
	
	public Uitvoer getUitvoer() {
		return uitvoer;
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
		invoer = new Invoer(this);
		uitvoer = new Uitvoer(this);
		spelers = new Vector<Speler_Gui>();
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(this);
		splitVoorOnderkant = new JSplitPane();
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
		knoppenPanel = new JPanel(new GridLayout(0,1));
		knop_undo = new JButton("<-");
		knop_undo.setActionCommand("undo");
		knop_undo.addActionListener(this);
		knop_undo.setEnabled(false);
		knop_volgendeSpeler = new JButton("->");
		knop_volgendeSpeler.setActionCommand("volgendeSpeler");
		knop_volgendeSpeler.addActionListener(this);
		knop_volgendeSpeler.setEnabled(false);
		//knoppenPanel
		knoppenPanel.add(knop_undo);
		knoppenPanel.add(knop_volgendeSpeler);
		//splitvoorOnderkant
		splitVoorOnderkant.setLeftComponent(splitOnderkant);
		splitVoorOnderkant.setRightComponent(knoppenPanel);
		// menubalk
		menuBalk = new JMenuBar();
		menuBestand = new JMenu("Bestand");
		menuHelp = new JMenu("Help");
		stopSpel = new JMenuItem("Stop");
		stopSpel.addActionListener(this);
		menuBestand.add(stopSpel);
		menuBalk.add(menuBestand);
		menuBalk.add(menuHelp);
		
		//scoreVeld;
		scoreVeld = new ScoreVeld();
		
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
		container.add(scoreVeld, BorderLayout.EAST);
		container.add(splitVoorOnderkant, BorderLayout.SOUTH);
		//container.add(splitOnderkant, BorderLayout.SOUTH);
		container.add(tegelVeldScroll, BorderLayout.CENTER);
		setJMenuBar(menuBalk);
		this.pack();
		this.setLocationRelativeTo(this.getOwner());

		//setVisible(true);
		
		/*for(int i = 0; i <20; i++)
			for(int y = 0; y <20 ;y++)
				voegTegelToe(i, y, null);*/
	}
	public synchronized void voegRegelToe(String str)
	{
		chatVeld.voegRegelToe(str);
	}
	@Deprecated
	public void voegTegelToe(int x, int y, Tegel tegel)
	{
		tegelVeld.voegTegelToe(x, y, tegel);
	}
	public synchronized void voegTegelToe(TegelBasis tegel, BordPositie bp)
	{
		tegelVeld.voegTegelToe(tegel, bp);
	}
	public synchronized void verwijderTegel(TegelBasis tegel)
	{
		tegelVeld.verwijderTegel(tegel);
	}
	public synchronized void voegSelectieTegelToe(TegelBasis tegel)
	{
		this.tegelSelecteer.voegTegelToe(tegel);
	}
	public synchronized void verwijderSelectieTegel(TegelBasis tegel)
	{
		this.tegelSelecteer.verwijderTegel(tegel);
	}
	public void tekenTerrein(Gebied gebied)
	{
		tegelVeld.tekenTerrein(gebied);
	}
	public synchronized void voegPionToe(PionBasis pion)
	{
		this.pionnenVeld.voegPionToe(pion);
	}
	public synchronized void verwijderPion(PionBasis pion)
	{
		pionnenVeld.verwijderPion(pion);
	}
	public synchronized void voegPionToe(Tegel tegel, Pion pion, Punt pos)
	{
		tegelVeld.voegPionToe(tegel, new Pion_Gui(pion), pos);
	}
	public synchronized void voegPionLuisteraarToe(PionLuisteraar pl)
	{
		this.pionnenVeld.addPionLuisteraar(pl);
	}
	public synchronized void verwijderPionLuisteraar(PionLuisteraar pl)
	{
		this.pionnenVeld.removePionLuisteraar(pl);
	}
	public synchronized void voegSelectieTegelLuisteraarToe(TegelLuisteraar tkl)
	{
		this.tegelSelecteer.addTegelKlikLuisteraar(tkl);
	}
	public synchronized void verwijderSelectieTegelLuisteraar(TegelLuisteraar tkl)
	{
		this.tegelSelecteer.removeTegelKlikLuisteraar(tkl);
	}
	public void wisPionnenEnSelectieTegels()
	{
		this.pionnenVeld.ledig();
		this.tegelSelecteer.ledig();
	}
	
	public void startTegelSelectie() {
		tegelSelecteer.startTegelSelectie();
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("undo"))
		{
			for(HoofdVensterLuisteraar hvl : this.hoofdVensterLuisteraars)
			{
				hvl.undo();
			}
		}else if(e.getActionCommand().equals("volgendeSpeler"))
		{
			for(HoofdVensterLuisteraar hvl : this.hoofdVensterLuisteraars)
			{
				hvl.volgendeSpeler();
			}
		}
		else if(e.getSource().equals(stopSpel)) {
			Systeem.getInstantie().stopSpel();
		}
	}
	
	public void zetTePlaatsenTegel(TegelBasis tb)
	{
		this.tegelVeld.zetTePlaatsenTegel(tb);
	}
	public void verwijderPionVanSpelbord(PionBasis pion)
	{
		this.tegelVeld.verwijderPion(pion);
	}
	public void voegSpelerToe(Speler s)
	{
		Speler_Gui nieuweSpeler = new Speler_Gui(s);
		spelers.add(nieuweSpeler);
		scoreVeld.voegSpelerToe(s);
	}
	public void voegPionToeAanSpeler(PionBasis pion, Speler s)
	{
		for(Speler_Gui sg : spelers)
		{
			if(sg.geefSpeler() == s)
			{
				sg.voegPionToe(pion);
			}
		}
		kijkVoorSpelerHernieuwing(s);
	}
	public void voegTegelToeAanSpeler(TegelBasis tegel, Speler s)
	{
		for(Speler_Gui sg : spelers)
		{
			if(sg.geefSpeler() == s)
			{
				sg.voegTegelToe(tegel);
			}
		}
		kijkVoorSpelerHernieuwing(s);
	}
	public void verwijderPionVanSpeler(PionBasis pion, Speler s)
	{
		for(Speler_Gui sg : spelers)
		{
			if(sg.geefSpeler() == s)
			{
				sg.verwijderPion(pion);
			}
		}
		kijkVoorSpelerHernieuwing(s);
	}
	public void verwijderTegelVanSpeler(TegelBasis tegel, Speler s)
	{
		for(Speler_Gui sg : spelers)
		{
			if(sg.geefSpeler() == s)
			{
				sg.verwijderTegel(tegel);
			}
		}
		kijkVoorSpelerHernieuwing(s);
	}
	public void zetActieveSpeler(Speler s)
	{
		Speler_Gui gevondenSpeler = null;
		for(Speler_Gui sg : spelers)
		{
			if(sg.geefSpeler() == s)
			{
				gevondenSpeler = sg;
			}
		}
		if(gevondenSpeler != null)
		{
			wisPionnenEnSelectieTegels();
			int aantalPionnen = gevondenSpeler.geefAantalPionnen();
			for(int i = 0; i < aantalPionnen; i++)
			{
				this.voegPionToe(gevondenSpeler.geefPion(i).getPion());
			}
			int aantalTegels = gevondenSpeler.geefAantalTegels();
			for(int i = 0; i < aantalTegels; i++)
			{
				this.voegSelectieTegelToe(gevondenSpeler.geefTegel(i).getTegel());
			}
		}
		scoreVeld.zetActieveSpeler(s);
		actieveSpeler = s;
	}
	public void updateScores()
	{
		scoreVeld.updateScores();
	}
	public synchronized void voegHoofdVensterLuisteraarToe(HoofdVensterLuisteraar hvl)
	{
		this.hoofdVensterLuisteraars.add(hvl);
	}
	public synchronized void verwijderHoofdVensterLuisteraar(HoofdVensterLuisteraar hvl)
	{
		this.hoofdVensterLuisteraars.remove(hvl);
	}
	private void kijkVoorSpelerHernieuwing(Speler s)
	{
		if(actieveSpeler == s)
		{
			zetActieveSpeler(s);
		}
	}
	
	public void enableUndo(boolean enable) {
		knop_undo.setEnabled(enable);
	}
	
	public void enableSkip(boolean enable) {
		knop_volgendeSpeler.setEnabled(enable);
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) {
		Systeem.getInstantie().stopSpel();
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {}
	@Override
	public void windowDeiconified(WindowEvent arg0) {}
	@Override
	public void windowIconified(WindowEvent arg0) {}
	@Override
	public void windowOpened(WindowEvent arg0) {}
}
