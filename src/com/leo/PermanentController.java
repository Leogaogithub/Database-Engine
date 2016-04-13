package com.leo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.TreeMap;
import java.util.Map;


/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class PermanentController {
	private static PermanentController instance = null;
	Map<String, RandomAccessFile> fileMap;			
	private String rootPath = "./database/";	
	//private String rootPath = "";
	public RandomAccessFile getFile(String name){
		RandomAccessFile file = null;
		if(fileMap.containsKey(name)){
			return fileMap.get(name);
		}
		try {
			file = new RandomAccessFile(rootPath + name, "rw");
			fileMap.put(name, file);			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		return file;
	}	
	
	public void removeAndDeleteFile(String name){
		if(!fileMap.containsKey(name)){
			line(name + " is not existing");
			return ;
		}
		RandomAccessFile file = fileMap.remove(name);	
		try {
			file.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		UtilityTool.deleteFile(rootPath+name);
		
	}

	public static PermanentController getSingle(){
		if(instance == null){
			instance = new PermanentController();			 
		}
		return instance;
	}
	
	private void line(String cont){
		System.out.println(cont);
	}
	private PermanentController(){	
		fileMap = new TreeMap<String, RandomAccessFile>();
	}		
}
