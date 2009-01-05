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
/**
 * Geeft een uitbreiding weer.
 * Bij een klik selecteerd deze zichzelf.
 * Indien men met de muis over dit venster komt, stuurt het een event 
 * uit, zodat men de beschrijving kan weergeven.
 * @author Jens
 *
 */
public class Uitbreiding_Gui extends JPanel implements MouseListener {
	private static final long serialVersionUID = -7890105708873496398L;

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

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}
}
