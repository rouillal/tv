import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestPrimeJUnit4 {

	public static void main(String args[]) {
    	String testClass = "TestPrimeJUnit4";
     	org.junit.runner.JUnitCore.main(testClass);
     }
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		org.jmlspecs.utils.Utils.useExceptions = true;
	}

	@Test
	public void  testSequence_0() {
		boolean b=Prime.is_prime(5);
	}
	
	@Test
	public void  testSequence_1() {
		boolean b=Prime.is_prime(4);
	}

	@Test
	public void  testSequence_2() {
		boolean b=Prime.is_prime(1);
	}
	@Test
	public void  testSequence_3() {
		boolean b=Prime.is_prime(0);
	}
	@Test
	public void  testSequence_4() {
		boolean b=Prime.is_prime(-1);
	}

	@Test
	public void  testSequence_5() {
		Prime s = new Prime(5);
		int x = s.get_p();
	}

	@Test(expected  = JmlAssertionError.PreconditionEntry.class)
	public void  testSequence_6() {
		Prime s = new Prime(4);
		int x = s.get_p();
	}

	@Test(expected  = JmlAssertionError.PreconditionEntry.class)
	public void  testSequence_7() {
		Prime s = new Prime(5);
		s.set_p(6);
	}

	@Test(expected  = JmlAssertionError.PreconditionEntry.class)
	public void  testSequence_8() {
		Prime s = new Prime(4);
		s.set_p(3);
	}


	@Test
	public void  testSequence_10() {
			Prime s=new Prime(-1);
	}
	
	@Test(expected  = JmlAssertionError.PreconditionEntry.class)
	public void  testSequence_11() {
			Prime s=new Prime(4);
	}
	
}
