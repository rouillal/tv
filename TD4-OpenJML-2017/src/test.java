


public class test {
	Explosives e = new Explosives();
	e.add_incomp("Prod_1","Prod_2");
	
	System.out.println("taille : "+e.nb_inc);
	for(int i=0; i<e.nb_inc;i++){
		if(e.incomp[i][0].equals("Prod_2") && e.incomp[i][1].equals("Prod_1")){
			System.out.println("ok");
			e.skip();
		}
	}
}
