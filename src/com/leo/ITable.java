package com.leo;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public abstract class ITable {
	
	protected String mAttrsType[];
	protected String mAttrsNames[];
	
	protected String tname;
	protected ITable parent;
	
	public void accept(ITableVisitor t){
		
	}
	
	public void setAttrNames(String attrsNames[]){
		mAttrsNames = attrsNames;
	}
	
	public String[] getAttrNames(){
		return mAttrsNames;
	}
	
	void setAttrsType(String inforAttrs[]){
		mAttrsType = inforAttrs;
	}
	
	public String[] getAttrsType(){
		return mAttrsType;
	}
	
	public String getName(){
		return tname;
	}
	
	public void setName(String name){
		tname = name;
	}
	public ITable getParent(){
		return parent;
	}
	public void setParent(ITable p){
		parent = p;
	}
}
