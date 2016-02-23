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

class runReadSpeed implements Runnable {
	ReadFromOutputBuffer readBuffer = new ReadFromOutputBuffer();
	ReadSpeedAndTorque readSpeed = new ReadSpeedAndTorque();

	@Override
	public void run() {
		while (true) {
			readSpeed.getSpeedAndTorque();
			try {
				Thread.sleep(1000);
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
/*
class runInput implements Runnable {
	double[] sensorData = { 0, 0, 0 };
	
	ReadUserInput readInput = new ReadUserInput();
	SendSensorData sendData = new SendSensorData();
	WriteToInputBuffer writeBuffer = new WriteToInputBuffer();

	@Override
	public void run() {
		while(true){
			readInput.getUserInput();
		}
	}

	public void start() {
		Thread t = new Thread(this);
		t.start();
	}

}
*/

/**
 * @author group3
 *
 */
/*
public class RafsMain {
	
	
	/**
	 * @param args
	 */
/*
	public static void main(String[] args) {
		UserInterface display = new UserInterface();
		runSensorData rsd = new runSensorData();
		runReadSpeed rrs = new runReadSpeed();
		//runInput ri = new runInput();
		display.frame.setVisible(true);
		rsd.start();
		rrs.start();
		//ri.start();
	}
}
*/