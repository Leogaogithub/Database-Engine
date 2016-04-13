package com.leo.DataType;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class Leo_DOUBLE implements IDataType {
	private double data;
	@Override
	public void accept(IDataTypeVisitor v) {
		v.visit(this);
	}
	
	public double getData(){
		return data;
	}
	
	public void setData(double newdata){
		data = newdata;
	}
	
	 public void setData(String sdata){
		 data = Double.parseDouble(sdata);
	 }
	 
	 public String toString(){
		 return Double.toString(data);
	 }

}
