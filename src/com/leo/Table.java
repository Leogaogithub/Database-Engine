package com.leo;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.leo.DataType.IndexTableRecord;
import com.leo.DataType.Leo_INT;
import com.leo.DataType.Record;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class Table{
	String schemaName = "";
	String tableName = "";
	Map<String, IndexTable> indexTables;
	Table(){		
		indexTables = new TreeMap<String, IndexTable>();
	}	
	
	public IndexTable getIndexTable(String name){
		return indexTables.get(name);
	}
	public boolean isSameTable(String schName, String tbName){
		if(schemaName.equalsIgnoreCase(schName)
				&&tableName.equalsIgnoreCase(tbName)){
			return true;			
		}
		return false;
	}
	
	public Table(String schName, String tbName){
		schemaName = schName;
		tableName = tbName;
		indexTables = new TreeMap<String, IndexTable>();
	}

	public void add(IndexTable t){		
		indexTables.put(t.getName(),t);
	}
	
	public void insertIntoValues(String values[]){	
		SysTable st = SysSchemaManager.getSingle().getSysTable(schemaName, tableName);
		String columTypes[] = st.getColumTypeNames();
		String columNames[] = st.getColumNames();
		String columKeys[] = st.getColumnKeys();
		
		for(int i = 0; i< columKeys.length; i++){
			if(columKeys[i].equalsIgnoreCase("pri")){
				if(indexTables.get(columNames[i]).isExist(values[i])){
					line(" primary key should not be same ");
					return;
				}
				if(columTypes[i].equalsIgnoreCase("date")){
					if(!UtilityTool.checkDateFormat(values[i])){
						line(" date formal is not right");
						return;
					}
				}
				if(columTypes[i].equalsIgnoreCase("datetime")){
					if(!UtilityTool.checkDateTimeFormat(values[i])){
						line(" datetime formal is not right");
						return;
					}
				}
			}
		}
		String fileName = getTableFileName();				
		long pos = InforTableReadWriter.insertIntoValues(fileName, columTypes, values);	
		String sPos = Long.toString(pos);
		for(int i = 0; i< columNames.length; i++){
			String clN = columNames[i];
			indexTables.get(clN).addIndexTableRecord(values[i],sPos);
		}	
		st.addRow();
	}
	
	public void loadIndxTables(){		
		String [] colNames = SysSchemaManager.getSingle().getTableColumNames(schemaName,tableName);
		
		SysTable st = SysSchemaManager.getSingle().getSysTable(schemaName,tableName);
		String [] colTypes = st.getColumTypeNames();		
		for(int i = 0;i < colNames.length;i++){
			String colName= colNames[i];
			String idxFileName = getIndexFileName(colName);
			IndexTable idxt= new IndexTable(colName);
			idxt.setIndexType(colTypes[i]);
			idxt.load(idxFileName);
			indexTables.put(colName, idxt);			
		}
	}
	
	public void getIndexTableRecordsByCondition(
			String clNm, 
			String operation, String value){		
		IndexTable curIdxTbl = getIndexTable(clNm);
		Vector<IndexTableRecord> idxResult = curIdxTbl.getIndexTableRecordsByCondition(operation, value);
		if(idxResult.isEmpty()){
			line("result is 0");
			return;
		}
		SysTable st = SysSchemaManager.getSingle().getSysTable(schemaName, tableName);
		
		String filename = getTableFileName();
		String attrTyps[] = st.getColumTypeNames();
		
		DisplayExpert.getSingle().setSystable(st);
		DisplayExpert.getSingle().updateLength();
		DisplayExpert.getSingle().displayTitle();
		//DataTypeDisplayVisitor displayer = new DataTypeDisplayVisitor();
		for(int i = 0; i <idxResult.size();i++){
			IndexTableRecord idxr=idxResult.get(i);
			for(int j = 0; j < idxr.getSize(); j++){
				Leo_INT loc = idxr.locations.get(j);
				Record r = InforTableReadWriter.RandomReadValues(filename, attrTyps, (long)loc.getData());
				DisplayExpert.getSingle().displayRecord(r);
				//r.accept(displayer);				
			}			
		}		
		DisplayExpert.getSingle().displayTitleLine();		
	}
	
	private void line(String str){
		System.out.println(str);
	}
	
	private String getIndexFileName(String colName){
		String fileName = "";
		fileName = schemaName+"."+tableName+"."+colName+".ndx";
		return fileName;
	}
	
	private String getTableFileName(){
		String fileName = "";
		fileName = schemaName+"."+tableName+".tbl";
		return fileName;
	}
	
	public void saveIndxTables(){
		if(!SysSchemaManager.getSingle().isTableExis(schemaName, tableName)){
			return;
		}
		if(indexTables == null) return;
		String [] colNames = SysSchemaManager.getSingle().getTableColumNames(schemaName,tableName);
		//SysTable st = SysSchemaManager.getSingle().getSysTable(schemaName,tableName);
		//String [] colTypes = st.getColumTypeNames();
		Set idxSet = indexTables.entrySet();
		Iterator iIdx = idxSet.iterator();
		while(iIdx.hasNext()){
			Map.Entry idxE = (Map.Entry)iIdx.next();
			IndexTable idxt = (IndexTable)idxE.getValue();			
			String colName = (String)idxE.getKey();
			String idxFileName = getIndexFileName(colName);			
			idxt.save(idxFileName);
		}
	}
	
	
}
