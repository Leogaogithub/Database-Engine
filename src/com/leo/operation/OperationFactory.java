package com.leo.operation;

import java.util.HashMap;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class OperationFactory {
	HashMap<String, IOperation> operations;
	static OperationFactory instance;
	public static OperationFactory getSingle(){
		if(instance == null){
			instance = new OperationFactory();
		}
		return instance;
	}
	
	private OperationFactory(){
		operations = new HashMap<String, IOperation>();
	}
	
	private IOperation createOperation(String name){
		IOperation op = null;
		
		switch(name){
			case ">=<":
				op = new AllTureOperating();
				break;
			case "=":
				op = new EqualOperating();
				break;
			case ">":
				op = new GreaterOperating();
				break;
			case "<":
				op = new LessOperating();
				break;			
			default:
				op = null;
		}
		return op;
	}
	
	public IOperation getOperation(String name){
		IOperation op = null;
		if(operations.containsKey(name)){
			op = operations.get(name);
		}
		else{
			op = createOperation(name);	
			operations.put(name, op);
		}
		return op;
	}
	
	
}
