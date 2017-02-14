
import org.junit.*; // JUnit4

import org.jmlspecs.utils.JmlAssertionError;




public class TS_TestAssign002 
 {

    static int nb_inconclusive = 0;
    static int nb_fail = 0;
    static int nb_err = 0;
 	
    public static void main(String args[]) {

         org.junit.runner.JUnitCore.main("TS_TestAssign002");
 
        System.out.println("inconclusive tests: "+TS_TestAssign002.nb_inconclusive+" -- failures : "+TS_TestAssign002.nb_fail+" -- errors : "+TS_TestAssign002.nb_err );
     }
		
    @Test
 	public void testSequence_1() 
 throws Exception{ org.jmlspecs.utils.Utils.useExceptions = true;
 
try{  Explosives e = new Explosives()  ;
    e.add_incomp("Prod1","Proud2") ;
    e.add_incomp("Prod1","Prod3") ;
     } 
		catch(JmlAssertionError.PreconditionEntry e$){System.out.println("\n INCONCLUSIVE "+(new Exception().getStackTrace()[0].getMethodName())+ "\n\t "+ e$.getMessage());
                               TS_TestAssign002.nb_inconclusive++;}
	catch(JmlAssertionError e$){
// test failure	
			TS_TestAssign002.nb_fail++;

         org.junit.Assert.fail("\n\t " +e$.getClass()+"\n\t " + e$.getMessage());
 		
	}
	 catch (Exception e) {TS_TestAssign002.nb_err++;
	
		throw e;
	   }   

			
  	}

}
