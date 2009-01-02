package ambiorix;

import java.util.Vector;

import javax.swing.SwingUtilities;

import ambiorix.gui.Uitvoer;
import ambiorix.gui.Uitvoer.SpelerPionGeven;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.PionTypeVerzameling;
import ambiorix.spelbord.ScoreBerekenaar;
import ambiorix.spelbord.Spelbord;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;
import ambiorix.spelbord.scoreberekenaars.SimpelScoreBerekenaar;
import ambiorix.spelers.Antwoord;
import ambiorix.spelers.Speler;

public class SpelToolkit {
	private Vector<Speler> spelers;
	private Spelbord spelbord;
	private Uitvoer gui;

	public SpelToolkit(Vector<Speler> spelers, Spelbord spelbord, Uitvoer gui) {
		this.spelers = spelers;
		this.spelbord = spelbord;
		this.gui = gui;
	}

	// van Spel
	
	public int getAantalSpelers() {
		return spelers.size();
	}

	public Vector<Speler> getSpelers() {
		return spelers;
	}	

	public Speler getActieveSpeler() {
		for(Speler s : spelers) {
			if(s.isActief())
				return s;
		}
		
		return null;
	}

	public void setActieveSpeler(Speler actieveSpeler) {
		Speler nuActief = getActieveSpeler();
		if(nuActief != null)
			nuActief.zetActief(false);
		actieveSpeler.zetActief(true);
		Uitvoer.ZetActieveSpeler run = gui.new ZetActieveSpeler(actieveSpeler);
		SwingUtilities.invokeLater(run);
	}

	// van Spelbord
	
	public boolean positieMogelijk(Tegel t, BordPositie p) {
		return t.kanBuurAccepteren(p.getBuur(), p.getRichting());
	}
	
	// van typeverzamelingen
	
	public Pion getPion(String piontype, Speler s) {
		return new Pion(0,PionTypeVerzameling.getInstantie().getType(piontype), s);
	}

	public void setTegelAantal(String tegelType, int hoeveelheid) {
		spelbord.setTegelAantal(tegelType, hoeveelheid);
	}
	
	// Van Speler
	//  1) Input
	public BordPositie selecteerBordPositie(Speler s) throws InterruptedException {
		Antwoord a = s.selecteerBordPositie();
		return a.getPosities().get(0);
	}
	
	public Tegel selecteerSpelerTegel(Speler s) throws InterruptedException {
		Antwoord a = s.selecteerSpelerTegel();
		return a.getTegels().get(0);
	}
	

	public Terrein selecteerTegelGebied(Speler s) throws InterruptedException {
		Antwoord a = s.selecteerTegelGebied();
		return a.getTerreinen().get(0);
	}
	
	public Pion selecteerSpelerPion(Speler s) throws InterruptedException {
		Antwoord a = s.selecteerSpelerPion();
		if(a != null)
			return a.getPionnen().get(0);
		else
			return null;
	}
	
	//  2) Output
	public void zetTegel(Speler s, Tegel t, BordPositie p) {
		spelbord.plaatsTegel(t, p);
		s.zetTegel(t, p);
	}
	
	public void zetPion(Speler s, Pion p, Terrein t) {
		spelbord.plaatsPion(p, t);
		s.zetPion(p, t);
	}
	
	public void geefSpelerTegel(Tegel t, Speler s) {
		s.addTegel(t);
	}
	
	public void geefSpelerPion(Pion p, Speler s) {
		s.addPion(p);
	}
	
	public void neemSpelerTegelAf(Tegel t, Speler s) {
		s.deleteTegel(t);
	}
	
	public void neemSpelerPionAf(Pion p, Speler s) {
		s.deletePion(p);
	}

	// 3) spelbord functies ROBIN
	public Vector<BordPositie> controleerGlobalePlaatsbaarheid(Tegel tegel,
			boolean stopDirect) {
		return spelbord.controleerGlobalePlaatsbaarheid(tegel, stopDirect);
	}

	public boolean controleerPlaatsbaarheid(Pion pion, Terrein terrein) {
		return spelbord.controleerPlaatsbaarheid(pion, terrein);
	}

	public boolean controleerPlaatsbaarheid(Tegel tegel, BordPositie positie) {
		return spelbord.controleerPlaatsbaarheid(tegel, positie);
	}

	public Gebied getGebied(Terrein start) {
		return spelbord.getGebied(start);
	}

	public Tegel getLaatstGeplaatsteTegel() {
		return spelbord.getLaatstGeplaatsteTegel();
	}

	public int getTegelAantal(String tegelType) {
		return spelbord.getTegelAantal(tegelType);
	}

	public void plaatsPion(Pion pion, Terrein terrein) {
		spelbord.plaatsPion(pion, terrein);
	}

	public void plaatsTegel(Tegel tegel, BordPositie positie) {
		spelbord.plaatsTegel(tegel, positie);
	}

	public void setBegintegel(Tegel beginTegel) {
		spelbord.setBegintegel(beginTegel);
		Speler actief = getActieveSpeler();
		actief.zetTegel(beginTegel, new BordPositie(null, null));
	}

	public void verwijderPion(Pion pion) {
		spelbord.verwijderPion(pion);
	}

	public void verwijderPion(Terrein positie) {
		spelbord.verwijderPion(positie);
	}

	public void verwijderTegel(Tegel tegel) {
		spelbord.verwijderTegel(tegel);
	}
	
	public Tegel getVolgendeTegel()
	{
		return spelbord.getVolgendeTegel();
	}
	
	public ScoreBerekenaar getScoreBerekenaar() {
		return new SimpelScoreBerekenaar(); // TODO: Aanpassen en opvragen op Spel
	}
}
