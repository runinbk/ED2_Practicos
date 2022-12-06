package List;

public class Lista {    //ADT Lista (Ordenada y sin duplicados).
    private Nodo L;
    private int n;
    
    public Lista(){
        L = null;
        n = 0;
    }
    
    public boolean isVacia() {  //Devuelve true sii esta lista está vacía.
        return (L==null);
    }
    
    public void add(int x){ //Inserta x a la Lista.
        Nodo Ant = null;
        Nodo p   = L;
        
        while (p != null && x >= p.getData()){
            Ant = p;
            p = p.getLink();
        }
        
        Nodo nuevo;
        if (Ant == null){   //x es menor a todos los que están en la Lista (o L==null)
            nuevo = new Nodo(x);
            nuevo.setLink(L);
            L = nuevo;
            n++;
        }
        else
            if (Ant.getData() != x){    //x no está en la lista.  Insertarlo entre Ant y p
                nuevo = new Nodo(x);
                Ant.setLink(nuevo);
                nuevo.setLink(p);
                n++;
            }
    }
    
    public void del(int x){
        Nodo Ant = null;
        Nodo p   = L;
        
        while (p != null && x > p.getData()){
            Ant = p;
            p = p.getLink();
        }
        
        if (p != null && p.getData() == x){  //x existe en la Lista 
            if (Ant == null)
                L = L.getLink();    //x era el primero de la Lista
            else
                Ant.setLink(p.getLink());
            
            p.setLink(null);
            n--;    
        }
    }
    
    
    public int indexOf(int x){
        int index=0;
        Nodo p = L;
        while (p != null){
            if (p.Data==x)
                return index;
            
            if (p.Data > x)
                return -1;
            
            p = p.Link;
            index ++ ;
        }
        
        return -1;
    }
    
    
    public boolean contains(int x){
        return (indexOf(x) != -1);
    }

    
    /**PRE: k es un valor entre 0 y length()-1.<br/><br/>
     * Devuelve el elemento de la posición k de la Lista. Las posiciones se 
     * enumeran desde el 0. <br/><br/>
     * Por ejemplo, si la lista es [4, 7, 9], entonces: <br/>
     * <blockquote>get(0)=4, get(1)=7 y get(2)=9</blockquote>
     * 
     * @param k Posición del elemento a obtener.
     * @return El elemento de la posición k de la Lista.
     */
    public int get(int k){
        if (k <0 || k > length()-1) //Diverge con la PRE
            throw new RuntimeException("Lista.get: Índice "+k+", fuera de rango.");
            
        Nodo p=L;
        for (int i=1; i<=k; i++){  //Hacer que p salte k veces.         
            p = p.getLink();
        }
        
        return p.getData();
    }
    
    public int length(){
        return n;
    }
    
    
    @Override
    public String toString(){ 
        String S = "[";
        String coma="";
       
        Nodo p  = L;
        while (p != null){
            S += coma + p.getData();
            coma=", ";
            p = p.getLink();
        }
       
       return S+"]";
    }
    
}
