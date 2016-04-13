package com.leo;

import java.util.TreeMap;
import java.util.Map;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class SysSchemata {
	public String schemaName= "";
	public Map<String,SysTable> tables;
	public SysSchemata(){
		tables = new TreeMap<String,SysTable>();
	}
	
	public void addTable(SysTable t){
		tables.put(t.tableName, t);
	}
	
	public SysTable getTable(String name){
		return tables.get(name);
	}
	
	public void removeTable(String tbname){
		SysTable st = tables.remove(tbname);
		String colNames[] = st.getColumNames();
		for(int i = 0; i < colNames.length; i++){
			String fileName = generateIndexName(schemaName,tbname,colNames[i]);
			PermanentController.getSingle().removeAndDeleteFile(fileName);
		}
		String tbFN = generateTableFileName(tbname);
		PermanentController.getSingle().removeAndDeleteFile(tbFN);
	}
	
	private String generateIndexName(String schNm, 
			String tbNm, String idxNm){		
		return schNm + "."+ tbNm +"."+idxNm+".ndx";
	}
	
	private String generateTableFileName(String tbname){
		return schemaName + "."+ tbname +".tbl";
	}
	
	public boolean isExist(String name){
		return tables.containsKey(name);
	}
	
	void display(int width[]){
		DisplayExpert.getSingle().begin();
		DisplayExpert.getSingle().display(schemaName,width[0]);
		DisplayExpert.getSingle().enter();
	}
	
}
