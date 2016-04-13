package com.leo.command;

import com.leo.SysSchemaManager;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class SelectFromCmd implements ICommand {
	String tableName = "";
	String colName = "";
	String operation = "";
	String value = "";
	
	public void setValues(String tbNm, String clNm, String op, String vl){
		tableName = tbNm;
		colName = clNm;
		operation = op;
		value = vl;
	}
	@Override
	public void exec() {
		SysSchemaManager.getSingle().selectFromWhere(tableName, colName,operation,value);
	}

}
