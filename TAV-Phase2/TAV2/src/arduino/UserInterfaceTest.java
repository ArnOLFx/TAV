/**
 * 
 */
package arduino;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * @author group3
 *
 */
public class UserInterfaceTest {
	
		//Instantiating the readUserInput class
		ReadUserInput input;
		Scanner in;
	
	
	
	@Before
	public void setUp() throws Exception {
		
		input = mock(ReadUserInput.class);
		in = new Scanner(System.in);
	}

	@Test
	public void test1() {
		fail("Not yet implemented");
	}

}
