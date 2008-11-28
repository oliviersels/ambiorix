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
import ambiorix.spelbord.TerreinType;

public class TegelVeld extends JPanel implements TegelKlikLuisteraar{
	private Vector<Tegel_Gui> mijnTegels;
	private Vector<TegelGeest> mijnTegelGeesten;
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
		mijnTegelGeesten = new Vector<TegelGeest>();
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
		voegTegelGeestToe(x, y+1, nieuweTegel);
		voegTegelGeestToe(x, y-1, nieuweTegel);
		voegTegelGeestToe(x+1, y, nieuweTegel);
		voegTegelGeestToe(x-1, y, nieuweTegel);
		verwijderTegelGeest(x, y);
		this.repaint();
	}

	@Override
	public synchronized void geklikt(TegelGebeurtenis tg) {
		Iterator<TegelKlikLuisteraar> it = tegelKlikLuisteraars.iterator();
		while(it.hasNext()) {
			it.next().geklikt(tg);
		}
		
		hv.voegRegelToe("Geklikt op tegel: " + tg.tegelX + ", " + tg.tegelY + " op pixel " + tg.tegelPixelX + ", " + tg.tegelPixelY);
		Tegel geklikteTegel = tg.tegel;
		if(geklikteTegel != null)
		{
			//TegelType tegelType = geklikteTegel.getType();
			TerreinType tt[][] = geklikteTegel.getTerrein();
			int lengte = tt.length;
			hv.voegRegelToe(tt[lengte*tg.tegelPixelX/100][lengte*tg.tegelPixelY/100].toString());
		}
	}
	
	private void voegTegelGeestToe(int x, int y, Tegel_Gui tg)
	{
		boolean gevonden = false;
		Iterator<TegelGeest> it = mijnTegelGeesten.iterator();
		while(it.hasNext())
		{
			TegelGeest huidig = it.next();
			if(huidig.getXPos() == x && huidig.getYPos() == y)
			{
				gevonden = true;
			}
		}
		Iterator<Tegel_Gui> it2 = mijnTegels.iterator();
		while(it2.hasNext())
		{
			
			Tegel_Gui huidig = it2.next();
			if(huidig.getXPos() == x && huidig.getYPos() == y)
			{
				gevonden = true;
			}
		}
		if(!gevonden)
		{
			TegelGeest nieuweTegelGeest = new TegelGeest(x, y, tg);
			mijnTegelGeesten.add(nieuweTegelGeest);
			this.add(nieuweTegelGeest);
		}
	}
	private void verwijderTegelGeest(int x, int y)
	{
		Iterator<TegelGeest> it = mijnTegelGeesten.iterator();
		while(it.hasNext())
		{
			TegelGeest huidig = it.next();
			System.out.println("mss verwijder: " + huidig.getXPos() + ", " + huidig.getYPos());
			if(huidig.getXPos() == x && huidig.getYPos() == y)
			{
				System.out.println("verwijder");
				this.remove(huidig);
				it.remove();
			}
		}
	}
}
