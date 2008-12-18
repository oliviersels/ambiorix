package ambiorix.acties.specifiek;

import ambiorix.SpelToolkit;
import ambiorix.acties.AbstractActie;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.Terrein;
import ambiorix.spelers.Speler;

public class ZetPion extends AbstractActie {
	private Pion gekozenPion;
	private Terrein gekozenTerrein;
	private Tegel vorigeTegel;

	public ZetPion(SpelToolkit kit, AbstractActie vorige) {
		super(kit, vorige);
		vorigeTegel = null;
	}
	
	public ZetPion(SpelToolkit kit, AbstractActie vorige, Tegel vorigeTegel) {
		super(kit, vorige);
		this.vorigeTegel = vorigeTegel;
	}
	
	private boolean controleerPositie(Pion p, Terrein t) {
		if(p == null || t == null)
			return false;
		
		// Check of we wel op dezelfde tegel plaatsen als de vorige
		if(vorigeTegel != null) {
			if(!vorigeTegel.equals(t.getTegel()))
				return false;
		}
		
		return kit.controleerPlaatsbaarheid(p, t);
	}

	@Override
	public AbstractActie doeActie() {
		System.out.println("ZetPion -> Wil de speler een pion zetten? Hij zal wel moeten.. want dit gaat nog niet");
		
		Speler actieveSpeler = kit.getActieveSpeler();
		
		// Heeft de speler nog pionnen?
		if(actieveSpeler.getAantalPionnen() != 0) {
			gekozenPion = null;
			gekozenTerrein = null;
			
			while(!controleerPositie(gekozenPion, gekozenTerrein)) {
				if(gekozenPion != null)
					kit.geefSpelerPion(gekozenPion, actieveSpeler);
				System.out.println("ZetPion -> kies pion");
				try {
					gekozenPion = kit.selecteerSpelerPion(actieveSpeler);
				} catch (InterruptedException e) {
					return null; // Spel afgelopen
				}
				System.out.println("ZetPion -> kies een locatie (terrein)");
				try {
					gekozenTerrein = kit.selecteerTegelGebied(actieveSpeler);
				} catch (InterruptedException e) {
					return null; // Spel gedaan
				}
				kit.neemSpelerPionAf(gekozenPion, actieveSpeler);
			}
			System.out.println("ZetPion -> Plaats pion op terrein");
			kit.zetPion(actieveSpeler, gekozenPion, gekozenTerrein);
		}
		
		return new GeefTegel(kit, this);
	}

	@Override
	public boolean kanOngedaanMaken() {
		// TODO Nog niet
		return false;
	}

	@Override
	public AbstractActie maakOngedaan() {
		// TODO Auto-generated method stub
		return null;
	}

}
