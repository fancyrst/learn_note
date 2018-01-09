package org.fancy.pattern.factory.method;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fancy.pattern.factory.xml.JavaNode;

/**
 * 具体产品类：交易P100
 * @author yexiong
 *
 */
public class TxP100 extends TxBase {

	@Override
	public JavaNode getTxNode(String txCode) throws Exception {
		if (txCode == null || "".equals(txCode)) 
			throw new IllegalArgumentException("txCode is empty!");
		
		List<JavaNode> nodeChilds = new ArrayList<JavaNode>();
		nodeChilds.add(new JavaNode("P100AcName", "中石油"));
		nodeChilds.add(new JavaNode("P100Ac", "01223934949323"));
		
		Map<String, String> nodeAttr = new LinkedHashMap<String, String>();
		nodeAttr.put("id", "P100");
		nodeAttr.put("ac", "2132323");
		
		return new JavaNode(txCode, nodeAttr, nodeChilds);
	}

}
