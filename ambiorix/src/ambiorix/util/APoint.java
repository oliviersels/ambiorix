package ambiorix.util;

import java.awt.Point;

public class APoint
{
	/*public APoint(int x, int y)
	{
		super(x,y);
	}
	
	public int getArrayCoord()
	{
		return x + y;
	}*/
	
	public static int getArrayCoord(Point p, int length)
	{
		
		int position = ( ( (int) Math.sqrt(length)) * ((int) p.getY())) + ((int) p.getX());
		
		if( (position < 0) || (position > length) )
			return -1;
		else
			return position;
		
	}
}
