package ambiorix.gui;

import java.awt.Component;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;

import ambiorix.spelbord.TegelBasis;
import ambiorix.util.Punt;

public class TegelSelectieVeld extends JPanel implements TegelKlikLuisteraar{
	private Vector<Tegel_Gui> mijnTegels;
	private Vector<TegelKlikLuisteraar> tegelKlikLuisteraars;
	
	public TegelSelectieVeld() {
		mijnTegels = new Vector<Tegel_Gui>();
		this.setLayout(new TegelSelectieVeldLayout());
		tegelKlikLuisteraars = new Vector<TegelKlikLuisteraar>();
	}
	
	@Override
	public synchronized void geklikt(TegelGebeurtenis tg) {
		Iterator<TegelKlikLuisteraar> it = tegelKlikLuisteraars.iterator();
		while(it.hasNext()) {
			it.next().geklikt(tg);
		}
	}
	public synchronized void addTegelKlikLuisteraar(TegelKlikLuisteraar tkl)
	{
		tegelKlikLuisteraars.add(tkl);
	}
	
	public synchronized void removeTegelKlikLuisteraar(TegelKlikLuisteraar tkl)
	{
		
		tegelKlikLuisteraars.remove(tkl);
	}
	public void voegTegelToe(TegelBasis tegel)
	{
		Tegel_Gui nieuweTegel = new Tegel_Gui(tegel);
		nieuweTegel.zetPos(0, 0);
		mijnTegels.add(nieuweTegel);
		nieuweTegel.setVisible(true);
		nieuweTegel.addTegelKlikLuisteraar(this);
		this.add(nieuweTegel);
		this.repaint();
		this.revalidate();
	}
	public void verwijderTegel(TegelBasis tegel)
	{
		Iterator<Tegel_Gui> it = mijnTegels.iterator();
		while(it.hasNext()) {
			Tegel_Gui tg= it.next();
			if(tg.getTegel() == tegel)
				mijnTegels.remove(tg);
		}
		Component[] comps = this.getComponents();
		int lengte = comps.length;
		for(int i = 0; i < lengte; i++)
		{
			Tegel_Gui tg = (Tegel_Gui) comps[i];
			if(tg.getTegel() == tegel)
				this.remove(tg);
		}
	}
	public void ledig() {
		mijnTegels.clear();
		this.removeAll();
		this.repaint();
		this.revalidate();
	}
}
