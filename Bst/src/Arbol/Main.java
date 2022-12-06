
package Arbol;

public class Main {
    
    private static final int[] datos = {3, 7, 9, 8, 3, 5};
      
    private static void cargarDatos(Arbol T){
        for (int i = 0; i < datos.length; i++) {
            T.add(datos[i]);
        }
    }
    
    
    public static void main(String[] args) {
        Arbol A = new Arbol();
        cargarDatos(A);
       
        A.inorden();
        A.niveles();
    }
    
}
