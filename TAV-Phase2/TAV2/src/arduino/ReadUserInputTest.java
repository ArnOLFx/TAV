package arduino;

import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ReadUserInputTest {

	//Instantiating the readUserInput class
	ReadUserInput input;
	//Instantiating the TestDisplay class
	TestDisplay display;
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
		input = new ReadUserInput();
		display = mock(TestDisplay.class);
		
		in = new Scanner(System.in);
	}

	/**
	 * Test case 1: reads data from user and tests if the output is equal to the input
	 * In this case we look for torque (the string starts with "t" for torque)
	 */
	@Test
	public void test1() {
					//torque
		
		System.out.print("\nEnter torque value (0.5): ");
		
		display.setTorque(Double.parseDouble(in.nextLine()));
		
		when(display.getTorque()).thenReturn(0.5);
		
		/* WHAT WE HAD BEFORE MOCKITO
		double expected = 0.5;
		
		System.out.print("\nEnter torque value (0.5): ");
		
		double actual = input.getUserInputTorque(in.nextLine());
		
		Assert.assertEquals(expected, actual, 0);*/
	}
	
	/**
	 * Test case 2: reads data from user and tests if the output is equal to the input
	 * In this case we look for ultraDistance (the string starts with "u" for "ultra")
	 */
	@Test
	public void test2() {
					//ul
		double expected = 5;
		
		System.out.print("\nEnter ultra value (5): ");
		
		double actual = input.getUserInputUltra(in.nextLine());
		
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
		
		System.out.print("\nEnter ir value (5): ");
		
		double actual = input.getUserInputIr(in.nextLine());
		
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
		
		System.out.print("\nEnter torque value (5): ");
		
		double actual = input.getUserInputTorque(in.nextLine());
		
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
		
		System.out.print("\nEnter ultra value (50): ");
		
		double actual = input.getUserInputUltra(in.nextLine());
		
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
		
		System.out.print("\nEnter ultra value (50): ");
		
		double actual = input.getUserInputIr(in.nextLine());
		
		Assert.assertEquals(expected, actual, 0);
	}
	
	/**
	 * Test case 3: reads data from user and tests if the output is equal to the input
	 * In this case we look for irDistance and if it is in bounds
	 */
	@Test
	public void test7() {
					//ir
		//Assert.assertEquals(5, actual)
	}
}
