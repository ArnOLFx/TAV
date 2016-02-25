package arduino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class MainTest {
	
	UserInterface display;
	runReadSpeed readSpeed;
	ReadSpeedAndTorque rst;
	ReadFromOutputBuffer rfob;
	@Mock Odroid odroid;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		display = new UserInterface();
		rfob = new ReadFromOutputBuffer(odroid);
		rst = new ReadSpeedAndTorque(rfob);
		readSpeed = new runReadSpeed(display, rst);
	}
	
	@Test
	/**
	 * Scenario: Receiving Normal Data
	 * Tested Class: runReadSpeed()
	 * Test description: mock a packet from odroid (normal data) and check if the latest received value is
	 *                   correct
	 * Conditions: - Valid, non-corrupted package 
	*/
	public void test1() {
		byte[] stream = {99,63,-61,51,51,51,51,51,51,80,64,46,0,0,0,0,0,0,18,102};
		when(odroid.getData()).thenReturn(stream);
		readSpeed.start();
		try {
			//Process is too fast...
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double[] expecteds = {15.0, 0.15};
		double[] actuals = {readSpeed.latestData.speed, readSpeed.latestData.torque};
		Assert.assertArrayEquals(expecteds, actuals, 0);
	}
}