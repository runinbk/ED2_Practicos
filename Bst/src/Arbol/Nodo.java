package Arbol;

public class Nodo {     //Nodo que usa la class Arbol
    public static final int M = 2;      //M=cantidad de hijos de éste nodo.
    
    public int Data;
    public Nodo HI, HD;

    public Nodo(){
        this(0);
    }
    
    public Nodo(int x){
        Data = x;
        HI = HD = null;
    }

    public void setData(int Data) {
        this.Data = Data;
    }

    public void setHI(Nodo HI) {
        this.HI = HI;
    }

    public void setHD(Nodo HD) {
        this.HD = HD;
    }

    public int getData() {
        return Data;
    }

    public Nodo getHI() {
        return HI;
    }

    public Nodo getHD() {
        return HD;
    }
    
    public Nodo getHijo(int i){ //Pre: i=1 o i=2.
        if (i<1 || i>2)    //Diverge
            throw new RuntimeException("Nodo.getHijo: "+i+" no es un número de hijo correcto.  Use 1 ó 2.");
        
        return (i==1 ? HI : HD);   //getHijo(1)=HijoIzq=HI, getHijo(2)=HijoDer=HD
    }
    
    
    public int cantHijos(){ //Devuelve la cantidad de hijos (no-nulos) que tiene este Nodo.
        int c=0;
        
        for (int i=1; i<=M; i++){   //Contar los hijos no-nulos.
            if (getHijo(i) != null)
                c++;
        }
        
        return c;
    }
}
