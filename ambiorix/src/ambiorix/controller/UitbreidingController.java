package ambiorix.controller;

import java.util.Vector;

import ambiorix.Spel;
import ambiorix.gui.HoofdVenster;
import ambiorix.guimenus.MenuLuisteraar;
import ambiorix.guimenus.SpelerOpties;
import ambiorix.guimenus.UitbreidingMenu;
import ambiorix.guimenus.SpelerOpties.SpelerOptiesType;
import ambiorix.spelers.AiSpeler;
import ambiorix.spelers.MenselijkeSpeler;
import ambiorix.spelers.Speler;
import ambiorix.uitbreidingen.UitbreidingVerzameling;


public class UitbreidingController implements MenuLuisteraar {
	UitbreidingMenu uitbreidingMenu;
	Vector<SpelerOpties> spelerOpties;
	Vector<Speler> spelers;
	HoofdVenster gui;
	Spel spel;

	public UitbreidingController(UitbreidingMenu um, Vector<SpelerOpties> s) {
		uitbreidingMenu = um;
		spelerOpties = s;
	}
	
	@Override
	public void volgende() {
		UitbreidingVerzameling.getInstantie().bereidUitbreidingenVoor(uitbreidingMenu.geefGeselecteerdeUitbreidingen());
		gui = new HoofdVenster();
		spel = new Spel(gui.getUitvoer());
		spelers = new Vector<Speler>();
		for(SpelerOpties so : spelerOpties) {
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
		uitbreidingMenu.setVisible(false);
		gui.requestFocus();
		
		spel.start();
	}

	@Override
	public void vorige() {
		// TODO Auto-generated method stub
		
	}

}
