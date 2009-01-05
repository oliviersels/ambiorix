package ambiorix.gui;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;

import ambiorix.spelbord.TegelBasis;

/**
 * Deze klasse stelt een veldje voor waarin tegels staan die een speler vast heeft.
 * @author Jens
 *
 */
public class TegelSelectieVeld extends JPanel implements TegelLuisteraar {
	private static final long serialVersionUID = 3293123065866281943L;
	private Vector<Tegel_Gui> mijnTegels;
	private Vector<TegelLuisteraar> tegelKlikLuisteraars;

	public TegelSelectieVeld() {
		mijnTegels = new Vector<Tegel_Gui>();
		this.setLayout(new TegelSelectieVeldLayout());
		tegelKlikLuisteraars = new Vector<TegelLuisteraar>();
	}

	@Override
	/**
	 * Als de rechtermuisknop gebruikt wordt, wordt de tegel gedraaid.
	 * Indien een andere muisknop gebruikt wordt, wordt de gebeurtenis doorgegeven.
	 */
	public synchronized void geklikt(TegelGebeurtenis tg) {
		Iterator<TegelLuisteraar> it = tegelKlikLuisteraars.iterator();
		if (tg.me.getButton() == MouseEvent.BUTTON3) {
			int huidigeRotatie = tg.tegel.getRotatie();
			int nieuweRotatie = (huidigeRotatie + 90) % 360;
			tg.tegel.setRotatie(nieuweRotatie);
			this.repaint();
			this.revalidate();
		} else {
			while (it.hasNext()) {
				it.next().geklikt(tg);
			}
		}
	}

	public synchronized void addTegelKlikLuisteraar(TegelLuisteraar tkl) {
		tegelKlikLuisteraars.add(tkl);
	}

	public synchronized void startTegelSelectie() {
		if (mijnTegels.size() == 1) {
			Tegel_Gui t = mijnTegels.get(0);
			MouseEvent e = new MouseEvent(t, 0, 0, 0, 0, 0, 1, false);
			t.mouseClicked(e);
		}
	}

	public synchronized void removeTegelKlikLuisteraar(TegelLuisteraar tkl) {

		tegelKlikLuisteraars.remove(tkl);
	}

	public void voegTegelToe(TegelBasis tegel) {
		Tegel_Gui nieuweTegel = new Tegel_Gui(tegel);
		nieuweTegel.zetPos(0, 0);
		mijnTegels.add(nieuweTegel);
		nieuweTegel.setVisible(true);
		nieuweTegel.addTegelKlikLuisteraar(this);
		this.add(nieuweTegel);
		this.repaint();
		this.revalidate();
	}

	public void verwijderTegel(TegelBasis tegel) {
		Iterator<Tegel_Gui> it = mijnTegels.iterator();
		Component teVerwijderenComp = null;
		Tegel_Gui teVerwijderenTG = null;
		while (it.hasNext()) {
			Tegel_Gui tg = it.next();
			if (tg.getTegel() == tegel)
				teVerwijderenTG = tg;
		}
		Component[] comps = this.getComponents();
		int lengte = comps.length;

		for (int i = 0; i < lengte; i++) {
			Tegel_Gui tg = (Tegel_Gui) comps[i];
			if (tg.getTegel() == tegel)
				teVerwijderenComp = comps[i];
		}
		if (teVerwijderenComp != null && teVerwijderenTG != null) {
			mijnTegels.remove(teVerwijderenTG);
			this.remove(teVerwijderenComp);
		}

		this.repaint();
		this.revalidate();
	}

	public void ledig() {
		mijnTegels.clear();
		this.removeAll();
		this.repaint();
		this.revalidate();
	}

	@Override
	public void bewogen(TegelGebeurtenis tg) {

	}
}
