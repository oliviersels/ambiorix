package ambiorix.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ambiorix.spelbord.Tegel;

public class TegelVeld extends JPanel implements TegelKlikLuisteraar{
	private Vector<Tegel_Gui> mijnTegels;
	private Vector<TegelKlikLuisteraar> tegelKlikLuisteraars;
	private HoofdVenster hv; //tijdelijk!!
	
	public synchronized void addTegelKlikLuisteraar(TegelKlikLuisteraar tkl)
	{
		tegelKlikLuisteraars.add(tkl);
	}
	
	public synchronized void removeTegelKlikLuisteraar(TegelKlikLuisteraar tkl)
	{
		
		tegelKlikLuisteraars.remove(tkl);
	}
	
	public TegelVeld(HoofdVenster hv) {
		this.hv = hv;
		setBorder(BorderFactory.createLineBorder(Color.black));
		mijnTegels = new Vector<Tegel_Gui>();
		this.setLayout(new TegelVeldLayout());
		tegelKlikLuisteraars = new Vector<TegelKlikLuisteraar>();
	}
	
	public void voegTegelToe(int x, int y, Tegel tegel)
	{
		Tegel_Gui nieuweTegel = new Tegel_Gui(tegel);
		nieuweTegel.zetPos(x, y);
		mijnTegels.add(nieuweTegel);
		nieuweTegel.setVisible(true);
		nieuweTegel.addTegelKlikLuisteraar(this);
		this.add(nieuweTegel);
		this.repaint();
	}

	@Override
	public synchronized void geklikt(TegelGebeurtenis tg) {
		Iterator<TegelKlikLuisteraar> it = tegelKlikLuisteraars.iterator();
		while(it.hasNext()) {
			it.next().geklikt(tg);
		}
		
		hv.voegRegelToe("Geklikt op tegel: " + tg.tegelX + ", " + tg.tegelY + " op pixel " + tg.tegelPixelX + ", " + tg.tegelPixelY);
	}
}
