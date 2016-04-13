package com.leo;
import java.util.Scanner;

/**
 * @author leo 
 *  email: longhaogao@gmail.com
 *
 * @date Apr 12, 2016
 * @version 1.0
 */
public class LeoBaseSysetm {
	static String prompt = "leosql> ";
	
	void dispatch(){		
		Scanner scanner = new Scanner(System.in).useDelimiter(";");
		String userCommand; // Variable to collect user input from the prompt
		InputRuleExpert exp = new InputRuleExpert();
		do {  // do-while !exit
			System.out.print(prompt);
			userCommand = scanner.next().trim();
			userCommand = userCommand.toLowerCase();
			exp.parseInput(userCommand);
		} while(!userCommand.equals("exit"));		
	}
	
	
	public static void main(String[] args) {
		/* Display the welcome splash screen */
		SystemDescription.getSingle().showSplashScreen();
		LeoBaseSysetm leoBase = new LeoBaseSysetm();
		
		leoBase.dispatch();
	}

}
