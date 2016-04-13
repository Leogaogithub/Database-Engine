package com.leo.DataType;


import com.leo.TableFactory;
import com.leo.UtilityTool;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class DataTypeFactory {
	private static DataTypeFactory instance = null;
	private DataTypeFactory(){}
	public static DataTypeFactory getSingle(){
		if(instance == null){
			instance = new DataTypeFactory();			 
		}
		return instance;
	}
	
	public IDataType createDataType(String name){
		IDataType dt = null;
		String typeName = name.toLowerCase();
		switch(typeName){
			case "byte": 
				dt = new Leo_BYTE();
				break;
			case "date":
				dt = new Leo_DATE();
				break;
			case "datetime": 
				dt = new Leo_DATETIME();
				break;
			case "double":
				dt = new Leo_DOUBLE();
				break;
			case "float":
				dt = new Leo_FLOAT();
				break;
			case "int": 
				dt = new Leo_INT();
				break;
			case "long":
				dt = new Leo_LONG();
				break;
			case "short": 
				dt = new Leo_SHORT();
				break;
			case "record":
				dt = new Record();
				break;
			case "indexrecord":
				dt = new IndexTableRecord();
				break;	
			default:
				dt = parseDataType(typeName);
		}
		return dt;
	}
	
	private IDataType parseDataType(String typeName){
		IDataType dt = null;
		
		if(typeName.startsWith("char")){
			String sN = UtilityTool.getValueInParentheses(typeName);
			sN = sN.trim();
			dt = new Leo_CHAR();
			Leo_CHAR mdt = (Leo_CHAR)dt;
			mdt.setN(Integer.parseInt(sN));
		}
		else if(typeName.startsWith("varchar"))
		{
			String sN = UtilityTool.getValueInParentheses(typeName);
			sN = sN.trim();
			dt = new Leo_VARCHAR();			
			Leo_VARCHAR mdt = (Leo_VARCHAR)dt;
			mdt.setMaxLength(Integer.parseInt(sN));
		}
		else {
			dt = null;
		}
		return dt;
	}
	public IndexTableRecord createIndexTableRecord(String indexType,String idxName,String loc){
		IndexTableRecord idx = (IndexTableRecord) createDataType("indexrecord");		
		idx.setIndxType(indexType);
		idx.setIndex(idxName);
		idx.addLocation(Integer.parseInt(loc));		
		return idx;
	}
	
	public IndexTableRecord createIndexTableRecord(String indexType){
		IndexTableRecord idx = (IndexTableRecord) createDataType("indexrecord");		
		idx.setIndxType(indexType);	
		return idx;
	}
	//new version
	public Record createRecord(String[] dataTypes){
		Record rcd = (Record)createDataType("record");
		for(int i = 0; i < dataTypes.length; i++){
			String sdt = dataTypes[i];
			IDataType dt = createDataType(sdt);
			rcd.add(dt);
		}
		return rcd;
	}
	
	//new version
	public Record createRecordWithValue(String[] dataTypes, String values[]){
		Record rcd = (Record)createDataType("record");
		for(int i = 0; i < dataTypes.length ; i++){
			String tname = dataTypes[i];
			IDataType dt = createDataType(tname);
			dt.setData(values[i]);
			rcd.add(dt);
		}
		return rcd;
	}
	
	public Record createSysSchemataRecordWithValue(String values[]){
		String[] dataTypes = TableFactory.getSingle().inforSchemaAttrsTypes;		
		Record rcd = (Record)createRecordWithValue(dataTypes, values);
		return rcd;
	}
	
	public Record createSysTableRecordWithValue(String values[]){
		String[] dataTypes = TableFactory.getSingle().inforTableAttrsTypes;		
		Record rcd = (Record)createRecordWithValue(dataTypes, values);
		return rcd;
	}
	
	public Record createSysColumnRecordWithValue(String values[]){
		String[] dataTypes = TableFactory.getSingle().inforColumnsAttrsTypes;		
		Record rcd = (Record)createRecordWithValue(dataTypes, values);
		return rcd;
	}	
}
