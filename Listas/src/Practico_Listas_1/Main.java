package Practico_Listas_1;

public class Main {

    private static final int[] datos = {10, 8, 3, 12, 8, 5, 1};

    private static void cargarDatos(Lista L) {
        for (int i = 0; i < datos.length; i++) {
            L.add(datos[i]);
        }
    }

    public static void main(String[] args) {
        Lista p = new Lista();
        cargarDatos(p);
        System.out.println(p.exist(5));
        System.out.println(p);  //Llamar a p.toString()
    }
}
