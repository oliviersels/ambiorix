package ambiorix.controller;

import ambiorix.guimenus.BestaatNogNietVenster;
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
		new BestaatNogNietVenster();

	}

	@Override
	public void laadSpel() {
		new BestaatNogNietVenster();

	}

	@Override
	public void nieuwSpel() {
		spelerMenu = new SpelerMenu();
		spelerController = new SpelerController(spelerMenu);
		spelerMenu.voegMenuLuisteraarToe(spelerController);
		spelerMenu.setLocationRelativeTo(startMenu);
		spelerMenu.setVisible(true);
		startMenu.setVisible(false);
		spelerMenu.requestFocus();
	}

	@Override
	public void stop() {
		new BestaatNogNietVenster();

	}

}
