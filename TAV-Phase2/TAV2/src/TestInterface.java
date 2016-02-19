
public class TestInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SendSensorData test = new SendSensorData();
		byte[] stream = test.createPacket(10.123456789, 0, 10);
		
		String array = "[";
		
		for (int i = 0; i < stream.length; i++) {
			if (i == stream.length-1) array += stream[i] + "]";
			else array += stream[i] + ", ";
		}
		System.out.println(array);
		
		
		ReadSpeedAndTorque test2 = new ReadSpeedAndTorque();
		ReadSpeedAndTorqueInterface.SpeedAndTorque st = test2.getSpeedAndTorque(stream);
		System.out.println(st.speed + ", " + st.torque);
	}
	


}
