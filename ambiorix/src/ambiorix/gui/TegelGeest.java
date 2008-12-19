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

import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelBasis;
import ambiorix.spelbord.TegelType;
import ambiorix.util.Punt;

public class TegelGeest extends JComponent implements MouseMotionListener, MouseListener, TegelVeldComponent{
	private int xPos;
	private int yPos;
	private Tegel.RICHTING richting;
	private Tegel_Gui buur;//1 buur is genoeg
	private Vector <TegelGeestLuisteraar> tegelGeestLuisteraars;
	private TegelBasis teTekenenTegel = null;
	private boolean isRood;
	public TegelGeest(int x, int y, Tegel_Gui tg, Tegel.RICHTING richting)
	{
		tegelGeestLuisteraars = new Vector<TegelGeestLuisteraar>();
		setXPos(x);
		setYPos(y);
		this.richting = richting;
		this.setBounds(x*100, y*100, 100, 100);
		this.buur = tg;
		this.revalidate();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void addTegelGeestLuisteraar(TegelGeestLuisteraar tgl)
	{
		tegelGeestLuisteraars.add(tgl);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3 && this.teTekenenTegel != null)
		{
			int rot = this.teTekenenTegel.getRotatie();
			rot += 90;
			if (rot == 360)
				rot = 0;
			this.teTekenenTegel.setRotatie(rot);
			this.repaint();
			this.revalidate();
		}else
			{
			Iterator<TegelGeestLuisteraar> it = tegelGeestLuisteraars.iterator();
			TegelGeestGebeurtenis tgg = new TegelGeestGebeurtenis();
			tgg.tegel = this.buur.getTegel();
			tgg.richting = this.richting;
			while(it.hasNext())
			{
				(it.next()).geklikt(tgg);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.teTekenenTegel = null;
		this.repaint();
		this.revalidate();
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawRect(0, 0, 99, 99);
		if(teTekenenTegel != null)
		{
			int rot = teTekenenTegel.getRotatie();
			g2.rotate(Math.toRadians(rot), 50, 50);
			AlphaComposite ap = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f);
			g2.setComposite(ap);
			g2.drawImage(AfbeeldingLader.geefAfbeelding(teTekenenTegel.getType().getID())
					, 1, 1, 98, 98, null);
		}
		if(isRood)
		{
			AlphaComposite ap = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f);
			g2.setComposite(ap);
			g2.setColor(Color.red);
			g2.fillRect(1, 1, 99, 99);
		}
	}
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	public int getYPos() {
		return yPos;
	}
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	public int getXPos() {
		return xPos;
	}

	@Override
	public Punt geefPositie() {
		return new Punt(xPos, yPos);
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		Iterator<TegelGeestLuisteraar> it = tegelGeestLuisteraars.iterator();
		TegelGeestGebeurtenis tgg = new TegelGeestGebeurtenis();
		tgg.tegel = this.buur.getTegel();
		tgg.richting = this.richting;
		tgg.tegelGeest = this;
		while(it.hasNext())
		{
			(it.next()).bewogen(tgg);
		}
	}

	public void zetAfbeelding(TegelBasis t) {
		this.teTekenenTegel = t;
		this.repaint();
		this.revalidate();
	}

	public void zetRood(boolean b) {
		isRood =b;
		this.repaint();
		this.revalidate();
	}
}
