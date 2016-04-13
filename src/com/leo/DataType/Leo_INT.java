package com.leo.DataType;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class Leo_INT implements IDataType {
	private int data;
	@Override
	public void accept(IDataTypeVisitor v) {
		v.visit(this);
	}
	public Leo_INT(int d){
		data = d;
	}
	public Leo_INT(){
		
	}
	public int getData(){
		return data;
	}
	
	public void setData(int newdata){
		data = newdata;
	}
	
	 public void setData(String sdata){
		 int in = Integer.parseInt(sdata);
		 setData(in);
	 }
	 
	 public String toString(){
		 return Integer.toString(data);
	 }

}
