/**
 * 
 */
package arduino;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author group3
 *
 */
public class WriteToInputBufferTest {
	
	WriteToInputBuffer write;
	SendSensorData send;
	byte[] emptyPacket;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		write = new WriteToInputBuffer();
		send = new SendSensorData();
		emptyPacket = new byte[29];
	}
	
	/**
	 * @throws java.lang.Exception
	 * Buffer is cleared after each test
	 */
	@After
	public void clearBuffer() throws Exception {
		write.inputBufferByteStream.reset();
	}

	/**
	 * Test method for {@link arduino.WriteToInputBuffer#sendByteToBuffer(int, byte[])}.
	 * test case 1a: positive n, n <= packet size, packet not empty (normal situation, return 0)
	 */
	@Test
	public final void testSendByteToBuffer1a() {
		int expected = 0; //successful operation
		int actual = write.sendByteToBuffer(10, send.createPacket(0.5, 2, 3));
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.WriteToInputBuffer#sendByteToBuffer(int, byte[])}.
	 * test case 1b: buffer contains the first n bytes of the packet.
	 * int n = 10;
	 */
	@Test
	public final void testSendByteToBuffer1b() {
		write.sendByteToBuffer(10, send.createPacket(0.5, 5, 5));
		byte[] expected = {99,63,-32,0,0,0,0,0,0,100};
		byte[] actual = write.inputBufferByteStream.toByteArray();
		Assert.assertArrayEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.WriteToInputBuffer#sendByteToBuffer(int, byte[])}.
	 * test case 2: positive n, packet not empty, n > packet (n too large, return 2)
	 */
	@Test
	public final void testSendByteToBuffer2() {
		int expected = 2; //n too large
		int actual = write.sendByteToBuffer(35, send.createPacket(0.5, 2, 3));
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.WriteToInputBuffer#sendByteToBuffer(int, byte[])}.
	 * test case 3: positive n, n <= packet, empty packet (no data, return 1)
	 */
	@Test
	public final void testSendByteToBuffer3() {
		int expected = 1; //no data
		int actual = write.sendByteToBuffer(29, emptyPacket);
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.WriteToInputBuffer#sendByteToBuffer(int, byte[])}.
	 * test case 4: n < 1 (invalid n, return -1)
	 */
	@Test
	public final void testSendByteToBuffer4() {
		int expected = -1; //n < 1
		int actual = write.sendByteToBuffer(0, send.createPacket(0.5, 2, 3));
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.WriteToInputBuffer#sendByteToBuffer(int, byte[])}.
	 * test case 5: n < 1, empty packet (invalid n, return -1)
	 */
	@Test
	public final void testSendByteToBuffer5() {
		int expected = -1; //n < 1
		int actual = write.sendByteToBuffer(-1, emptyPacket);
		Assert.assertEquals(expected, actual);
	}

}
