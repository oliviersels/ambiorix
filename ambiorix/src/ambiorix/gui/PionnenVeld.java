package ambiorix.gui;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import ambiorix.spelbord.Pion;

public class PionnenVeld extends JTable {
	private Vector<Pion_Gui> mijnPionnen;
	
	public PionnenVeld() {
		mijnPionnen = new Vector<Pion_Gui>();
		this.setLayout(new TegelVeldLayout());
	}
	
	public void voegPionToe(Pion pion)
	{
		Pion_Gui nieuwePion= new Pion_Gui(pion);
		mijnPionnen.add(nieuwePion);
		this.add(nieuwePion);
		
	}
	
}
