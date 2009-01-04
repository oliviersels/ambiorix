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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import ambiorix.Systeem;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.PionBasis;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelBasis;
import ambiorix.spelers.Speler;
import ambiorix.util.Punt;
/**
 * Het venster waarin het eigenlijke spel gespeeld wordt.
 * @author Jens
 *
 */
public class HoofdVenster extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	
	private Invoer invoer;
	private Uitvoer uitvoer;
	
	private JMenuBar menuBalk;
	private JMenu menuBestand;
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
	
	public Invoer getInvoer() {
		return invoer;
	}
	
	public Uitvoer getUitvoer() {
		return uitvoer;
	}
	
	protected TegelVeld geefTegelVeld() {
		return tegelVeld;
	}
	
	public HoofdVenster()
	{	
		super("Ambiorix");
		invoer = new Invoer(this);
		uitvoer = new Uitvoer(this);
		spelers = new Vector<Speler_Gui>();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		splitVoorOnderkant = new JSplitPane();
		splitOnderkant = new JSplitPane();
		
		splitOnderkantLinks = new JSplitPane();
		splitOnderkantLinksNogEenKeer = new JSplitPane();
		chatVeldScroll = new JScrollPane();
		chatVeld = new ChatVeld();
		chatInvoer = new JTextField();
		pionnenVeldScroll = new JScrollPane();
		pionnenVeld = new PionnenVeld();
		tegelVeldScroll = new JScrollPane();
		tegelVeld = new TegelVeld();
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
		stopSpel = new JMenuItem("Stop");
		stopSpel.addActionListener(this);
		menuBestand.add(stopSpel);
		menuBalk.add(menuBestand);
		
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
		container.add(tegelVeldScroll, BorderLayout.CENTER);
		setJMenuBar(menuBalk);
		this.pack();
		this.setLocationRelativeTo(this.getOwner());

	}
	/**
	 * Voegt een String toe aan het chatvenster.
	 * @param str De String om toe te voegen.
	 */
	public synchronized void voegRegelToe(String str)
	{
		chatVeld.voegRegelToe(str);
	}
	@Deprecated
	public void voegTegelToe(int x, int y, Tegel tegel)
	{
		tegelVeld.voegTegelToe(x, y, tegel);
	}
	/**
	 * Voegt een tegel toe aan het TegelVeld.
	 * @param tegel De tegel om toe te voegen.
	 * @param bp De positie waar de tegel moet komen.
	 */
	public synchronized void voegTegelToe(TegelBasis tegel, BordPositie bp)
	{
		tegelVeld.voegTegelToe(tegel, bp);
	}
	public synchronized void verwijderTegel(TegelBasis tegel)
	{
		tegelVeld.verwijderTegel(tegel);
	}
	/**
	 * Voegt een tegel toe aan het veld waar de speler tegels uit kan kiezen.
	 * @param tegel De tegel om toe te voegen.
	 */
	public synchronized void voegSelectieTegelToe(TegelBasis tegel)
	{
		this.tegelSelecteer.voegTegelToe(tegel);
	}
	/**
	 * Verwijdert een tegel van het TegelSelectieVeld.
	 * @param tegel
	 */
	public synchronized void verwijderSelectieTegel(TegelBasis tegel)
	{
		this.tegelSelecteer.verwijderTegel(tegel);
	}
	/**
	 * Teken een bepaald gebied rood op het tegelveld.
	 * @param gebied
	 */
	public void tekenTerrein(Gebied gebied)
	{
		tegelVeld.tekenTerrein(gebied);
	}
	/**
	 * Voegt een pion toe aan het PionnenVeld waaruit de speler pionnen kan kiezen.
	 * @param pion De pion om toe te voegen.
	 */
	public synchronized void voegPionToe(PionBasis pion)
	{
		this.pionnenVeld.voegPionToe(pion);
	}
	public synchronized void verwijderPion(PionBasis pion)
	{
		pionnenVeld.verwijderPion(pion);
	}
	/**
	 * Plaatst een pion op een tegel.
	 * @param tegel De Tegel waar de pion op moet geplaatst worden.
	 * @param pion De pion om te plaatsen.
	 * @param pos De positie op de Tegel waar de pion op moet geplaatst worden.
	 */
	public synchronized void voegPionToe(Tegel tegel, Pion pion, Punt pos)
	{
		tegelVeld.voegPionToe(tegel, new Pion_Gui(pion), pos);
	}
	/**
	 * Voegt een PionLuisteraar toe die gewaarschuwd wordt wanneer er op een pion geklikt wordt.
	 * @param pl De PionLuisteraar om toe te voegen.
	 */
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
	/**
	 * Maakt het PionnenVeld en het TegelSelectieVeld leeg.
	 * Wordt gebruikt bij het wisselen tussen spelers.
	 */
	public void wisPionnenEnSelectieTegels()
	{
		this.pionnenVeld.ledig();
		this.tegelSelecteer.ledig();
	}
	/**
	 * Deze functie wordt gebruikt om de eerste tegel van het TegelSelectieVeld al te selecteren, zodat de speler niet telkens op een tegel moet klikken.
	 */
	public void startTegelSelectie() {
		tegelSelecteer.startTegelSelectie();
	}
	/**
	 * Vangt de events op wanneer er op undo of volgendeSpeler geduwd wordt.
	 */
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
			//Systeem.getInstantie().stopSpel();
		}
	}
	/**
	 * Wordt gebruikt om aan de gui aan te geven welke tegel de gebruiker wilt plaatsen, zodat het TegelVeld deze al kan weergeven bij het bewegen over het TegelVeld.
	 * @param tb De Tegel die de gebruiker wil plaatsen.
	 */
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
	/**
	 * Gaat de scores hernieuwen.
	 */
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
	/**
	 * Wordt gebruikt om te kijken bij een aanpassen van de pionnen en tegels van een speler of dit de actieve speler is.
	 * Indien dit de actieve speler is, wordt de gui aangepast.
	 * @param s
	 */
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
}
