// Time-stamp: <modified the 06/03/2017 (at 17:03) by Erwan Jahier> 
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.contrib.theories.DataPoints;
import org.junit.contrib.theories.Theories;
import org.junit.contrib.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class HeaterControlTestWithTheories {
	private Sensor s1 = mock(Sensor.class);
	private Sensor s2 = mock(Sensor.class);
	private Sensor s3 = mock(Sensor.class);
	private double tc;
	private HeaterControl hc = new HeaterControl(tc, s1, s2, s3);
	
	private boolean lastOn;
	private boolean firstPass = true;
	
    @DataPoints
    public static double[] some_temp() {
        return new double[]{16.1,16.1,16.62,17,18,19,20,21,22,0,-23,152};
    } 
    @Theory
    public void hotter_implies_no_rising_edge(double tc,
                                              double t1,
                                              double t2,
                                              double t3) {    	
		when(s1.getT()).thenReturn(t1);
		when(s2.getT()).thenReturn(t2);
		when(s3.getT()).thenReturn(t3);
		double t = hc.mean();
		hc.setTC(tc);
		if(firstPass){
			lastOn = hc.getOn();
			firstPass = false;
		} else {
			if((t < tc && !lastOn) || (t > tc && lastOn)) 
				assertTrue(hc.getOn() == lastOn);
			lastOn = hc.getOn();
		}		
        System.out.printf("test theories 1 : tc=%f; t1=%f; t2=%f; t3=%f \n", tc,t1,t2,t3);
    }
    
    @Theory
    public void sensors_commute(double tc, double t1, double t2, double t3) {
    	when(s1.getT()).thenReturn(t1);
		when(s2.getT()).thenReturn(t2);
		when(s3.getT()).thenReturn(t3);
		hc.setTC(tc);
		boolean on1 = hc.getOn();
		
		when(s1.getT()).thenReturn(t1);
		when(s2.getT()).thenReturn(t3);
		when(s3.getT()).thenReturn(t2);
		hc.setTC(tc);
		boolean on2 = hc.getOn();
		
		when(s1.getT()).thenReturn(t2);
		when(s2.getT()).thenReturn(t1);
		when(s3.getT()).thenReturn(t3);
		hc.setTC(tc);
		boolean on3 = hc.getOn();
		
		when(s1.getT()).thenReturn(t2);
		when(s2.getT()).thenReturn(t3);
		when(s3.getT()).thenReturn(t1);
		hc.setTC(tc);
		boolean on4 = hc.getOn();
		
		when(s1.getT()).thenReturn(t3);
		when(s2.getT()).thenReturn(t1);
		when(s3.getT()).thenReturn(t2);
		hc.setTC(tc);
		boolean on5 = hc.getOn();
		
		when(s1.getT()).thenReturn(t3);
		when(s2.getT()).thenReturn(t2);
		when(s3.getT()).thenReturn(t1);
		hc.setTC(tc);
		boolean on6 = hc.getOn();
		
		assertTrue(on1 == on2 == on3 == on4 == on5 == on6);	
		
        System.out.printf("test theories 2: tc=%f; t1=%f; t2=%f; t3=%f \n", tc,t1,t2,t3);
    }
}

