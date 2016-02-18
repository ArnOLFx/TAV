package arduino;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

public class readUserInputTest {

	//Instantiating the readUserInput class
	readUserInput input;
	//Scanner for getting expected input
	Scanner in;
	
	
	/**
	 * 
	 * @throws Exception
	 * 
	 * Setup for test cases
	 */
	@Before
	public void setUp() throws Exception {
		input = new readUserInput();
		in = new Scanner(System.in);
	}

	/**
	 * Test case 1: reads data from user and tests if the output is equal to the input
	 * In this case we look for torque (the string starts with "t" for torque)
	 */
	@Test
	public void test1() {
					//torque
		double expected = 0.5;
		
		double actual = input.getUserInput();
		
		Assert.assertEquals(expected, actual, 0);
	}
	
	/**
	 * Test case 2: reads data from user and tests if the output is equal to the input
	 * In this case we look for ultraDistance (the string starts with "u" for "ultra")
	 */
	@Test
	public void test2() {
					//ul
		double expected = 5;
		
		double actual = input.getUserInput();
		
		Assert.assertEquals(expected, actual, 0);
	}
	
	/**
	 * Test case 3: reads data from user and tests if the output is equal to the input
	 * In this case we look for irDistance (the string starts with "i" for "ir")
	 */
	@Test
	public void test3() {
					//ir
		double expected = 5;
		
		double actual = input.getUserInput();
		
		Assert.assertEquals(expected, actual, 0);
	}
	
	/**
	 * Test case 3: reads data from user and tests if the output is equal to the input
	 * In this case we look for torque and if it is in bounds
	 */
	@Test
	public void test4() {
					//torque
		double expected = 99.0;
		
		double actual = input.getUserInput();
		
		Assert.assertEquals(expected, actual, 0);
	}
	
	/**
	 * Test case 3: reads data from user and tests if the output is equal to the input
	 * In this case we look for ultraDistance and if it is in bounds
	 */
	@Test
	public void test5() {
					//ultra
		double expected = 99.0;
		
		double actual = input.getUserInput();
		
		Assert.assertEquals(expected, actual, 0);
	}
	
	/**
	 * Test case 3: reads data from user and tests if the output is equal to the input
	 * In this case we look for irDistance and if it is in bounds
	 */
	@Test
	public void test6() {
					//ir
		double expected = 99.0;
		
		double actual = input.getUserInput();
		
		Assert.assertEquals(expected, actual, 0);
	}
}
