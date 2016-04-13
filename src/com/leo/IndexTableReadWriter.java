package com.leo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import com.leo.DataType.DataTypeFactory;
import com.leo.DataType.DataTypeReadVisitor;
import com.leo.DataType.DataTypeWriteVisitor;
import com.leo.DataType.IndexTableRecord;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class IndexTableReadWriter extends ITable{
	Map<String, IndexTableRecord> mIndexs;
	private String idxType;
	
	public void setIdxType(String idt){
		idxType = idt;
	}
	public Map<String, IndexTableRecord> getIndexsTable(){
		return mIndexs;
	}
	
	public void setIndexsTable(Map<String, IndexTableRecord> idxs){
		mIndexs = idxs;
	}
	
	public IndexTableReadWriter(){
		mIndexs = new TreeMap<String, IndexTableRecord>();
	}
	
	public IndexTableReadWriter(String filename, String idxTypeName){
		setIdxType(idxTypeName);
		mIndexs = new TreeMap<String, IndexTableRecord>();
		loadTableFromFile(filename);
	}
	
	public void addIndex(IndexTableRecord r){
		String ndxName = r.mIndex.toString();
		mIndexs.put(ndxName, r);
	}
	
	void loadTableFromFile(String name){
		DataTypeReadVisitor dtv = new DataTypeReadVisitor();
		IndexTableRecord ir = null;
		dtv.setCurrentFile(name);		
		dtv.seek(0L);
		RandomAccessFile file = dtv.getCurrentFile();
		try {
			while(file.getFilePointer()< file.length()-1){
				ir = (IndexTableRecord)DataTypeFactory.getSingle().createIndexTableRecord(idxType);
				ir.accept(dtv);
				addIndex(ir);				
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	void WriteTableToFile(String name){
		DataTypeWriteVisitor dtv = new DataTypeWriteVisitor();		
		dtv.setCurrentFile(name);
		dtv.clearFile();
		dtv.seek(0L);
		IndexTableRecord idxr = null;
		for(Entry<String,IndexTableRecord> idxEn : mIndexs.entrySet()) {
			Object key = idxEn.getKey();         // Get the index key
			idxr = (IndexTableRecord)idxEn.getValue();			      
			idxr.accept(dtv);
		}				
	}
	
	void WriteTableToFile2(String name){
		DataTypeWriteVisitor dtv = new DataTypeWriteVisitor();		
		dtv.setCurrentFile(name);
		dtv.clearFile();
		dtv.seek(0L);
		Set idxSet = mIndexs.entrySet();		     		
		Iterator idx = idxSet.iterator();
		IndexTableRecord idxr = null;
		while(idx.hasNext()) {
			Map.Entry idxEn = (Map.Entry)idx.next();
			idxr = (IndexTableRecord)idxEn.getValue();			
			idxr.accept(dtv);
		}			
	}	
}
