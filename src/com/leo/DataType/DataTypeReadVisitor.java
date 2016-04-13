package com.leo.DataType;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;

import com.leo.PermanentController;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class DataTypeReadVisitor implements IDataTypeVisitor{	
	RandomAccessFile currentFile = null;
	static private boolean debugOn = false;
	
	public void seek(long pos){
		try {			
			currentFile.seek(pos);
		} catch (IOException e) {			
			e.printStackTrace();
		}	
	}

	public void setCurrentFile(String fileName){
		currentFile = PermanentController.getSingle().getFile(fileName);
	}
	
	public RandomAccessFile getCurrentFile(){
		return currentFile;
	}
	
	@Override
	public void visit(Leo_LONG data){
		try {
			long newIdata = currentFile.readLong();
			data.setData(newIdata);	
			if(debugOn)System.out.println(newIdata);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void visit(Leo_VARCHAR data){
		try {
			long n = data.getN();
			byte b[] = new byte[(int)n];
			char c[] = new char[(int)n];			
			currentFile.read(b);
			for(int i = 0; i < n; i++){
				c[i] = (char) b[i];
			}
			String sdata = String.valueOf(c);
			data.setData(sdata);
			if(debugOn)System.out.println(sdata);
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	
	public void visit(Leo_INT data){
		try {
			int newIdata = currentFile.readInt();
			data.setData(newIdata);
			if(debugOn) System.out.println(newIdata);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public void visit(Record data){
	
	}
	
	public void visit(Leo_BYTE data){
		try {
			byte newIdata = currentFile.readByte();
			data.setData(newIdata);
			if(debugOn) System.out.println(newIdata);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void visit(Leo_CHAR data){
		try {				
			Vector<Byte> Vxx = new Vector<Byte>();
			Byte newIdata = currentFile.readByte();	
			Byte end = '\n';
			Vxx.add(newIdata);
			while(!newIdata.equals(end)){
				newIdata = currentFile.readByte();
				Vxx.add(newIdata);
			}
			if(Vxx.size()!=1){
				Vxx.add(end);
			}
			data.setData(Vxx);			
			//data.setData(newIdata);
			if(debugOn) System.out.println(newIdata);
		} catch (IOException e) {			
			e.printStackTrace();
		}	
	}
	public void visit(Leo_DATE data){
		try {
			long newIdata = currentFile.readLong();
			data.setData(newIdata);
			if(debugOn) System.out.println(newIdata);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void visit(Leo_DATETIME data){
		try {
			long newIdata = currentFile.readLong();
			data.setData(newIdata);
			if(debugOn) System.out.println(newIdata);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void visit(Leo_DOUBLE data){
		try {
			double newIdata = currentFile.readDouble();
			data.setData(newIdata);
			if(debugOn) System.out.println(newIdata);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void visit(Leo_FLOAT data){
		try {
			float newIdata = currentFile.readFloat();
			data.setData(newIdata);
			if(debugOn) System.out.println(newIdata);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void visit(Leo_SHORT data){
		try {
			short newIdata = currentFile.readShort();
			data.setData(newIdata);
			if(debugOn) System.out.println(newIdata);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	public void visit(IndexTableRecord data){
		int size = data.getSize();
		data.initalLocations(size);
	}
}
