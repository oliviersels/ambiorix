package ambiorix.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;


public class TypeVerzameling<T>
{
	private HashMap<String, T> types = new HashMap<String, T>();	
	
	protected TypeVerzameling()
	{
		
	}
	
	public void registreerType(T type)
	{
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
	
	public Vector<String> getTypes()
	{
		Vector<String> output = new Vector<String>();
		
		Set<String> keys = types.keySet();
		for( String key : keys )
			output.add(key);
		
		return output;
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
