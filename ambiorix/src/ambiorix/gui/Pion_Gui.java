package ambiorix.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import ambiorix.spelbord.Pion;
import ambiorix.spelbord.PionBasis;
import ambiorix.util.Punt;

public class Pion_Gui extends JComponent implements MouseListener{
	private static final int width = 20;
	private static final int height = 20;
	private PionBasis mijnPion;
	private BufferedImage mijnAfbeelding = null;
	private Vector<PionLuisteraar> mijnPionLuisteraars;
	
	public void update(Graphics g){paint(g);}
	public Pion_Gui(PionBasis pion) {
		mijnPionLuisteraars = new Vector<PionLuisteraar>();
		mijnPion = pion;
		mijnAfbeelding = AfbeeldingLader.geefAfbeelding("pion");
		this.addMouseListener(this);
		this.setBounds(0, 0, 20, 20);
	}
	
	public PionBasis getPion() {
		return mijnPion;
	}
	
	public void voegPionLuisteraarToe(PionLuisteraar pl)
	{
		mijnPionLuisteraars.add(pl);
	}
	
	public void verwijderPionLuisteraar(PionLuisteraar pl)
	{
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
		while(it.hasNext())
		{
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
