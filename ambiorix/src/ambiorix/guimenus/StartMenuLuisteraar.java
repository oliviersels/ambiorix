package ambiorix.guimenus;
/**
 * Interface die geimplementeerd moet worden als je op events in een StartMenu wil luisteren.
 * @author Jens
 *
 */
public interface StartMenuLuisteraar {
	public void nieuwSpel();

	public void laadSpel();

	public void highscores();

	public void stop();
}
