package ambiorix.spelbord;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class TypeVerzameling<T>
{
	private HashMap<String, T> types = new HashMap<String, T>();	
	
	protected TypeVerzameling()
	{
		
	}
	
	public void registreerType(T type)
	{
		//TODO : check of ie nog niet bestaat mss ? om ervoor te zorgen dat niet zomaar kan overschreven worden
		types.put(( (Type) type).getID(), type);
	}
	
	/*
	 * Geeft het gevraagde type terug als gevonden, anders null 
	 */
	public T getType(String ID)
	{
		return ((T) types.get(ID));
	}
	
	public T getType( Type type )
	{
		return ((T) types.get( type.getID() ));
	}
	
	public void maakLeeg()
	{
		//Set<String> keys = types.keySet();
		
		Collection<T> c = types.values();
		Iterator<T> it = c.iterator();
		
		while(it.hasNext())
		{
			it.remove();
			it.next();
		}
		
		types = new HashMap<String, T>();
	}
}
