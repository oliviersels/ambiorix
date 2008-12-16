package ambiorix.xml;
import java.util.Collection;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class XmlNode
{
	private Node node = null;
	
	public XmlNode(Node node)
	{
		this.node = node;
	}

	public Node getChildByName( String name )
	{
		Vector<Node> list = getChildrenByName(name);
		if(list != null)
			return list.get(0);
		else
			return null;
	}
	
	public Vector<Node> getChildrenByName( String name )
	{
		Vector<Node> output = new Vector<Node>();
		
		NodeList children = node.getChildNodes();
		for(int i = 0; i < children.getLength(); i++)
		{
			if( children.item(i).getNodeName() == name )
				output.add( children.item(i) );
		}
		
		if( output.size() == 0)
			output = null;
		
		return output;
	}
	
}
