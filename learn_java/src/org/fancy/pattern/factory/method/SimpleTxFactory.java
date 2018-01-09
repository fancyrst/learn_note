package org.fancy.pattern.factory.method;

import org.fancy.pattern.factory.abstrac.Factory;

/**
 * 工厂方法模式
 * @author Administrator
 *
 */
public class SimpleTxFactory implements Factory {
	
	public TxBase createTx(String txCode) throws Exception {
		try {
			@SuppressWarnings("rawtypes")
			Class txClass = Class.forName("org.sam.factory.method.Tx"+txCode);
			TxBase txBase = (TxBase)txClass.newInstance();		
			return txBase;
		} catch (ClassNotFoundException cne) { 
			throw cne;
		} catch (Exception e) {
			throw e;
		}		
	}
}
