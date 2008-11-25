package ambiorix.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.util.Vector;

public class TegelVeldLayout implements LayoutManager {
	int i = 0;
	@Override
	public void addLayoutComponent(String s, Component c) {
		System.out.println("component added: " + i);
		i++;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		return(preferredLayoutSize(parent));
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		Dimension dim = new Dimension(0, 0);
        int nComps = parent.getComponentCount();
        int minBreedte = 0;
        int maxBreedte = 0;
        int minHoogte = 0;
        int maxHoogte = 0;
        System.out.println("aantal: " + nComps);
        for (int i = 0 ; i < nComps ; i++) {
            Component c = parent.getComponent(i);
           
            Rectangle rec = ((Tegel_Gui)c).getBounds();
            
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
        System.out.println(dim + "\nminh: " + minHoogte + " maxh: " + maxHoogte + " minb: " + minBreedte + " maxb: " + maxBreedte);
        return dim;

	}

	@Override
	public void removeLayoutComponent(Component arg0) {
		// TODO Auto-generated method stub

	}
	
	
	public void layoutContainer(Container parent) {

        Insets insets = parent.getInsets();
        int maxWidth = parent.getWidth()
                       - (insets.left + insets.right);
        int maxHeight = parent.getHeight()
                        - (insets.top + insets.bottom);
        int nComps = parent.getComponentCount();


        for (int i = 0 ; i < nComps ; i++) {
            Component c = parent.getComponent(i);
           
            Rectangle rec = ((Tegel_Gui)c).getBounds();
            c.setBounds(rec);
        }
    }


}
