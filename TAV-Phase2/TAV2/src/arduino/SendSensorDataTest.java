/**
 * 
 */
package arduino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author group3
 */
public class SendSensorDataTest {
	
	SendSensorData send;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		 send = new SendSensorData();
	}

	/**
	 * Test method for {@link arduino.SendSensorData#createPacket(torque, ultra_distance, ir_distance)}.
	 * test case 1: all input values in range: (0.5,5,5)
	 */
	@Test
	public void testCase1() {
		byte expected[] = {99,63,-32,0,0,0,0,0,0,100,64,20,0,0,0,0,0,0,101,64,20,0,0,0,0,0,0,99,102};
		byte actual[] = send.createPacket(0.5,5,5);
		Assert.assertArrayEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.SendSensorData#createPacket(torque, ultra_distance, ir_distance)}.
	 * test case 2: torque outside range (bigger) ('3',1,1)
	 */
	@Test
	public void testCase2() {	
		byte expected[] = {98,64,8,0,0,0,0,0,0,100,63,-16,0,0,0,0,0,0,101,63,-16,0,0,0,0,0,0,66,102};
		byte actual[] = send.createPacket(3, 1, 1);
		Assert.assertArrayEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.SendSensorData#createPacket(torque, ultra_distance, ir_distance)}.
	 * test case 3: torque (smaller) and ultra_distance (smaller) outside range ('-2','-12',6)
	 */
	@Test
	public void testCase3() {
		byte expected[] = {98,-64,0,0,0,0,0,0,0,98,-64,40,0,0,0,0,0,0,101,64,24,0,0,0,0,0,0,0,102};
		byte actual[] = send.createPacket(-2, -12, 6);	
		Assert.assertArrayEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.SendSensorData#createPacket(torque, ultra_distance, ir_distance)}.
	 * test case 4: torque (bigger) and ir_distance (smaller) outside range ('100',4,'-0.1')
	 */
	@Test
	public void testCase4() {
		byte expected[] = {98,64,89,0,0,0,0,0,0,100,64,16,0,0,0,0,0,0,98,-65,-71,-103,-103,-103,-103,-103,-102,20,102};
		byte actual[] = send.createPacket(100, 4, -0.1);
		Assert.assertArrayEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.SendSensorData#createPacket(torque, ultra_distance, ir_distance)}.
	 * test case 5: torque (smaller), ultra_distance(bigger) and ir_distance (bigger) outside range (-1.0001,10.0001,10.0001)
	 */
	@Test
	public void testCase5() {
		byte expected[] = {98,-65,-16,0,104,-37,-117,-84,113,98,64,36,0,13,27,113,117,-114,98,64,36,0,13,27,113,117,-114,10,102};	
		byte actual[] = send.createPacket(-1.0001,10.0001,10.0001);
		Assert.assertArrayEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.SendSensorData#createPacket(torque, ultra_distance, ir_distance)}.
	 * test case 6: ultra_distance(smaller) outside range (0,-999.999,0)
	 */
	@Test
	public void testCase6() {
		byte expected[] = {99,0,0,0,0,0,0,0,0,98,-64,-113,63,-3,-13,-74,69,-94,101,0,0,0,0,0,0,0,0,29,102};	
		byte actual[] = send.createPacket(0,-999.999,0);	
		Assert.assertArrayEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.SendSensorData#createPacket(torque, ultra_distance, ir_distance)}.
	 * test case 7: ultra_distance(bigger) and ir_distance(smaller) outside range (0.99997,12.34567,-12.34567)
	 */
	@Test
	public void testCase7() {
		byte expected[] = {99,63,-17,-1,-63,21,-33,101,86,98,64,40,-80,-5,-88,-126,106,-87,98,-64,40,-80,-5,-88,-126,106,-87,23,102};
		byte actual[] = send.createPacket(0.99997,12.34567,-12.34567);
		Assert.assertArrayEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.SendSensorData#createPacket(torque, ultra_distance, ir_distance)}.
	 * test case 8: ir_distance(bigger) outside range (1,10,999999999)
	 */
	@Test
	public void testCase8() {
		byte expected[] = {99,63,-16,0,0,0,0,0,0,100,64,36,0,0,0,0,0,0,98,65,-51,-51,100,-1,-128,0,0,81,102};
		byte actual[] = send.createPacket(1,10,999999999);
		Assert.assertArrayEquals(expected, actual);
	}
}

