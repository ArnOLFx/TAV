package arduino;

import java.io.ByteArrayOutputStream;

public class Odroid {
	
	static ByteArrayOutputStream networkStream = new ByteArrayOutputStream(256);
	
	void sendData(byte[] data){
		for (byte i : data) networkStream.write(i);
	}
	
	public byte[] getData() {
		byte[] data = networkStream.toByteArray();
		return data; 
	}

}
