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

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelBasis;
import ambiorix.spelbord.Terrein;
import ambiorix.spelbord.TerreinType;
import ambiorix.util.Punt;

public class TegelVeld extends JPanel implements TegelKlikLuisteraar, TegelGeestLuisteraar{
	private Vector<Tegel_Gui> mijnTegels;
	private Vector<TegelGeest> mijnTegelGeesten;
	private Vector<TegelKlikLuisteraar> tegelKlikLuisteraars;
	private Vector<TegelGeestLuisteraar> tegelGeestLuisteraars;
	private HoofdVenster hv; // TODO tijdelijk!!
	
	public synchronized void addTegelKlikLuisteraar(TegelKlikLuisteraar tkl)
	{
		tegelKlikLuisteraars.add(tkl);
	}
	
	public synchronized void removeTegelKlikLuisteraar(TegelKlikLuisteraar tkl)
	{
		
		tegelKlikLuisteraars.remove(tkl);
	}
	
	public synchronized void addTegelGeestLuisteraar(TegelGeestLuisteraar tgl)
	{
		tegelGeestLuisteraars.add(tgl);
	}
	
	public synchronized void removeTegelGeestLuisteraar(TegelGeestLuisteraar tgl)
	{
		
		tegelGeestLuisteraars.remove(tgl);
	}
	
	public TegelVeld(HoofdVenster hv) {
		this.hv = hv;
		mijnTegels = new Vector<Tegel_Gui>();
		mijnTegelGeesten = new Vector<TegelGeest>();
		tegelGeestLuisteraars = new Vector<TegelGeestLuisteraar>();
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
		voegTegelGeestToe(x, y+1, nieuweTegel, Tegel.RICHTING.ONDER);
		voegTegelGeestToe(x, y-1, nieuweTegel, Tegel.RICHTING.BOVEN);
		voegTegelGeestToe(x+1, y, nieuweTegel, Tegel.RICHTING.LINKS);
		voegTegelGeestToe(x-1, y, nieuweTegel, Tegel.RICHTING.RECHTS);
		verwijderTegelGeest(x, y);
		this.repaint();
		this.revalidate();
	}

	@Override
	public synchronized void geklikt(TegelGebeurtenis tg) {
		
		
		
		hv.voegRegelToe("Geklikt op tegel: " + tg.tegelX + ", " + tg.tegelY + " op pixel " + tg.tegelPixelX + ", " + tg.tegelPixelY);
		TegelBasis geklikteTegel = tg.tegel;
		Iterator<TegelKlikLuisteraar> it = tegelKlikLuisteraars.iterator();
		while(it.hasNext()) {
			it.next().geklikt(tg);
		}
	}
	@Override
	public synchronized void geklikt(TegelGeestGebeurtenis gtg) {
		Iterator<TegelGeestLuisteraar> it = tegelGeestLuisteraars.iterator();
		while(it.hasNext()) {
			it.next().geklikt(gtg);
		}
	}
	private void voegTegelGeestToe(int x, int y, Tegel_Gui tg, Tegel.RICHTING richting)
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
			TegelGeest nieuweTegelGeest = new TegelGeest(x, y, tg, richting);
			nieuweTegelGeest.addTegelGeestLuisteraar(this);
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
			if(huidig.getXPos() == x && huidig.getYPos() == y)
			{
				this.remove(huidig);
				it.remove();
			}
		}
	}
	
	public void tekenTerrein(Gebied gebied)
	{
		for(Tegel_Gui tg: mijnTegels)
		{
			tg.wisGebiedenTeTekenen();
		}
		Vector <Terrein> terreinstukken = gebied.getTerreinStukken();
		for( Terrein terrein: terreinstukken)
		{
			for(Tegel_Gui tg: mijnTegels)
			{
				if(tg.getTegel().getID() == terrein.getTegel().getID())
				{
					tg.addGebiedTeTekenen(terrein.getPositie());
				}
			}
		}
		this.repaint();
		this.revalidate();
	}

	public void voegTegelToe(Tegel tegel, BordPositie bp) {
		Tegel buur = bp.getBuur();
		int grootte = mijnTegels.size();
		if(buur != null)
		{
			for(int i = 0; i < grootte; i++)
			{
				Tegel_Gui tg = mijnTegels.get(i);
				if(tg.getTegel().getID() == buur.getID())
				{
					Punt p = new Punt(tg.getPos());
					if(bp.getRichting() == Tegel.RICHTING.BOVEN){
						p.setY(p.getY()-1);
					}else if(bp.getRichting() == Tegel.RICHTING.ONDER){
						p.setY(p.getY()+1);
					}else if(bp.getRichting() == Tegel.RICHTING.RECHTS){
						p.setX(p.getX()+1);
					}else if(bp.getRichting() == Tegel.RICHTING.LINKS){
						p.setX(p.getX()-1);
					}
					this.voegTegelToe(p.getX(), p.getY(), tegel);
				}
			}
		}else
		{
			this.voegTegelToe(3, 3, tegel);
		}
	}

	public void voegPionToe(Tegel tegel, Pion pion, Punt pos) {
		for(Tegel_Gui tg: mijnTegels)
		{
			if(tg.getTegel().getID() == tegel.getID())
			{
				tg.voegPionToe(pion, pos);
			}
		}	
	}
}
