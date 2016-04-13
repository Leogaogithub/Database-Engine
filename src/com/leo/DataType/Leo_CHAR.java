package com.leo.DataType;

import java.util.Vector;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class Leo_CHAR implements IDataType {
	byte data[] = null;
	int N = 0;//char(n)
	@Override
	public void accept(IDataTypeVisitor v){		
		v.visit(this);
	}
	
	void setN(int n){
		N = n;
	}
	
	int getN(){
		return N;
	}
	
	public byte[] getData(){
		return data;
	}
	
	public void setData(String sdata){
		 data = new byte[N];
		 data[N-1] = '\n';
		 for(int i = 0; i< sdata.length(); i++){
			 data[i] = (byte)sdata.charAt(i);
		 }		 
		 for(int i = sdata.length(); i < N-1; i++){
			 data[i] = '\0';
		 }
	 }
	
	public void setData(Vector<Byte> sdata){
		 N = sdata.size();
		 data = new byte[N];
		 data[N-1] = '\n';
		 for(int i = 0; i< sdata.size(); i++){
			 data[i] = (byte) sdata.get(i).byteValue();
		 }		
		 data[N-1] = '\n';
	 }
	
	public String toString(){
		String result = "";
		int i = 0;
		while(data[i] != '\n' && data[i] !='\0'){
			result += (char)data[i];
			//System.out.print(data[i]);
			i++;
		}		
		return result;
	}
}
