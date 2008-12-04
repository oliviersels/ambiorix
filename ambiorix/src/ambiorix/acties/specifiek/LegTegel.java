package ambiorix.acties.specifiek;

import ambiorix.SpelToolkit;
import ambiorix.acties.AbstractActie;

public class LegTegel extends AbstractActie {

	public LegTegel(SpelToolkit kit, AbstractActie vorige) {
		super(kit, vorige);
	}

	@Override
	public AbstractActie doeActie() {
		System.out.println("start -> legtegel");
		System.out.println("legtegel -> vraag aan gebruiker welke tegel hij wilt leggen");
		System.out.println("legtegel -> vraag aan gebruiker waar hij deze wilt leggen");
		// Dit duurt lang..
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("doeActie onderbroken!");
			return null;
		}

		System.out.println("Volgende actie is zetpion");
		return null; // TODO: return zetPion actie
	}

	@Override
	public boolean kanOngedaanMaken() {
		return false; // TODO: Ongedaan maken van LegTegel
	}

	@Override
	public AbstractActie maakOngedaan() {
		System.out.println("legtegel -> Ongedaan maken");
		System.out.println("legtegel -> tegel verwijderen van speelbord en teruggeven aan speler");

		return vorigeActie;
	}
	
	

}
