package com.leo;


import com.leo.DataType.Record;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class DisplayExpert {	
	int[] displayWidth = null;
	String title[] = null;
	SysTable st = null;
	int length = 0;
	private static DisplayExpert instance = null;
	public static DisplayExpert getSingle(){
		if(instance == null){
			instance = new DisplayExpert();			
		}
		return instance;
	}
	public void setSystable(SysTable s){
		st = s;
	}
	
	public int[] getDisplayWith(){
		return displayWidth;
	}
	
	public void setDisplayWith(int width[]){
		displayWidth = width;
	}
	
	public void setTile(String ttl[]){
		title = ttl;
	}	
	
	private DisplayExpert(){						
	}
	
	public void updateLength(){		
		title = st.getColumNames();
		String typeName[] = st.getColumTypeNames();
		displayWidth = new int[title.length];
		for(int i = 0; i < title.length; i++){
			if(typeName[i].startsWith("char")){
				String len = UtilityTool.getValueInParentheses(typeName[i]);
				displayWidth[i] = Integer.parseInt(len);
			}else if(typeName[i].startsWith("varchar")){
				String len = UtilityTool.getValueInParentheses(typeName[i]);
				displayWidth[i] = Integer.parseInt(len);
			}else if(typeName[i].startsWith("byte")){
				displayWidth[i] = 4;			
			}else if(typeName[i].startsWith("short")){
				displayWidth[i] = 6;			
			}else if(typeName[i].startsWith("int")){
				displayWidth[i] = 11;			
			}else if(typeName[i].startsWith("long")){
				displayWidth[i] = 21;
			}else if(typeName[i].startsWith("float")){
				displayWidth[i] = 15;			
			}else if(typeName[i].startsWith("double")){
				displayWidth[i] = 24;
			}else if(typeName[i].startsWith("datetime")){
				displayWidth[i] = 20;				
			}else if(typeName[i].startsWith("date")){
				displayWidth[i] = 11;
			}			
		}		
		length =0;
		for(int i = 0; i < title.length; i++){
			displayWidth[i] = Math.max(displayWidth[i], title[i].length())+2;
			length +=displayWidth[i];
		}
		length += title.length;
	}
	
	
	
	public void displayTitleLine(){
		display("+");
		for(int i = 0; i < displayWidth.length; i++){
			diplayMiusAdd(displayWidth[i]);
		}
		enter();
	}
	public void displayTitle(){		
		displayTitleLine();
		display("|");
		for(int i = 0; i < displayWidth.length; i++){
			display(title[i],displayWidth[i]);
		}
		enter();
		displayTitleLine();				
	}	
	
	
	private void diplayMiusAdd(int size){
		String cont = "";
		for(int i = 0; i < size; i++){
			cont += "-";
		}
		cont += "+";
		display(cont);		
	}
	
	public void display(String cont, int width){
		String formula = "%"+Integer.toString(width)+"s|";
		System.out.printf(formula,cont);
	}
	
	private void display(String cont){
		System.out.printf(cont);
	}	
	public void begin(){
		display("|");
	}
	public void enter(){
		display("\n");
	}
	
	public void displayRecord(Record r){
		display("|");
		for(int i = 0; i < displayWidth.length; i++){
			display(r.getDataTypes().get(i).toString(),displayWidth[i]);
		}
		enter();
	}	
}
