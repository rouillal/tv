// Time-stamp: <modified the 06/03/2017 (at 15:52) by Erwan Jahier> 
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class HeaterControlTest {
	@Test
	public void ca_chauffe_sil_fait_froid() {
		Sensor mockS1 = mock(Sensor.class);
		Sensor mockS2 = mock(Sensor.class);
		Sensor mockS3 = mock(Sensor.class);
		when(mockS1.getT()).thenReturn(18.0);
		when(mockS2.getT()).thenReturn(16.0);
		when(mockS3.getT()).thenReturn(23.0);
		HeaterControl hc = new HeaterControl(21,mockS1,mockS2,mockS3);
		assertTrue(hc.getOn() == true);
		System.out.printf("ca_chauffe_sil_fait_froid: ok.\n");
	}
	@Test
	public void ca_chauffe_pas_sil_fait_chaud() {
		Sensor mockS1 = mock(Sensor.class);
		Sensor mockS2 = mock(Sensor.class);
		Sensor mockS3 = mock(Sensor.class);
		when(mockS1.getT()).thenReturn(18.0);
		when(mockS2.getT()).thenReturn(16.0);
		when(mockS3.getT()).thenReturn(23.0);
		HeaterControl hc = new HeaterControl(18,mockS1,mockS2,mockS3);
		assertTrue(hc.getOn() == false);
		System.out.printf("ca_chauffe_pas_sil_fait_chaud:  ok.\n");
	}
}
