package com.leo;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class SystemDescription {
	private static SystemDescription instance = null;
	private SystemDescription(){}
	private int starLength = 80;
	
	private String version = "LeoBaseSystem v1.0\n";

	private String help = "\tSHOW SCHEMAS;\t\t\tDisplays all schemas defined in your database.\n"
			
			+ "\tUSE;\t\t\t\tChooses a schema.\n"
			+ "\tSHOW TABLES;\t\t\tDisplays all tables in the currectly chosen schema.\n"
			+"\tCREATE SCHEMA;\t\t\tCreates a new schema to hold tables.\n"
			+ "\tCREATE TABLE;\t\t\tCreates a new table schema, i.e. a new empty table.\n"
			+ "\tINSERT INTO TABLE;\t\tInserts a row/record into a table.\n"
			//+ "\tDELETE FROM;\t\t\tDeletes one or more rows/records from a table.\n"
			+ "\tDROP TABLE;\t\t\tRemove a table schema, and all of its contained data.\n"
			+ "\tSELECT-FROM-WHERE;\t\tstyle query\n"
			+ "\tVERSION;\t\t\tShow the program version.\n"
			+ "\tHELP;\t\t\t\tShow this help information \n"
			+ "\tEXIT;\t\t\t\tExit the program \n";
	
	private String splashScreen = " Welcome to DavisBaseLite \n \n"
			+ "Type \"help;\" to display supported commands.\n";
	
	public static SystemDescription getSingle(){
		if(instance == null){
			instance = new SystemDescription();			 
		}
		return instance;
	}
	void showVersion(){
		System.out.print(version);
	}
	void showHelp(){
		System.out.println(line("*",starLength));
		newline(1);
		System.out.print(help);
		newline(2);
		System.out.println(line("*",starLength));		
	}
	
	void showSplashScreen(){
		System.out.println(line("*",starLength));
		System.out.print(version);
		System.out.print(splashScreen);		
		System.out.println(line("*",starLength));
		
	}	
	
	public String line(String s,int num) {
		String a = "";
		for(int i=0;i<num;i++) {
			a += s;
		}
		return a;
	}
	
	public void newline(int num) {
		for(int i=0;i<num;i++) {
			System.out.println();
		}
	}
}
