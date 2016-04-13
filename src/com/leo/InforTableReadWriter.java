package com.leo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;

import com.leo.DataType.DataTypeFactory;
import com.leo.DataType.DataTypeReadVisitor;
import com.leo.DataType.DataTypeWriteVisitor;
import com.leo.DataType.Record;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class InforTableReadWriter extends ITable {

	Vector<Record> mRecords;
	
	public Vector<Record> getRecords(){
		return mRecords;
	}
	public InforTableReadWriter(){
		mRecords = new Vector<Record>();
	}
	
	public InforTableReadWriter(String name, String attrTypes[]){
		mRecords = new Vector<Record>();
		mAttrsType = attrTypes;
		loadTableFromFile(name);
	}
	
	public void addRecord(Record r){
		mRecords.add(r);
	}
	
	@Override
	public void accept(ITableVisitor t) {		
		//t.visit(this);
	}
	
	public void loadTableFromFile(String name){
		DataTypeReadVisitor dtv = new DataTypeReadVisitor();
		Record r = null;
		dtv.setCurrentFile(name);
		dtv.seek(0L);
		RandomAccessFile file = dtv.getCurrentFile();
		try {
			while(file.getFilePointer()< file.length()-1){
				r = DataTypeFactory.getSingle().createRecord(mAttrsType);
				r.accept(dtv);
				addRecord(r);				
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	public void WriteTableToFile(String name){
		DataTypeWriteVisitor dtv = new DataTypeWriteVisitor();		
		dtv.setCurrentFile(name);
		dtv.clearFile();
		dtv.seek(0L);
		for( int i = 0; i<mRecords.size() ;i++){
			Record r= mRecords.get(i);
			r.accept(dtv);
		}		
	}
	
	public static long insertIntoValues(String fileName,String attrTyps[], String values[]){
		DataTypeWriteVisitor dtv = new DataTypeWriteVisitor();
		dtv.setCurrentFile(fileName);
		dtv.seekToEnd();
		long pos = dtv.getFilePointer();
		Record r = DataTypeFactory.getSingle().createRecordWithValue(attrTyps,values);
		r.accept(dtv);
		return pos;
	}
	
	public static Record RandomReadValues(String fileName,String attrTyps[],long pos){
		DataTypeReadVisitor dtv = new DataTypeReadVisitor();
		dtv.setCurrentFile(fileName);
		dtv.seek(pos);
		Record r = DataTypeFactory.getSingle().createRecord(attrTyps);
		r.accept(dtv);
		return r;
	}

}
