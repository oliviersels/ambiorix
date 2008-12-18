
package ambiorix.tests;

import org.junit.Test;

import ambiorix.Systeem;

public class TestSpel {
	@Test
	public void testActies() {		
		Systeem.prepareForTests();
		Systeem.getInstantie().startGUI();
	}
}
