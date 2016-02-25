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
	public void test1() throws InterruptedException {
		byte[] stream = {99,63,-61,51,51,51,51,51,51,80,64,46,0,0,0,0,0,0,18,102};
		when(odroid.getData()).thenReturn(stream);
		readSpeed.start();
		
		//Process is too fast...
		Thread.sleep(500);
		
		System.out.println("Test: 1");
		
		double[] expecteds = {15.0, 0.15};
		double[] actuals = {readSpeed.latestData.speed, readSpeed.latestData.torque};
		Assert.assertArrayEquals(expecteds, actuals, 0);
	}
	
	@Test
	/**
	 * Scenario: Corrupt packet
	 * runReadSpeed - verify that latest data value is not corrupted
	 * conditions: - invalid, corrupt packet
	*/
	public void test2() throws InterruptedException {
		byte correctPacket[] = {99,63,-61,51,51,51,51,51,51,80,64,46,0,0,0,0,0,0,18,102};
																		//Corrupt byte 123
		byte incorrectPacket[] = {99,63,-61,51,51,51,51,51,51,80,64,46,0,0,0,123,0,0,18,102};
		
		when(odroid.getData()).thenReturn(correctPacket);
		
		readSpeed.start();
		
		//Process is too fast...
		Thread.sleep(500);
		
		System.out.println("Test: 2");
		
		when(odroid.getData()).thenReturn(incorrectPacket);
		
		//Process too fast
		Thread.sleep(500);
		
		//The data is not changed since the second packet sent is corrupt
		double[] expecteds = {15.0, 0.15};
		double[] actuals = {readSpeed.latestData.speed, readSpeed.latestData.torque};
		Assert.assertArrayEquals(expecteds, actuals, 0);
	}
	
	@Test
	/**
	 * Scenario: Out of range data
	 * runReadSpeed - verify that latest data value is within range of accepted values
	 * conditions: - invalid, non-corrupt packet.
	*/
	public void test3() throws InterruptedException {
		byte[] stream = {98,64,20,0,0,0,0,0,0,98,64,73,0,0,0,0,0,0,21,102};
		when(odroid.getData()).thenReturn(stream);
		readSpeed.start();
		
		//Process is too fast...
		Thread.sleep(1000);
		
		System.out.println("Test: 3");
		
		//Since the data sent is out of range, the values for speed and torque are not changed.
		//They remain un-initialized (default 0.0)
		double[] expecteds = {0.0, 0.0};
		double[] actuals = {readSpeed.latestData.speed, readSpeed.latestData.torque};
		Assert.assertArrayEquals(expecteds, actuals, 0);
	}
}