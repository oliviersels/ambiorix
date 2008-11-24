package ambiorix.gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tegel_Gui {
	private int rotatie = 0;
	int posX = 0;
	int posY = 0;
	private BufferedImage mijnAfbeelding = null;
	private BufferedImage mijnGeroteerdeAfbeelding = null;
	public Tegel_Gui()
	{
		try {
			mijnAfbeelding = ImageIO.read(new File("testtegel.jpg"));
		} catch (IOException e) {
			System.out.println("opgegeven image niet gevonden in Tegel_Gui");
		}
		mijnGeroteerdeAfbeelding = mijnAfbeelding;
	}
	public void zetPosX(int x)
	{
		posX = x;
	}
	public void zetPosY(int y)
	{
		posY = y;
	}
	public void zetPos(int x, int y)
	{
		posX = x;
		posY = y;
	}
	public void teken(Graphics g)
	{
		g.drawImage(mijnGeroteerdeAfbeelding, posX * 98 + 49, posY * 98 + 49, null);
	}
	
}
