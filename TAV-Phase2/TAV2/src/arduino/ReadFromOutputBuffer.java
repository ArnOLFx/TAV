package arduino;

import java.io.ByteArrayInputStream;

/**
 * Methods:
 * readObject readFromBuffer(int n);
 */
public class ReadFromOutputBuffer {
	
	// object containing error code and byte stream. (return object)
	class readObject {
		int error;
		ByteArrayInputStream byteStream;
		
		public readObject(int error, ByteArrayInputStream byteStream){
			this.error = error;
			this.byteStream = byteStream;
		}
	}
	
	// the output buffer stream.
	byte[] outputBufferStream = new byte[20];
	
	//initialise auxiliary buffer class for creating buffer packets.
	SpeedAndTorqueBuffer outputBuffer = new SpeedAndTorqueBuffer();
	
	//sample readObject for testing purposes
	readObject testRO = new readObject(0,new ByteArrayInputStream(new byte[0]));
	
	/**
	 * Description: 
	 * takes an int n as input and return an object containing the first n bytes
	 * from the outputBufferStream and an int error code (0 if successful)
	 * 
	 * Pre-condition: 
	 * ReadFromOutputBuffer needs to be instantiated before performing this operation. 
	 *  
	 * Post-condition: 
	 * Returns:
	 *  0 - successful operation
	 * -1 - n < 1
	 * +1 - empty buffer
	 * +2 - n > buffer
	 * 
	 *  Test-cases: 
	 *  test case 1a: positive n, n <= buffer size, buffer not empty (normal situation, return 0 + stream)
	 *  test case 1b: stream of size n
	 *  test case 1c: stream contents match buffer contents
	 *  test case 2: positive n, n <= buffer size, buffer empty (empty buffer, return 1 + empty stream)
	 *  test case 3: n < 1 (invalid n, return -1 + empty stream with size 1)
	 *  test case 4: n > buffer (return 2 + stream with size n including buffer + zeroes)
	 *  
	*/
	public readObject readFromBuffer(int n) {
		
		/*
		 * n < 1 check implemented to fulfil test case 3
		 * -> java.lang.AssertionError: expected:<-1> but was:<0>
		 */
		if (n < 1) return new readObject(-1, new ByteArrayInputStream(new byte[1]));
		
		/*
		 * empty buffer check implemented to fulfil test case 2
		 * -> java.lang.AssertionError: expected:<1> but was:<0>
		 */
		boolean isEmpty = true;
		for (byte i : outputBufferStream) {
			if (i != 0) {
				isEmpty = false;
				break;
			}
		}
		if (isEmpty) return new readObject(1, new ByteArrayInputStream(new byte[n])); // returns error code + empty bytestream
		
		/*
		 * new byte size assigned to n (fulfils test case 1b)
		 * -> java.lang.AssertionError: expected:<10> but was:<1>
		 */
		byte[] stream = new byte[n];
		
		/*
		 * read from buffer implementation (fulfils test case 1c)
		 * -> arrays first differed at element [0]; expected:<99> but was:<0>
		 * 
		 * modified read from buffer implementation to fulfil test case 4
		 * -> java.lang.ArrayIndexOutOfBoundsException: 20
		 */
		for (int i = 0; i < n; i++){
			if (i > outputBufferStream.length -1)
				stream[i] = 0;
			else
				stream[i] = outputBufferStream[i];
		}
		
		readObject streamObject = new readObject(0, new ByteArrayInputStream(stream));
		
		/*
		 * check for n > outputbufferstream implemented (fulfils test case 4)
		 * -> java.lang.AssertionError: expected:<10> but was:<1>
		 */
		if (n > outputBufferStream.length) streamObject.error = 2;
		
		
		return streamObject;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
