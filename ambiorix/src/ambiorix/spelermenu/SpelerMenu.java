package ambiorix.spelermenu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SpelerMenu extends JFrame{
	private static final int MAX_AANTAL_SPELERS = 5;
	private static final String[] KLEUREN = {"Rood", "Groen", "Blauw", "Geel", "Zwart", "Roos", "Paars", "Oranje"};
	private static final String[] SPELER_TYPES = {"Hot Seat", "AI", "Netwerk"};
	private JPanel ondersteKnoppenPanel;
	private JButton knop_terug;
	private JButton knop_volgende;
	private JPanel middenPanel;
	private Vector <JPanel>  spelerPanels = new Vector <JPanel>();
	private Vector <JTextField>  spelerNamen = new Vector <JTextField>();
	private Vector <JComboBox>  spelerTypes = new Vector <JComboBox>();
	private Vector <JComboBox>  spelerKleuren = new Vector <JComboBox>();
	
	public SpelerMenu() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Jens Bruggemans
		ondersteKnoppenPanel = new JPanel();
		knop_terug = new JButton();
		knop_volgende = new JButton();
		middenPanel = new JPanel();
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(10, 10));
		
		middenPanel.setLayout(new GridLayout(0, 1, 10, 10));
		for(int i = 0; i < MAX_AANTAL_SPELERS; i++)
		{
			JPanel nieuwPanel = new JPanel();
			nieuwPanel.setLayout(new GridLayout());
			JTextField naamVeld = new JTextField();
			JComboBox typeVeld = new JComboBox(KLEUREN);
			JComboBox kleurVeld = new JComboBox(SPELER_TYPES);
			
			spelerNamen.add(naamVeld);
			spelerTypes.add(typeVeld);
			spelerKleuren.add(kleurVeld);
			nieuwPanel.add(naamVeld);
			nieuwPanel.add(typeVeld);
			nieuwPanel.add(kleurVeld);
			spelerPanels.add(nieuwPanel);
			middenPanel.add(nieuwPanel);
		}
		contentPane.add(middenPanel, BorderLayout.CENTER);
		
		ondersteKnoppenPanel.setLayout(new GridLayout());
		knop_terug.setText("Terug");
		ondersteKnoppenPanel.add(knop_terug);
		knop_volgende.setText("Volgende");
		ondersteKnoppenPanel.add(knop_volgende);
		contentPane.add(ondersteKnoppenPanel, BorderLayout.SOUTH);

		contentPane.add(ondersteKnoppenPanel, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(getOwner());
	}

	public static void main(String args[])
	{
		SpelerMenu spelerMenu = new SpelerMenu();
		spelerMenu.setVisible(true);
	}
}
