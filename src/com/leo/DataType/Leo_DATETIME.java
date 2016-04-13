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
public class Leo_DATETIME implements IDataType {
	private long data;
	//Date data = new Date();
	SimpleDateFormat ftt = new SimpleDateFormat ("yyyy-MM-dd'_'hh:mm:ss"); 
	
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
			Date d = ftt.parse(sdata);
			data = d.getTime();
		} catch (ParseException e) {			
			e.printStackTrace();
		}
	 }
	 
	 public String toString(){		  
		 Date d = new Date();
		 d.setTime(data);
		 String result = ftt.format(d);		
		 return result;
	 }

	 public void line(String con){
		 System.out.println(con);
	 }
	 
	 public static void main1(String[] args){
		 Leo_DATETIME dd = new Leo_DATETIME();
		 dd.setData("1995-08-25_13:45:27");
		 dd.line(dd.toString());		 
		 dd.setData(2995999);
		 dd.line(dd.toString());		 
	 }

}
