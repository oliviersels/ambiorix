package ambiorix.controller;

import java.util.Vector;

import ambiorix.Spel;
import ambiorix.gui.HoofdVenster;
import ambiorix.guimenus.MenuLuisteraar;
import ambiorix.guimenus.SpelerMenu;
import ambiorix.guimenus.SpelerOpties;
import ambiorix.guimenus.SpelerOpties.SpelerOptiesType;
import ambiorix.spelers.AiSpeler;
import ambiorix.spelers.MenselijkeSpeler;
import ambiorix.spelers.Speler;

public class SpelerController implements MenuLuisteraar {
	SpelerMenu menu;
	Vector<Speler> spelers;
	HoofdVenster gui;
	Spel spel;
	
	public SpelerController(SpelerMenu menu) {
		this.menu = menu;
		gui = new HoofdVenster();
		spel = new Spel(gui.getUitvoer());
		spelers = new Vector<Speler>();
	}

	@Override
	public void volgende() {
		Vector<SpelerOpties> spelerO = menu.geefSpelers();
		for(SpelerOpties so : spelerO) {
			if(so.type == SpelerOptiesType.HOT_SEAT) {
				MenselijkeSpeler s = new MenselijkeSpeler(gui.getInvoer(), gui.getUitvoer());
				s.setKleur(so.kleur);
				s.setNaam(so.naam);
				spelers.add(s);
			}
			else if(so.type == SpelerOptiesType.AI) {
				AiSpeler s = new AiSpeler(spel.getSpelbord(), gui.getUitvoer());
				s.setKleur(so.kleur);
				s.setNaam(so.naam);
				spelers.add(s);
			}
		}
		for(Speler s : spelers) {
			spel.addSpeler(s);
			gui.voegSpelerToe(s);
		}
		
		gui.setLocationRelativeTo(gui);
		gui.setVisible(true);
		menu.setVisible(false);
		gui.requestFocus();
		spel.start();
	}

	@Override
	public void vorige() {
		// TODO Auto-generated method stub
		
	}

}
