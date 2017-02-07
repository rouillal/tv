import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestExplosivesJUnit4 {

    static int nb_inconclusive = 0;
    static int nb_fail = 0;

    Explosives e;

    public static void main(String args[]) {
    	String testClass = "TestExplosivesJUnit4";
     	org.junit.runner.JUnitCore.main(testClass);
     }


    private void handleJMLAssertionError(JmlAssertionError e) {
    	if (e.getClass().equals(JmlAssertionError.PreconditionEntry.class)) {
    	    System.out.println("\n INCONCLUSIVE "+(new Exception().getStackTrace()[1].getMethodName())+ "\n\t "+ e.getMessage());
            nb_inconclusive++;}
    else{
	    // test failure	
	    nb_fail++;
	    fail("\n\t" + e.getMessage());	
		}  
    }
	
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nb_inconclusive = 0;
		nb_fail = 0;
		org.jmlspecs.utils.Utils.useExceptions = true;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	     System.out.println("\n inconclusive tests: "+nb_inconclusive+" -- failures : "+nb_fail );
	}
	
	@Test
	public void  testSequence_0() { //Prop 7 invalidée
		try{
			e=new Explosives();
			e.add_incomp("Prod_Nitro","Prod_Glycerine");
			e.add_incomp("Prod_Dyna","Prod_Mite");
			e.add_assign("Bat_1","Prod_Dyna");
			e.add_assign("Bat_1","Prod_Nitro");
			e.add_assign("Bat_2","Prod_Mite");
			e.add_assign("Bat_1","Prod_Glycerine");
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}

	@Test
	public void testSequence_1(){ //Prop 1 invalidée
		try{
			e =new Explosives();
			for(int i=0;i<49;i++){
				e.add_incomp("Prod_"+i,"Prod_"+i+51);
			}
		} catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}

	@Test
	public void testSequence_2(){ //Prop 2 invalidée
		try{
			e =new Explosives();
			for(int i=0;i<30;i++){
				e.add_assign("Bat_"+i,"Prod_"+i+51);
			}
		} catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}

	@Test
	public void testSequence_3(){ //Prop 3 invalidée
		try{
			e =new Explosives();
			e.add_incomp("Proud_1","Proud_2");
			
		} catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}


	@Test
	public void testSequence_4(){ //Prop 4 invalidée
		try{
			e =new Explosives();
			e.add_assign("Baat_1","Proud_2");
			
		} catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}

	@Test
	public void testSequence_5(){ //Prop 5 invalidée
		try{
			e =new Explosives();
			e.add_incomp("Prod_1","Prod_1");
			
		} catch(JmlAssertionError e){
				handleJMLAssertionError(e);		
		}  
	}
    
    @Test
    public void testSequence_7(){ //Precondition fausse (propriété 3 de l'invariant)
        try{
            e =new Explosives();
            e.add_incomp("Prod_1","Pro_2");
            
        } catch(JmlAssertionError e){
            handleJMLAssertionError(e);
        }  
    }
    
    @Test
    public void testSequence_8(){ //Precondition fausse (doublon)
        try{
            e =new Explosives();
            e.add_incomp("Prod_1","Prod_2");
            e.add_incomp("Prod_1","Prod_2");
            
        } catch(JmlAssertionError e){
            handleJMLAssertionError(e);
        }
    }
    
    @Test
    public void testSequence_9(){ //Precondition fausse (doublon)
        try{
            e =new Explosives();
            e.add_assign("Bat_1","Prod_2");
            e.add_assign("Bat_2","Prod_2");
            
        } catch(JmlAssertionError e){
            handleJMLAssertionError(e);
        }
    }


}
