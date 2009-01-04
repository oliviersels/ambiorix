package ambiorix.util;

import java.awt.Point;

public class Punt {
	int x;
	int y;

	public Punt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Punt(Punt punt) {
		this.x = punt.getX();
		this.y = punt.getY();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public static int getArrayCoord(Point p, int length) {
		int position = (((int) Math.sqrt(length)) * ((int) p.getY()))
				+ ((int) p.getX());

		if ((position < 0) || (position > length))
			return -1;
		else
			return position;
	}

	public static Object[] rotateArray(Object[] array, int rotation) {
		if (rotation == 0)
			return array;
		else if ((rotation == 90) || (rotation == 180) || (rotation == 270)) {
			// 1ste van laatste rij = 0
			// die - dimensie = 1
			// die - dimensie = 2
			// 2de van laatste rij = 3
			// die - dimensie = 4
			// etc.

			Object[] output = new Object[array.length];

			int nieuweIndexTeller = 0;
			int aantalRijen = (int) Math.sqrt(array.length);

			int elementInLaatsteRij = (aantalRijen - 1) * aantalRijen; // eerste
																		// in
																		// laatste
																		// rij
			int oudeIndexTeller = elementInLaatsteRij;

			while (nieuweIndexTeller < array.length) {
				while (oudeIndexTeller > 0) {
					output[nieuweIndexTeller] = array[oudeIndexTeller];
					nieuweIndexTeller++;
					oudeIndexTeller -= aantalRijen;
				}

				oudeIndexTeller = ++elementInLaatsteRij;
			}

			return output;
		} else
			return null;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}

	public static Punt fromString(String input) {
		String[] delen = input.split(",");
		return new Punt(Integer.valueOf(delen[0]).intValue(), Integer.valueOf(
				delen[1]).intValue());
	}
}
