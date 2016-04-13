package com.leo.command;

import com.leo.SysSchemaManager;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class ShowTablesCmd implements ICommand {

	String schemaName ="";
	void setSchemaName(String name){
		schemaName = name;
	}
	@Override
	public void exec() {
		SysSchemaManager.getSingle().showTables();		
	}
}
