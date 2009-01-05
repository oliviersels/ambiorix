package ambiorix.guimenus;
/**
 * Interface met de functies volgende() en vorige().
 * Dient om kliks op te vangen op de 2 onderste knoppen in sommige menus.
 * @author Jens
 *
 */
public interface MenuLuisteraar {
	public void volgende();

	public void vorige();
}
