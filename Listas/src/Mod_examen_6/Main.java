package Mod_examen_6;

public class Main {

    private static final int[] datos = {10, 8, 12, 3, 5, 9, 11};

    private static void cargarDatos(Lista L) {
        for (int i = 0; i < datos.length; i++) {
            L.add(datos[i]);
        }
    }

    public static void main(String[] args) {
        Lista p = new Lista();
        cargarDatos(p);
        System.out.println(p);
        p.invX(0, 6);
        System.out.println(p);  //Llamar a p.toString()
    }
}
