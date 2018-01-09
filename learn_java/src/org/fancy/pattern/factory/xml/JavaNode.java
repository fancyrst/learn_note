package org.fancy.pattern.factory.xml;

import java.util.List;
import java.util.Map;

/**
 * 用JAVA Object模拟XML
 * @author yexiong
 */
public class JavaNode {
	
	/** XML节点名称 **/
	private String nodeName = null;
	
	/** XML节点值 **/	
	private String nodeValue = null;
	
	/** XML节点属性 **/
	private Map<String, String> nodeAttr = null;
	
	/** XML节点孩子节点**/
	private List<JavaNode> nodeChilds = null;	
	
	public JavaNode(String nodeName, Map<String, String> nodeAttr,
			List<JavaNode> nodeChilds) {
		this.nodeName = nodeName;
		this.nodeAttr = nodeAttr;
		this.nodeChilds = nodeChilds;
	}

	public JavaNode(String nodeName, List<JavaNode> nodeChilds) {
		this.nodeName = nodeName;
		this.nodeChilds = nodeChilds;
	}

	public JavaNode(String nodeName, String nodeValue) {
		this.nodeName = nodeName;
		this.nodeValue = nodeValue;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeValue() {
		return nodeValue;
	}

	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}

	public Map<String, String> getNodeAttr() {
		return nodeAttr;
	}

	public void setNodeAttr(Map<String, String> nodeAttr) {
		this.nodeAttr = nodeAttr;
	}

	public List<JavaNode> getNodeChilds() {
		return nodeChilds;
	}

	public void setNodeChilds(List<JavaNode> nodeChilds) {
		this.nodeChilds = nodeChilds;
	}	
}
