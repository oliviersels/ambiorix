package ambiorix.acties.basisspel;

import ambiorix.acties.Actie;

public class Post_Winnaar extends Actie {

	public Post_Winnaar() {
		System.out.println("-------> Post - Winnaar");
		
		undo = UNDO.NIET_BESCHIKBAAR;
		status = STATUS.KLAAR;
	}

	@Override
	public void start() {
		System.out.println("Post - Winnaar -> lijst van uitsaande pionnen verkrijgen");
		System.out.println("Post - Winnaar -> score voor elke pion berekenen");
		System.out.println("Post - Winnaar -> deze pionnen verwijderen");
		
		System.out.println("Post - Winnaar -> lijst van spelers verkrijgen");
		System.out.println("Post - Winnaar -> speler met meeste punten zoeken");
		
		System.out.println("Post - Winnaar -> MELDING, Speler heeft gewonnen");

		status = STATUS.GEDAAN;
	}

	@Override
	public void undo() {
		System.out.println("Post - Winnaar -> undo");
	}

	@Override
	public Actie volgende() {
		return null; // opruimen?
	}

}
