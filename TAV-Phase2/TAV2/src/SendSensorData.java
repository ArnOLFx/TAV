import java.io.IOException;
import java.nio.ByteBuffer;

public class SendSensorData implements SendSensorDataInterface {

	@Override
	public byte[] createPacket(double torque, double ultra_distance,
			double ir_distance) {
		// TODO Auto-generated method stub
		
		byte[] outputStream = new byte[31];
		
		byte torqueID = 1;
		byte ultraID = 2;
		byte irID = 3;
		byte delimiter = 126;
		byte endStream = 99;
		
		byte[] torqueToByte = new byte[8];
		byte[] ultraToByte = new byte[8];
		byte[] irToByte = new byte[8];
		
		ByteBuffer.wrap(torqueToByte).putDouble(torque);
		ByteBuffer.wrap(ultraToByte).putDouble(ultra_distance);
		ByteBuffer.wrap(irToByte).putDouble(ir_distance);
	
		outputStream[0] = delimiter;
		outputStream[1] = torqueID;
		for (int i = 0; i < torqueToByte.length; i++) {
			outputStream[2+i] = torqueToByte[i];
		}
		outputStream[10] = delimiter;
		outputStream[11] = ultraID;
		for (int i = 0; i < ultraToByte.length; i++) {
			outputStream[12+i] = ultraToByte[i];
		}
		outputStream[20] = delimiter;
		outputStream[21] = irID;
		for (int i = 0; i < irToByte.length; i++) {
			outputStream[22+i] = irToByte[i];
		}
		outputStream[30] = endStream;
		
		return outputStream;
	}
	/*
	 * Takes a "bitstream" and extracts the double precision
	 * floating point values representing torque, ir/ul -distance.
	 * Returns an array of 3 double elements.
	 */
	public double[] decryptPacket(byte bitstream[]) {
		
		byte byteTorque[] = new byte[8];
		byte byteUlDistance[] = new byte[8];
		byte byteIrDistance[] = new byte[8];
		
		double extractedData[] = new double[3];
		
		for (int i = 0; i < byteTorque.length; i++) {
			byteTorque[i] = bitstream[2+i];
			byteUlDistance[i] = bitstream[12+i];
			byteIrDistance[i] = bitstream[22+i];
		}
		
		extractedData[0] = ByteBuffer.wrap(byteTorque).getDouble();
		extractedData[1] = ByteBuffer.wrap(byteUlDistance).getDouble();
		extractedData[2] = ByteBuffer.wrap(byteIrDistance).getDouble();
		
		return extractedData;
	}
	
	public static void main(String args[]) throws IOException {
		
		SendSensorData send = new SendSensorData();
		
		double torque = 11.6, ulDis = 5.6, irDis = 5.2;
		double decryptedData[];
		
		byte buffer[] = send.createPacket(torque, ulDis, irDis);
		
		for (int i = 0; i < buffer.length; i++) {
			System.out.println("Buffer[" + i + "] = " + buffer[i]);
		}
		
		System.out.println("\n\nFinished reading buffer!\n");
		
		decryptedData = send.decryptPacket(buffer);
		
		for (int i = 0; i < decryptedData.length; i++) {
			System.out.println("Data[" + i + "] = " + decryptedData[i]);
		}
		
		System.out.println("\n\nFinished decrypting data from buffer!");
	}

}
