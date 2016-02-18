
package arduino;

import java.io.ByteArrayOutputStream;

/**
 * Methods:
 * int sendByteToBuffer(int n, byte[] stream);
 * 
 */
public class WriteToInputBuffer {
	
	// buffer in memory - all data sent to the stream is stored in the buffer. 32-byte buffer (standard)
	ByteArrayOutputStream inputBufferByteStream = new ByteArrayOutputStream();
	
	/**
	 * Description: 
	 * takes a packet of data from the sensors and an integer n, attaches the n first bytes of
	 * the bytestream to the input buffer bytestream and returns 0 if the operation is successful,
	 * -1, 1 or 2 if there are errors.
	 * 
	 * Pre-condition: 
	 * WriteToInputBuffer needs to be instantiated before performing this operation. 
	 * SendSensorData should be instantiated, so sensor data can be generated.
	 * 
	 * Post-condition: 
	 * Returns:
	 *  0 - successful operation
	 * -1 - n < 1
	 * +1 - empty packet
	 * +2 - n > packet
	 * 
	 *  Test-cases: 
	 *  test case 1: positive n, n <= packet size, packet not empty (normal situation, return 0)
	 *  test case 2: positive n, packet not empty, n > packet (n too large, return 2)
	 *  test case 3: positive n, n <= packet, empty packet (no data, return 1)
	 *  test case 4: n < 1 (invalid n, return -1)
	 *  test case 5: n < 1, empty packet (invalid n, return -1)
	 *  
	*/
	public int sendByteToBuffer(int n, byte[] stream){
		
		/*
		 * n < 1 check implemented to fulfil test case 4
		 * -> java.lang.AssertionError: expected:<-1> but was:<0>
		 */
		if (n < 1) return -1;
		
		/*
		 * n > packet check implemented to fulfil test case 2
		 * -> java.lang.ArrayIndexOutOfBoundsException: 29
		 */
		if (n > stream.length) return 2;
		
		/*
		 * empty packet check implemented to fulfil test case 3
		 * -> expected:<1> but was:<0>
		 */
		boolean isEmpty = true;
		for (byte i : stream) {
			if (i != 0) {
				isEmpty = false;
				break;
			}
		}
		if (isEmpty) return 1;
		
		/*
		 * Write to buffer operation implemented to fulfil test case 1b
		 * -> array lengths differed, expected.length=10 actual.length=0
		 */
		for (int i = 0; i < n; i++){
			inputBufferByteStream.write(stream[i]);
		}
		
		return 0;
	}
}
