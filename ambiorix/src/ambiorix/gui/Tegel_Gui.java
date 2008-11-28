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

import ambiorix.spelbord.Tegel;

public class Tegel_Gui extends JComponent implements MouseListener{
	
	public void update(Graphics g){paint(g);}
	private int rotatie = 0;
	private BufferedImage mijnAfbeelding = null;
	private BufferedImage mijnGeroteerdeAfbeelding = null;
	private Vector<TegelKlikLuisteraar> tegelKlikLuisteraars;
	private Tegel tegel;
	private int xPos, yPos;
	public Tegel_Gui(Tegel t)
	{
		try {
			mijnAfbeelding = ImageIO.read(new File("testtegel.jpg"));
		} catch (IOException e) {
			System.out.println("opgegeven image niet gevonden in Tegel_Gui");
		}
		mijnGeroteerdeAfbeelding = mijnAfbeelding;
		this.addMouseListener(this);
		tegelKlikLuisteraars = new Vector<TegelKlikLuisteraar>();
		tegel = t;
		
		
		//tegel.getTerrein()[1][2].getID() = "TerreinType_Gras"
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Rectangle rec = this.getBounds();
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(mijnGeroteerdeAfbeelding, 1, 1, rec.width-2, rec.height-2, null);
	}
	
	public void zetPos(int x, int y)
	{
		xPos = x;
		yPos = y;
		this.setBounds(x*100, y*100, 100, 100);
		this.revalidate();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Iterator<TegelKlikLuisteraar> it = tegelKlikLuisteraars.iterator();
		TegelGebeurtenis tg = new TegelGebeurtenis();
		tg.tegel = this.tegel;
		Rectangle rec = this.getBounds();
		tg.tegelX = xPos;
		tg.tegelY = yPos;
		tg.tegelPixelX = e.getX();
		tg.tegelPixelY = e.getY();
		while(it.hasNext())
		{
			(it.next()).geklikt(tg);
		}
		
	}
	
	public int getXPos() {
		return xPos;
	}

	public void setXPos(int pos) {
		xPos = pos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setYPos(int pos) {
		yPos = pos;
	}
	
	public Tegel getTegel()
	{
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
	
	public void addTegelKlikLuisteraar(TegelKlikLuisteraar tkl)
	{
		
		tegelKlikLuisteraars.add(tkl);
	}
}
