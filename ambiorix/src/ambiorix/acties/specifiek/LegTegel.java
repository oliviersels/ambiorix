package ambiorix.acties.specifiek;

import ambiorix.SpelToolkit;
import ambiorix.acties.AbstractActie;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Tegel;
import ambiorix.spelers.Speler;

public class LegTegel extends AbstractActie {
	private Tegel gekozenTegel;
	private BordPositie positie;

	public LegTegel(SpelToolkit kit, AbstractActie vorige) {
		super(kit, vorige);
	}
	
	private boolean controleerPositie(BordPositie p, Tegel t) {
		if(p == null || t == null)
			return false;
		return true;
	}

	@Override
	public AbstractActie doeActie() {
		System.out.println("start -> legtegel");
		gekozenTegel = null;
		positie = null;
		Speler actieveSpeler = kit.getActieveSpeler();
		
		while(!controleerPositie(positie, gekozenTegel)) {
			System.out.println("legtegel -> vraag aan gebruiker welke tegel hij wilt leggen");
			gekozenTegel = kit.selecteerSpelerTegel(actieveSpeler);
			
			System.out.println("legtegel -> vraag aan gebruiker waar hij deze wilt leggen");
			positie = kit.selecteerBordPositie(actieveSpeler);
		}
		System.out.println("legtegel -> plaats de tegel op het bord");
		kit.zetTegel(actieveSpeler, gekozenTegel, positie);

		System.out.println("Volgende actie is zetpion");
		return new GeefTegel(kit, this); // TODO: return zetPion actie
	}

	@Override
	public boolean kanOngedaanMaken() {
		return false; // TODO: Ongedaan maken van LegTegel
	}

	@Override
	public AbstractActie maakOngedaan() {
		System.out.println("legtegel -> Ongedaan maken");
		System.out.println("legtegel -> tegel verwijderen van speelbord en teruggeven aan speler");

		return null;
	}
	
	

}
