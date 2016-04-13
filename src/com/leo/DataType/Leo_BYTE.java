package com.leo.DataType;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class Leo_BYTE implements IDataType {
	private byte data;
	@Override
	public void accept(IDataTypeVisitor v) {
		v.visit(this);
	}
	
	public int getData(){
		return data;
	}
	
	public void setData(byte newdata){
		data = newdata;
	}
	
	 public void setData(String sdata){
		 data = Byte.parseByte(sdata);
	 }
	 
	 public String toString(){
		 return Byte.toString(data);
	 }

}
