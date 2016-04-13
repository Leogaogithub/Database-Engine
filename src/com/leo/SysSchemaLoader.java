package com.leo;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.leo.DataType.DataTypeFactory;
import com.leo.DataType.Record;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class SysSchemaLoader {
	Map<String, SysSchemata> sysSchematas;	
	
	private String infoSchemaFileName = "information_schema.schemata.tbl";
	private String infoSchemaTableFileName = "information_schema.table.tbl";
	private String infoSchemaColumnsFileName = "information_schema.columns.tbl";
	
	private static SysSchemaLoader instance = null;
	
	Map<String, SysSchemata> getSysSchematas(){
		return sysSchematas;
	}
	
	public static SysSchemaLoader getSingle(){
		if(instance == null){
			instance = new SysSchemaLoader();			
		}
		return instance;
	}
	
	private SysSchemaLoader(){
		sysSchematas = new TreeMap<String, SysSchemata>();		
		init();
	}
	
	private void init(){
		loadSysSchemata();
		loadSysTable();
		loadSysColumns();
	}
	
	public void saveAllSystemfile(){
		saveSysSchemata();
		saveSysTable();
		saveSysColumns();
	}
	private void saveSysSchemata(){
		InforTableReadWriter saver = TableFactory.getSingle().createInforSchemaReadWriter();				
		Set set = sysSchematas.entrySet();		
		Iterator i = set.iterator();		 
		while(i.hasNext()) {
		     Map.Entry me = (Map.Entry)i.next();
		     String schName[] = {(String) me.getKey()};
		     Record r = (Record)DataTypeFactory.getSingle().createSysSchemataRecordWithValue(schName); 
			 saver.addRecord(r);			 		     
		}	
		saver.WriteTableToFile(infoSchemaFileName);
	}		
	
	private void loadSysSchemata(){		
		InforTableReadWriter loader = TableFactory.getSingle().createInforSchemaReadWriter();				
		loader.loadTableFromFile(infoSchemaFileName);
		Vector<Record> schemas = loader.getRecords();
		for(int i = 0; i<schemas.size();i++){
			Record sch= schemas.get(i);
			String schName = sch.getDataTypes().get(0).toString();
			SysSchemata schma = TableFactory.getSingle().createSysSchemata(schName);			
			sysSchematas.put(schName, schma);
		}		
	}
	
	private void saveSysColumns(){		
		InforTableReadWriter saver = TableFactory.getSingle().createInforTablesReadWriter();				
		Set schSet = sysSchematas.entrySet();		
		Iterator isch = schSet.iterator();		 
		while(isch.hasNext()) {
		     Map.Entry schEn = (Map.Entry)isch.next();
		     String schName = (String) schEn.getKey();		     
		     // table
		     Set tblSet = ((SysSchemata)schEn.getValue()).tables.entrySet();		     		
			 Iterator iTbl = tblSet.iterator();
			 while(iTbl.hasNext()) {
				 Map.Entry tblEn = (Map.Entry)iTbl.next();
				 String tblAttrs[] = new String[3];
				 tblAttrs[0] = schName;
				 tblAttrs[1] = ((SysTable)tblEn.getValue()).tableName;
				 tblAttrs[2] = Long.toString(((SysTable)tblEn.getValue()).rows);
				 
				 //cols
				 Set colSet = ((SysTable)tblEn.getValue()).colums.entrySet();		     		
				 Iterator iCol = colSet.iterator();
				 while(iCol.hasNext()) {
					 Map.Entry colEn = (Map.Entry)iCol.next();
					 String colAttrs[] = new String[7];
					 colAttrs[0] = schName;
					 colAttrs[1] = ((SysTable)tblEn.getValue()).tableName;
					 colAttrs[2] = ((SysColumn)colEn.getValue()).Column_name;
					 colAttrs[3] = Integer.toString(((SysColumn)colEn.getValue()).Ordinal_position);
					 colAttrs[4] = ((SysColumn)colEn.getValue()).Column_type;
					 colAttrs[5] = ((SysColumn)colEn.getValue()).Is_nullable;
					 colAttrs[6] = ((SysColumn)colEn.getValue()).Column_key;					 
					 Record r = (Record)DataTypeFactory.getSingle().createSysColumnRecordWithValue(colAttrs); 
					 saver.addRecord(r);				 
			    }
				  
			 }		     		     
		}
		saver.WriteTableToFile(infoSchemaColumnsFileName);				
	}
	
	public void displaySysSchemata(){	
		String titleName[] = {"SCHEMA_NAME"};
		int width[] = {20};
		DisplayExpert.getSingle().setDisplayWith(width);
		DisplayExpert.getSingle().setTile(titleName);		
		DisplayExpert.getSingle().displayTitle();		
		Set schSet = sysSchematas.entrySet();		
		Iterator isch = schSet.iterator();		 
		while(isch.hasNext()) {
		     Map.Entry schEn = (Map.Entry)isch.next();		     		     
		     ((SysSchemata)schEn.getValue()).display(width);	    		     		     
		}
		DisplayExpert.getSingle().displayTitleLine();
	}
	
	public void displaySysTable(){	
		String titleName[] = {"TABLE_SCHEMA","TABLE_NAME",
				"TABLE_ROWS"};
		int width[] = {20,12,13};
		DisplayExpert.getSingle().setDisplayWith(width);
		DisplayExpert.getSingle().setTile(titleName);		
		DisplayExpert.getSingle().displayTitle();		
		Set schSet = sysSchematas.entrySet();		
		Iterator isch = schSet.iterator();		 
		while(isch.hasNext()) {
		     Map.Entry schEn = (Map.Entry)isch.next();		     		     
		     // table
		     Set tblSet = ((SysSchemata)schEn.getValue()).tables.entrySet();		     		
			 Iterator iTbl = tblSet.iterator();
			 while(iTbl.hasNext()) {
				 Map.Entry tblEn = (Map.Entry)iTbl.next();				 
				 ((SysTable)tblEn.getValue()).display(width);			  
			 }		     		     
		}
		DisplayExpert.getSingle().displayTitleLine();
	}
	
	public void displaySysColumns(){	
		String titleName[] = {"TABLE_SCHEMA","TABLE_NAME",
				"COLUMN NAME","ORDINAL_POSITION","COLUMN_TYPE",
				"IS_NULLABLE","COLUMN_KEY"};
		int width[] = {20,12,20,18,15,14,12};
		DisplayExpert.getSingle().setDisplayWith(width);
		DisplayExpert.getSingle().setTile(titleName);		
		DisplayExpert.getSingle().displayTitle();		
		Set schSet = sysSchematas.entrySet();		
		Iterator isch = schSet.iterator();		 
		while(isch.hasNext()) {
		     Map.Entry schEn = (Map.Entry)isch.next();		     		     
		     // table
		     Set tblSet = ((SysSchemata)schEn.getValue()).tables.entrySet();		     		
			 Iterator iTbl = tblSet.iterator();
			 while(iTbl.hasNext()) {
				 Map.Entry tblEn = (Map.Entry)iTbl.next();				 
				 //cols
				 Set colSet = ((SysTable)tblEn.getValue()).colums.entrySet();		     		
				 Iterator iCol = colSet.iterator();
				 while(iCol.hasNext()) {
					 Map.Entry colEn = (Map.Entry)iCol.next();
					 ((SysColumn)colEn.getValue()).display(width);				 
			    }				  
			 }		     		     
		}
		DisplayExpert.getSingle().displayTitleLine();
	}
	
	private void saveSysTable(){		
		InforTableReadWriter saver = TableFactory.getSingle().createInforTablesReadWriter();				
		Set schSet = sysSchematas.entrySet();		
		Iterator isch = schSet.iterator();		 
		while(isch.hasNext()) {
		     Map.Entry schEn = (Map.Entry)isch.next();
		     String schName = (String) schEn.getKey();		     
		     // table
		     Set tblSet = ((SysSchemata)schEn.getValue()).tables.entrySet();		     		
			 Iterator iTbl = tblSet.iterator();
			 while(iTbl.hasNext()) {
				 Map.Entry tblEn = (Map.Entry)iTbl.next();
				 String tblAttrs[] = new String[3];
				 tblAttrs[0] = schName;
				 tblAttrs[1] = ((SysTable)tblEn.getValue()).tableName;
				 tblAttrs[2] = Long.toString(((SysTable)tblEn.getValue()).rows);			     
				 Record r = (Record)DataTypeFactory.getSingle().createSysTableRecordWithValue(tblAttrs); 
				 saver.addRecord(r);				  
			 }		     		     
		}
		saver.WriteTableToFile(infoSchemaTableFileName);				
	}
	
	private void loadSysTable(){		
		InforTableReadWriter loader = TableFactory.getSingle().createInforTablesReadWriter();				
		loader.loadTableFromFile(infoSchemaTableFileName);
		Vector<Record> tableRocords = loader.getRecords();
		for(int i = 0; i < tableRocords.size();i++){
			Record tr= tableRocords.get(i);
			String schName = tr.getDataTypes().get(0).toString();
			String tbName = tr.getDataTypes().get(1).toString();
			String rows = tr.getDataTypes().get(2).toString();
			long lRows = Long.parseLong(rows);
			SysTable st = TableFactory.getSingle().createSysTable(schName,tbName);
			
			st.rows = lRows;
			SysSchemata sch = sysSchematas.get(schName);
			sch.addTable(st);			
		}		
	}	
	
	private void loadSysColumns(){		
		InforTableReadWriter loader = TableFactory.getSingle().createInforColumnsReadWriter();				
		loader.loadTableFromFile(infoSchemaColumnsFileName);
		Vector<Record> tableRocords = loader.getRecords();
		for(int i = 0; i < tableRocords.size(); i++){
			Record tr = tableRocords.get(i);
			String schName = tr.getDataTypes().get(0).toString();
			String tbName = tr.getDataTypes().get(1).toString();
			String colName = tr.getDataTypes().get(2).toString();
			String ordPos = tr.getDataTypes().get(3).toString();
			String colType = tr.getDataTypes().get(4).toString();
			String isNullable = tr.getDataTypes().get(5).toString();
			String colKey = tr.getDataTypes().get(6).toString();
			
			int iordPos = Integer.parseInt(ordPos);
			SysColumn col = TableFactory.getSingle().createSysColumn(schName,tbName,colName);
			col.Column_key = colKey;
			col.Column_type = colType;
			col.Is_nullable = isNullable;
			col.Ordinal_position = iordPos;			
			SysSchemata sch = sysSchematas.get(schName);
			SysTable st = sch.getTable(tbName);
			
			st.addColumn(col);			
		}	
		updateColumInfo();
	}
	
	 private void updateColumInfo(){
		Set schSet = sysSchematas.entrySet();		
		Iterator isch = schSet.iterator();		 
		while(isch.hasNext()) {
		     Map.Entry schEn = (Map.Entry)isch.next();
		     String schName = (String) schEn.getKey();		     
		     // table
		     Set tblSet = ((SysSchemata)schEn.getValue()).tables.entrySet();		     		
			 Iterator iTbl = tblSet.iterator();
			 while(iTbl.hasNext()) {
				 Map.Entry tblEn = (Map.Entry)iTbl.next();	
				 SysTable st = (SysTable) tblEn.getValue();
				 st.updateVectors();					  
			 }		     		     
		}
	}
}
