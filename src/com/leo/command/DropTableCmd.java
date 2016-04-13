package com.leo.command;

import com.leo.SysSchemaManager;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class DropTableCmd implements ICommand {
	String tableName;
	public void setTableName(String tbN){
		tableName = tbN;
	}
	@Override
	public void exec() {
		SysSchemaManager.getSingle().dropTable(tableName);
	}
}
