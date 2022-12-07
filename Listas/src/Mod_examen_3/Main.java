package Mod_examen_3;

import listas.*;

public class Main {

    private static final int[] datos = {10, 8, 12, 3, 12, 8, 5, 1};

    private static void cargarDatos(Lista L) {
        for (int i = 0; i < datos.length; i++) {
            L.add(datos[i]);
        }
    }

    public static void main(String[] args) {
        Juego J = new Juego();
        J.lanzar(0, 9);
        J.lanzar(1, 9);
        J.lanzar(0, 6);
        J.lanzar(2, 6);
        J.lanzar(2, 3);
        J.lanzar(2, 9);
        J.lanzar(2, 27);

        if (J.gano(2)) {
            System.out.println("Gano");
        } else {
            System.out.println("No gano");
        }
    }
}
