package ambiorix.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import ambiorix.util.Punt;

public class TegelVeldLayout implements LayoutManager {
	int minHoogte = 0;
	int minBreedte = 0;

	@Override
	public void addLayoutComponent(String s, Component c) {
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return (preferredLayoutSize(parent));
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		Dimension dim = new Dimension(0, 0);
		int nComps = parent.getComponentCount();
		minBreedte = 0;
		int maxBreedte = 0;
		minHoogte = 0;
		int maxHoogte = 0;
		for (int i = 0; i < nComps; i++) {
			Component c = parent.getComponent(i);

			Rectangle rec = (c).getBounds();

			if (rec.y < minHoogte)
				minHoogte = rec.y;
			if (rec.x < minBreedte)
				minBreedte = rec.x;
			if (rec.y + rec.height > maxHoogte)
				maxHoogte = rec.y + rec.height;
			if (rec.x + rec.width > maxBreedte)
				maxBreedte = rec.x + rec.width;
		}
		Insets insets = parent.getInsets();
		dim.width = maxBreedte - minBreedte + insets.left + insets.right;
		dim.height = maxHoogte - minHoogte + insets.top + insets.bottom;
		return dim;

	}

	@Override
	public void removeLayoutComponent(Component arg0) {
		// TODO Auto-generated method stub

	}

	public void layoutContainer(Container parent) {
		int nComps = parent.getComponentCount();
		int minH = 0;
		int minB = 0;

		for (int i = 0; i < nComps; i++) {
			Component c = parent.getComponent(i);

			Punt p = ((TegelVeldComponent) c).geefPositie();
			if (p.getX() < minB)
				minB = p.getX();
			if (p.getY() < minH)
				minH = p.getY();

		}

		for (int i = 0; i < nComps; i++) {
			Component c = parent.getComponent(i);

			Rectangle rec = (c).getBounds();
			Punt p = ((TegelVeldComponent) c).geefPositie();

			c.setBounds((p.getX() - minB) * 100, (p.getY() - minH) * 100,
					rec.width, rec.height);

		}
	}

}
