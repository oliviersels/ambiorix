package ambiorix.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ambiorix.spelers.Speler;

public class ScoreVeld extends JPanel {
	private Vector<Speler> spelers = new Vector<Speler>();
	private Vector<JLabel> spelerLabels = new Vector<JLabel>();
	public ScoreVeld()
	{
		super();
		this.setLayout(new GridLayout(0,1));
	}
	public void voegSpelerToe(Speler speler)
	{
		spelers.add(speler);
		JLabel nieuwLabel = new JLabel(speler.getNaam() + ": " + speler.getScore());
		nieuwLabel.setForeground(speler.getKleur());
		nieuwLabel.setBackground(new Color(200,200,200));
		nieuwLabel.setOpaque(true);
		spelerLabels.add(nieuwLabel);
		this.add(nieuwLabel);
		this.revalidate();
		this.repaint();
	}
	public void zetActieveSpeler(Speler s)
	{
		for(JLabel jl : spelerLabels)
		{
			jl.setBackground(new Color(200,200,200));
		}
		int index = spelers.indexOf(s);
		if(index != -1)
		{
			spelerLabels.get(index).setBackground(Color.WHITE);
			System.out.println("wit gemaakt");
		}
		this.revalidate();
		this.repaint();
	}
	public void updateScores()
	{
		int i =0;
		for(JLabel jl : spelerLabels)
		{
			jl.setText(spelers.get(i).getNaam() + ": " + spelers.get(i).getScore());
			i++;
		}
	}
}
