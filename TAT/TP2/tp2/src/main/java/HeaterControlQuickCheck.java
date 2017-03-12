// Time-stamp: <modified the 06/03/2017 (at 17:08) by Erwan Jahier> 

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

@RunWith(JUnitQuickcheck.class)
public class HeaterControlQuickCheck {
	private Sensor s1 = mock(Sensor.class);
	private Sensor s2 = mock(Sensor.class);
	private Sensor s3 = mock(Sensor.class);
	private double tc;
	private HeaterControl hc1 = new HeaterControl(tc, s1, s2, s3);
	
	private boolean lastOn;
	private boolean firstPass = true;
  
    @Property
    public void hotter_implies_no_rising_edge(
                                   @InRange(min="0.0", max="50.0") double tc, 
                                   @InRange(min="0.0", max="50.0") double t1, 
                                   @InRange(min="0.0", max="50.0") double t2, 
                                   @InRange(min="0.0", max="50.0") double t3) {
    	when(s1.getT()).thenReturn(t1);
		when(s2.getT()).thenReturn(t2);
		when(s3.getT()).thenReturn(t3);
		double t = hc1.mean();
		hc1.setTC(tc);
		if(firstPass){
			lastOn = hc1.getOn();
			firstPass = false;
		} else {
			if((t < tc && !lastOn) || (t > tc && lastOn)) 
				assertTrue(hc1.getOn() == lastOn);
			lastOn = hc1.getOn();
		}		       
        System.out.printf("QC(hotter=>RE): tc=%f; t1=%f; t2=%f; t3=%f \n", tc,t1,t2,t3);

    }

    @Property
    public void sensors_commute( @InRange(min="0.0", max="50.0") double tc, 
                                 @InRange(min="0.0", max="50.0") double t1, 
                                 @InRange(min="0.0", max="50.0") double t2, 
                                 @InRange(min="0.0", max="50.0") double t3) {
    	when(s1.getT()).thenReturn(t1);
		when(s2.getT()).thenReturn(t2);
		when(s3.getT()).thenReturn(t3);
		hc1.setTC(tc);
		boolean on1 = hc1.getOn();
		
		when(s1.getT()).thenReturn(t1);
		when(s2.getT()).thenReturn(t3);
		when(s3.getT()).thenReturn(t2);
		hc1.setTC(tc);
		boolean on2 = hc1.getOn();
		
		when(s1.getT()).thenReturn(t2);
		when(s2.getT()).thenReturn(t1);
		when(s3.getT()).thenReturn(t3);
		hc1.setTC(tc);
		boolean on3 = hc1.getOn();
		
		when(s1.getT()).thenReturn(t2);
		when(s2.getT()).thenReturn(t3);
		when(s3.getT()).thenReturn(t1);
		hc1.setTC(tc);
		boolean on4 = hc1.getOn();
		
		when(s1.getT()).thenReturn(t3);
		when(s2.getT()).thenReturn(t1);
		when(s3.getT()).thenReturn(t2);
		hc1.setTC(tc);
		boolean on5 = hc1.getOn();
		
		when(s1.getT()).thenReturn(t3);
		when(s2.getT()).thenReturn(t2);
		when(s3.getT()).thenReturn(t1);
		hc1.setTC(tc);
		boolean on6 = hc1.getOn();
		
		assertTrue(on1 == on2 == on3 == on4 == on5 == on6);	
        System.out.printf("QC(sensors_commute):  On=%b ;tc=%f; t1=%f; t2=%f; t3=%f \n", hc1.getOn(),tc,t1,t2,t3);
    }

}

