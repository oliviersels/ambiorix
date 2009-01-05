package ambiorix.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
/**
 * De LayoutManager voor TegelSelectieVeld.
 * @author Jens
 *
 */
public class TegelSelectieVeldLayout implements LayoutManager {
	int minH = 0;
	int minB = 100;

	@Override
	public void addLayoutComponent(String arg0, Component arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void layoutContainer(Container parent) {
		int nComps = parent.getComponentCount();
		minH = 0;

		for (int i = 0; i < nComps; i++) {
			Component c = parent.getComponent(i);
			c.setBounds(0, i * 100, 100, 100);
			minH = i * 100 + 100;

		}

	}

	@Override
	public Dimension minimumLayoutSize(Container arg0) {
		return preferredLayoutSize(arg0);
	}

	@Override
	public Dimension preferredLayoutSize(Container arg0) {
		// TODO Auto-generated method stub
		return new Dimension(minB, minH);
	}

	@Override
	public void removeLayoutComponent(Component arg0) {
		// TODO Auto-generated method stub

	}

}
