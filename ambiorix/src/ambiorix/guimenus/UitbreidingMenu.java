package ambiorix.guimenus;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ambiorix.uitbreidingen.Uitbreiding;
import ambiorix.uitbreidingen.UitbreidingVerzameling;

public class UitbreidingMenu extends JFrame implements UitbreidingLuisteraar, ActionListener{
	private JPanel knoppenPanel;
	private JButton knop_vorige;
	private JButton knop_volgende;
	private JSplitPane splitter;
	private JScrollPane scroller;
	private JPanel uitbreidingLijstPanel;
	private Vector<Uitbreiding_Gui> uitbreidingPanels;
	private JScrollPane beschrijvingScroller;
	private JTextArea uitbreidingBeschrijving;
	private Vector <MenuLuisteraar> menuLuisteraars = new Vector<MenuLuisteraar>();

	public UitbreidingMenu() {
		super("Ambiorix");
		knoppenPanel = new JPanel();
		knop_vorige = new JButton("Terug");
		knop_volgende = new JButton("Volgende");
		splitter = new JSplitPane();
		scroller = new JScrollPane();
		uitbreidingLijstPanel = new JPanel();
		uitbreidingPanels = new Vector<Uitbreiding_Gui>();
		beschrijvingScroller = new JScrollPane();
		uitbreidingBeschrijving = new JTextArea();
		uitbreidingBeschrijving.setLineWrap(true);
		uitbreidingBeschrijving.setWrapStyleWord(true);
		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//======== knoppenPanel ========


		knoppenPanel.setLayout(new GridLayout());

		//---- knop_vorige ----
		knop_vorige.setActionCommand("vorige");
		knop_vorige.addActionListener(this);
		knoppenPanel.add(knop_vorige);

		//---- knop_volgende ----
		knop_volgende.setActionCommand("volgende");
		knop_volgende.addActionListener(this);
		knoppenPanel.add(knop_volgende);
		
		contentPane.add(knoppenPanel, BorderLayout.SOUTH);

		//======== splitter ========

		splitter.setOrientation(JSplitPane.VERTICAL_SPLIT);


		//======== uitbreidingLijstPanel ========

		uitbreidingLijstPanel.setLayout(new GridLayout());
		UitbreidingVerzameling uv = UitbreidingVerzameling.getInstantie();
		for( String naam : UitbreidingVerzameling.getInstantie().getTypes() )
		{
			Uitbreiding uitbreiding = UitbreidingVerzameling.getInstantie().getType(naam);
			Uitbreiding_Gui ug = new Uitbreiding_Gui(uitbreiding);
			ug.VoegUitbreidingLuisteraarToe(this);
			uitbreidingLijstPanel.add(ug);
			uitbreidingPanels.add(ug);
		}
		

		scroller.setViewportView(uitbreidingLijstPanel);

		splitter.setTopComponent(scroller);

		//======== beschrijvingScroller ========
		{
			beschrijvingScroller.setViewportView(uitbreidingBeschrijving);
			uitbreidingBeschrijving.setEditable(false);
		}
		splitter.setBottomComponent(beschrijvingScroller);

		contentPane.add(splitter, BorderLayout.CENTER);
		this.setSize(800, 600);
		setLocationRelativeTo(getOwner());
	}

	public Vector<String> geefGeselecteerdeUitbreidingen()
	{
		Vector<String> rStrings = new Vector<String>();
		for(Uitbreiding_Gui ug:this.uitbreidingPanels)
		{
			if(ug.isGeselecteerd())
				rStrings.add(ug.getUitbreidingID());
		}
		return rStrings;
	}
	
	public static void main(String args[])
	{
		UitbreidingMenu um = new UitbreidingMenu();
		um.setVisible(true);
	}

	
	@Override
	public void muisBinnenKomst(Uitbreiding_Gui ug) {
		this.uitbreidingBeschrijving.setText(ug.getUitbreiding().getBeschrijving());
	}
	/**
	 * Gaat al de MenuLuisteraars waarschuwen dat er op een knop is geklikt.
	 */
	@Override
	public void actionPerformed(ActionEvent AE) {
		if(AE.equals("vorige"))
		{
			for(MenuLuisteraar ml : menuLuisteraars)
				ml.vorige();
		}else if(AE.equals("volgende"))
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
}
