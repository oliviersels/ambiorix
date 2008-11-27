package ambiorix.acties.basisspel;

import ambiorix.acties.Actie;

public class Beurt_Begin extends Actie {
	
	private boolean speelbaar = true;	

	public Beurt_Begin() {
		System.out.println("-------> Beurt - Begin");
		
		// TODO_S ref naar huidige speler
		// TODO_S ref naar tegel
		
		undo = UNDO.NIET_BESCHIKBAAR;
		status = STATUS.KLAAR;
	}
	
	static int i = 5;
	
	@Override
	public void start() {
		if(speltoolkit==null)
			return;
		// lijst van mogelijke tegeltypes opstellen
		System.out.println("Beurt - Begin -> alle tegeltypes opvragen");
		System.out.println("Beurt - Begin -> checken welk tegeltype nog te plaatsen is");
		System.out.println("Beurt - Begin -> zijn er geen mogelijkheden meer ? -> STOP");
		System.out.println("Beurt - Begin -> i " + i);

		speelbaar = (--i > 0);
		
		if(speelbaar) {
			System.out.println("Beurt - Begin -> doorspelen");
			System.out.println("Beurt - Begin -> willekeurig tegeltype selecteren");
			System.out.println("Beurt - Begin -> Tegel aanmaken van dit type");
			System.out.println("Beurt - Begin -> Tegel aan de huidige speler geven");
			
		} else {
			System.out.println("Beurt - Begin -> stoppen");		
		}
		
		status = STATUS.GEDAAN;	
	}

	@Override
	public void undo() {		
		// normaal niks undo doen
		System.out.println("Beurt - Begin -> undo");
		if(speelbaar) {
			System.out.println("Beurt - Begin -> Tegel van speler afnemen en verwijderen");				
		}

	}

	@Override
	public Actie volgende() {
		
		System.out.println("Beurt - Begin -> volgende");
		if(speelbaar)		
			return new Beurt_TegelPlaatsen();
		else
			return new Post_Winnaar();
			
	}

}
