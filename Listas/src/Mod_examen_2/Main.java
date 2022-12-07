package Mod_examen_2;

public class Main {

    private static final int[] datos = {8, 6, 9, 7, 6, 9};

    private static void cargarDatos(Lista L) {
        for (int i = 0; i < datos.length; i++) {
            L.addr(datos[i]);
        }
    }

    public static void main(String[] args) {
        Lista p = new Lista();
        cargarDatos(p);

        p.delM(6, 9);
        System.out.println(p);  //Llamar a p.toString()
    }
}
