package ambiorix.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JComponent;

import ambiorix.spelbord.PionBasis;
import ambiorix.spelbord.TegelBasis;
import ambiorix.util.Punt;

public class Tegel_Gui extends JComponent implements MouseListener,
		MouseMotionListener, TegelVeldComponent {

	private static final long serialVersionUID = 1898186744968544521L;

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	private BufferedImage mijnAfbeelding = null;
	private Vector<TegelLuisteraar> tegelKlikLuisteraars;
	private Vector<Punt> gebiedenTeTekenen;
	private Vector<Pion_Gui> mijnPionnen;
	private Vector<Punt> mijnPionPunten;
	private TegelBasis tegel;
	private Punt p;

	public Tegel_Gui(TegelBasis t) {
		String fileNaam = t.getType().getAfbeelding();
		mijnAfbeelding = AfbeeldingLader.geefAfbeelding(fileNaam);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		tegelKlikLuisteraars = new Vector<TegelLuisteraar>();
		gebiedenTeTekenen = new Vector<Punt>();
		mijnPionnen = new Vector<Pion_Gui>();
		mijnPionPunten = new Vector<Punt>();
		tegel = t;
		p = new Punt(0, 0);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Rectangle rec = this.getBounds();
		Graphics2D g2 = (Graphics2D) g;
		int rot = tegel.getRotatie();
		g2.rotate(Math.toRadians(rot), 50, 50);
		g2.drawImage(mijnAfbeelding, 0, 0, rec.width, rec.height, null);
		g2.rotate(-Math.toRadians(rot), 50, 50);
		AlphaComposite ap = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				.3f);
		g2.setComposite(ap);
		g2.setColor(Color.red);
		float tb = tegel.getTerreinBreedte();
		float th = tegel.getTerreinHoogte();
		for (Punt p : gebiedenTeTekenen) {
			// g2.drawRect((rec.width + 1)/tb * p.getX(), (rec.height + 1)/tb *
			// p.getY(), 98/tb, 98/tb);
			g2.fillRect(Math.round((rec.width) / tb * p.getY()), Math
					.round((rec.height) / th * p.getX()), (int) Math
					.ceil(100.0f / tb), (int) Math.ceil(100.0f / th));
		}

		AlphaComposite ap2 = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 1.0f);
		g2.setComposite(ap2);

		int aantalPionnen = this.mijnPionnen.size();
		for (int i = 0; i < aantalPionnen; i++) {
			BufferedImage mijnPionImg = null;
			// TODO image loader maken

			mijnPionImg = mijnPionnen.get(i).geefAfbeelding();
			Punt p = mijnPionPunten.get(i);
			g2.drawImage(mijnPionImg, Math.round((rec.width) / tb * p.getY()),
					Math.round((rec.height) / th * p.getX()), (int) Math
							.ceil(100.0f / tb), (int) Math.ceil(100.0f / th),
					null);
		}
	}

	public void zetPos(int x, int y) {
		p.setX(x);
		p.setY(y);
		this.setBounds(x * 100, y * 100, 100, 100);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Iterator<TegelLuisteraar> it = tegelKlikLuisteraars.iterator();
		TegelGebeurtenis tg = new TegelGebeurtenis();
		tg.tegel = this.tegel;
		tg.tegelX = p.getX();
		tg.tegelY = p.getY();
		tg.tegelPixelX = e.getX();
		tg.tegelPixelY = e.getY();
		tg.me = e;
		Punt p = new Punt((int) ((tg.tegelPixelY / 100f) * tg.tegel
				.getTerreinHoogte()), (int) ((tg.tegelPixelX / 100f) * tg.tegel
				.getTerreinBreedte()));
		tg.terrein = tg.tegel.getTerrein(p);
		while (it.hasNext()) {
			(it.next()).geklikt(tg);
		}

	}

	public int getXPos() {
		return p.getX();
	}

	public void setXPos(int x) {
		p.setX(x);
	}

	public int getYPos() {
		return p.getY();
	}

	public void setYPos(int y) {
		p.setY(y);
	}

	public Punt getPos() {
		return p;
	}

	public TegelBasis getTegel() {
		return tegel;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void addTegelKlikLuisteraar(TegelLuisteraar tkl) {

		tegelKlikLuisteraars.add(tkl);
	}

	public void addGebiedTeTekenen(Punt positie) {
		this.gebiedenTeTekenen.add(positie);
	}

	public void wisGebiedenTeTekenen() {
		gebiedenTeTekenen.clear();
	}

	public void voegPionToe(Pion_Gui pion, Punt pos) {
		mijnPionnen.add(pion);
		mijnPionPunten.add(pos);
		this.repaint();
		this.revalidate();

	}

	@Override
	public Punt geefPositie() {
		return p;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO functie maken die een tg invult
		Iterator<TegelLuisteraar> it = tegelKlikLuisteraars.iterator();
		TegelGebeurtenis tg = new TegelGebeurtenis();
		tg.tegel = this.tegel;
		tg.tegelX = p.getX();
		tg.tegelY = p.getY();
		tg.tegelPixelX = e.getX();
		tg.tegelPixelY = e.getY();
		tg.me = e;
		while (it.hasNext()) {
			(it.next()).bewogen(tg);
		}
	}

	public void verwijderPion(PionBasis pion) {
		int index = -1;
		int i = 0;
		for (Pion_Gui pg : this.mijnPionnen) {
			if (pg.getPion() == pion)
				index = i;
			i++;
		}
		if (index != -1) {
			mijnPionnen.remove(index);
			mijnPionPunten.remove(index);
			repaint();
		}
	}
}