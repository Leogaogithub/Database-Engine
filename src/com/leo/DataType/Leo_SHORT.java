package com.leo.DataType;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class Leo_SHORT implements IDataType {
	private short data;
	@Override
	public void accept(IDataTypeVisitor v) {
		v.visit(this);
	}
	
	public int getData(){
		return data;
	}
	
	public void setData(short newdata){
		data = newdata;
	}
	
	 public void setData(String sdata){
		 data = Short.parseShort(sdata);
	 }
	 
	 public String toString(){
		 return Short.toString(data);
	 }
	 

}
