package com.leo;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public interface ITableVisitor {	
	public void visit(Table t);
	public void visit(IndexTable t);
	public void visit(InforTableReadWriter t);
}
