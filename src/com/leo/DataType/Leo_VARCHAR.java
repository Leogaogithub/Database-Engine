package com.leo.DataType;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class Leo_VARCHAR implements IDataType {
	String data;
	Leo_BYTE N;
	int maxlength = 0;
	public Leo_VARCHAR(){
		N = new Leo_BYTE();
	}
	@Override
	public void accept(IDataTypeVisitor v) {	
		N.accept(v);
		v.visit(this);
	}
	
	public void setMaxLength(int mxl){
		maxlength = mxl;
	}
	
	public int getMaxLength(){
		return maxlength;
	}
	public String getData(){
		return data;
	}
	
	public void setData(String sdata){
		data = sdata;
		byte n = (byte)sdata.length();
		setN(n);		
	}
	public void setN(byte n){
		N.setData(n);
	}
	
	public long getN(){		
		return N.getData();				
	}
	
	 public String toString(){
		 return data;
	 }

}
