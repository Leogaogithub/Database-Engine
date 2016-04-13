package com.leo;

import com.leo.command.CommandFactory;
import com.leo.command.CreateSchemaCmd;
import com.leo.command.CreateTableCmd;
import com.leo.command.DropTableCmd;
import com.leo.command.ICommand;
import com.leo.command.UseCmd;
import com.leo.operation.OperationFactory;
import com.leo.command.InsertIntoTableCmd;
import com.leo.command.SelectFromCmd;  

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class InputRuleExpert {
	static String twoBlanks = "  ";
	static String oneBlank = " ";
	public void parseInput(String userCommand){
		ICommand cmd = null;
		userCommand = UtilityTool.regularingCmd(userCommand);
		switch (userCommand) {
			case "show schemas":
				cmd = CommandFactory.getSingle().getCommand("show schemas");
				break;
			case "show tables":					
				cmd = CommandFactory.getSingle().getCommand("show tables");					
				break;			
			case "help":
				SystemDescription.getSingle().showHelp();
				break;
			case "version":
				SystemDescription.getSingle().showVersion();
				break;
			case "exit":
				cmd = CommandFactory.getSingle().getCommand("exit");				
				break;
			case "select * from columns":
				SysSchemaLoader.getSingle().displaySysColumns();				
				break;
			case "select * from tables":
				SysSchemaLoader.getSingle().displaySysTable();				
				break;
			case "select * from schemata":
				SysSchemaLoader.getSingle().displaySysSchemata();				
				break;
			default:
				cmd = parse(userCommand);				
		}
		if(cmd != null)cmd.exec();		
	}	
	
	ICommand parseCreateSchemaCmd(String sCmd){
		CreateSchemaCmd cmd = null;
		String schemaName = UtilityTool.getMatcher("create schema (\\S*)", sCmd);
		if(UtilityTool.checkStringNotEmpty(schemaName)){
			cmd = (CreateSchemaCmd) CommandFactory.getSingle().getCommand("create schema");
			cmd.setSchemaName(schemaName);
		}
		return cmd;		
	}
	
	ICommand parseDropTableCmd(String sCmd){
		DropTableCmd cmd = null;
		String tableName = UtilityTool.getMatcher("drop table (\\S*)", sCmd);
		if(UtilityTool.checkStringNotEmpty(tableName)){
			cmd = (DropTableCmd) CommandFactory.getSingle().getCommand("drop table");
			cmd.setTableName(tableName);
		}
		return cmd;	 
	 }
	ICommand parseUseCmd(String sCmd){
		UseCmd cmd = null;
		String schemaName = UtilityTool.getMatcher("use (\\S*)", sCmd);
		if(UtilityTool.checkStringNotEmpty(schemaName)){
			cmd = (UseCmd) CommandFactory.getSingle().getCommand("use");
			cmd.setSchemaName(schemaName);
		}
		return cmd;		
	}
	
	ICommand parseCreateTableCmd(String sCmd){
		CreateTableCmd cmd = null;
		sCmd = reNameKeyword(sCmd);
		String tblName = UtilityTool.getMatcher("create table (.*?)\\(", sCmd);
		tblName = tblName.trim();		
		if(UtilityTool.checkStringNotEmpty(tblName)){			
			cmd = (CreateTableCmd) CommandFactory.getSingle().getCommand("create table");
			cmd.setTableName(tblName);
			String midStr = UtilityTool.getValueInParentheses(sCmd);;
			String columsInfors[] = midStr.split(",");
			String schName = SysSchemaManager.getSingle().getCurrentSchemaName();
			for(int i = 0; i <columsInfors.length;i++){
				String column=columsInfors[i];
				column = column.trim();
				String colElements[] = column.split(oneBlank);
				UtilityTool.trim(colElements);
				SysColumn sysCol = TableFactory.getSingle().createSysColumn(schName,tblName,colElements[0].trim());
				sysCol.Column_type = colElements[1].trim();
				if(colElements.length > 2){					
					if(colElements[2].equalsIgnoreCase("pri")){
						sysCol.Column_key = "pri";
					}
					if(colElements[2].equalsIgnoreCase("no")){
						sysCol.Is_nullable = "no";
					}
				}
				cmd.addColumn(sysCol);
			}			
		}
		return cmd;		
	}
	
	
	ICommand parseInsertIntoTableCmd(String sCmd){
		InsertIntoTableCmd cmd = null;
		sCmd = reNameKeyword(sCmd);
		String tblName = UtilityTool.getMatcher("insert into (.*?) values\\(", sCmd);
		tblName = tblName.trim();		
		if(UtilityTool.checkStringNotEmpty(tblName)){			
			cmd = (InsertIntoTableCmd) CommandFactory.getSingle().getCommand("insert into table");
			cmd.setTableName(tblName);
			String midStr = UtilityTool.getValueInParentheses(sCmd);
			String values[] = midStr.split(",");
			
			for(int i = 0; i < values.length; i++){
				if(values[i].contains("'")){
					String xx =  UtilityTool.getValueInSingleQuotes(values[i]);
					 if(xx == null){
						 line("values is not in right format");
						 return null;
					 }
					 values[i] = xx;
				}				
			}
			cmd.setValues(values);
		}
		else{
			line("insert into table command is not right");
		}
		return cmd;		
	}
	
	String findOperation(String where){
		String operation = "";
		if(where.contains(">=<")){
			operation = ">=<";
		}
		else if(where.contains(">")){
			operation = ">";
		}
		else if(where.contains("<")){
			operation = "<";
		}
		else if(where.contains("=")){
			operation = "=";
		}
		else{
			operation = "";
		}			
		
		return operation;
	}
	
	ICommand parseSelectFromCmd(String sCmd){
		SelectFromCmd cmd = null;		
		String tblName = UtilityTool.getMatcher("select \\* from (.*?) where ", sCmd);
		tblName = tblName.trim();		
		if(UtilityTool.checkStringNotEmpty(tblName)){			
			cmd = (SelectFromCmd) CommandFactory.getSingle().getCommand("select");
			String where = UtilityTool.getWordsAfter(sCmd, "where");	
			where = where.trim();
			String opNm = findOperation(where);
			if(!UtilityTool.checkStringNotEmpty(opNm)){
				line("operation is not existing");
				return null;
			}
			String values[] = where.split(opNm);			
			if(values.length != 2){
				line("where statement should be column_name operator value");
				return null;
			}
			if(values[1].contains("'")){
				if(values[1].contains("'")){
					String xx =  UtilityTool.getValueInSingleQuotes(values[1]);
					 if(xx == null){
						 line("values is not in right format");
						 return null;
					 }
					 values[1] = xx;
				}							
			}
			
			UtilityTool.trim(values);			
			if(null == OperationFactory.getSingle().getOperation(opNm)){
				line("we could not process " + opNm +" operation");
				return null;
			}			
			cmd.setValues(tblName, values[0],opNm, values[1]);
		}
		else{
			line("select from where command is not right");
		}
		return cmd;		
	}	
	
	ICommand parse(String sCmd){
		ICommand cmd = null;		
		if(sCmd.startsWith("create schema ")){
			cmd = parseCreateSchemaCmd(sCmd);
		}
		else if(sCmd.startsWith("create table ")){
			cmd = parseCreateTableCmd(sCmd);
		}		
		else if(sCmd.startsWith("use ")){
			cmd = parseUseCmd(sCmd);
		}
		else if(sCmd.startsWith("insert into ")){
			cmd = parseInsertIntoTableCmd(sCmd);
		}
		else if(sCmd.startsWith("select * from ")){
			cmd = parseSelectFromCmd(sCmd);
		}
		else if(sCmd.startsWith("drop table ")){
			cmd = parseDropTableCmd(sCmd);
		}
		else{
			line("I didn't understand the command: \"" + sCmd + "\"");
			cmd = null;
		}
		return cmd;
	}

	
	private static String reNameKeyword(String str){
		//replace short int with short
		str = str.replaceAll("short int", "short");
		str = str.replaceAll("long int", "long");
		str = str.replaceAll("primary key", "pri");
		str = str.replaceAll("not null", "no");		
		return str; 
	}
	
	public static void line(String con){
		System.out.println(con);
	}
}
