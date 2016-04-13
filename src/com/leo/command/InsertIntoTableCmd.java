package com.leo.command;

import com.leo.SysSchemaManager;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class InsertIntoTableCmd implements ICommand {
	String tableName="";
	String mValues[];
	public void setTableName(String tname){
		tableName = tname;
	}
	
	public void setValues(String values[]){
		mValues = values;
	}
	@Override
	public void exec() {
		SysSchemaManager.getSingle().insertIntoValues(tableName,mValues);
	}
}
