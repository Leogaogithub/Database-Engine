package com.leo.command;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class CommandFactory {
	
	private CommandFactory(){		
	}
	private static CommandFactory instance = null;	
	public static CommandFactory getSingle(){
		if(instance == null){
			instance = new CommandFactory();				
		}
		return instance;
	}
	
	public ICommand getCommand(String name){
		ICommand cmd = null;		
		if(cmd == null){
			switch(name.toLowerCase()){
			case "show schemas":
				cmd = new ShowSchemasCmd();
				break;
			case "use":
				cmd = new UseCmd();
				break;
			case "show tables":
				cmd = new ShowTablesCmd();
				break;
			case "create schema":
				cmd = new CreateSchemaCmd();
				break;
			case "create table":
				cmd = new CreateTableCmd();
				break;
			case "insert into table":
				cmd = new InsertIntoTableCmd();
				break;
			case "drop table":
				cmd = new DropTableCmd();
				break;
			case "select":
				cmd = new SelectFromCmd();
				break;
			case "exit":
				cmd = new ExitCmd();
				break;
			default:
				break;	
			}
		}
		return cmd;
	}
}
