public class Prime{
    private /*@ spec_public @*/ int p;
    /*@ public invariant
      @ (* A COMPLETER *);
      @*/
    public Prime(){
      p = 3;
    }

    //@ requires (is_prime(x));
    //@ ensures (p == x);
    public Prime(int x){
      p = x;
    }

    //@ requires (is_prime(x));
    //@ ensures (p==x);
    public void set_p(int x){
      p = x;
    }

    //@ ensures (\result == p);
    public /*@ pure @*/ int get_p(){
      return p;
    }

    /*@ ensures \result == true <==>
      @   (n > 1 ) && (\forall int d; 2<= d && d<= n-1; n % d != 0);
      @*/
    public static /*@ pure helper @*/ boolean is_prime(int n){ 
      if(n <= 1 ){
        return false;
      }

      for (int i = 2; i < n-1; i++){
          if (n % i == 0){
            return false;
          }
      }
      return true;    
    }
}
