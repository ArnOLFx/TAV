package arduino;

import java.nio.ByteBuffer;

/**
 * Methods:
 * byte[] createMockPacket(double speed, double torque)
 * byte[] doubleToByteArray (double value);
 * byte genSpeedAndTorqueChecksum(byte[] torque, byte[] speed);
 */
public class SpeedAndTorqueBuffer {
	
	/**
	 * Description: 
	 * Generates a packet that can be used in the outputBufferStream.
	 * Optional method, not essential for the assignment
	 * 
	 * Pre-condition: 
	 * SpeedAndTorqueBuffer instantiated
	 * 
	 * Post-condition: 
	 * Returns a 20-byte packet representation of the speed and torque packet
	 * 
	 * Test-cases: 
	 * Not applicable.
	*/
	public byte[] createMockPacket(double speed, double torque){
		
		byte[] packet = new byte[20];
		
		byte startDel = 99, speedDel = 80, endDel = 102, errorDel = 98; 
		
		// check if torque and speed are in range
		if (torque > 1 || torque < -1) startDel = errorDel;
		if (speed > 25 || speed < -15) speedDel = errorDel;
		
		packet[0] = startDel;
		packet[9] = speedDel;
		packet[19] = endDel;
		
		// double to byte[] conversions
		byte[] torqueArray = doubleToByteArray(torque);
		byte[] speedArray = doubleToByteArray(speed);
		
		for (int i = 0; i < torqueArray.length; i++){
			packet[1+i] = torqueArray[i];
		}
		for (int i = 0; i < speedArray.length; i++){
			packet[10+i] = speedArray[i];
		}
		
		/* checksum implemented to fulfil test case 1
		 * -> arrays first differed at element [27]; expected:<99> but was:<0>
		 */
		packet[18] = genSpeedAndTorqueChecksum(torqueArray, speedArray);
		
		return packet;
	}
	
	/**
	 * Description: 
	 * Takes a double value and return it as a 8-byte array
	 * Auxiliary method for createMockPacket(), not essential for the assignment
	 * 
	 * Pre-condition: 
	 * SpeedAndTorqueBuffer needs to be instantiated before performing this operation.
	 * Input value must be a valid double
	 * 
	 * Post-condition: 
	 * Returns a 8-byte array representation of the input double
	 * 
	 *  Test-cases: 
	 *	Not applicable
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
	 * SpeedAndTorqueBuffer needs to be instantiated before performing this operation.
	 * Input values must be valid byte arrays
	 * 
	 * Post-condition: 
	 * Returns a 1 byte checksum
	 * 
	 *  Test-cases: 
	 *	All test cases will only pass if the checksum is correct
	*/
	public byte genSpeedAndTorqueChecksum(byte[] torque, byte[] speed){
		int byteSum = 0;
		
		// three loops add each byte values to the byteSum value
		for (int i : torque) {
			byteSum += i;
		}
		for (int i : speed) {
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpeedAndTorqueBuffer satb = new SpeedAndTorqueBuffer();
		byte[] array = satb.createMockPacket(15.0, 0.75);
		String printArray = "";
		for (int i = 0; i < array.length; i++){
			if (i == array.length -1) printArray += array[i];
			else printArray += array[i] + ",";
		}
		System.out.println(printArray);

	}
}
