package Practico_Listas_2;

import listas.*;


public class Main {
    private static final int[] datos = {10, 8, 12, 3, 12, 8, 5, 1};
    
    private static void cargarDatos(Lista L){
        for (int i = 0; i < datos.length; i++) {
            L.add(datos[i]);
        }
    }
    
    
    public static void main(String[] args) {
        Cola p = new Cola();
        //cargarDatos(p);
        p.meter(0);
        p.meter(1);
        p.meter(2);
        System.out.println( p ); 

    }  
}
