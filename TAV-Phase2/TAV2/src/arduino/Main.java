package arduino;


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

/**
 * 
 * This might not be needed anymore.
 * Do this exact thing in runSensorData instead.
 *
 */

class runInput implements Runnable {
	double[] sensorData = { 0, 0, 0 };
	
	ReadUserInput readInput = new ReadUserInput();
	SendSensorData sendData = new SendSensorData();
	WriteToInputBuffer writeBuffer = new WriteToInputBuffer();

	@Override
	public void run() {
		while(true){
			
			/**
			 * Added call to buffer in order to finally get the recieved values for
			 * torque, ultra and ir.
			 */
			
			if (readInput.getTorque() != 0.0 && readInput.getUltra() != 0.0 
					&& readInput.getIr() != 0.0) {
				
				if (writeBuffer.sendByteToBuffer(29, sendData.createPacket(
						readInput.getTorque(), readInput.getUltra(), readInput.getIr())) != 1) {
					//Values sent to input buffer, can perform "operation success" message.
				} else {
					//There was an error, handle it.
				}
			}
		}
	}

	public void start() {
		Thread t = new Thread(this);
		t.start();
	}
}

class runDisplay implements Runnable {
	TestDisplay display= new TestDisplay();
	public void run() {
			try { 
				display.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public void start() {
			Thread t = new Thread(this);
			t.start();
		}	
		public void updateTSent(String string){
			display.TSent.setText(string);
		}
			
		public void updateUSent(String string){
			display.USent.setText(string);
		}	
		
		public void updateISent(String string){
			display.ISent.setText(string);
		}
		
		public void updateTRec(String string){
			display.TRec.setText(string);
		}
		
		public void updateSRec(String string){
			display.SRec.setText(string);
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
		runDisplay rd = new runDisplay();
		runSensorData rsd = new runSensorData();
		runReadSpeed rrs = new runReadSpeed();
		runInput ri = new runInput();
		rsd.start();
		rrs.start();
		ri.start();
		
		rd.start();
	}

	private static String toString(Double t) {
		// TODO Auto-generated method stub
		return null;
	}
}
