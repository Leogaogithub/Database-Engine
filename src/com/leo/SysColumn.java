package com.leo;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class SysColumn {
	public String Table_schema ="";
	public String Table_name ="";
	public String Column_name = "";
	public int Ordinal_position;
	public String Column_type= "";
	public String Is_nullable= "";
	public String Column_key= "";
	public SysColumn(){
		Is_nullable = "yes";
		Column_key = "";
	}
	
	void display(int width[]){
		DisplayExpert.getSingle().begin();
		DisplayExpert.getSingle().display(Table_schema,width[0]);
		DisplayExpert.getSingle().display(Table_name,width[1]);
		DisplayExpert.getSingle().display(Column_name,width[2]);
		DisplayExpert.getSingle().display(Integer.toString(Ordinal_position),width[3]);
		DisplayExpert.getSingle().display(Column_type,width[4]);
		DisplayExpert.getSingle().display(Is_nullable,width[5]);
		DisplayExpert.getSingle().display(Column_key,width[6]);
		DisplayExpert.getSingle().enter();
	}
}
