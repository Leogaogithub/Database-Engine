package com.leo;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class TableFactory {
	private static TableFactory instance = null;
	public String inforTableAttrsTypes[];
	public String inforSchemaAttrsTypes[];
	public String inforColumnsAttrsTypes[];
	private TableFactory(){
		String inforTableAttrs = "varchar(64),varchar(64),long";
		inforTableAttrsTypes = inforTableAttrs.split(",");
		inforTableAttrs = "varchar(64),varchar(64),varchar(64),int,varchar(64),varchar(3),varchar(3)";
		inforColumnsAttrsTypes = inforTableAttrs.split(",");
		inforTableAttrs = "varchar(64)";
		inforSchemaAttrsTypes = inforTableAttrs.split(",");
	}
	public static TableFactory getSingle(){
		if(instance == null){
			instance = new TableFactory();				
		}
		return instance;
	}	
	
	public InforTableReadWriter createInforTablesReadWriter(){
		InforTableReadWriter t = new InforTableReadWriter();
		t.setAttrsType(inforTableAttrsTypes);
		return t;		
	}
	
	public InforTableReadWriter createInforSchemaReadWriter(){
		InforTableReadWriter t = new InforTableReadWriter();
		t.setAttrsType(inforSchemaAttrsTypes);
		return t;		
	}
	
	public InforTableReadWriter createInforColumnsReadWriter(){
		InforTableReadWriter t = new InforTableReadWriter();
		t.setAttrsType(inforColumnsAttrsTypes);
		return t;		
	}
	
	public SysSchemata createSysSchemata(){
		return new SysSchemata();
	}	
	
	public SysSchemata createSysSchemata(String name){
		SysSchemata sch = new SysSchemata();
		sch.schemaName = name;
		return sch;
	}
	public SysTable createSysTable(){
		return new SysTable();
	}	
	
	public SysTable createSysTable(String schName, String tname){
		SysTable st = new SysTable();
		st.Table_schema = schName;
		st.tableName =  tname;
		return st;
	}
	public SysColumn createSysColumn(){
		return new SysColumn();
	}
	
	public SysColumn createSysColumn(String schName,String tbName,String name){
		SysColumn col = new SysColumn();
		col.Column_name = name;
		col.Table_schema = schName;
		col.Table_name = tbName;
		return col;
	}
}
