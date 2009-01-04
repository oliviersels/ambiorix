package ambiorix.guimenus;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BestaatNogNietVenster extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;

	public BestaatNogNietVenster()
	{
		super();
		this.setTitle("Fout");
		JPanel boodSchapPanel = new JPanel();
		boodSchapPanel.add(new JLabel("Dit bestaat momenteel nog niet!"));
		JPanel okKnopPanel = new JPanel();
		JButton okKnop = new JButton("OK");
		okKnopPanel.add(okKnop);
		okKnop.addActionListener(this);
		this.add(boodSchapPanel, BorderLayout.CENTER);
		this.add(okKnop, BorderLayout.SOUTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
		this.setLocationRelativeTo(this.getOwner());
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
		this.dispose();
		
	}
	
	public static void main(String[] args) {
		BestaatNogNietVenster bsn = new BestaatNogNietVenster();
	}
}
