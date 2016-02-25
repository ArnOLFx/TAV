package arduino;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class OdroidTest {

	Odroid odroid;
	ReadFromOutputBuffer read;
	
	@Before
	public void setup() {
		odroid = mock(Odroid.class);
		read = mock(ReadFromOutputBuffer.class);
	}
	
	@Test
	public void test1() {
		//when(odroid.getBuffer()).thenReturn(read.testRO);
	}
	
	@Test
	public void test2() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test3() {
		fail("Not yet implemented");
	}
}
