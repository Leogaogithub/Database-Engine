package com.leo.DataType;

import java.util.Vector;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class IndexTableRecord implements IDataType{
	public IDataType mIndex;
	public Leo_INT mSize;
	public Vector<Leo_INT> locations;
	public IndexTableRecord(){
		mIndex = null;
		mSize = new Leo_INT(0);
		locations = new Vector<Leo_INT>();		
	}
	
	public void setIndex(String idx){
		mIndex.setData(idx); 
	}
	
	public void setIndxType(String idxType){
		mIndex = DataTypeFactory.getSingle().createDataType(idxType);
	}
	
	public void setSize(int size){
		mSize.setData(size);		
	}
	
	public int getSize(){
		return mSize.getData();
	}
	
	public void initalLocations(int size){
		locations = new Vector<Leo_INT>();
		for(int i = 0; i < size; i++){
			Leo_INT e = new Leo_INT();
			locations.add(e);
		}
		mSize.setData(locations.size());
	}
	@Override
	public void accept(IDataTypeVisitor v) {
		mIndex.accept(v);
		mSize.accept(v);
		v.visit(this);
		for(int i = 0; i < locations.size();i++){
			Leo_INT elem=locations.get(i);
			elem.accept(v);			
		}		
	}
	@Override
	public void setData(String sdata) {		
		
	}
	
	public void setData(String sdata[]){		
		for(int i= 0; i < sdata.length; i++){
			locations.get(i).setData(sdata[i]); 
		}
		mSize.setData(locations.size());
	}
	public void addLocation(int loc){		
		Leo_INT newLoc = new Leo_INT(loc);
		locations.addElement(newLoc);
		mSize.setData(locations.size());
	}
}
