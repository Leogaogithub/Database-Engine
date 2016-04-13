package com.leo;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class SysSchemaManager {
private static SysSchemaManager instance = null;
	Map<String, SysSchemata> sysSchematas;
	String currentSchemaName = "";
	//String currentTableName= "";
	Table currentTable = null;
	
	public String getCurrentSchemaName(){
		return currentSchemaName;
	}
	public static SysSchemaManager getSingle(){
		if(instance == null){
			instance = new SysSchemaManager();
		}
		return instance;
	}
	public SysSchemata getSysSchema(String name){
		return sysSchematas.get(name);
	}
	
	public SysTable getSysTable(String schName, String tbName){
		SysSchemata sch = getSysSchema(schName);
		SysTable tb = sch.getTable(tbName);
		return tb;
	}
	public String[] getTableColumNames(String schName, String tbName){		
		SysTable tb = getSysTable(schName, tbName);		 
		return tb.getColumNames();
	}
	
	public int getTableColumNum(String schName, String tbName){		
		SysTable tb = getSysTable(schName, tbName);		 
		return tb.getColumsNum();
	}
	
	private SysSchemaManager(){
		currentSchemaName = "information_schema";
		//currentTableName = null;
		sysSchematas = SysSchemaLoader.getSingle().getSysSchematas();
	}
	
	public void showSchemas(){
		Set set = sysSchematas.entrySet();		
		Iterator i = set.iterator();		 
		while(i.hasNext()) {
		     Map.Entry me = (Map.Entry)i.next();
		     String schName = (String) me.getKey();
		     line(schName);		     		     
		}
	}
	
	public void showSelectFromSchemas(){
		SysTable st = getSysTable("information_schema", "columns");		
		String attrTyps[] = st.getColumTypeNames();		
		DisplayExpert.getSingle().setSystable(st);
		DisplayExpert.getSingle().updateLength();
		DisplayExpert.getSingle().displayTitle();		
		int displayWith[] = DisplayExpert.getSingle().getDisplayWith();
		Set set = sysSchematas.entrySet();		
		Iterator i = set.iterator();		 
		while(i.hasNext()) {
		     Map.Entry me = (Map.Entry)i.next();
		     String schName = (String) me.getKey();
		     line(schName);		     		     
		}		
		DisplayExpert.getSingle().displayTitleLine();
	}
	
	public void showTables(){
		SysSchemata sch = sysSchematas.get(currentSchemaName);		
		 Set tblSet = sch.tables.entrySet();		     		
		 Iterator iTbl = tblSet.iterator();
		 while(iTbl.hasNext()) {
			 Map.Entry tblEn = (Map.Entry)iTbl.next();
			 String tblName =  ((SysTable)tblEn.getValue()).tableName;
			 line(tblName);			 			     		     
		}
	}
	
	public void createSchema(String name){
		SysSchemata sch = TableFactory.getSingle().createSysSchemata(name);
		sysSchematas.put(name, sch);
	}
	
	public void createTable(String tbname, Vector<SysColumn> sysColumns){
		SysSchemata sch = getSysSchema(currentSchemaName);
		if(sch.isExist(tbname)){
			line("schema " + currentSchemaName + 
					" has a table with same name : "+ tbname+"");
			return;
		}
		SysTable t = TableFactory.getSingle().createSysTable(currentSchemaName,tbname);
		for(int i = 0; i <sysColumns.size();i++){
			 SysColumn col = sysColumns.get(i);
			if(t.isExist(col.Column_name)){
				line("table " + tbname + 
						" has a Column with same name : "+ col.Column_name+"");
				return;
			}
			t.addColumn(col);			
		}
		t.updateVectors();
		sch.addTable(t);
		createTableFile(t);
	}
	

	public void createTableFile(SysTable t){
		String tableName = t.tableName;
		String tableFilePath = currentSchemaName+"."+ tableName;
		PermanentController.getSingle().getFile(tableFilePath + ".tbl");		
		 //cols
		Set colSet = t.colums.entrySet();		     		
		Iterator iCol = colSet.iterator();
		while(iCol.hasNext()) {
			 Map.Entry colEn = (Map.Entry)iCol.next();
			 String Column_name = ((SysColumn)colEn.getValue()).Column_name;
			 PermanentController.getSingle().getFile(tableFilePath +"."+ Column_name + ".ndx");					 
		} 		 
	}
	

	public void useSchema(String name){
		if(!isSchemaExis(name)){
			line(name + " is not existing");
			return;
		}
		currentSchemaName = name;
		line("Database changed");		
	}
	
	public void dropTable(String tname){
		if(!isTableExis(tname)){
			line(tname + " is not existing");
			return;
		}
		SysSchemata sch = getSysSchema(currentSchemaName);
		sch.removeTable(tname);
		if(currentTable != null){
			if(currentTable.isSameTable(currentSchemaName, tname)){
				currentTable = null;
			}
		}
	}
	private void lazyLoadTable(String tbname){
		if(!isTableExis(tbname)){
			line(tbname + " is not exist in " + currentSchemaName);
			return;
		}
		if(currentTable == null){
			currentTable = new Table(currentSchemaName, tbname);
			currentTable.loadIndxTables();
		}
		if(!currentTable.isSameTable(currentSchemaName, tbname)){
			currentTable.saveIndxTables();
			currentTable = new Table(currentSchemaName, tbname);
			currentTable.loadIndxTables();
		}
	}
	
	public void insertIntoValues(String tbname, String values[]){
		if(!isTableExis(tbname)){
			line(tbname + " is not exist in " + currentSchemaName);
			return;
		}
		if(getTableColumNum(currentSchemaName,tbname) != values.length){			
			line("values number is not matching");
			return;
		}
		lazyLoadTable(tbname);
		
		currentTable.insertIntoValues(values);
		
	}

	
	public void selectFromWhere(String tbNm, String clNm, 
			String op, String vl){
		if(!isTableExis(tbNm)){
			line(tbNm + " is not exist in schema " + currentSchemaName);
			return;
		}
		if(!getSysTable(currentSchemaName,tbNm).isExist(clNm)){
			line(clNm + " is not exist in table " + tbNm);
			return;
		}		
		lazyLoadTable(tbNm);
		currentTable.getIndexTableRecordsByCondition(clNm,op,vl);
	}
	
	private String getTableFileName(String tableName){
		String fileName = "";
		fileName = currentSchemaName+"."+tableName+".tbl";
		return fileName;
	}
	
	public boolean isSchemaExis(String schName){
		return sysSchematas.containsKey(schName);
	}
	
	public boolean isTableExis(String tbName){
		SysSchemata sch = getSysSchema(currentSchemaName);		
		return sch.isExist(tbName);
	}
	
	public boolean isColumExis(String schName, String tbName, String cN){
		SysTable st = getSysTable(schName, tbName);
		return st.isExist(cN);
	}
	public boolean isTableExis(String schName, String tbName){
		if(isSchemaExis(schName)){
			SysSchemata sch = getSysSchema(schName);
			if(sch.isExist(tbName)){
				return true;
			}
		}
		return false;
	}
	
	public void line(String con){
		System.out.println(con);
	}
	
	public void saveAllFile(){
		SysSchemaLoader.getSingle().saveAllSystemfile();
		if(currentTable != null){
			currentTable.saveIndxTables();
		}
	}
}
