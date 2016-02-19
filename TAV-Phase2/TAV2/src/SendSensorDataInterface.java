import java.io.ByteArrayOutputStream;

public interface SendSensorDataInterface {
	
	public byte[] createPacket(double torque, double ultra_distance, double ir_distance);

}
