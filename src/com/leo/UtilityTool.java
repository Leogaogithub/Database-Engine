package com.leo;

import java.io.File;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class UtilityTool {
	static String oneBlank = " ";
	static String twoBlanks = "  ";
	public static String getValueInParentheses(String str){
		int startIdx = str.indexOf("(");
		int endIdx = str.lastIndexOf(")");
		String midStr = str.substring(startIdx+1, endIdx);
		return midStr.trim();
	}
	
	public static String getValueInSingleQuotes(String str){
		int startIdx = str.indexOf("'");
		int endIdx = str.lastIndexOf("'");
		String result = null;
		if(startIdx >= endIdx){
			return null;
		}	
		result = str.substring(startIdx+1, endIdx);
		result.trim();
		return result;
	}
	
	public static String getWordsAfter(String str, String where){
		int startIdx = str.indexOf(where);
		int endIdx = str.length();
		String midStr = str.substring(startIdx+where.length(), endIdx);
		return midStr.trim();
	}
	
	public static void trim(String strs[]){
		for(int i = 0; i< strs.length; i++){
			strs[i] = strs[i].trim();
		}		
	}
	public static String getMatcher(String regex, String source) {  
        String result = "";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(source);  
        while (matcher.find()) {  
            result = matcher.group(1); 
        }  
        return result;  
    } 
	
	public static boolean checkDateFormat(String date){
		String xx = "2016-03-23";
		if(date.length()!= xx.length()){
			return false;
		}
		return true;
	}
	
	public static boolean checkDateTimeFormat(String date){
		String xx = "2016-03-23_13:52:23";
		if(date.length()!= xx.length()){
			return false;
		}
		return true;
	}
	
	public static boolean checkStringNotEmpty(String item){
		if(item==null)
			return false;
		if(item.isEmpty())
			return false;
		if(item.equals(""))
			return false;
		return true;
	}
	
	public static boolean checkIntegerMoreThan0(String item){
		if(item==null)
			return false;
		if(item.isEmpty())
			return false;
		if(Integer.parseInt(item)< 1){
			return false;
		}
		return true;
	}
	
	 public static boolean VectorVectorStringNotEmpty(Vector<Vector<String>> result){
		 if(result == null) return false;
		 if(result.isEmpty()) return false;
		 if(result.get(0) == null) return false;
		 if(result.get(0).isEmpty()) return false;
		 if(result.get(0).get(0).equals("")) return false;		 
		 return true;
	 }	
	 
	public static String regularingCmd(String str){		

		str = str.replaceAll("\n", oneBlank);
		str = str.replaceAll("\t", oneBlank);
		//remove extral blank
		str = str.trim();
		while(str.contains(twoBlanks)){
			str = str.replaceAll(twoBlanks,oneBlank);
		}			
		return str; 
	}
	
	private static void line(String cont){
		System.out.println(cont);
	}
	
	 public static boolean deleteFile(String fileName){     
        File file = new File(fileName);     
        if(file.isFile() && file.exists()){     
            file.delete();     
            line("delete "+fileName+" success!");     
            return true;     
        }else{     
        	line("delete "+fileName+" fail!");     
	        return false;     
	    }     
	}       
}
