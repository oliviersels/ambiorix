package ambiorix;


import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ambiorix.controller.StartController;
import ambiorix.guimenus.StartMenu;



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
