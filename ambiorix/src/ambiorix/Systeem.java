package ambiorix;

import static org.junit.Assert.assertEquals;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ambiorix.acties.ActieVerzameling;
import ambiorix.acties.specifiek.BerekenScore;
import ambiorix.acties.specifiek.EindeBeurt;
import ambiorix.acties.specifiek.EindeSpel;
import ambiorix.acties.specifiek.GeefTegel;
import ambiorix.acties.specifiek.LegTegel;
import ambiorix.acties.specifiek.StartSpel;
import ambiorix.acties.specifiek.ZetPion;
import ambiorix.controller.StartController;
import ambiorix.gui.HoofdVenster;
import ambiorix.guimenus.StartMenu;
import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.Pion;
import ambiorix.spelbord.PionTypeVerzameling;
import ambiorix.spelbord.Spelbord;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelTypeVerzameling;
import ambiorix.spelbord.Terrein;
import ambiorix.spelbord.TerreinTypeVerzameling;
import ambiorix.spelbord.piontypes.PionType_Volgeling;
import ambiorix.spelbord.tegeltypes.TegelType_BBBBB;
import ambiorix.spelbord.tegeltypes.TegelType_BurchtMetBochtweg;
import ambiorix.spelbord.tegeltypes.TegelType_Driesprong;
import ambiorix.spelbord.tegeltypes.TegelType_EenZijdeBurcht;
import ambiorix.spelbord.tegeltypes.TegelType_GGGGK;
import ambiorix.spelbord.tegeltypes.TegelType_LavaBocht;
import ambiorix.spelbord.tegeltypes.TegelType_LavaMetBurchten;
import ambiorix.spelbord.tegeltypes.TegelType_LavaMetWeg;
import ambiorix.spelbord.tegeltypes.TegelType_LavaPoel;
import ambiorix.spelbord.tegeltypes.TegelType_LavaRecht;
import ambiorix.spelbord.tegeltypes.TegelType_RechteWeg;
import ambiorix.spelbord.tegeltypes.TegelType_WGGWW;
import ambiorix.spelbord.terreintypes.TerreinType_Burcht;
import ambiorix.spelbord.terreintypes.TerreinType_Gras;
import ambiorix.spelbord.terreintypes.TerreinType_Klooster;
import ambiorix.spelbord.terreintypes.TerreinType_Lava;
import ambiorix.spelbord.terreintypes.TerreinType_Weg;
import ambiorix.spelbord.terreintypes.TerreinType_Wildcard;
import ambiorix.uitbreidingen.Uitbreiding;
import ambiorix.uitbreidingen.UitbreidingVerzameling;
import ambiorix.util.File;
import ambiorix.util.Punt;


public class Systeem {
	
	public synchronized void startGUI() {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					System.exit(0);
				} catch (InstantiationException e) {
					e.printStackTrace();
					System.exit(0);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					System.exit(0);
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
					System.exit(0);
				}
				
				StartMenu start = new StartMenu();
				StartController sc = new StartController(start);
				start.voegStartMenuLuisteraarToe(sc);
				start.setVisible(true);
		    }
		});

	}

	public static void main(String args[]) {
		Systeem s = new Systeem();
		s.startGUI();
	}
}
