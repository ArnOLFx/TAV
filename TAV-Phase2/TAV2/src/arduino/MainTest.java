package arduino;

import static org.mockito.Mockito.when;

import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MainTest {
	
	UserInterface display;
	
	runReadSpeed readSpeed;
	@Mock runInput input;
	Scanner sc;
	
	ReadSpeedAndTorque rst;
	ReadFromOutputBuffer rfob;
	@Mock Odroid odroid;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		sc = new Scanner(System.in);
		display = new UserInterface();
		rfob = new ReadFromOutputBuffer(odroid);
		rst = new ReadSpeedAndTorque(rfob);
		readSpeed = new runReadSpeed(display, rst);
	}
	
	@Test
	/**
	 * runReadSpeed - verify that latest data value is correct
	 * conditions: - valid, non-corrupted package 
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
	
	//@Test
	/**
	 * Scenario: Testing display member in runInput + user input.
	 * runInput - 
	 * conditions: - 
	*//*
	public void test4() throws InterruptedException {
		
		input.setDisplay(new UserInterface());
		
		when(input.display.getTorque()).thenReturn(null);
		
		/*byte[] stream = {98,64,20,0,0,0,0,0,0,98,64,73,0,0,0,0,0,0,21,102};
		when(odroid.getData()).thenReturn(stream);
		readSpeed.start();
		
		//Process is too fast...
		Thread.sleep(1000);
		
		System.out.println("Test: 4");
		
		//Since the data sent is out of range, the values for speed and torque are not changed.
		//They remain un-initialized (default 0.0)
		double[] expecteds = {0.0, 0.0};
		double[] actuals = {readSpeed.latestData.speed, readSpeed.latestData.torque};
		Assert.assertArrayEquals(expecteds, actuals, 0);*/
	//}
	
	//@Test
	/**
	 * Scenario: 
	 * runInput - 
	 * conditions: - 
	*//*
	public void test5() throws InterruptedException {
		byte[] stream = {98,64,20,0,0,0,0,0,0,98,64,73,0,0,0,0,0,0,21,102};
		when(odroid.getData()).thenReturn(stream);
		readSpeed.start();
		
		//Process is too fast...
		Thread.sleep(1000);
		
		System.out.println("Test: 5");
		
		//Since the data sent is out of range, the values for speed and torque are not changed.
		//They remain un-initialized (default 0.0)
		double[] expecteds = {0.0, 0.0};
		double[] actuals = {readSpeed.latestData.speed, readSpeed.latestData.torque};
		Assert.assertArrayEquals(expecteds, actuals, 0);
	}*/
}