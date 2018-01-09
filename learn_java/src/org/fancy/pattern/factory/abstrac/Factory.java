package org.fancy.pattern.factory.abstrac;

import org.fancy.pattern.factory.method.TxBase;

/**
 * 抽象工厂:提供接口，用于创建相关或依赖对象的家族，而不需要明确指定具体的类。 
 * @author yexiong
 */
public interface Factory {
	
	public abstract TxBase createTx(String txCode) throws Exception ;
}
