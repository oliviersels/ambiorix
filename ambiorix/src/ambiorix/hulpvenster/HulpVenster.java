package ambiorix.hulpvenster;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HulpVenster extends JFrame {
	private static final long serialVersionUID = -7916877981585493428L;
	private Vector<HulpItem> hulpItems = new Vector<HulpItem>();
	private JTextField hulpNaam = new JTextField();
	private JScrollPane beschrijvingScroller = new JScrollPane();
	private JTextArea hulpBeschrijving = new JTextArea();

	public HulpVenster() {
		super("Ambiorix - Hulp");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		hulpNaam.setEditable(false);
		this.add(hulpNaam, BorderLayout.NORTH);
		hulpBeschrijving.setEditable(false);
		hulpBeschrijving.setLineWrap(true);
		hulpBeschrijving.setWrapStyleWord(true);
		beschrijvingScroller.setViewportView(hulpBeschrijving);
		this.add(beschrijvingScroller, BorderLayout.CENTER);
		this.setSize(800, 600);
	}

	/**
	 * Voegt een HulpItem toe. Indien er al een HulpItem is met dezelfde naam,
	 * wordt dit vervangen.
	 * 
	 * @param naam
	 *            De naam voor het HulpItem.
	 * @param beschrijving
	 *            De beschrijving van het HulpItem.
	 */
	public void voegHulpItemToe(String naam, String beschrijving) {
		HulpItem hi = new HulpItem(naam, beschrijving);
		hulpItems.remove(hi);
		hulpItems.add(hi);
	}

	/**
	 * Laadt hulpItems in van uit een bestand. Een hulpItem waarvoor er al een
	 * entry bestaat wordt vervangen.
	 * 
	 * @param FileNaam
	 *            De naam van het bestand.
	 */
	public void laadVanBestand(String FileNaam) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(FileNaam));
			String lijn;
			while ((lijn = in.readLine()) != null) {
				HulpItem hi = new HulpItem(lijn);
				hulpItems.remove(hi);
				hulpItems.add(hi);
			}
			in.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sla de HulpItems op in een bestand.
	 * 
	 * @param FileNaam
	 *            De naam van het bestand.
	 */
	public void slaOpInBestand(String FileNaam) {
		try {
			BufferedWriter uit = new BufferedWriter(new FileWriter(FileNaam));
			for (HulpItem hi : this.hulpItems) {
				uit.write(hi.naarString() + "\n");
			}
			uit.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param naam
	 *            De naam van het te tonen HulpItem.
	 */
	public void toonHulp(String naam) {
		HulpItem teTonen = new HulpItem("NIET GEVONDEN", "");
		HulpItem teZoeken = new HulpItem(naam, "");
		int index;
		if ((index = hulpItems.indexOf(teZoeken)) != -1)
			teTonen = hulpItems.get(index);
		hulpNaam.setText(teTonen.getNaam());
		hulpBeschrijving.setText(teTonen.getBeschrijving());
	}

	public static void main(String args[]) {
		HulpVenster hv = new HulpVenster();
		hv.setVisible(true);
		hv.laadVanBestand("hulpFiles/hulptest1.txt");
		// hv.voegHulpItemToe("test1", "beschrijving van test 1");
		hv.voegHulpItemToe("test3", "beschrijving van test 3");
		hv.toonHulp("test2");
		// hv.slaOpInBestand("hulpFiles/hulptest1.txt");
	}
}
