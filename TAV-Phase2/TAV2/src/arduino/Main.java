package arduino;


/*
class runSensorData implements Runnable {
	double[] sensorData = { 0, 0, 0 };

	SendSensorData sendData = new SendSensorData();
	WriteToInputBuffer writeBuffer = new WriteToInputBuffer();

	@Override
	public void run() {
		while (true) {
			writeBuffer.sendByteToBuffer(29, sendData.createPacket(sensorData[0], sensorData[1], sensorData[2]));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		Thread t = new Thread(this);
		t.start();
	}

}
*/
class runReadSpeed implements Runnable {
	ReadSpeedAndTorque readSpeed;
	SpeedAndTorque latestData = new SpeedAndTorque(0,0);
	UserInterface display;
	
	public runReadSpeed(UserInterface display, ReadSpeedAndTorque readSpeed) {
		this.display = display;
		this.readSpeed = readSpeed;
	}

	@Override
	public void run() {		
		while (true) {
			SpeedAndTorque tempST = readSpeed.getSpeedAndTorque();
			//tempST = readSpeed.receiveData();
			if (tempST != null){
				latestData = tempST;
				display.SRec.setText(String.valueOf(latestData.speed));
				display.TRec.setText(String.valueOf(latestData.torque));
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Speed: " + latestData.speed);
		}
	}

	public void start() {
		Thread t = new Thread(this);
		t.start();
	}

}

class runInput implements Runnable {
	
	double t = 0, u = 0, i = 0;
	
	WriteToInputBuffer writeBuffer = new WriteToInputBuffer();
	SendSensorData sendData = new SendSensorData();
	UserInterface display;
	
	public runInput(UserInterface display) {
		this.display = display;
	}
	
	public void setDisplay(UserInterface display) {
		this.display = display;
	}

	@Override
	public void run() {
		while(true){
			if(display.getTorque()!=99.0){
				t=display.getTorque();
			}
			if(display.getUltra()!=99.0){
				u=display.getUltra();
			}
			if(display.getIr()!=99.0){
				i=display.getIr();
			}
			System.out.println("torque: " + t);
			System.out.println("ultra: " + u);
			System.out.println("IR: " + i);
			writeBuffer.sendByteToBuffer(29, sendData.createPacket(t, u, i));				
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		Thread t = new Thread(this);
		t.start();
	}

}


/**
 * @author group3
 *
 */
public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserInterface display = new UserInterface();
		ReadSpeedAndTorque readSpeed = new ReadSpeedAndTorque(new ReadFromOutputBuffer(new Odroid()));
		//runSensorData rsd = new runSensorData();
		runInput ri = new runInput(display);
		runReadSpeed rrs = new runReadSpeed(display, readSpeed);
		display.frame.setVisible(true);
		//rsd.start();
		rrs.start();
		ri.start();
	}
}