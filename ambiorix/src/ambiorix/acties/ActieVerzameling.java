package ambiorix.acties;

import java.lang.reflect.InvocationTargetException;

import ambiorix.util.TypeVerzameling;

public class ActieVerzameling extends TypeVerzameling<AbstractActie> {
	private static ActieVerzameling instantie = null;
	
	protected ActieVerzameling(){}
	
	public static ActieVerzameling getInstantie() {
		if(instantie == null)
			instantie = new ActieVerzameling();
		
		return instantie;
	}
	
	public AbstractActie getNewInstantie(String ID, Object[] parameters, Class<?>[] parameterKlassen) throws IllegalArgumentException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {		
		Class<?> klasse = getType(ID).getClass();
		return (AbstractActie) klasse.getConstructor(parameterKlassen).newInstance(parameters);
	}
}
