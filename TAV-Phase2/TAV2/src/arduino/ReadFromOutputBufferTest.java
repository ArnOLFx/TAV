package arduino;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReadFromOutputBufferTest {
	
	ReadFromOutputBuffer read;
	byte[] emptyBuffer;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		emptyBuffer = new byte[20];
		read = new ReadFromOutputBuffer();
		read.outputBufferStream = read.outputBuffer.createMockPacket(15, 0.75);
	}

	/**
	 * Test method for {@link arduino.ReadFromOutputBuffer#readFromBuffer(int)}.
	 * test case 1a: positive n, n <= buffer size, buffer not empty (normal situation, return 0 + stream)
	 */
	@Test
	public final void testReadFromBuffer1a() {
		int expected = 0; //successful operation
		int actual = read.readFromBuffer(20).error;
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.ReadFromOutputBuffer#readFromBuffer(int)}.
	 * test case 1b: stream of size n (10)
	 */
	@Test
	public final void testReadFromBuffer1b() {
		int expected = 10;
		int actual = read.readFromBuffer(10).byteStream.available();
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.ReadFromOutputBuffer#readFromBuffer(int)}.
	 * test case 1c: stream contents match buffer contents
	 */
	@Test
	public final void testReadFromBuffer1c() {
		byte[] expected = read.outputBufferStream;
		byte[] actual = new byte[20];
		int c;
		int i = 0;
		ByteArrayInputStream input = read.readFromBuffer(20).byteStream;
		while((c = input.read())!= -1) {
            actual[i] = (byte) c;
            i++;
         }
		Assert.assertArrayEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.ReadFromOutputBuffer#readFromBuffer(int)}.
	 * test case 2: positive n, n <= buffer size, buffer empty (empty buffer, return 1 + empty stream)
	 */
	@Test
	public final void testReadFromBuffer2() {
		read.outputBufferStream = emptyBuffer; //clears the buffer
		int expected = 1; //empty buffer
		int actual = read.readFromBuffer(20).error;
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.ReadFromOutputBuffer#readFromBuffer(int)}.
	 * test case 3: n < 1 (invalid n, return -1 + empty stream with size 1)
	 */
	@Test
	public final void testReadFromBuffer3() {
		int expected = -1; //invalid n
		int actual = read.readFromBuffer(0).error;
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * Test method for {@link arduino.ReadFromOutputBuffer#readFromBuffer(int)}.
	 * test case 4: n > buffer (return 2 + stream with size n including buffer + zeroes)
	 */
	@Test
	public final void testReadFromBuffer4() {
		int expected = 2; //n > buffer
		int actual = read.readFromBuffer(30).error;
		Assert.assertEquals(expected, actual);
	}

}
