package arduino;

import java.util.Scanner;

public class readUserInput {
	
	Scanner sc;
	String globalInput;
	
	public readUserInput() {
		sc = new Scanner(System.in);
	}
	
	/**
	 * Description: 
	 * This method makes use of the Scanner, which is instantiated in the constructor.
	 * It get input from the user via console in the form of a string.
	 * 
	 * Pre-condition: 
	 * readUserInput needs to be instantiated before performing this operation. 
	 *  
	 * Post-condition: 
	 * Returns String containing input values
	 * 
	 *  Test-cases: 
	 *  test case 1: check if user input (a torque value) is what the program expected.
	 *  test-case 2: check if user input (a ultra distance value) is what the program expected.
	 *  test-case 3: check if user input (a ir distance value) is what the program expected.
	 *  
	*/
	double getUserInput() {
		String input = "";
		
		System.out.println("Enter input: ");
		
		//implemented to pass test case 1, to get the input from the user.
		input = sc.nextLine();
		
		/**
		 * Added switch case with string -> double conversion to satisfy test cases 1-3, which expect a double value as output from this method.
		 * Also added a default case to return an error message, in this case its 99 (meaning error).
		 * Also added a check to see if the values are in range of acceptable values.
		 */
		switch(input.charAt(0)) {
		case 't':
			double torque = Double.parseDouble(input.substring(1, input.length()));
			
			if (torque < -1 || torque > 1) {
				return 99.0;
			} else {
				return (torque);
			}
		case 'u':
			double ultra = Double.parseDouble(input.substring(1, input.length()));
			if (ultra < 0 || ultra > 10) {
				return 99.0;
			} else {
				return (ultra);
			}
		case 'i':
			double ir = Double.parseDouble(input.substring(1, input.length()));
			if (ir < 0 || ir > 10) {
				return 99.0;
			} else {
				return (ir);
			}
		default:
			return 99.0;
		}
	}
}