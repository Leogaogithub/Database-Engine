package com.leo.DataType;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class DataTypeDisplayVisitor implements IDataTypeVisitor {
	
	void display(String cont, int width){
		String sWidth = Integer.toString(width);
		String format = "%"+ sWidth + "s";
		System.out.printf(format, cont);
		System.out.print("|");		
	}
	
	@Override
	public void visit(Leo_BYTE data) {
		display(data.toString(), 10);
	}

	@Override
	public void visit(Leo_CHAR data) {
		display(data.toString(), data.getN()+5);
	}

	@Override
	public void visit(Leo_DATE data) {
		display(data.toString(), 25);
	}

	@Override
	public void visit(Leo_DATETIME data) {
		display(data.toString(), 25);
	}

	@Override
	public void visit(Leo_DOUBLE data) {
		display(data.toString(), 20);
	}

	@Override
	public void visit(Leo_FLOAT data) {
		display(data.toString(), 20);
	}

	@Override
	public void visit(Leo_INT data) {
		display(data.toString(), 15);
	}

	@Override
	public void visit(Leo_LONG data) {
		display(data.toString(), 25);
	}

	@Override
	public void visit(Leo_SHORT data) {
		display(data.toString(), 10);
	}

	@Override
	public void visit(Leo_VARCHAR data) {
		display(data.toString(), data.maxlength);
	}

	@Override
	public void visit(Record data) {
		System.out.print("\n");
	}

	@Override
	public void visit(IndexTableRecord data) {
		//display(data.toString(), 10);

	}

}
