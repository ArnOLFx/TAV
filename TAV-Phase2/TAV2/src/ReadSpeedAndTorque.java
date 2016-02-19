import java.nio.ByteBuffer;

public class ReadSpeedAndTorque implements ReadSpeedAndTorqueInterface {
	
	@Override
	public SpeedAndTorque getSpeedAndTorque(byte[] stream) {
		
		byte[] byteTorque = new byte[8];
		for (int i = 0; i < byteTorque.length; i++) {
			byteTorque[i] = stream[2+i];
		}
		double torque = ByteBuffer.wrap(byteTorque).getDouble();
		
		SpeedAndTorque result = new SpeedAndTorque(10, torque);
		
		return result;
	}

}
