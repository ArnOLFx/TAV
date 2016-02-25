package arduino;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;

/**
 *  Methods:
 *  SpeedAndTorque getSpeedAndTorque();
 *  void add20BytePacket(double speed, double torque);
 *  void addCustomPacket(int errorCode, byte[] custom);
 *  public byte[] getPacket();
 */
public class ReadSpeedAndTorque {
	
	//initialise ReadFromOutputBuffer class for creating readObjects for the network bytestream.
	ReadFromOutputBuffer rfoBuffer;
	
	//private field (attribute) containing the current bytestream from received from the network
	byte[] testBA = {0};
	ByteArrayInputStream bais = new ByteArrayInputStream(testBA);
	private readObject networkBytestream;
	
	SpeedAndTorque testSAT = new SpeedAndTorque(0,0);
	
	public ReadSpeedAndTorque(ReadFromOutputBuffer outputBuffer){
		this.rfoBuffer = outputBuffer;
		this.networkBytestream = outputBuffer.testRO;
	}
	
	
	/**
	 * Description: 
	 * Auxiliary method for testing. Creates a mock speed and torque packet and
	 * places it in the networkBytestream buffer. Needed in order to be able to
	 * populate the private field for testing.
	 * 
	 * Pre-condition: 
	 * ReadSpeedAndTorque needs to be instantiated before performing this operation.
	 *  
	 * Post-condition: 
	 * networkBytestream contains generated object.
	 * 
	 *  Test-cases: 
	 *  As networkBytestream is a private field, this method cannot be directly tested  
	*/
	public void add20BytePacket(double speed, double torque){
		rfoBuffer.odroidData = rfoBuffer.outputBuffer.createMockPacket(speed, torque);
		networkBytestream = rfoBuffer.readFromBuffer(20);
	}
	
	/**
	 * Description: 
	 * Auxiliary method for testing. Creates a custom speed and torque packet and
	 * places it in the networkBytestream buffer. Useful when testing for wrong delimiters,
	 * size, values...
	 * 
	 * Pre-condition: 
	 * ReadSpeedAndTorque needs to be instantiated before performing this operation.
	 *  
	 * Post-condition: 
	 * networkBytestream contains generated object.
	 * 
	 *  Test-cases: 
	 *  As networkBytestream is a private field, this method cannot be directly tested  
	*/
	public void addCustomPacket(int errorCode, byte[] custom){
		networkBytestream.error = errorCode;
		networkBytestream.byteStream = new ByteArrayInputStream(custom);
	}
	
	/**
	 * Description: 
	 * reads the private field networkBitstream, searches for packet delimiters, checks if
	 * packet is not corrupted and returns an object SpeedAndTorque containing the values
	 * of speed and torque (double).
	 * 
	 * Pre-condition: 
	 * ReadSpeedAndTorque needs to be instantiated before performing this operation.
	 *  
	 * Post-condition: 
	 * Returns:
	 * SpeedAndTorque object if successful
	 * 
	 * Test-cases: 
	 *  
	 *  
	*/
	public SpeedAndTorque getSpeedAndTorque(){
		byte[] packet = getPacket();
		
		/*
		 * null package check (test case 6)
		 */
		if (packet == null) return null;
		
		byte[] speedArray = new byte[8];
		byte[] torqueArray = new byte[8];
		
		for (int i = 0; i < 8; i++){
			torqueArray[i] = packet[1+i];
			speedArray[i]  = packet[10+i];
			
		}
		double speedD = ByteBuffer.wrap(speedArray).getDouble();
		double torqueD = ByteBuffer.wrap(torqueArray).getDouble();
		
		System.out.println(speedD + ", " + torqueD);
		
		SpeedAndTorque result = new SpeedAndTorque(speedD,torqueD);
		
		return result;
	}
	
	/**
	 * Description: 
	 * reads the private field networkBitstream, searches for packet delimiters, checks if
	 * packet is not corrupted and returns it as a byte array.
	 * 
	 * Pre-condition: 
	 * ReadSpeedAndTorque needs to be instantiated before performing this operation.
	 *  
	 * Post-condition: 
	 * Returns:
	 * packet in a byte array
	 * 
	 * Test-cases: 
	 * Implicitly tested when getSpeedAndTorque() is tested.
	*/
	public byte[] getPacket(){
		
		byte[] packet = new byte[20]; //stores the returning packet
		
		/*
		 * while loop through network bytestream + constants 
		 * implemented to fulfill test case 1 (get packet from bytestream)
		 * -> java.lang.ArrayIndexOutOfBoundsException: 1
		 */
		int pos = 0;  				  //marks the current position in the array to be filled
	    int current;				  //marks the current byte from the network bytestream
		boolean start = false;	      //start delimiter has been found or not
		boolean end = false;		  //end delimiter has been found or not
		
		networkBytestream = rfoBuffer.readFromBuffer(50);

		while ((current = networkBytestream.byteStream.read()) != -1) {
			if (pos > 19) {
				break;
			}
			if (!start){
				if (current == 99){
					packet[pos] = (byte) current;
					start = true;
					pos++;
				}
			} 
			else if (start && !end) {
				if (current != 102) {
					/*
					 * speed del check implemented to fulfill test case 4 (correct delimiter)
					 */
					if (pos == 9 && current != 80){
						pos = 0;
						start = false;
					} else {
						packet[pos] = (byte) current;
						pos++;
					}
					
				}
				else {
					packet[pos] = (byte) current;
					/*
					 * pos check implemented to fulfill test case 3 (packet size == 20)
					 */
					if (checkSum(packet) && pos == 19) {
						end = true;
						break;
					}
					else {
						pos = 0;
						start = false;
					}
					
				}
			}
		}

		/*
		 * no package check (test case 6)
		 */
		if (!start || !end) return null;
		
		return packet;
	}
	
	/**
	 * Description: 
	 * Perform checkSum test in a package in order to determine if it is corrupted
	 * 
	 * Pre-condition: 
	 * ReadSpeedAndTorque needs to be instantiated before performing this operation.
	 *  
	 * Post-condition: 
	 * Returns:
	 * false for corrupted packages, true otherwise
	 * 
	 * Test-cases: 
	 * Implicitly tested when getSpeedAndTorque() is tested.
	*/
	public boolean checkSum(byte[] packet){
		/*
		 * method implemented to fulfil test case 2 (check for corrupted packages)
		 * -> java.lang.ArrayIndexOutOfBoundsException: 1
		 */
		int expected = packet[18];
		int actual = 0;
		
		for (int i = 0; i < 8; i++){
			actual += packet[1+i];
			actual += packet[10+i];
		}
		
		String actualStr = String.valueOf(actual);
		if (actualStr.length() > 2) {
			actual = Integer.parseInt(actualStr.substring(actualStr.length()-2, actualStr.length()));
		}
		
		if (expected == actual) return true;
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadSpeedAndTorque rst = new ReadSpeedAndTorque(new ReadFromOutputBuffer(new Odroid()));
		rst.add20BytePacket(10.0, 0.25);
		//SpeedAndTorque geronimo = rst.getSpeedAndTorque();
		
		byte[] val = rst.getPacket();
		
		for (int i = 0; i < rst.getPacket().length; i++) {
			System.out.println(val[i] + " ");
		}

	}

}
//class containing values of speed and torque (return object)
	class SpeedAndTorque {
		double speed;
		double torque;
		
		public SpeedAndTorque(double speed, double torque) {
			this.speed = speed;
			this.torque = torque;
		}
	}