// Time-stamp: <modified the 06/03/2017 (at 16:17) by Erwan Jahier> 
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


public class HeaterControlTestRandom {
	private Random r;
	// XXX
	@Before public void setUp(){
		this.r = new Random();
		// XXX
	}
	@Test
	public void test_alea1() {
		double t1,t2,t3,tmpT;
		Sensor mockS1 = mock(Sensor.class);
		Sensor mockS2 = mock(Sensor.class);
		Sensor mockS3 = mock(Sensor.class);
		HeaterControl hc = new HeaterControl(21,mockS1,mockS2,mockS3);
		double[] t_list = new double[] {16,17,18,19,20,21,22,21,20,19,18,17,16};
		for (double t : t_list) {
			boolean lastON = hc.getOn();
			t1 = t+r.nextDouble()-0.5 ;
			t2 = t+r.nextDouble()-0.5;
			t3 = t+r.nextDouble()-0.5;
			when(mockS1.getT()).thenReturn(t1);
			when(mockS2.getT()).thenReturn(t2);
			when(mockS3.getT()).thenReturn(t3);
			assertTrue(hc.getOn() == lastON);
			System.out.printf("test alea 1: t=%f  \n", t);
		}
	}

	public void test_alea2() {
		double t1,t2,t3;
		Sensor mockS1 = mock(Sensor.class);
		Sensor mockS2 = mock(Sensor.class);
		Sensor mockS3 = mock(Sensor.class);
		HeaterControl hc = new HeaterControl(21,mockS1,mockS2,mockS3);
		double[] t_list = new double[] {16,17,18,19,20,21,22,21,20,19,18,17,16};
		for (double t : t_list) {
			boolean lastON = hc.getOn();
			t1 = t-r.nextDouble()-0.5 ;
			t2 = t-r.nextDouble()-0.5;
			t3 = t-r.nextDouble()-0.5;
			when(mockS1.getT()).thenReturn(t1);
			when(mockS2.getT()).thenReturn(t2);
			when(mockS3.getT()).thenReturn(t3);
			assertTrue(hc.getOn() == lastON);
			System.out.printf("test alea 2: t=%f  \n", t);
		}
	} 
}



