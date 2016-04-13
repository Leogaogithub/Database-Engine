package com.leo.command;

import com.leo.SysSchemaManager;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class ExitCmd implements ICommand {

	@Override
	public void exec() {
		SysSchemaManager.getSingle().saveAllFile();
		System.out.println("byebye!");
	}

}
