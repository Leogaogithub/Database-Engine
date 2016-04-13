package com.leo.DataType;

import java.util.Vector;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class Record implements IDataType {
	Vector<IDataType> vDataTypes = null;
	public Record(){
		vDataTypes = new Vector<IDataType>();
	}
	public Vector<IDataType> getDataTypes(){
		return vDataTypes;
	}
	@Override
	public void accept(IDataTypeVisitor v) {
		for(int i = 0; i < vDataTypes.size(); i++){
			IDataType elem= vDataTypes.get(i);
			elem.accept(v);
		}
		v.visit(this);
	}
	
	 public void setData(String sdata){
		 
	 }
	
	public void add(IDataType dataType){
		vDataTypes.add(dataType);
	}
	
	public String toString(){
		String result = "";
		for(int i = 0; i< vDataTypes.size();i++ ){
			IDataType dt  = vDataTypes.get(i);
			result += dt.toString()+"|\t";
		}
		return result;
	}

}
