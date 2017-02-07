import static org.junit.Assert.*;

import org.jmlspecs.utils.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestExplosivesFindBat {
    
    static int nb_inconclusive = 0;
    static int nb_fail = 0;
    
    Explosives e;
    
    public static void main(String args[]) {
        String testClass = "TestExplosivesFindBat";
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
    public void  testSequence_0() { //prop 3 invalidée
        try{
            e=new Explosives();
            e.findBat("_1");
        } 	catch(JmlAssertionError e){
            handleJMLAssertionError(e);
        }
    }
    
    @Test
    public void  testSequence_1() { //produit déjà assigné
        try{
            e=new Explosives();
            e.add_assign("Bat_1","Prod_1");
            e.findBat("Prod_1");
        } 	catch(JmlAssertionError e){
            handleJMLAssertionError(e);
        }
    }
    
    @Test
    public void  testSequence_2() { //test ok
        try{
            e=new Explosives();
            e.add_incomp("Prod_Nitro","Prod_Glycerine");
            e.add_incomp("Prod_Dyna","Prod_Mite");
            e.add_assign("Bat_1","Prod_Dyna");
            e.add_assign("Bat_1","Prod_Nitro");
            e.add_assign("Bat_2","Prod_Mite");
            System.out.println("testSequence_2 "+e.findBat("Prod_Glycerine"));
        } 	catch(JmlAssertionError e){
            handleJMLAssertionError(e);
        }
    }
    
    @Test
    public void  testSequence_3() { //test ok ==> nouveau bâtiment car bâtiments incompatibles
        try{
            e=new Explosives();
            e.add_incomp("Prod_Nitro","Prod_Glycerine");
            e.add_incomp("Prod_Dyna","Prod_Glycerine");
            e.add_incomp("Prod_Dyna","Prod_Mite");
            e.add_incomp("Prod_Glycerine","Prod_Mite");
            e.add_assign("Bat_1","Prod_Dyna");
            e.add_assign("Bat_1","Prod_Nitro");
            e.add_assign("Bat_2","Prod_Mite");
            System.out.println("testSequence_3 "+e.findBat("Prod_Glycerine"));
        } 	catch(JmlAssertionError e){
            handleJMLAssertionError(e);
        }
    }

    
    @Test
    public void  testSequence_4() { //test ok ==> assign vide
        try{
            e=new Explosives();
            System.out.println("testSequence_4 "+e.findBat("Prod_1"));
        } 	catch(JmlAssertionError e){
            handleJMLAssertionError(e);
        }
    }
    
}
