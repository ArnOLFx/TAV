package arduino;

import java.util.Scanner;

public class ReadUserInput {
	
	Scanner sc;
	private double torque, ultra, ir;
	
	public ReadUserInput() {
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
	double getUserInputTorque(String input) {
		//String input = "";
		
		//System.out.print("\nEnter input (ex t0.5): ");
		
		//implemented to pass test case 1, to get the input from the user.
		//input = sc.nextLine();
		
		/**
		 * Added switch case with string -> double conversion to satisfy test cases 1-3, which expect a double value as output from this method.
		 * Also added a default case to return an error message, in this case its 99 (meaning error).
		 * Also added a check to see if the values are in range of acceptable values.
		 * Also added a check to see if there exists a value after the letter.
		 */
	
		double torque;
		
		try {
			
			if (input.substring(0, input.length()).isEmpty()) {
				return 99.0;
			}
			
			torque = Double.parseDouble(input.substring(0, input.length()));
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return 99.0;
		}
		
		if (torque < -1 || torque > 1) {
			return 99.0;
		} else {
			this.torque = torque;
			return (torque);
		}
	}
	
	public double getUserInputUltra(String input) {
		
		double ultra;
		
		try {
			
			if (input.substring(0, input.length()).isEmpty()) {
				return 99.0;
			}
			
			ultra = Double.parseDouble(input.substring(0, input.length()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 99.0;
		}
		if (ultra < 0 || ultra > 10) {
			return 99.0;
		} else {
			this.ultra = ultra;
			return (ultra);
		}
	}
	
	public double getUserInputIr(String input) {
		
		double ir;
		
		try {
			
			if (input.substring(0, input.length()).isEmpty()) {
				return 99.0;
			}
			
			ir = Double.parseDouble(input.substring(0, input.length()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 99.0;
		}
		if (ir < 0 || ir > 10) {
			return 99.0;
		} else {
			this.ir = ir;
			return (ir);
		}
		
	}
	
	public double getTorque() {
		return torque;
	}
	
	public double getUltra() {
		return ultra;
	}
	
	public double getIr() {
		return ir;
	}
}