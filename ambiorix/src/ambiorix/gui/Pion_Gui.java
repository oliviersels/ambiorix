package ambiorix.gui;

import java.awt.Graphics;

import javax.swing.JComponent;

import ambiorix.spelbord.Pion;

public class Pion_Gui extends JComponent{
	public void update(Graphics g){paint(g);}
	private Pion mijnPion;
	
	public Pion_Gui(Pion pion) {
		mijnPion = pion;
	}

}
