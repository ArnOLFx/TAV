package arduino;


class runSensorData implements Runnable {
	double t=0,u=0,i=0; 		// varaibles for torque, ultra distance and IR distance to be sent to buffer.
	ReadUserInput readInput = new ReadUserInput();
	SendSensorData sendData = new SendSensorData();
	WriteToInputBuffer writeBuffer = new WriteToInputBuffer();

	@Override
	public void run() {
		while (true) {
			if(readInput.getTorque()!=99.0){
				t=readInput.getTorque();
			}
			if(readInput.getUltra()!=99.0){
				u=readInput.getUltra();
			}
			if(readInput.getIr()!=99.0){
				i=readInput.getIr();
			}
			System.out.println("torque: "+t);
			System.out.println("ultra: "+u);
			System.out.println("IR: "+i);

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
		rsd.start();
		rrs.start();		
		rd.start();
	}

}
