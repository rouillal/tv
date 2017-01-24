public class MainSetAsTree{

	@org.jmlspecs.annotation.SkipRac
    public static void main(String[] args) {
    org.jmlspecs.utils.Utils.useExceptions = true;
    	
		/*
    SetAsTree s = new SetAsTree(5);
	System.out.println(s);
	s.insert(10);
	System.out.println(s);
	s.insert(1);
	System.out.println(s);
	s.delete(5);
	System.out.println(s);*/
        
        SetAsTree s = new SetAsTree(5);
        System.out.println(s);
        s.insert(10);
        System.out.println(s);
        s.insert(1);
        System.out.println(s);
        s.insert(11);
        System.out.println(s);
        s.insert(4);
        System.out.println(s);
        s.insert(0);
        System.out.println(s);
        s.insert(2);
        System.out.println(s);
        s.insert(8);
        System.out.println(s);
        s.insert(5);
        System.out.println(s);
        s.insert(7);
        System.out.println(s);
        s.insert(9);
        System.out.println(s);
        s.delete(2);
        System.out.println(s);
        
    }
}
