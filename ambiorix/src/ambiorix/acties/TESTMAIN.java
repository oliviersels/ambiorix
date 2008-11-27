package ambiorix.acties;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.Vector;

import ambiorix.acties.specifiek.GeefTegel;

public class TESTMAIN {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Stack<Integer> stack = new Stack<Integer>();
		
		Deque<Integer> deque = new ArrayDeque<Integer>();
		
		deque.addFirst(1);
		deque.addFirst(2);
		deque.addFirst(3);
		deque.addFirst(4);
		deque.addFirst(4);
		deque.addFirst(4);
		deque.addFirst(4);
		deque.addFirst(4);
		
		stack.add(1);
		stack.add(2);
		stack.add(3);
		stack.add(4);
		
		for (Integer integer : deque) {
			System.out.println("integer: "+integer);
		}
		
//		try {
//			ActieBestuurder ab = new ActieBestuurder();
//			ab.start(new GeefTegel());
//			
//			DRAAD d = null;
//			d = new DRAAD(ab, 1);
//			d.join();
//			d = new DRAAD(ab, 2);
//			d.join();
//			d = new DRAAD(ab, 3);
//			d.join();
//			System.out.println("---- DONE ----");
//		} catch (InterruptedException e) {}
	}

}
