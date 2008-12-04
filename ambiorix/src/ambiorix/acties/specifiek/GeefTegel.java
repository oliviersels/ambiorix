package ambiorix.acties.specifiek;

import ambiorix.SpelToolkit;
import ambiorix.acties.AbstractActie;

public class GeefTegel extends AbstractActie {

	public GeefTegel(SpelToolkit kit, AbstractActie vorige) {
		super(kit, vorige);
	}

	@Override
	public AbstractActie doeActie() {
		System.out.println("start -> geeftegel");
		
		System.out.println("geeftegel -> geef tegel aan huidige speler");
		System.out.println("geeftegel -> verwacht geen input ofzo");
		
		System.out.println("geeftegel -> volgende -> LegTegel");
		
		return new LegTegel(kit, this); // TODO veranderen
	}

	@Override
	public boolean kanOngedaanMaken() {
		return false; // TODO moet nog gemaakt worden
	}

	@Override
	public AbstractActie maakOngedaan() {
		System.out.println("geeftegel -> tegel van speler terug naar de verzameling");
		System.out.println("Terug aan speler vragen om tegel te leggen");
		
		return vorigeActie;
	}

}
