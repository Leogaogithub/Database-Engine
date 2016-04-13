package com.leo.DataType;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class Leo_DATE implements IDataType {
	private long data;
	//Date data = new Date();
	static SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
	
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
		 try {
			Date d = ft.parse(sdata);
			data = d.getTime();
		} catch (ParseException e) {			
			e.printStackTrace();
		}
	 }
	 
	 public String toString(){		  
		 Date d = new Date();
		 d.setTime(data);
		 String result = ft.format(d);
		// System.out.println(d);
		 return result;
	 }

	 public void line(String con){
		 System.out.println(con);
	 }
	 
	 public static void main1(String[] args){
		 Leo_DATE dd = new Leo_DATE();
		 dd.setData("1995-08-22");
		 dd.line(dd.toString());
		 
		 dd.setData(1995999);
		 dd.line(dd.toString());
	 }
}
