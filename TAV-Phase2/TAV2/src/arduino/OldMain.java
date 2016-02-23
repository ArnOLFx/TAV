package arduino;

/**
 * @author group3
 *
 */
public class OldMain {		
	/**
	 * @param args
	 */
public static void main(String[] args) {
	
	/**
	 * Added final for compliance level <1.8
	 * Take out final if compliance level is higher than 1.7
	 */
/*	
	final TestDisplay display = new TestDisplay();
	final SendSensorData sendData = new SendSensorData();
	final WriteToInputBuffer writeBuffer = new WriteToInputBuffer();
	//final ReadFromOutputBuffer readBuffer = new ReadFromOutputBuffer();
	final ReadSpeedAndTorque readSpeed = new ReadSpeedAndTorque();
	
//******** THREAD FOR THE GUI   **********
	Thread rd = new Thread(new Runnable() {
		public void run() {
			try { 
				display.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	rd.start();

		
// ********* THREAD FOR SENDING TO INPUT BUFFER *********
	Thread sendToInput = new Thread(new Runnable() {
		double t=0,u=0,i=0;
		public void run() {
			while (true) {
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
	});
	sendToInput.start();

// ********** THREAD FOR RECEIVNG FROM OUTPUT BUFFER *************8	
	Thread receiveFromOutput = new Thread(new Runnable() {
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
	});

	receiveFromOutput.start();
	*/}
}
