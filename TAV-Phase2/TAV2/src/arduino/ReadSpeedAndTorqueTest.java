package arduino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import arduino.ReadSpeedAndTorque.SpeedAndTorque;

public class ReadSpeedAndTorqueTest {
	
	ReadSpeedAndTorque rst;

	@Before
	public void setUp() throws Exception {
		rst = new ReadSpeedAndTorque();
	}

	@Test
	/**
	  * test case 1: 
	  * 	network bytestream == 20 bytes; 
	  * 	correct delimiters; 
	  * 	1 packet; 
	  * 	correct checksum
	  * should return correct speed and torque.
	  */
	public final void testGetSpeedAndTorque1() {
		
		SpeedAndTorque expected = rst.testSAT;
		expected.speed = 25;
		expected.torque = 0.25;
		rst.add20BytePacket(25, 0.25);
		SpeedAndTorque actual = rst.getSpeedAndTorque();
		double[] exp = {expected.speed, expected.torque};
		double[] act = {actual.speed, actual.torque};
		Assert.assertArrayEquals(exp, act, 0);

	
	}
	
	@Test
	/**
	  * test case 2: 
	  * 	network bytestream == 40 bytes (2 packets); 
	  * 	correct delimiters; 
	  * 	packet 1: WRONG checksum   (speed 7, torque 0.75)
	  * 	packet 2: correct checksum (speed 12, torque -0.33)
	  * should return correct speed and torque for 2nd packet.
	  */
	public final void testGetSpeedAndTorque2() {
		SpeedAndTorque expected = rst.testSAT;
		expected.speed = 12;
		expected.torque = -0.33;
		byte[] custom = {99,-65,-24,0,0,0,7,0,0,80,64,28,0,0,0,0,0,0,3,102,    //wrong packet
				99,-65,-43,30,-72,81,-21,-123,31,80,64,40,0,0,0,0,0,0,78,102}; //correct packet
		rst.addCustomPacket(0, custom);
		SpeedAndTorque actual = rst.getSpeedAndTorque();
		double[] exp = {expected.speed, expected.torque};
		double[] act = {actual.speed, actual.torque};
		Assert.assertArrayEquals(exp, act, 0);
	}
	
	@Test
	/**
	  * test case 3: 
	  * 	network bytestream == 39 bytes (2 packets); 
	  * 	correct delimiters; 
	  * 	packet 1: MISSING BYTE (speed 10, torque 0.1)
	  * 	packet 2: correct size (speed -5, torque 0.05)
	  * should return correct speed and torque for 2nd packet.
	  */
	public final void testGetSpeedAndTorque3() {
		SpeedAndTorque expected = rst.testSAT;
		expected.speed = -5;
		expected.torque = 0.05;
		byte[] custom = {
				99,63,-71,-103,-103,-103,-103,-103,-102,80,64,36,0,0,0,0,0,25,102,   //missing byte
				99,63,-87,-103,-103,-103,-103,-103,-102,80,-64,20,0,0,0,0,0,0,85,102 //correct package
		};
		rst.addCustomPacket(0, custom);
		SpeedAndTorque actual = rst.getSpeedAndTorque();
		double[] exp = {expected.speed, expected.torque};
		double[] act = {actual.speed, actual.torque};
		Assert.assertArrayEquals(exp, act, 0);
	}
	
	@Test
	/**
	  * test case 4: 
	  * 	network bytestream == 40 bytes (2 packets); 
	  * 	packet 1: INCORRECT SPEED DELIMITER 81 (speed 20, torque 0.8)
	  * 	packet 2: correct speed delimiter 80   (speed 1, torque 0.2)
	  * should return correct speed and torque for 2nd packet.
	  */
	public final void testGetSpeedAndTorque4() {
		SpeedAndTorque expected = rst.testSAT;
		expected.speed = 1;
		expected.torque = 0.2;
		byte[] custom = {
				99,63,-23,-103,-103,-103,-103,-103,-102,81,64,52,0,0,0,0,0,0,61,102, //incorect speed del
				99,63,-55,-103,-103,-103,-103,-103,-102,80,63,-16,0,0,0,0,0,0,62,102 //correct package
		};
		rst.addCustomPacket(0, custom);
		SpeedAndTorque actual = rst.getSpeedAndTorque();
		double[] exp = {expected.speed, expected.torque};
		double[] act = {actual.speed, actual.torque};
		Assert.assertArrayEquals(exp, act, 0);
	}
	
	@Test
	/**
	  * test case 5: 
	  * 	network bytestream == 30 bytes (1 packet + garbage); 
	  * 	packet 1: start delimiter located on 5th byte
	  * should return correct speed and torque for the packet.
	  */
	public final void testGetSpeedAndTorque5() {
		SpeedAndTorque expected = rst.testSAT;
		expected.speed = 17;
		expected.torque = 0.17;
		byte[] custom = {
				0,0,0,62,102,99,63,-59,-62,-113,92,40,-11,-61,80,64,49,0,0,0,0,0,0,2,102,99,63,-23,-103,-103
		};
		rst.addCustomPacket(0, custom);
		SpeedAndTorque actual = rst.getSpeedAndTorque();
		double[] exp = {expected.speed, expected.torque};
		double[] act = {actual.speed, actual.torque};
		Assert.assertArrayEquals(exp, act, 0);
	}
	
	@Test
	/**
	  * test case 6: 
	  * 	network bytestream < 20 bytes (19 bytes)
	  * 	should return null.
	  */
	public final void testGetSpeedAndTorque6() {
		SpeedAndTorque expected = null;
		byte[] custom = {
				99,63,-23,-103,-103,-103,-103,-103,-102,81,64,52,0,0,0,0,0,61,102
		};
		rst.addCustomPacket(0, custom);
		SpeedAndTorque actual = rst.getSpeedAndTorque();
		Assert.assertEquals(expected, actual);
	}

}
