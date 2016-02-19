
public class ReadFromOutputBuffer {
	
	private int n, errorcode;
	private byte nBits[], bitstream[];
	
	/*
	 * First constructor. Used for setting n (amount of bits).
	 * This is used to construct a RFOB-object, then you set the
	 * bitstream buffer with setBitstream(byte[]). Then you call
	 * getOutputObject() to get a RFOB-object with the n-bits and the
	 * error code.
	 */
	public ReadFromOutputBuffer(int n) {
		this.n = n;
	}
	
	/*
	 * Second constructor. Used for setting
	 */
	public ReadFromOutputBuffer(byte buffer[], int errorcode) {
		this.errorcode = errorcode;
		
		nBits = getBuffer(buffer);
	}
	
	/*
	 * Returns an object of type RFOB with the n first bits and an
	 * error code.
	 */
	public ReadFromOutputBuffer getOutputObject() {
		
		if (nBits.length == n) {
			return new ReadFromOutputBuffer(nBits, 0);
		} else {
			return new ReadFromOutputBuffer(nBits, 1);
		}
	}
	
	/*
	 * Used to get the n-bit buffer so that it can be returned with the
	 * error code in the above method.
	 */
	public byte[] getBuffer(byte bitstream[]) {
		
		byte nBits[] = new byte[getN()];
		
		for (int i = 0; i < getN(); i++) {
			nBits[i] = bitstream[i];
		}
		
		return nBits;
	}
	
	
	/*
	 * Setters and getters!
	 */
	public void setBitstream(byte buffer[]) {
		bitstream = buffer;
	}
	
	public int getErrorcode() {
		return errorcode;
	}
	
	public int getN() {
		return n;
	}
	
	public void setN(int n) {
		this.n = n;
	}
}
