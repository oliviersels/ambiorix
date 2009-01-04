package ambiorix.controller;

import java.io.File;

import ambiorix.guimenus.MenuLuisteraar;
import ambiorix.guimenus.SpelerMenu;
import ambiorix.guimenus.UitbreidingMenu;
import ambiorix.uitbreidingen.UitbreidingVerzameling;

public class SpelerController implements MenuLuisteraar {
	SpelerMenu spelerMenu;
	UitbreidingMenu uitbreidingMenu;
	UitbreidingController uitbreidingController;
	
	public SpelerController(SpelerMenu menu) {
		spelerMenu = menu;
	}

	@Override
	public void volgende() {
		UitbreidingVerzameling.getInstantie().vulMetUitbreidingenUit("uitbreidingen" + File.separator);
		uitbreidingMenu = new UitbreidingMenu();
		uitbreidingController = new UitbreidingController(uitbreidingMenu, spelerMenu.geefSpelers());
		uitbreidingMenu.voegMenuLuisteraarToe(uitbreidingController);
		uitbreidingMenu.setLocationRelativeTo(spelerMenu);
		uitbreidingMenu.setVisible(true);
		spelerMenu.setVisible(false);
		uitbreidingMenu.requestFocus();
	}

	@Override
	public void vorige() {
		// TODO Auto-generated method stub
		
	}

}
