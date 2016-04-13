package com.leo.DataType;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.leo.PermanentController;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class DataTypeWriteVisitor implements IDataTypeVisitor{
	RandomAccessFile currentFile = null;
	public void setCurrentFile(String fileName){
		currentFile = PermanentController.getSingle().getFile(fileName);
	}
	
	public void clearFile(){
		try {
			currentFile.setLength(0L);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public long getFilePointer(){
		long curpos = 0;
		try {
			curpos = currentFile.getFilePointer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return curpos;
	}
	
	public void seekToEnd(){
		long pos;
		try {
			pos = currentFile.length();
			currentFile.seek(pos);
		} catch (IOException e) {			
			e.printStackTrace();
		}		
	}
	
	public void seek(long pos){
		try {			
			currentFile.seek(pos);
		} catch (IOException e) {			
			e.printStackTrace();
		}	
	}
	
	@Override
	public void visit(Leo_LONG data){
		try {
			currentFile.writeLong(data.getData());
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void visit(Leo_VARCHAR data){
		try {
			String sdata = data.getData();
			currentFile.writeBytes(sdata);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	public void visit(Leo_INT data){
		try {
			currentFile.writeInt(data.getData());
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public void visit(Record data){
		
	}
	
	public void visit(Leo_BYTE data){
		try {
			currentFile.writeByte(data.getData());
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void visit(Leo_CHAR data){
		try {
			for(int i = 0; i < data.getN(); i++){
				currentFile.writeByte(data.getData()[i]);
			}			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void visit(Leo_DATE data){
		try {
			currentFile.writeLong(data.getData());
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void visit(Leo_DATETIME data){
		try {
			currentFile.writeLong(data.getData());
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void visit(Leo_DOUBLE data){
		try {
			currentFile.writeDouble(data.getData());
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void visit(Leo_FLOAT data){
		try {
			currentFile.writeFloat(data.getData());
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void visit(Leo_SHORT data){
		try {
			currentFile.writeShort(data.getData());
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}	
	
	public void visit(IndexTableRecord data){
		
	}

}
