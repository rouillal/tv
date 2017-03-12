import java.util.ArrayList;
import java.util.Arrays;

// Time-stamp: <modified the 06/03/2017 (at 16:46) by Erwan Jahier> 

public class HeaterControl {
    private double TC;
    private Sensor s1; 
    private Sensor s2;
    private Sensor s3;
    private Boolean On;

    public HeaterControl(double TC_init, Sensor s1_init, Sensor s2_init, Sensor s3_init) {
        this.s1 = s1_init;
        this.s2 = s2_init;
        this.s3 = s3_init;    
        this.TC = TC_init;
        step();
    }
    
    public void setTC(double  newValue) {  
    	this.TC = newValue;
    	step();
    }
    public double getTC() { 
    	return TC;
    }
    public double getT1() { 
    	return s1.getT();
    }
    public double getT2() { 
    	return s2.getT();
    }
    public double getT3() { 
    	return s3.getT();
    }
    public Boolean getOn() { 
    	return On;
    }
    
    public void updateOn(){    	
    	if(TC  >= mean()){
    		this.On = true;
    	} else {
    		this.On = false;
    	}
    }
    
    public double mean(){
    	ArrayList<Sensor> sensors = new ArrayList<>(Arrays.asList(s1,s2,s3));
    	int cmp = 0;
    	double m = 0.0;
    	for (Sensor s:sensors){
    		if(s.getValid()){
    			m+=s.getT();
    			cmp++;
    		}
    	}    	
    	return m/cmp;
    }

    public void step() {
    	double hy = 0.5;
    	if(!((mean() >= TC + hy) && (mean() <= TC - hy ))){
    		updateOn();
    	} 
    }
}
