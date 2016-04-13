package com.leo.DataType;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class Leo_LONG implements IDataType {
	private long data;
	@Override
	public void accept(IDataTypeVisitor v) {
		v.visit(this);
	}
	public long getData(){
		return data;
	}
	
	public void setData(long newdata){
		data = newdata;
	}
	
	 public void setData(String sdata){
		 data = Long.parseLong(sdata);
	 }
	 
	 public String toString(){
		 return Long.toString(data);
	 }
}
