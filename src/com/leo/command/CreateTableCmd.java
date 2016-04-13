package com.leo.command;

import java.util.Vector;

import com.leo.SysColumn;
import com.leo.SysSchemaManager;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class CreateTableCmd implements ICommand {
	private String tableName="";
	private Vector<SysColumn> columns;
	public CreateTableCmd(){
		columns = new Vector<SysColumn>();
	}
	public void setTableName(String tblName){
		tableName = tblName;
	}
	public void addColumn(SysColumn col){
		int OrdPos = columns.size()+1;
		col.Ordinal_position = OrdPos;
		columns.add(col);
	}
	@Override
	public void exec() {
		SysSchemaManager.getSingle().createTable(tableName, columns);

	}

}
