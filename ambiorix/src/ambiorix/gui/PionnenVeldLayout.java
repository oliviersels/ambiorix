package ambiorix.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;

public class PionnenVeldLayout implements LayoutManager {
	int Hoogte = 100;
	int Breedte = 300;
	int aantal = 0;
	@Override
	public void addLayoutComponent(String arg0, Component com) {
		aantal++;
		com.setBounds((aantal * 20)% this.Breedte, (aantal*20) / Hoogte, 20, 20);
	}

	@Override
	public void layoutContainer(Container parent) {
		int nComps = parent.getComponentCount();


        for (int i = 0 ; i < nComps ; i++) {
            Component c = parent.getComponent(i);
            c.setBounds((i * 20)% this.Breedte, (i*20) / Hoogte, 20, 20);
        }
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return(preferredLayoutSize(parent));
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		Dimension dim = new Dimension(Breedte, Hoogte);
		/*
        int nComps = parent.getComponentCount();
        minBreedte = 0;
        int maxBreedte = 0;
        minHoogte = 0;
        int maxHoogte = 0;
        for (int i = 0 ; i < nComps ; i++) {
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
        dim.width = maxBreedte - minBreedte  + insets.left + insets.right;
        dim.height = maxHoogte - minHoogte + insets.top + insets.bottom;
        */
        return dim;
	}

	@Override
	public void removeLayoutComponent(Component arg0) {
		aantal--;
		
	}

}
