package ambiorix.acties;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

// kunnen dit niet zomaar doen met TypeVerzameling : dat is voor flyweights, wat deze niet zijn

public class ActieVerzameling {
	private HashMap<String, Class<AbstractActie>> types = new HashMap<String, Class<AbstractActie>>();

	protected ActieVerzameling() {

	}

	public void registreerType(Class<AbstractActie> type) {
		try {
			/*
			 * Class[] params = new Class[] {}; Object[] params2 = new
			 * Object[]{}; String ID = (String) type.getMethod("getID",
			 * params).invoke(null, params2); System.out.println(
			 * "ActieVerzameling::registreerType : id : " + ID );
			 */

			// FIXME : dit mag niet zomaar !!!! inladen met goeie reflectie obv.
			// TIjdelijk :::
			// zorgen dat de acties ook mooi voorgaande dinges overschrijven
			// overschrijven :
			String ID = type.getSimpleName();
			// van het type Lava_EchteNaam
			if (ID.lastIndexOf("_") != -1)
				ID = ID.substring(ID.lastIndexOf("_") + 1);

			System.out.println("ActieVerzameling::registreerType : id : " + ID);
			types.put(ID, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Geeft het gevraagde type terug als gevonden, anders null
	 */
	public Class<AbstractActie> getType(String ID) {
		return (types.get(ID));
	}

	public Vector<String> getTypes() {
		Vector<String> output = new Vector<String>();

		Set<String> keys = types.keySet();
		for (String key : keys)
			output.add(key);

		return output;
	}

	public void maakLeeg() {
		// Set<String> keys = types.keySet();

		Collection<Class<AbstractActie>> c = types.values();
		Iterator<Class<AbstractActie>> it = c.iterator();

		while (it.hasNext()) {
			it.remove();
			it.next();
		}

		types = new HashMap<String, Class<AbstractActie>>();
	}

	private static ActieVerzameling instantie = null;

	public static ActieVerzameling getInstantie() {
		if (instantie == null)
			instantie = new ActieVerzameling();

		return instantie;
	}

	public AbstractActie getNewInstantie(String ID, Object[] parameters,
			Class<?>[] parameterKlassen) throws IllegalArgumentException,
			SecurityException, InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Class<AbstractActie> klasse = getType(ID);
		return klasse.getConstructor(parameterKlassen).newInstance(parameters);
	}
}
