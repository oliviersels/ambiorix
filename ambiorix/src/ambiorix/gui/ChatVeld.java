package ambiorix.gui;

import javax.swing.JTextArea;
/**
 * Deze klasse is een kleine aanpassing van JTextArea.
 * De voornaamste aanpassing is de automatische scroll.
 * @author Jens
 *
 */
public class ChatVeld extends JTextArea{
	private static final long serialVersionUID = 1L;
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
	 * Append is aangepast zodat hij automatisch mee scrollt.
	 */
	public void append(String text) {
		super.append(text);
		aantal_karakters += text.length();
		this.setCaretPosition(aantal_karakters);
	}
}
