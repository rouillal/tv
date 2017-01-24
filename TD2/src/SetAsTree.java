// This version is adapted to OpenJML and recent versions of java
/*@ nullable_by_default @*/
public class SetAsTree{
    public Integer val;
    public SetAsTree ltree;
    public SetAsTree rtree;

    //@ public invariant ((val != null) || (ltree == null && rtree == null));
    //@ public invariant ((ltree == null) || (!ltree.emptySet() && ( ltree.max() < val)));
    //@ public invariant ((rtree == null) || (!rtree.emptySet() && ( rtree.min() > val)));
    //@ public invariant (this.verify());
    //@ public invariant (* no cycle in the tree *);
      
    // Constructor
    public SetAsTree(){ // produces an empty set
        val = null;
        ltree = null;
        rtree = null;
    }
    public SetAsTree(int v){ // produces a singleton node
        val = v;
        ltree = null;
        rtree = null;
    }
    public SetAsTree(int v, SetAsTree l, SetAsTree r){ // arbitrary node
        val = v;
        ltree = l;
        rtree = r;
    }

    //getters and setters
    public /*@ pure helper @*/ Integer getVal() {
        return val;
    }
    public void setVal(Integer val) {
        this.val = val;
    }
    public /*@ pure helper @*/ SetAsTree getLtree() {
        return ltree;
    }
    public void setLtree(SetAsTree ltree) {
        this.ltree = ltree;
    }
    public /*@ pure helper @*/ SetAsTree getRtree() {
        return rtree;
    }
    public void setRtree(SetAsTree rtree) {
        this.rtree = rtree;
    }

    // Application specific methods
    //@ ensures contains(v);
    public void insert(int v){
        boolean insertion = false;
        boolean alreadyHere = false;
        SetAsTree tmp = this ;
        while (!insertion && !alreadyHere){
            if (v > tmp.getVal()){ //insertion en fils droit
                if (tmp.getRtree() == null){ //fils droit du noeud courant vide
                    SetAsTree fd = new SetAsTree(v);
                    tmp.setRtree(fd);
                    insertion = true;
                } else if (v < tmp.getRtree().getVal()){ //fils droit du noeud courant non vide et v inférieur
                    SetAsTree fd = new SetAsTree(v, null, tmp.getRtree());
                    tmp.setRtree(fd);
                    insertion = true;
                } else //fils droit du noeud courant non vide et v supérieur
                    tmp = tmp.getRtree();
            } else if(v < tmp.getVal()){ //insertion en fils gauche
                if (tmp.getLtree() == null){ //fils gauche du noeud courant vide
                    SetAsTree fg = new SetAsTree(v);
                    tmp.setLtree(fg);
                    insertion = true;
                } else if (v > tmp.getLtree().getVal()){ //fils gauche du noeud courant non vide et v supérieur
                    SetAsTree fg = new SetAsTree(v, tmp.getLtree(),null);
                    tmp.setLtree(fg);
                    insertion = true;
                } else //fils gauche du noeud courant non vide et v inférieur
                    tmp = tmp.getLtree();
            } else
                alreadyHere = true ;
        }
        refactor();
    }   

    private /*@ helper @*/ void refactor(){
        //nb element / 2 => prendre supérieur si impair
        //on obtient un noeud => racine
        //noeud + 1 = fils droit 
    }

    //@ ensures !contains(v);
    public void delete(int v){
        delete_helper(v);
    }

    private /*@ helper @*/ void delete_helper(int v){
        if (v== val && ltree == null && rtree == null){
            val = null;
        } else if(v > val && rtree != null){
            rtree.delete_helper(v);
        } else if (v == val && rtree != null){
            val = rtree.min();
            rtree.delete_helper(val);
        } else if(v<val && ltree !=null){
            ltree.delete_helper(v);
        } else if (v == val && ltree != null){
            val = ltree.max();
            ltree.delete_helper(val);
        } 
        
        if(rtree != null && rtree.emptySet()){
            rtree = null;
        }
        if(ltree != null && ltree.emptySet()){
            ltree = null;
        }
    }
    
    // Pure functions used in the specification
    public /*@ pure helper @*/ boolean emptySet(){
         return val == null;
    } 
    //@ requires !emptySet();
    public /*@ pure helper @*/ int min(){
        if (ltree != null && ltree.getVal() < val){return ltree.min();}
        else return val;
    }    
    //@ requires !emptySet();
    public /*@ pure helper @*/ int max(){
        if (rtree != null && rtree.getVal() > val){return rtree.max();}
        else return val;
    }
    
    public /*@  pure helper @*/ boolean contains(int v){
    	if (val == null) {return false;}
    	else if (v == val) {return true;}
    	else if (v > val && (rtree!= null)) {return rtree.contains(v);}
    	else if (v < val && (ltree!= null)) {return ltree.contains(v);}
    	else {return false;}
        }

    // Non side-effecting methods
    public /*@ non_null @*/  String toString(){
        String s = "";
        if (ltree != null) {s=s+ltree.toString();};
        s = s+" "+val+" ";
        if (rtree != null) {s=s+rtree.toString();};
        return s;
    }

    public /*@ pure helper @*/ boolean verify(){        
        if(ltree != null && rtree != null){
            if((ltree.size() == rtree.size()) || (ltree.size() == rtree.size() + 1)||(ltree.size() == rtree.size()-1)){
                return true;
            }
        } else if (ltree == null && rtree != null){
            if (rtree.size() == 1){
                return true;
            }
        } else if(rtree == null && ltree != null){
            if (ltree.size() == 1){
                return true;
            }
        } else if (rtree == null && ltree == null){
            return true;
        }

        return false;


    }

    public /*@ pure helper @*/ int size(){ 
        int size = 1; 
        if (val == null){
            return 0;
        } 
        else {
            if (ltree != null){
                size += ltree.size();
            } 
            if (rtree != null){
                size += rtree.size();
            }
            return size; 
        }
    }   

    public void skip(){ } // useful to test the invariant.

}
