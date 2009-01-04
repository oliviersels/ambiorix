package ambiorix.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JComponent;

import ambiorix.spelbord.PionBasis;

/**
 * De klasse die gebruikt wordt om een pion weer te geven. Nadat de afbeelding
 * van de pion is ingeladen wordt deze aangepast. Elke pixel die volledig wit
 * is, wordt vervangen door de kleur van de speler die eigenaar is van de pion.
 * 
 * @author Jens
 * 
 */

public class Pion_Gui extends JComponent implements MouseListener {
	private static final long serialVersionUID = 1L;
	private PionBasis mijnPion;
	private BufferedImage mijnAfbeelding = null;
	private Vector<PionLuisteraar> mijnPionLuisteraars;

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	public Pion_Gui(PionBasis pion) {
		mijnPionLuisteraars = new Vector<PionLuisteraar>();
		mijnPion = pion;
		WritableRaster raster = AfbeeldingLader.geefAfbeelding(
				pion.getType().getAfbeelding()).copyData(null);
		mijnAfbeelding = new BufferedImage(AfbeeldingLader.geefAfbeelding(
				pion.getType().getAfbeelding()).getColorModel(), raster,
				AfbeeldingLader.geefAfbeelding(pion.getType().getAfbeelding())
						.isAlphaPremultiplied(), null);
		for (int y = 0; y < mijnAfbeelding.getHeight(); y++)
			for (int x = 0; x < mijnAfbeelding.getWidth(); x++) {
				if (mijnAfbeelding.getRGB(x, y) == Color.WHITE.getRGB()) {
					mijnAfbeelding.setRGB(x, y, pion.getSpeler().getKleur()
							.getRGB());
				}
			}
		this.addMouseListener(this);
	}

	public BufferedImage geefAfbeelding() {
		return this.mijnAfbeelding;
	}

	public PionBasis getPion() {
		return mijnPion;
	}

	public void voegPionLuisteraarToe(PionLuisteraar pl) {
		mijnPionLuisteraars.add(pl);
	}

	public void verwijderPionLuisteraar(PionLuisteraar pl) {
		mijnPionLuisteraars.remove(pl);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Rectangle rec = this.getBounds();
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(mijnAfbeelding, 0, 0, rec.width, rec.height, null);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		PionGebeurtenis pg = new PionGebeurtenis();
		pg.pion = this.mijnPion;
		Iterator<PionLuisteraar> it = mijnPionLuisteraars.iterator();
		while (it.hasNext()) {
			(it.next()).geklikt(pg);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
