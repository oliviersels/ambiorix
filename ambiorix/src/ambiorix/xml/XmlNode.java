package ambiorix.xml;
import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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

	public XmlNode getElementByTagName( String name )
	{
		Vector<XmlNode> list = getElementsByTagName(name);
		if(list != null)
			return list.get(0);
		else
		{
			System.out.println("XmlNode::getElementByTagName : LEGE NODE TERUGGEVEN");
			return null;
		}
	}
	
	public Vector<XmlNode> getElementsByTagName( String name )
	{
		Vector<XmlNode> output = new Vector<XmlNode>();
		
		NodeList children = node.getChildNodes();
		
		for(int i = 0; i < children.getLength(); i++)
		{
			//System.out.println( children.item(i).getNodeName() + " == " + name  );
			if( children.item(i).getNodeName() == name )
				output.add( new XmlNode(children.item(i)) );
		}
		
		if( output.size() == 0)
			output = null;
		
		return output;
	}
	
	public static XmlNode fromString(String input)
	{
		XmlNode output = null;
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        //factory.setValidating(true);
	        factory.setNamespaceAware(false);
	
	        DocumentBuilder builder = factory.newDocumentBuilder();
	
	
	        byte currentXMLBytes[] = input.getBytes();
	        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(currentXMLBytes);
	        
	
	        Document root = builder.parse(byteArrayInputStream);
	        
	        output = new XmlNode( root );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return output;
	}
	
	public String getValue()
	{
		//System.out.println("NodeValue : " + node.getTextContent() );
		return node.getTextContent();
	}
}
