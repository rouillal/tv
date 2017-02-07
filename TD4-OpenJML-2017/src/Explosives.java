// Based on a B specification from Marie-Laure Potet.

public class Explosives{
    public int nb_inc = 0;
    public String [][] incomp = new String[50][2];
    public int nb_assign = 0;
    public String [][] assign = new String[30][2];
 
    /*@ public invariant // Prop 1 : le nombre de produits incompatibles dans un bâtiment est compris entre 0 et 49
      @ (0 <= nb_inc && nb_inc < 50); 
      @*/
    /*@ public invariant // Prop 2 : le nombre de produits assignés dans un bâtiment est compris entre 0 et 29
      @ (0 <= nb_assign && nb_assign < 30);
      @*/
    /*@ public invariant // Prop 3 : chaque produit incompatible dans un bpatiment doit commencer par la chaine de caractère 'Prod'
      @ (\forall int i; 0 <= i && i < nb_inc; 
      @         incomp[i][0].startsWith("Prod") && incomp[i][1].startsWith("Prod"));
      @*/
    /*@ public invariant // Prop 4 : chaque produit assigné à un bâtiment doit commencer par 'Prod' et chaque bâtiment doit commencer par 'Bat' 
      @ (\forall int i; 0 <= i && i < nb_assign; 
      @         assign[i][0].startsWith("Bat") && assign[i][1].startsWith("Prod"));
      @*/
    /*@ public invariant // Prop 5 : un produit ne peut pas être incompatible avec lui-même
      @ (\forall int i; 0 <= i && i < nb_inc; !(incomp[i][0]).equals(incomp[i][1]));
      @*/
    /*@ public invariant // Prop 6 : Si un produit A est incompatible avec un produit B, alors B est incompatible avec A
      @ (\forall int i; 0 <= i && i < nb_inc; 
      @        (\exists int j; 0 <= j && j < nb_inc; 
      @           (incomp[i][0]).equals(incomp[j][1]) 
      @              && (incomp[j][0]).equals(incomp[i][1]))); 
      @*/
    /*@ public invariant // Prop 7 : Les produits stockés dans le même bâtiment ne doivent pas être incompatibles
      @ (\forall int i; 0 <= i &&  i < nb_assign; 
      @     (\forall int j; 0 <= j && j < nb_assign; 
      @        (i != j && (assign[i][0]).equals(assign [j][0])) ==>
      @        (\forall int k; 0 <= k && k < nb_inc;
      @           (!(assign[i][1]).equals(incomp[k][0])) 
      @              || (!(assign[j][1]).equals(incomp[k][1])))));
      @*/


    //@requires (nb_inc >=0 && nb_inc < 48);
    //@requires (prod1.startsWith("Prod") && prod2.startsWith("Prod"));
    //@requires (!isAlreadyHere(prod1,prod2));
    //@requires (prod1 != prod2);
    public void add_incomp(String prod1, String prod2){
    	incomp[nb_inc][0] = prod1;
    	incomp[nb_inc][1] = prod2;
    	incomp[nb_inc+1][1] = prod1;
    	incomp[nb_inc+1][0] = prod2;
      nb_inc = nb_inc+2;
     }


    //@requires (nb_assign >=0 && nb_assign < 29);
    //@requires (bat.startsWith("Bat") && prod.startsWith("Prod"));
    //@requires (!isAlreadyInBat(prod));
    //@requires (prodCompInBat(bat,prod));
    public void add_assign(String bat, String prod){
    	assign[nb_assign][0] = bat;
    	assign[nb_assign][1] = prod;
    	nb_assign = nb_assign+1;
    }


    public void skip(){
    }

    //@requires (prod1.startsWith("Prod") && prod2.startsWith("Prod"));
    //@requires (prod1 != prod2);
    public boolean isAlreadyHere(String prod1, String prod2){
      for (int i=0;i<nb_inc;i++){
        if(incomp[i][0].equals(prod1) && incomp[i][1].equals(prod2)){
          return true;
        }
      }
      return false;
    }

    //@requires (prod.startsWith("Prod"));
    public boolean isAlreadyInBat(String prod){
      for (int i=0;i<nb_assign;i++){
        if(assign[i][1].equals(prod)){
          return true;
        }
      }
      return false;
    }

    //@requires (bat.startsWith("Bat") && prod.startsWith("Prod"));
    public boolean prodCompInBat(String bat, String prod){
        for (int i=0; i<nb_assign;i++){
            if(assign[i][0].equals(bat)){
                for(int k=0; k<nb_inc;k++){
                    if(assign[i][1].equals(incomp[k][0]) && prod.equals(incomp[k][1])){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    //@requires (prod1.startsWith("Prod") && prod2.startsWith("Prod"));
    //@requires (prod1 != prod2);
    public boolean compatible(String prod1, String prod2){
        for (int i=0; i <nb_inc; i++){
            if(incomp[i][0].equals(prod1) && incomp[i][1].equals(prod2))
                return false;
        }
        return true;
    }
    
    //@requires (prod.startsWith("Prod"));
    //@requires (!isAlreadyInBat(prod));
    //@requires (nb_assign >=0 && nb_assign < 29);
    //@ensures prodCompInBat(\result,prod);
    public String findBat (String prod){
        boolean b = false;
        int cpt = 0;
        String [] tmp = new String[nb_assign];
        if (nb_assign == 0)
            return "Bat_1";
        else {
            for (int i=0; i<nb_assign;i++){
                b = false;
                for (int j=0; j<tmp.length;j++){
                    if (assign[i][0].equals(tmp[j])){
                        b = true;
                        break;
                    }
                }
                if (!b){
                    tmp[cpt] = assign[i][0];
                    cpt++;
                }
            }
            for (int j=0; j<cpt;j++){
                if (prodCompInBat(tmp[j],prod))
                    return tmp[j];
            }
        }
        return "Bat_"+(nb_assign+1);
    }




}
