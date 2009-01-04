package ambiorix.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
/**
 * De LayoutManager voor PionnenVeld.
 * Elke pion wordt 20 op 20 weergegeven en het venster heeft een vaste grootte.
 * @author Jens
 *
 */
public class PionnenVeldLayout implements LayoutManager {
	int Hoogte = 100;
	int Breedte = 300;
	@Override
	public void addLayoutComponent(String arg0, Component com) {

	}

	@Override
	public void layoutContainer(Container parent) {
		int nComps = parent.getComponentCount();


        for (int i = 0 ; i < nComps ; i++) {
            Component c = parent.getComponent(i);
            c.setBounds((i * 20)% this.Breedte, ((i*20) / Breedte)*20, 20, 20);
        }
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return(preferredLayoutSize(parent));
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		Dimension dim = new Dimension(Breedte, Hoogte);
        return dim;
	}

	@Override
	public void removeLayoutComponent(Component arg0) {
		
	}

}
