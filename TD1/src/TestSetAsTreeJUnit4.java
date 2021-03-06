import static org.junit.Assert.*;

import org.jmlspecs.utils.*;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestSetAsTreeJUnit4 {

    public static void main(String args[]) {
    	String testClass = "TestSetAsTreeJUnit4";
     	org.junit.runner.JUnitCore.main(testClass);
     }

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		org.jmlspecs.utils.Utils.useExceptions = true;
	}

	@Test
	public void  testSequence_0() {
		SetAsTree s=new SetAsTree(5);
		System.out.println(s);
		s.insert(10);
		System.out.println(s);
		s.insert(1);
		System.out.println(s);
		s.delete(5);
		System.out.println(s);
	}

}
