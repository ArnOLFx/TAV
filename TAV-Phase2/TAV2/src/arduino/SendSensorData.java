package arduino;

import java.nio.ByteBuffer;

/**
 * Methods:
 * byte[] createPacket(double torque, double ultra_distance, double ir_distance);
 * byte[] doubleToByteArray (double value);
 * byte genSensorChecksum(byte[] torque, byte[] ultra, byte[] ir);
 * 
 */
public class SendSensorData {
	
	/**
	 * Description: 
	 * takes three sensor values as input and create a bitstream packet with 
	 * delimiters and checksum mechanism.
	 * 
	 * Pre-condition: 
	 * SendSensorData needs to be instantiated before performing this operation. 
	 * Sensor values must be received.
	 * 
	 * Post-condition: 
	 * Returns a bitstream packet as a byte array containing the appropriate
	 * value delimiters and checksum
	 * 
	 *  Test-cases: 
	 *  test case 1 - all input values in range: (0.5,5,5)
	 *  test case 2 - torque outside range (bigger) ('3',1,1)
	 *  test case 3 - torque (smaller) and ultra_distance (smaller) outside range ('-2','-12',6)
	 *  test case 4 - torque (bigger) and ir_distance (smaller) outside range ('100',4,'-0.1')
	 *  test case 5 - torque (smaller), ultra_distance(bigger) and ir_distance (bigger) outside range (-1.0001,10.0001,10.0001)
	 *  test case 6 - ultra_distance(smaller) outside range (0,-999.999,0)
	 *  test case 7 - ultra_distance(bigger) and ir_distance(smaller) outside range (0.99997,12.34567,-12.34567)
	 *  test case 8 - ir_distance(bigger) outside range (1,10,999999999)
	*/
	public byte[] createPacket(double torque, double ultra_distance, double ir_distance){
		
		byte[] packet = new byte[29];
		
		/* Delimiters implemented to fulfil test case 1
		 * -> arrays first differed at element [0]; expected:<99> but was:<0>
		 */
		byte startDel = 99, ultraDel = 100, irDel = 101, endDel = 102, errorDel = 98; 
		
		/* Torque range check implemented to fulfil test case 2
		 * -> arrays first differed at element [0]; expected:<98> but was:<99>
		 */
		if (torque > 1 || torque < -1) startDel = errorDel;
		
		/* ultra_Distance range check implemented to fulfil test case 3
		 * -> arrays first differed at element [9]; expected:<98> but was:<100>
		 */
		if (ultra_distance > 10 || ultra_distance < 0) ultraDel = errorDel;
		
		/* ir_Distance range check implemented to fulfil test case 4
		 * -> arrays first differed at element [18]; expected:<98> but was:<101>
		 */
		if (ir_distance > 10 || ir_distance < 0) irDel = errorDel;
		
		packet[0] = startDel;
		packet[9] = ultraDel;
		packet[18] = irDel;
		packet[28] = endDel;
		
		/* double to byte[] conversions implemented to fulfil test case 1
		 * -> arrays first differed at element [1]; expected:<63> but was:<0>
		 */
		byte[] torqueArray = doubleToByteArray(torque);
		byte[] ultraArray = doubleToByteArray(ultra_distance);
		byte[] irArray = doubleToByteArray(ir_distance);
		
		for (int i = 0; i < torqueArray.length; i++){
			packet[1+i] = torqueArray[i];
		}
		for (int i = 0; i < ultraArray.length; i++){
			packet[10+i] = ultraArray[i];
		}
		for (int i = 0; i < irArray.length; i++){
			packet[19+i] = irArray[i];
		}
		
		/* checksum implemented to fulfil test case 1
		 * -> arrays first differed at element [27]; expected:<99> but was:<0>
		 */
		packet[27] = genSensorChecksum(torqueArray, ultraArray, irArray);
		
		return packet;
	}
	
	/**
	 * Description: 
	 * Takes a double value and return it as a 8-byte array
	 * 
	 * Pre-condition: 
	 * SendSensorData needs to be instantiated before performing this operation.
	 * Input value must be a valid double
	 * 
	 * Post-condition: 
	 * Returns a 8-byte array representation of the input double
	 * 
	 *  Test-cases: 
	 *	All test cases will only pass if byte representations are correct
	*/
	public byte[] doubleToByteArray (double value){
		byte[] result = new byte[8];
		ByteBuffer.wrap(result).putDouble(value);
		return result;
	}
	
	/**
	 * Description: 
	 * Takes three byte array values and implements a simple checksum
	 * algorithm, returning its value as a byte
	 * 
	 * Pre-condition: 
	 * SendSensorData needs to be instantiated before performing this operation.
	 * Input values must be a valid byte arrays
	 * 
	 * Post-condition: 
	 * Returns a 1 byte checksum
	 * 
	 *  Test-cases: 
	 *	All test cases will only pass if the checksum is correct
	*/
	public byte genSensorChecksum(byte[] torque, byte[] ultra, byte[] ir){
		int byteSum = 0;
		
		// three loops add each byte values to the byteSum value
		for (int i : torque) {
			byteSum += i;
		}
		for (int i : ultra) {
			byteSum += i;
		}
		for (int i : ir) {
			byteSum += i;
		}
		
		// converts byteSum to string and returns the last 2 digits as a byte
		String result2 = String.valueOf(byteSum);
		
		if (result2.length() > 2) {
			int result3 = Integer.parseInt(result2.substring(result2.length()-2, result2.length()));
			return (byte) result3;
		} else {
			int result3 = Integer.parseInt(result2);
			return (byte) result3;
		}
	}
}
