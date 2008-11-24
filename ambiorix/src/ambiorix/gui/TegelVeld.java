package ambiorix.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class TegelVeld extends JPanel {
	private Vector<Tegel_Gui> mijnTegels;

	public TegelVeld() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		mijnTegels = new Vector<Tegel_Gui>();
	}
	
	public void voegTegelToe(int x, int y)
	{
		Tegel_Gui nieuweTegel = new Tegel_Gui();
		nieuweTegel.zetPos(x, y);
		mijnTegels.add(nieuweTegel);
		this.repaint();
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawString("testing", 10, 20);
		
		ListIterator<Tegel_Gui> iter = mijnTegels.listIterator();
		while (iter.hasNext()){
		    (iter.next()).teken(g);
		    //System.out.println("volgende");
		}

	}
	

}
