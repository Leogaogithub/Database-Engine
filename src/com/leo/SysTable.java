package com.leo;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class SysTable {
	public String Table_schema ="";
	public String tableName = "";
	public long rows = 0;
	
	Map<String, SysColumn> colums = new TreeMap<String, SysColumn>();
	Map<Integer, SysColumn> orderColums = new TreeMap<Integer, SysColumn>();
	String columNames[] = null;
	String [] columTypeNames = null;
	String [] isNulls = null;
	String [] columnKeys = null;
	
	public void addRow(){
		rows++;
	}
	
	public void updateVectors(){
		columNames = new String[orderColums.size()];
		columTypeNames= new String[orderColums.size()];
		columnKeys= new String[orderColums.size()];
		isNulls= new String[orderColums.size()];
		//be careful i-1.
		for(int i = 1; i <= orderColums.size(); i++){
			SysColumn col = orderColums.get(i);
			columNames[i-1] = col.Column_name;
			columTypeNames[i-1] = col.Column_type;
			isNulls[i-1] = col.Is_nullable;
			columnKeys[i-1] = col.Column_key;
		}
	}
	
	public String[] getColumNames(){
		if(columNames==null){
			updateVectors();
		}
		return columNames;
	}
	
	public String[] getIsNulls(){
		if(isNulls==null){
			updateVectors();
		}
		return isNulls;		
	}
	
	public String[] getColumnKeys(){
		if(columnKeys==null){
			updateVectors();
		}
		return columnKeys;			
	}
	
	public String[] getColumTypeNames(){
		if(columTypeNames==null){
			updateVectors();
		}
		return columTypeNames;		
	}
	
	public int getColumsNum(){
		return orderColums.size();
	}
	public SysTable(){
		colums = new TreeMap<String, SysColumn>();
		orderColums = new TreeMap<Integer, SysColumn>();		
		columNames = null;
		columTypeNames = null;
		isNulls = null;
		columnKeys = null;
	}
	
	public void addColumn(SysColumn column){
		colums.put(column.Column_name, column);
		orderColums.put(column.Ordinal_position, column);
	}
	
	public SysColumn getColumn(String name){
		return colums.get(name);
	}
	
	public SysColumn getColumn(int ordPos){
		return orderColums.get(ordPos);
	}
	
	public boolean isExist(String colName){
		return colums.containsKey(colName);
	}
	
	void display(int width[]){
		DisplayExpert.getSingle().begin();
		DisplayExpert.getSingle().display(Table_schema,width[0]);
		DisplayExpert.getSingle().display(tableName,width[1]);
		DisplayExpert.getSingle().display(Long.toString(rows),width[2]);		
		DisplayExpert.getSingle().enter();
	}	
}
