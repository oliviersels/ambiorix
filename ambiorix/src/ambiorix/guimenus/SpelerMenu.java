package ambiorix.guimenus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SpelerMenu extends JFrame implements ActionListener{
	private static final int MAX_AANTAL_SPELERS = 5;
	private static final String[] KLEUREN = {"Rood", "Groen", "Blauw", "Geel", "Zwart", "Roze", "Oranje"};
	private static final Color[] COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.BLACK, Color.PINK, Color.ORANGE};
	private static final String[] SPELER_TYPES = {"Gesloten" ,"Hot Seat", "AI"};
	private JPanel ondersteKnoppenPanel;
	private JButton knop_vorige;
	private JButton knop_volgende;
	private JPanel middenPanel;
	private Vector <JPanel>  spelerPanels = new Vector <JPanel>();
	private Vector <JTextField>  spelerNamen = new Vector <JTextField>();
	private Vector <JComboBox>  spelerTypes = new Vector <JComboBox>();
	private Vector <JComboBox>  spelerKleuren = new Vector <JComboBox>();
	private Vector <MenuLuisteraar> menuLuisteraars = new Vector<MenuLuisteraar>();
	public SpelerMenu() {
		super("Ambiorix");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ondersteKnoppenPanel = new JPanel();
		knop_vorige = new JButton();
		knop_volgende = new JButton();
		middenPanel = new JPanel();
		

		this.setLayout(new BorderLayout(10, 10));
		
		middenPanel.setLayout(new GridLayout(0, 1, 10, 50));
		for(int i = 0; i < MAX_AANTAL_SPELERS; i++)
		{
			JPanel nieuwPanel = new JPanel();
			nieuwPanel.setLayout(new GridLayout());
			JTextField naamVeld = new JTextField();
			JComboBox typeVeld = new JComboBox(SPELER_TYPES);
			JComboBox kleurVeld = new JComboBox(KLEUREN);
			
			spelerNamen.add(naamVeld);
			spelerTypes.add(typeVeld);
			spelerKleuren.add(kleurVeld);
			nieuwPanel.add(naamVeld);
			nieuwPanel.add(typeVeld);
			nieuwPanel.add(kleurVeld);
			spelerPanels.add(nieuwPanel);
			middenPanel.add(nieuwPanel);
		}
		this.add(middenPanel, BorderLayout.CENTER);
		
		ondersteKnoppenPanel.setLayout(new GridLayout());
		knop_vorige.setText("Terug");
		knop_vorige.setActionCommand("vorige");
		knop_vorige.addActionListener(this);
		ondersteKnoppenPanel.add(knop_vorige);
		knop_volgende.setText("Volgende");
		knop_volgende.setActionCommand("volgende");
		knop_volgende.addActionListener(this);
		ondersteKnoppenPanel.add(knop_volgende);
		this.add(ondersteKnoppenPanel, BorderLayout.SOUTH);

		this.add(ondersteKnoppenPanel, BorderLayout.SOUTH);
		Dimension d = new Dimension(800,600);
		this.setMinimumSize(d);
		this.setSize(800,600);
		spelerNamen.get(0).setText("Maximus Decimus Meridius");
		spelerTypes.get(0).setSelectedIndex(1);
		spelerNamen.get(1).setText("Batman");
		spelerTypes.get(1).setSelectedIndex(2);
		spelerKleuren.get(1).setSelectedIndex(4);
	}

	public static void main(String args[])
	{
		SpelerMenu spelerMenu = new SpelerMenu();
		spelerMenu.setVisible(true);
	}
	
	/**
	 * Gaat al de MenuLuisteraars waarschuwen dat er op een knop is geklikt.
	 */
	@Override
	public void actionPerformed(ActionEvent AE) {
		if(AE.getActionCommand().equals("vorige"))
		{
			for(MenuLuisteraar ml : menuLuisteraars)
				ml.vorige();
		}else if(AE.getActionCommand().equals("volgende"))
		{
			for(MenuLuisteraar ml : menuLuisteraars)
				ml.volgende();
		}
	}
	/**
	 * Voegt een MenuLuisteraar toe.
	 * @param ML De MenuLuisteraar om toe te voegen.
	 */
	public void voegMenuLuisteraarToe(MenuLuisteraar ML)
	{
		this.menuLuisteraars.add(ML);
	}
	/**
	 * Verwijder een MenuLuisteraar.
	 * @param ML De MenuLuisteraar om te verwijderen.
	 */
	public void verwijderMenuLuisteraar(MenuLuisteraar ML)
	{
		this.menuLuisteraars.remove(ML);
	}
	public Vector<SpelerOpties> geefSpelers()
	{
		Vector<SpelerOpties> rSpelers = new Vector<SpelerOpties>();
		int aantal = spelerPanels.size();
		for(int i = 0; i < aantal; i++)
		{
			SpelerOpties so = new SpelerOpties();
			so.kleur = COLORS[this.spelerKleuren.get(i).getSelectedIndex()];
			so.naam = this.spelerNamen.get(i).getText();
			String typeString = SPELER_TYPES[this.spelerTypes.get(i).getSelectedIndex()];
			if(typeString.equals("Hot Seat"))
			{
				so.type = SpelerOpties.SpelerOptiesType.HOT_SEAT;
			}else if(typeString.equals("AI"))
			{
				so.type = SpelerOpties.SpelerOptiesType.AI;
			}else if(typeString.equals("Gesloten"))
			{
				so.type = SpelerOpties.SpelerOptiesType.GESLOTEN;
			}
			rSpelers.add(so);
		}
		return rSpelers;
	}
}
