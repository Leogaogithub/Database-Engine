package com.leo.DataType;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class Leo_FLOAT implements IDataType {
	private float data;
	@Override
	public void accept(IDataTypeVisitor v) {
		v.visit(this);
	}
	
	public float getData(){
		return data;
	}
	
	public void setData(float newdata){
		data = newdata;
	}
	
	 public void setData(String sdata){
		 data = Float.parseFloat(sdata);
	 }
	 
	 public String toString(){
		 return Float.toString(data);
	 }

}
