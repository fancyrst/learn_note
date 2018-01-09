package org.fancy.pattern.factory.xml;

import java.util.List;
import java.util.Map;

public class XmlTools {

	public static String xmlHead = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	public static String getXML(JavaNode node) throws Exception {
		return xmlHead + nodeToXml(node);
	}
	
	private static String nodeToXml(JavaNode node) throws Exception
	{
		if (node == null) {
			return "";
		}
		StringBuilder xml = new StringBuilder();
		xml.append("<").append(node.getNodeName());		
		xml.append(getNodeAttr(node.getNodeAttr())).append(">");;
		
		List<JavaNode> childs = node.getNodeChilds();
		if (childs != null && childs.size()>0) {
			for(JavaNode subNode : childs) {
				xml.append(nodeToXml(subNode));
			}
		} 
		
		String nodeValue = node.getNodeValue();
		if (nodeValue != null) {
			xml.append(nodeValue);
		}
		xml.append("</").append(node.getNodeName()).append(">");
		
		
		return xml.toString();
	}
	
	private static String getNodeAttr(Map<String, String> nodeAttr) {
		if (nodeAttr == null || nodeAttr.isEmpty()) 
			return "";
		
		StringBuilder xmlAttr = new StringBuilder();
		for(Map.Entry<String, String> entry : nodeAttr.entrySet()) {
			xmlAttr.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
		}
		
		return xmlAttr.toString();
	}
}
