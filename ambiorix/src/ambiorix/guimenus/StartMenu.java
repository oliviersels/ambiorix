package ambiorix.guimenus;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

public class StartMenu extends JFrame implements ActionListener{
	private JButton knop_nieuwSpel;
	private JButton knop_laadSpel;
	private JButton knop_bekijkHighscores;
	private JButton knop_stop;
	
	private Vector<StartMenuLuisteraar> startMenuLuisteraars = new Vector<StartMenuLuisteraar>();
	
	public StartMenu()
	{
		super("Ambiorix");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		knop_nieuwSpel = new JButton("Start spel");
		knop_nieuwSpel.setActionCommand("nieuwSpel");
		knop_nieuwSpel.addActionListener(this);
		knop_laadSpel = new JButton("Laad spel");
		knop_laadSpel.setActionCommand("laadSpel");
		knop_laadSpel.addActionListener(this);
		knop_bekijkHighscores = new JButton("Highscores");
		knop_bekijkHighscores.setActionCommand("highscores");
		knop_bekijkHighscores.addActionListener(this);
		knop_stop = new JButton("Stop");
		knop_stop.setActionCommand("stop");
		knop_stop.addActionListener(this);
	
		this.setLayout(new GridLayout(0,1));
		this.add(knop_nieuwSpel);
		this.add(knop_laadSpel);
		this.add(knop_bekijkHighscores);
		this.add(knop_stop);
		
		
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension d = new Dimension(300,300);
		this.setMinimumSize(d);
		this.setResizable(false);
		setLocationRelativeTo(getOwner());
		
	}
	
	public static void main(String args[])
	{
		StartMenu startMenu = new StartMenu();
		startMenu.setVisible(true);
	}

	public void voegStartMenuLuisteraarToe(StartMenuLuisteraar sml)
	{
		this.startMenuLuisteraars.add(sml);
	}
	public void verwijderStartMenuLuisteraarToe(StartMenuLuisteraar sml)
	{
		this.startMenuLuisteraars.remove(sml);
	}
	
	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		if("nieuwSpel".equals(e.getActionCommand()))
		{
			for(StartMenuLuisteraar sml : startMenuLuisteraars)
				sml.nieuwSpel();
		}else if("laadSpel".equals(e.getActionCommand()))
		{
			for(StartMenuLuisteraar sml : startMenuLuisteraars)
				sml.laadSpel();
		}else if("highscores".equals(e.getActionCommand()))
		{
			for(StartMenuLuisteraar sml : startMenuLuisteraars)
				sml.highscores();
		}else if("stop".equals(e.getActionCommand()))
		{
			for(StartMenuLuisteraar sml : startMenuLuisteraars)
				sml.stop();
		}
		
	}
}
