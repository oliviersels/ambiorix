package ambiorix.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JComponent;

import ambiorix.spelbord.Tegel;
import ambiorix.util.Punt;

public class TegelGeest extends JComponent implements MouseListener, TegelVeldComponent{
	private int xPos;
	private int yPos;
	private Tegel.RICHTING richting;
	private Tegel_Gui buur;//1 buur is genoeg
	private Vector <TegelGeestLuisteraar> tegelGeestLuisteraars;
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
	}
	
	public void addTegelGeestLuisteraar(TegelGeestLuisteraar tgl)
	{
		tegelGeestLuisteraars.add(tgl);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Iterator<TegelGeestLuisteraar> it = tegelGeestLuisteraars.iterator();
		TegelGeestGebeurtenis tgg = new TegelGeestGebeurtenis();
		tgg.tegel = this.buur.getTegel();
		tgg.richting = this.richting;
		while(it.hasNext())
		{
			(it.next()).geklikt(tgg);
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

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawRect(0, 0, 99, 99);
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
}
