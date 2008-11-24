package ambiorix.gui;

import javax.swing.JTextArea;

public class ChatVeld extends JTextArea{
	boolean eerste = true;
	ChatVeld()
	{
	}
	public void voegRegelToe(String str)
	{
		if(eerste == true)
		{
			eerste = false;
			this.append(str);
		}else
			this.append("\n" + str);
		
	}
}
