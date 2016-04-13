package com.leo.command;

import com.leo.SysSchemaManager;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class UseCmd implements ICommand {
	private String schemaName;
	public void setSchemaName(String name){
		schemaName = name;
	}
	@Override
	public void exec() {
		SysSchemaManager.getSingle().useSchema(schemaName);
			
	}

}
