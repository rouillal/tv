// Time-stamp: <modified the 06/03/2017 (at 16:17) by Erwan Jahier> 
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;


public class HeaterControlTestRandom {
	private Random r;
	private Sensor s1,s2,s3;
	private HeaterControl hc;

	@Before public void setUp(){
		this.r = new Random();
		s1 = mock(Sensor.class);
		s2 = mock(Sensor.class);
		s3 = mock(Sensor.class);
		hc = new HeaterControl(19.0, s1, s2, s3);
	}
	@Test
	public void test_alea1() {
		double t1,t2,t3;
		double[] t_list = new double[] {16,17,18,19,20,21,22,21,20,19,18,17,16};
		boolean OnPast = true; //16 <= 19 : le chauffage est allumé
		for (double t : t_list) {	
			t1 = t+r.nextDouble()-0.5 ;
			t2 = t+r.nextDouble()-0.5;
			t3 = t+r.nextDouble()-0.5;
			when(s1.getT()).thenReturn(t1);
			when(s2.getT()).thenReturn(t2);
			when(s3.getT()).thenReturn(t3);
			if((t < 18.5 && !OnPast) || (t > 19.5 && OnPast)) 
				assertTrue(hc.getOn() == OnPast);
			OnPast = hc.getOn();
			System.out.printf("test alea 1: t=%f  \n", t);
		}
	}

	@Test
	public void test_alea2() {
		double t1,t2,t3;
		boolean OnPast;
		double t = getRandom(10, 30);
		if(t <= 19){
			OnPast = true;
		} else {
			OnPast = false;
		}
		for(int i=0;i<200;i++){
			t1 = t+r.nextDouble()-0.5 ;
			t2 = t+r.nextDouble()-0.5;
			t3 = t+r.nextDouble()-0.5;
			when(s1.getT()).thenReturn(t1);
			when(s2.getT()).thenReturn(t2);
			when(s3.getT()).thenReturn(t3);
			if((t < 18.5 && !OnPast) || (t > 19.5 && OnPast)) 
				assertTrue(hc.getOn() == OnPast);
			OnPast = hc.getOn();
			System.out.printf("test alea 2: t=%f  \n", t);
			t += r.nextBoolean() ? 1 : -1;
		}
		
	}

	public double getRandom(int min,int max){
		return r.nextInt(max + 1 - min) + min;
	}
} 



