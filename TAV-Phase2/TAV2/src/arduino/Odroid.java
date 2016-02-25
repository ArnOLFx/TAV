package arduino;

public class Odroid {
	
	ReadSpeedAndTorque read;
	int startTime, stopTime;
	
	public Odroid() {
		read = new ReadSpeedAndTorque();
		startTime = (int) System.currentTimeMillis() / 1000;
	}
	
	public void setBuffer(double torque, double ultra, double ir) {
		
		stopTime = (int) System.currentTimeMillis() / 1000;
		
		int time = stopTime - startTime;
		double distance = (ultra + ir) / 2;
		
		read.add20BytePacket((distance / time), torque);
		
		startTime = (int) System.currentTimeMillis() / 1000;
	}
	
	public byte[] getBuffer() {
		return read.getPacket();
	}
}
