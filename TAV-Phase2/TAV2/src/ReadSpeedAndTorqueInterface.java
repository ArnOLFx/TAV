
public interface ReadSpeedAndTorqueInterface {
	
	public SpeedAndTorque getSpeedAndTorque(byte[] stream);
	
	public class SpeedAndTorque {
		double speed;
		double torque;
		
		public SpeedAndTorque(double speed, double torque){
			this.speed = speed;
			this.torque = torque;
		}
	}

}
