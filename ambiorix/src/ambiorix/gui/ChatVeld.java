package ambiorix.gui;

import javax.swing.JTextArea;

public class ChatVeld extends JTextArea{
	private int aantal_karakters = 0;
	boolean eerste = true;
	ChatVeld()
	{
		super();
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
	/**
	 * Append is aangepast zodat hij automatisch mee scrolled.
	 */
	public void append(String text) {
		super.append(text);
		aantal_karakters += text.length();
		this.setCaretPosition(aantal_karakters);
	}
}
