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
	runInput input;
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
		input = new runInput(display);
	}
	
	@Test
	/**
	 * Scenario: Receive normal data
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
	 * Scenario: Receive corrupt packet
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
	 * Scenario: Receive out of range data
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
	
	@Test
	/**
	 * Scenario: Send normal data
	 * runInput - send torque data within range and verify that the display is correctly updated
	 * conditions: - valid torque entry
	*/
	public void test4() throws InterruptedException {
		
		input.start();
		display.InputT.setText("0.5");
		display.InputT.postActionEvent();
		//Process is too fast...
		Thread.sleep(1000);
		
		double expected = 0.5;
		double actual = Double.parseDouble(display.TSent.getText());

		Assert.assertEquals(expected, actual, 0);
	}
	
	@Test
	/**
	 * Scenario: Send out of range data
	 * runInput - send ultra_distance value out of range and verify that the display is not updated
	 * conditions: - invalid ultra entry
	*/
	public void test5() throws InterruptedException {
		input.start();
		display.InputU.setText("60");
		display.InputU.postActionEvent();
		//Process is too fast...
		Thread.sleep(1000);
		
		double expected = 0.0;
		double actual = Double.parseDouble(display.USent.getText());

		Assert.assertEquals(expected, actual, 0);
	}
}