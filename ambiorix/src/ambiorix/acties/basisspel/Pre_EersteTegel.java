package ambiorix.acties.basisspel;
import ambiorix.acties.Actie;


public class Pre_EersteTegel extends Actie {

	public Pre_EersteTegel() {
		System.out.println("-------> Pre - EersteTegel");
		
		// TODO_S ref naar Tegel bijhouden
		
		status = STATUS.KLAAR;
	}

	@Override
	public void start() {
		System.out.println("Pre - EersteTegel -> TegelTypes opvragen");
		System.out.println("Pre - EersteTegel -> willekeurig een kiezen");
		System.out.println("Pre - EersteTegel -> Tegel aanmaken van dit type");
		System.out.println("Pre - EersteTegel -> deze Tegel plaatsen als root");
		
		status = STATUS.GEDAAN;
	}

	@Override
	public void undo() {
		System.out.println("Pre - EersteTegel -> Tegel verwijderen als root");
		
		status = STATUS.UNDO;
	}

	@Override
	public Actie volgende() {
		System.out.println("Pre - EersteTegel -> volgende");
		
		return new Pre_BeginSpeler();
	}

}
