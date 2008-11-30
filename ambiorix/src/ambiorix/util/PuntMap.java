package ambiorix.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 * Class die eigenlijk een HashMap implementeert.
 * 
 * Probleem met een hashmap is dat de objecten gebruikt als key hetzelfde moeten zijn.
 * Maar vaak willen we dat de key gezet wordt op waarden binnen de keys, ipv de keyinstanties zelf.
 * 
 * Deze PuntMap zorgt hiervoor voor de klasse punt door strings als keys te nemen voor de hashmap.
 */
public class PuntMap<T>
{
	private HashMap<String,T> map = new HashMap<String,T>();
	
	public PuntMap()
	{
		
	}
	
	public void put(Punt p, T value)
	{
		map.put(p.toString(), value);
	}
	
	public T get(Punt p)
	{
		return map.get( p.toString() );
	}
	
	public Set<Punt> keySet()
	{
		Set<Punt> output = new HashSet<Punt>();
		
		Set<String> punten = map.keySet();
		
		for( String huidigPunt: punten )
		{
			Punt p = Punt.fromString(huidigPunt);
			output.add(p);
		}
		
		return output;
	}
	
	public boolean containsValue(T value)
	{
		return map.containsValue(value);
	}
}
