package ambiorix.guimenus;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ambiorix.uitbreidingen.Uitbreiding;

public class Uitbreiding_Gui extends JPanel implements MouseListener {
	private Vector<UitbreidingLuisteraar> uitbreidingLuisteraars = new Vector<UitbreidingLuisteraar>();

	private Uitbreiding uitbreiding;
	private JLabel mijnLabel;
	private ImageIcon mijnIcoontje;
	private boolean geselecteerd = false;

	public Uitbreiding_Gui(Uitbreiding uitbreiding) {
		super(new GridLayout(0, 1));
		this.uitbreiding = uitbreiding;

		BufferedImage nieuweAfbeelding = null;
		try {
			nieuweAfbeelding = ImageIO.read(new File(uitbreiding
					.getAfbeelding()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mijnIcoontje = new ImageIcon(nieuweAfbeelding);

		mijnLabel = new JLabel(uitbreiding.getNaam(), mijnIcoontje,
				SwingConstants.CENTER);
		mijnLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		mijnLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		mijnLabel.setForeground(Color.black);
		this.add(mijnLabel);
		this.addMouseListener(this);
	}

	public void toggleGeselecteerd() {
		if (!geselecteerd) {
			mijnLabel.setForeground(Color.green);
			geselecteerd = true;
		} else {
			mijnLabel.setForeground(Color.black);
			geselecteerd = false;
		}
	}

	public boolean isGeselecteerd() {
		return geselecteerd;
	}

	public String getUitbreidingID() {
		return this.uitbreiding.getID();
	}

	public Uitbreiding getUitbreiding() {
		return this.uitbreiding;
	}

	public void VoegUitbreidingLuisteraarToe(UitbreidingLuisteraar ul) {
		this.uitbreidingLuisteraars.add(ul);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		toggleGeselecteerd();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		for (UitbreidingLuisteraar ul : this.uitbreidingLuisteraars) {
			ul.muisBinnenKomst(this);
		}

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
