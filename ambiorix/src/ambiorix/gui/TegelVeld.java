package ambiorix.gui;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;

import ambiorix.spelbord.BordPositie;
import ambiorix.spelbord.Gebied;
import ambiorix.spelbord.PionBasis;
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelBasis;
import ambiorix.spelbord.Terrein;
import ambiorix.util.Punt;

public class TegelVeld extends JPanel implements TegelLuisteraar,
		TegelGeestLuisteraar {
	private static final long serialVersionUID = 853739061815235412L;
	private Vector<Tegel_Gui> mijnTegels;
	private Vector<TegelGeest> mijnTegelGeesten;
	private Vector<TegelLuisteraar> tegelKlikLuisteraars;
	private Vector<TegelGeestLuisteraar> tegelGeestLuisteraars;
	private Vector<TegelGeest> mijnVerwijderdeTegelGeesten; // wordt gebruikt om
	// bij een undo de
	// TegelGeest terug
	// te plaatsen
	private TegelBasis tePlaatsenTegel;

	public synchronized void addTegelKlikLuisteraar(TegelLuisteraar tkl) {
		tegelKlikLuisteraars.add(tkl);
	}

	public synchronized void removeTegelKlikLuisteraar(TegelLuisteraar tkl) {

		tegelKlikLuisteraars.remove(tkl);
	}

	public synchronized void addTegelGeestLuisteraar(TegelGeestLuisteraar tgl) {
		tegelGeestLuisteraars.add(tgl);
	}

	public synchronized void removeTegelGeestLuisteraar(TegelGeestLuisteraar tgl) {

		tegelGeestLuisteraars.remove(tgl);
	}

	public TegelVeld() {
		super();
		mijnTegels = new Vector<Tegel_Gui>();
		mijnTegelGeesten = new Vector<TegelGeest>();
		mijnVerwijderdeTegelGeesten = new Vector<TegelGeest>();
		tegelGeestLuisteraars = new Vector<TegelGeestLuisteraar>();
		this.setLayout(new TegelVeldLayout());
		tegelKlikLuisteraars = new Vector<TegelLuisteraar>();
	}

	public void voegTegelToe(int x, int y, TegelBasis tegel) {
		this.tePlaatsenTegel = null;
		Tegel_Gui nieuweTegel = new Tegel_Gui(tegel);
		nieuweTegel.zetPos(x, y);
		mijnTegels.add(nieuweTegel);
		nieuweTegel.setVisible(true);
		nieuweTegel.addTegelKlikLuisteraar(this);
		this.add(nieuweTegel);
		voegTegelGeestToe(x, y + 1, nieuweTegel, Tegel.RICHTING.ONDER);
		voegTegelGeestToe(x, y - 1, nieuweTegel, Tegel.RICHTING.BOVEN);
		voegTegelGeestToe(x - 1, y, nieuweTegel, Tegel.RICHTING.LINKS);
		voegTegelGeestToe(x + 1, y, nieuweTegel, Tegel.RICHTING.RECHTS);
		verwijderTegelGeest(x, y);
		this.repaint();
		this.revalidate();
	}

	@Override
	public synchronized void geklikt(TegelGebeurtenis tg) {
		Iterator<TegelLuisteraar> it = tegelKlikLuisteraars.iterator();
		while (it.hasNext()) {
			it.next().geklikt(tg);
		}
	}

	@Override
	public synchronized void geklikt(TegelGeestGebeurtenis gtg) {
		Iterator<TegelGeestLuisteraar> it = tegelGeestLuisteraars.iterator();
		while (it.hasNext()) {
			it.next().geklikt(gtg);
		}
	}

	private void voegTegelGeestToe(int x, int y, Tegel_Gui tg,
			Tegel.RICHTING richting) {
		// we moeten controleren of er al een TegelGeest of TegelGui op deze
		// positie staat
		boolean TegelGeestGevonden = false;
		boolean TegelGuiGevonden = false;
		Iterator<TegelGeest> it = mijnTegelGeesten.iterator();
		while (it.hasNext()) {
			TegelGeest huidig = it.next();
			if (huidig.getXPos() == x && huidig.getYPos() == y) {
				// indien er hier al een TegelGeestStaat, voegen we meteen een
				// buur toe
				huidig.voegBuurToe(tg, richting);
				TegelGeestGevonden = true;
			}
		}
		Iterator<Tegel_Gui> it2 = mijnTegels.iterator();
		while (it2.hasNext()) {

			Tegel_Gui huidig = it2.next();
			if (huidig.getXPos() == x && huidig.getYPos() == y) {
				TegelGuiGevonden = true;
			}
		}

		if (!TegelGeestGevonden && !TegelGuiGevonden) {
			TegelGeest nieuweTegelGeest = new TegelGeest(x, y, tg, richting);
			nieuweTegelGeest.addTegelGeestLuisteraar(this);
			mijnTegelGeesten.add(nieuweTegelGeest);
			this.add(nieuweTegelGeest);
		}
	}

	private void verwijderTegelGeest(int x, int y) {
		Iterator<TegelGeest> it = mijnTegelGeesten.iterator();
		while (it.hasNext()) {
			TegelGeest huidig = it.next();
			if (huidig.getXPos() == x && huidig.getYPos() == y) {
				this.remove(huidig);
				mijnVerwijderdeTegelGeesten.add(huidig);
				it.remove();
			}
		}
	}

	public void tekenTerrein(Gebied gebied) {
		for (Tegel_Gui tg : mijnTegels) {
			tg.wisGebiedenTeTekenen();
		}
		Vector<Terrein> terreinstukken = gebied.getTerreinStukken();
		for (Terrein terrein : terreinstukken) {
			for (Tegel_Gui tg : mijnTegels) {
				if (tg.getTegel().getID() == terrein.getTegel().getID()) {
					tg.addGebiedTeTekenen(terrein.getPositie());
				}
			}
		}
		this.repaint();
		this.revalidate();
	}

	public void voegTegelToe(TegelBasis tegel, BordPositie bp) {
		this.tePlaatsenTegel = null;
		Tegel buur = bp.getBuur();
		int grootte = mijnTegels.size();
		if (buur != null) {
			for (int i = 0; i < grootte; i++) {
				Tegel_Gui tg = mijnTegels.get(i);
				if (tg.getTegel().getID() == buur.getID()) {
					Punt p = new Punt(tg.getPos());
					if (bp.getRichting() == Tegel.RICHTING.BOVEN) {
						p.setY(p.getY() - 1);
					} else if (bp.getRichting() == Tegel.RICHTING.ONDER) {
						p.setY(p.getY() + 1);
					} else if (bp.getRichting() == Tegel.RICHTING.RECHTS) {
						p.setX(p.getX() + 1);
					} else if (bp.getRichting() == Tegel.RICHTING.LINKS) {
						p.setX(p.getX() - 1);
					}
					this.voegTegelToe(p.getX(), p.getY(), tegel);
				}
			}
		} else {
			this.voegTegelToe(1, 1, tegel);
		}
	}

	public void voegPionToe(Tegel tegel, Pion_Gui pion, Punt pos) {
		for (Tegel_Gui tg : mijnTegels) {
			if (tg.getTegel().getID() == tegel.getID()) {
				tg.voegPionToe(pion, pos);
			}
		}
	}

	@Override
	public void bewogen(TegelGebeurtenis tg) {
		Punt p = new Punt((int) ((tg.tegelPixelY / 100f) * tg.tegel
				.getTerreinHoogte()), (int) ((tg.tegelPixelX / 100f) * tg.tegel
				.getTerreinBreedte()));
		Terrein ter = (Terrein) tg.tegel.getTerrein(p);
		if (ter.getType().isToegankelijk() == true) {
			Tegel teg = (Tegel) tg.tegel;
			tekenTerrein(teg.getGebied(ter));
		} else {
			for (Tegel_Gui tgui : mijnTegels) {
				tgui.wisGebiedenTeTekenen();
			}
		}
	}

	@Override
	public void bewogen(TegelGeestGebeurtenis tgg) {
		for (TegelGeest tg : mijnTegelGeesten) {
			tg.zetAfbeelding(null);
			tg.zetRood(false);
		}
		if (tePlaatsenTegel != null) {
			boolean tePlaatsen = true;
			Vector<Tegel_Gui> tgs = tgg.tegelGeest.geefBuren();
			Vector<Tegel.RICHTING> richtingen = tgg.tegelGeest.geefRichtingen();
			int i = 0;
			for (Tegel_Gui tg : tgs) {
				if (!tg.getTegel().kanBuurAccepteren((Tegel) tePlaatsenTegel,
						richtingen.get(i))) {
					tePlaatsen = false;
				}
				i++;
			}
			if (tePlaatsen) {
				tgg.tegelGeest.zetAfbeelding(tePlaatsenTegel);
			} else {
				tgg.tegelGeest.zetAfbeelding(tePlaatsenTegel);
				tgg.tegelGeest.zetRood(true);
			}
		}
	}

	public void zetTePlaatsenTegel(TegelBasis tb) {
		this.tePlaatsenTegel = tb;

	}

	public void verwijderPion(PionBasis pion) {
		for (Tegel_Gui tg : this.mijnTegels) {
			tg.verwijderPion(pion);
		}
	}

	public void verwijderTegel(TegelBasis tegel) {
		int index = -1;
		int i = 0;
		for (Tegel_Gui tg : this.mijnTegels) {
			if (tg.getTegel().equals(tegel)) {
				index = i;
			}
			i++;
		}
		Tegel_Gui teVerwijderen = mijnTegels.get(index);
		Vector<TegelGeest> teVerwijderenTegelGeesten = new Vector<TegelGeest>();
		for (TegelGeest tg : mijnTegelGeesten) {
			tg.verwijderBuur(teVerwijderen);
			if (tg.geefBuren().size() == 0) {
				teVerwijderenTegelGeesten.add(tg);
			}
		}
		for (TegelGeest tg : teVerwijderenTegelGeesten) {
			this.remove(tg);
			mijnTegelGeesten.remove(tg);
		}
		this.remove(mijnTegels.get(index));
		mijnTegels.remove(index);

		// voeg de laatst verwijderde TegelGeest terug toe
		TegelGeest tgeest = mijnVerwijderdeTegelGeesten.lastElement();
		if (tgeest != null) {
			this.add(tgeest);
			this.mijnTegelGeesten.add(tgeest);
			mijnVerwijderdeTegelGeesten.remove(tgeest);
		}
		this.revalidate();
		this.repaint();
	}
}
