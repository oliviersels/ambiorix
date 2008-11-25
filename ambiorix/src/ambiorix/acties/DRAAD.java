package ambiorix.acties;

public class DRAAD extends Thread {
	
	public DRAAD(ActieBestuurder ab, int mode) {
		this.mode = mode;
		this.ab = ab;
		start();
	}
	
	public void run() {	
		try {
			
		if(mode==1)
			while(true) {			
				sleep(250);
				System.out.println("THREAD -> undo");
				ab.undo();
			}
		else if (mode==2)
			while(true) {
				sleep(2000);
				System.out.println("THREAD -> legTegel");
			}
		else if (mode==3) 
			while(true) {
//				System.out.println("---- DONE ----");
			}
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ActieBestuurder ab;
	int mode;
}
