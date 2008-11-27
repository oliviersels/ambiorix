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
//				ab.undo();
			}
		else if (mode==2)
			while(true) {
				sleep(1000);
				ab.legTegel();
			}
		else if (mode==3) 
			while(true) {
				sleep(1000);
				ab.zetPion();
			}
		else if (mode==4) 
			while(true) {
				sleep(1000);
				ab.volgendeBeurt();
			}
		
		else if(mode==0) {			
			while(true) {
				sleep(10000);
			}
		}
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ActieBestuurder ab;
	int mode;
}
