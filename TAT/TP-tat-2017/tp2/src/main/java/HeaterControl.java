// Time-stamp: <modified the 06/03/2017 (at 16:46) by Erwan Jahier> 

public class HeaterControl{
    private double TC;
    private Sensor s1; 
    private Sensor s2;
    private Sensor s3;
    private Boolean On;

    public HeaterControl(double TC_init, Sensor s1_init, Sensor s2_init, Sensor s3_init) {
        this.TC = TC_init;
        this.s1 = s1_init;
        this.s2 = s2_init;
        this.s3 = s3_init;
        updateOn();
    }
    
    public void setTC(double  newValue) {
    	this.TC = newValue;
    	updateOn();
    }
    public double getTC() {
    	return this.TC;
    }
    public double getT1() {
    	return this.s1.getT();
    }
    public double getT2() {
    	return this.s2.getT();
    }
    public double getT3() {
    	return this.s3.getT();
    }
    public Boolean getOn() {
    	return this.On;
    }
    
    public void updateOn(){
    	if (getTC() < (getT1()+getT2()+getT3())/3)
    		this.On = false;
    	else
    		this.On = true;
    }

    public void step() {
    }
}
