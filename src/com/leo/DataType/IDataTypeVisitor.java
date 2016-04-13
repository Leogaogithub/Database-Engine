package com.leo.DataType;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public interface IDataTypeVisitor {
	public void visit(Leo_BYTE data);
	public void visit(Leo_CHAR data);
	public void visit(Leo_DATE data);
	public void visit(Leo_DATETIME data);
	public void visit(Leo_DOUBLE data);
	public void visit(Leo_FLOAT data);
	public void visit(Leo_INT data);
	public void visit(Leo_LONG data);
	public void visit(Leo_SHORT data);
	public void visit(Leo_VARCHAR data);
	public void visit(Record data);	
	public void visit(IndexTableRecord data);
}
