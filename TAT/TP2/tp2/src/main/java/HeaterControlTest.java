// Time-stamp: <modified the 06/03/2017 (at 15:52) by Erwan Jahier> 
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class HeaterControlTest {
	@Test
	public void ca_chauffe_sil_fait_froid() {
		Sensor s1 = mock(Sensor.class);
		Sensor s2 = mock(Sensor.class);
		Sensor s3 = mock(Sensor.class);
		when(s1.getT()).thenReturn(15.0);
		when(s2.getT()).thenReturn(20.0);
		when(s3.getT()).thenReturn(19.0);	
		when(s1.getValid()).thenReturn(true);
		when(s2.getValid()).thenReturn(true);
		when(s3.getValid()).thenReturn(true);	
		HeaterControl hc = new HeaterControl(21, s1, s2, s3);
		assertEquals(true,hc.getOn());
		System.out.printf("ca_chauffe_sil_fait_froid: ok.\n");
	}
	@Test
	public void ca_chauffe_pas_sil_fait_chaud() {
		Sensor s1 = mock(Sensor.class);
		Sensor s2 = mock(Sensor.class);
		Sensor s3 = mock(Sensor.class);
		when(s1.getT()).thenReturn(19.5);
		when(s2.getT()).thenReturn(20.0);
		when(s3.getT()).thenReturn(23.0);		
		HeaterControl hc = new HeaterControl(19, s1, s2, s3);
		assertEquals(hc.getOn(),false);
		System.out.printf("ca_chauffe_pas_sil_fait_chaud:  ok.\n");
	}
	
	@Test
	public void sensor_broken(){
		Sensor s1 = mock(Sensor.class);
		Sensor s2 = mock(Sensor.class);
		Sensor s3 = mock(Sensor.class);
		when(s1.getT()).thenReturn(19.0);
		when(s2.getT()).thenReturn(20.0);
		when(s3.getT()).thenReturn(23.0);	
		when(s1.getValid()).thenReturn(true);
		when(s2.getValid()).thenReturn(true);
		when(s3.getValid()).thenReturn(false);	
		
		HeaterControl hc = new HeaterControl(18.5, s1, s2, s3);
		assertEquals(false,hc.getOn()); //moyenne de s1, s2 = 19		
	}
	
	@Test
	public void sensor_broken2(){
		Sensor s1 = mock(Sensor.class);
		Sensor s2 = mock(Sensor.class);
		Sensor s3 = mock(Sensor.class);
		when(s1.getT()).thenReturn(19.0);
		when(s2.getT()).thenReturn(20.0);
		when(s3.getT()).thenReturn(17.0);	
		when(s1.getValid()).thenReturn(false);
		when(s2.getValid()).thenReturn(false);
		when(s3.getValid()).thenReturn(true);	
		
		HeaterControl hc = new HeaterControl(20, s1, s2, s3);
		assertEquals(hc.getOn(),true); //s3 = 17		
	}
	
}

