package ambiorix.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import ambiorix.spelers.Speler;

public class ScoreVeld extends JPanel {
	private Vector<Speler> spelers = new Vector<Speler>();
	private Vector<JTextArea> spelerTextAreas = new Vector<JTextArea>();
	private static final Color inactieveKleur = new Color(220, 220, 220);

	public ScoreVeld() {
		super();
		this.setLayout(new GridLayout(0, 1));
	}

	public void voegSpelerToe(Speler speler) {
		spelers.add(speler);
		JTextArea nieuwTextArea = new JTextArea(speler.getNaam() + "\nScore: "
				+ speler.getScore() + "\nPionnen: " + speler.getAantalPionnen());
		nieuwTextArea.setForeground(speler.getKleur());
		nieuwTextArea.setBackground(inactieveKleur);
		nieuwTextArea.setOpaque(true);
		spelerTextAreas.add(nieuwTextArea);
		this.add(nieuwTextArea);
		this.revalidate();
		this.repaint();
	}

	public void zetActieveSpeler(Speler s) {
		for (JTextArea jl : spelerTextAreas) {
			jl.setBackground(inactieveKleur);
		}
		int index = spelers.indexOf(s);
		if (index != -1) {
			spelerTextAreas.get(index).setBackground(Color.WHITE);
		}
		this.revalidate();
		this.repaint();
	}

	public void updateScores() {
		int i = 0;
		for (JTextArea jl : spelerTextAreas) {
			Speler speler = spelers.get(i);
			jl.setText(speler.getNaam() + "\nScore: " + speler.getScore()
					+ "\nPionnen: " + speler.getAantalPionnen());
			i++;
		}
	}
}
