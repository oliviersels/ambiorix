package ambiorix.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
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
import ambiorix.spelbord.Tegel;
import ambiorix.spelbord.TegelBasis;
import ambiorix.util.Punt;

public class Tegel_Gui extends JComponent implements MouseListener, TegelVeldComponent{
	
	public void update(Graphics g){paint(g);}
	private BufferedImage mijnAfbeelding = null;
	private Vector<TegelKlikLuisteraar> tegelKlikLuisteraars;
	private Vector<Punt> gebiedenTeTekenen;
	private Vector<Pion> mijnPionnen;// TODO moet eventueel in 1 lijst / klasse
	private Vector<Punt> mijnPionPunten;
	private TegelBasis tegel;
	private Punt p;
	
	public Tegel_Gui(TegelBasis t)
	{
		String fileNaam = t.getType().getID();
		try {
			
			mijnAfbeelding = ImageIO.read(new File(fileNaam + ".jpg"));
		} catch (IOException e) {
			System.out.println(fileNaam + " niet gevonden in Tegel_Gui");
		}
		this.addMouseListener(this);
		tegelKlikLuisteraars = new Vector<TegelKlikLuisteraar>();
		gebiedenTeTekenen = new Vector<Punt>();
		mijnPionnen = new Vector<Pion>();
		mijnPionPunten = new Vector<Punt>();
		tegel = t;
		p = new Punt(0,0);
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
		AlphaComposite ap = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .3f);
		g2.setComposite(ap);
		g2.setColor(Color.red);
		float tb = tegel.getTerreinBreedte();
		float th = tegel.getTerreinHoogte();
		for (Punt p: gebiedenTeTekenen)
		{
			//g2.drawRect((rec.width + 1)/tb * p.getX(), (rec.height + 1)/tb * p.getY(), 98/tb, 98/tb);
			g2.fillRect((int)Math.round(((float)rec.width)/tb * (float)p.getY()),
					(int)Math.round((float)(rec.height)/th * (float)p.getX()), (int)Math.ceil(100.0f/tb), (int)Math.ceil(100.0f/th));
		}
		
		AlphaComposite ap2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
		g2.setComposite(ap2);
		
		int aantalPionnen = this.mijnPionnen.size();
		for(int i = 0; i < aantalPionnen ; i++)
		{
			BufferedImage mijnPionImg = null;
			// TODO image loader maken
			try {
				
				mijnPionImg = ImageIO.read(new File("pion.png"));
			} catch (IOException e) {
				System.out.println("pion.png" + " niet gevonden in Tegel_Gui");
			}
			
			Punt p = mijnPionPunten.get(i);
			g2.drawImage(mijnPionImg, (int)Math.round(((float)rec.width)/tb * (float)p.getY()),
					(int)Math.round((float)(rec.height)/th * (float)p.getX()), (int)Math.ceil(100.0f/tb), (int)Math.ceil(100.0f/th), null);
		}
	}
	
	public void zetPos(int x, int y)
	{
		p.setX(x);
		p.setY(y);
		this.setBounds(x*100, y*100, 100, 100);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Iterator<TegelKlikLuisteraar> it = tegelKlikLuisteraars.iterator();
		TegelGebeurtenis tg = new TegelGebeurtenis();
		tg.tegel = this.tegel;
		tg.tegelX = p.getX();
		tg.tegelY = p.getY();
		tg.tegelPixelX = e.getX();
		tg.tegelPixelY = e.getY();
		while(it.hasNext())
		{
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
	public Punt getPos(){
		return p;
	}
	
	public TegelBasis getTegel()
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

	public void addGebiedTeTekenen(Punt positie) {
		this.gebiedenTeTekenen.add(positie);
	}
	
	public void wisGebiedenTeTekenen()
	{
		gebiedenTeTekenen.clear();
	}

	public void voegPionToe(Pion pion, Punt pos) {
		mijnPionnen.add(pion);
		mijnPionPunten.add(pos);
		
	}

	@Override
	public Punt geefPositie() {
		return p;
	}
	
	
}
