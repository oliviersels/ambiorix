package ambiorix.controller;

import ambiorix.guimenus.SpelerMenu;
import ambiorix.guimenus.StartMenu;
import ambiorix.guimenus.StartMenuLuisteraar;

public class StartController implements StartMenuLuisteraar {
	SpelerMenu spelerMenu;
	SpelerController spelerController;
	StartMenu startMenu;
	
	public StartController(StartMenu menu) {
		startMenu = menu;
	}
	
	@Override
	public void highscores() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void laadSpel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nieuwSpel() {
		spelerMenu = new SpelerMenu();
		spelerController = new SpelerController(spelerMenu);
		spelerMenu.voegMenuLuisteraarToe(spelerController);
		spelerMenu.setVisible(true);
		startMenu.setVisible(false);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
