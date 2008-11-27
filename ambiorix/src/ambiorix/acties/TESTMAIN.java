package ambiorix.acties;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.Vector;

import ambiorix.acties.basisspel.Pre_GeefPionnen;
import ambiorix.acties.specifiek.GeefTegel;

public class TESTMAIN {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		
		try {
			ActieBestuurder ab = new ActieBestuurder(null);			
			
			DRAAD d = null;
			d = new DRAAD(ab, 1);
			d = new DRAAD(ab, 2);
			d = new DRAAD(ab, 3);
			d = new DRAAD(ab, 4);
			
			ab.start(new Pre_GeefPionnen());
			
			d = new DRAAD(ab, 0);
			d.join();
			
			System.out.println("---- DONE ----");
		} catch (/*Interrupted*/Exception e) {}
	}

}
