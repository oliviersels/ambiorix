package ambiorix.acties.specifiek;

import ambiorix.SpelToolkit;
import ambiorix.acties.AbstractActie;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.PionTypeVerzameling;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelTypeVerzameling;
import ambiorix.spelers.Speler;

public class StartSpel extends AbstractActie {

	public StartSpel(SpelToolkit kit, AbstractActie vorige) {
		super(kit, vorige);
	}

	@Override
	public AbstractActie doeActie() {
		// TODO: Voeg alle tegels toe aan de pool (juiste aantallen)
		kit.setTegelAantal("TegelType_WGGWW", 4);
		kit.setTegelAantal("TegelType_RechteWeg", 4);
		kit.setTegelAantal("TegelType_Driesprong", 4);
		kit.setTegelAantal("TegelType_EenZijdeBurcht", 4);
		//kit.setTegelAantal("TegelType_BBBBB", 4);
		kit.setTegelAantal("TegelType_GGGGK", 2);
		kit.setTegelAantal("TegelType_BurchtMetBochtweg", 2);
		
		// TODO: Spelers juiste pionnen geven
		for(Speler s : kit.getSpelers()) {
			for(int i = 0; i < 7; i++)
				kit.geefSpelerPion(new Pion(0, PionTypeVerzameling.getInstantie().getType("PionType_Volgeling"), s), s);
		}
		// Actieve Speler instellen (de eerste)
		kit.setActieveSpeler(kit.getSpelers().get(0));
		
		Tegel t = new Tegel(TegelTypeVerzameling.getInstantie().getType("TegelType_BurchtMetBochtweg"));
		kit.setBegintegel(t);
		
		return new GeefTegel(kit, this);
	}

	@Override
	public boolean kanOngedaanMaken() {
		return false;
	}

	@Override
	public AbstractActie maakOngedaan() {
		return null;
	}

}
