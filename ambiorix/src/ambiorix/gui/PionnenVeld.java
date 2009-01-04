package ambiorix.gui;

import java.awt.Component;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;

import ambiorix.spelbord.PionBasis;

/**
 * Wordt gebruikt om pionnen weer te geven die daarna geselecteerd kunnen
 * worden.
 * 
 * @author Jens
 * 
 */
public class PionnenVeld extends JPanel implements PionLuisteraar {

	private static final long serialVersionUID = 1L;
	private Vector<Pion_Gui> mijnPionnen;
	private Vector<PionLuisteraar> mijnPionLuisteraars;

	public PionnenVeld() {
		mijnPionnen = new Vector<Pion_Gui>();
		mijnPionLuisteraars = new Vector<PionLuisteraar>();
		this.setLayout(new PionnenVeldLayout());
	}

	public void voegPionToe(PionBasis pion) {
		Pion_Gui nieuwePion = new Pion_Gui(pion);
		nieuwePion.setVisible(true);
		nieuwePion.voegPionLuisteraarToe(this);
		mijnPionnen.add(nieuwePion);
		this.add(nieuwePion);
		this.repaint();
		this.revalidate();
	}

	public void verwijderPion(PionBasis pion) {
		Pion_Gui teVerwijderenPion = null;
		Component teVerwijderenComp = null;

		for (Pion_Gui pg : mijnPionnen) {
			if (pg.getPion() == pion)
				teVerwijderenPion = pg;
		}

		Component comps[] = getComponents();
		for (Component c : comps) {
			Pion_Gui pg = (Pion_Gui) c;
			if (pg.getPion() == pion)
				teVerwijderenComp = c;
		}

		if (teVerwijderenPion != null && teVerwijderenComp != null) {
			mijnPionnen.remove(teVerwijderenPion);
			this.remove(teVerwijderenComp);

			this.repaint();
			this.revalidate();
		}

	}

	public synchronized void addPionLuisteraar(PionLuisteraar pl) {
		mijnPionLuisteraars.add(pl);
	}

	public synchronized void removePionLuisteraar(PionLuisteraar pl) {

		mijnPionLuisteraars.remove(pl);
	}

	@Override
	public synchronized void geklikt(PionGebeurtenis pg) {
		Iterator<PionLuisteraar> it = mijnPionLuisteraars.iterator();
		while (it.hasNext()) {
			it.next().geklikt(pg);
		}
	}

	public void ledig() {
		mijnPionnen.clear();
		this.removeAll();
		this.repaint();
		this.revalidate();
	}
}
