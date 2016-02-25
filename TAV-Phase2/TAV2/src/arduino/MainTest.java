package arduino;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import arduino.ReadSpeedAndTorque.SpeedAndTorque;
import static org.mockito.Mockito.*;

public class MainTest {
	
	UserInterface display;
	runReadSpeed readSpeed;
	@Mock ReadSpeedAndTorque odroid;
	//@Mock SpeedAndTorque sat;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		display = new UserInterface();
		readSpeed = new runReadSpeed(display, odroid);
	}
	
	@Test
	/**
	 * runReadSpeed - verify that latest data value is correct
	 * conditions: - valid, uncorrupted package 
	*/
	public void test1() {
		
		when(odroid.testSAT.speed).thenReturn(25.0);
		when(odroid.testSAT.torque).thenReturn(0.25);
		
		SpeedAndTorque sat = odroid.testSAT;
		
		when(odroid.testSAT).thenReturn(sat);
		
		sat = odroid.testSAT;
		sat.speed = 25.0;
		sat.torque = 0.25;
		when(odroid.getSpeedAndTorque()).thenReturn(sat);
		readSpeed.start();
		double[] expected = {sat.torque, sat.speed};
		double[] actual = {readSpeed.latestData.torque, readSpeed.latestData.speed};
		Assert.assertArrayEquals(expected, actual, 0);
		
		/*
		SpeedAndTorque expected = odroid.testSAT;
		SpeedAndTorque actual;
		expected.speed = 25;
		expected.torque = 0.25;
		odroid.add20BytePacket(25, 0.25);
		
		actual = odroid.getSpeedAndTorque();
		
		when(odroid.getSpeedAndTorque()).thenReturn(expected);
		double[] exp = {expected.speed, expected.torque};
		double[] act = {actual.speed, actual.torque};
		Assert.assertArrayEquals(exp, act, 0);
		*/
	}
}