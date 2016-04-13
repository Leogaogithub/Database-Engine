package com.leo;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.leo.DataType.DataTypeFactory;
import com.leo.DataType.IndexTableRecord;
import com.leo.operation.IOperation;
import com.leo.operation.OperationFactory;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class IndexTable{
	private String indexTableName = "";
	private Map<String, IndexTableRecord> mIndexs;
	private String indexType;
	public void setName(String name){
		indexTableName = name;
	}
	public void setIndexType(String it){
		indexType = it;
	}
	public String getName(){
		return indexTableName;
	}	
	public boolean isExist(String idxnm){
		return mIndexs.containsKey(idxnm);
	}
	public IndexTable(){	
		mIndexs = new TreeMap<String, IndexTableRecord>();
	}	
	
	public IndexTable(String name){	
		mIndexs = new TreeMap<String, IndexTableRecord>();
		setName(name);
	}
	
	public void setIndexTable(Map<String, IndexTableRecord> it ){
		mIndexs = it;
	}
	
	public Map<String, IndexTableRecord> getIndexTable(){
		return mIndexs;
	}
	
	public void addIndexTableRecord(String ndxName, String loc){				
		if(mIndexs.containsKey(ndxName)){
			IndexTableRecord idxr = mIndexs.get(ndxName);			
			idxr.addLocation(Integer.parseInt(loc));
		}else{
			IndexTableRecord idxr = DataTypeFactory.getSingle().createIndexTableRecord(indexType,ndxName,loc);			
			mIndexs.put(ndxName, idxr);
		}		
	}
	
	public Vector<IndexTableRecord> getIndexTableRecordsByCondition(String operation, String value){
		Vector<IndexTableRecord> result = new Vector<IndexTableRecord>();
		IOperation op = OperationFactory.getSingle().getOperation(operation);
		 Set idxSet = mIndexs.entrySet();		     		
		 Iterator iIdx = idxSet.iterator();
		 while(iIdx.hasNext()) {
			 Map.Entry idxEn = (Map.Entry)iIdx.next();
			 String idxValue =  ((IndexTableRecord)idxEn.getValue()).mIndex.toString();
			 if(op.operate(idxValue, value)){
				 result.add((IndexTableRecord)idxEn.getValue());
			 }
		}		
		return result;
	}
	
	void load(String fileName){
		IndexTableReadWriter reader = new IndexTableReadWriter(fileName,indexType);
		mIndexs = reader.getIndexsTable();
	}
	
	void save(String fileName){
		IndexTableReadWriter writer = new IndexTableReadWriter();
		writer.setIdxType(indexType);
		writer.setIndexsTable(mIndexs);		
		writer.WriteTableToFile(fileName);		
	}
}
