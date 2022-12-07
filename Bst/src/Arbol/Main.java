
package Arbol;

public class Main {

    private static final int[] datos = { 3, 7, 9, 8, 3, 5 };
    // private static final int[] datos2 = { 30, 20, 60, 19, 21, 45, 70, 8, 22, 44,
    // 46, 7, 9, 69, 71 };
    private static final int[] datos2 = { 30, 20, 60, 19, 45, 8, 44, 46, 7, 9 };

    private static void cargarDatos(Arbol T) {
        for (int i = 0; i < datos.length; i++) {
            T.add(datos[i]);
        }
    }

    private static void cargarDatos2(Arbol T) {
        for (int i = 0; i < datos2.length; i++) {
            T.add(datos2[i]);
        }
    }

    public static void main(String[] args) {
        Arbol A = new Arbol();
        cargarDatos(A);
        Arbol B = new Arbol();
        cargarDatos2(B);

        A.inorden();
        A.niveles();

        System.out.println("Cantidad de gajos: " + A.cantGajos());
        A.descendientes(3);
        // A.podar();
        // A.niveles();
        // System.out.println();
        // System.out.println("padre(3, 7) = " + A.isPadre(3, 7));
        // System.out.println("padre(7, 3) = " + A.isPadre(7, 3));
        // System.out.println("padre(7, 8) = " + A.isPadre(7, 8));
        // System.out.println("padre(9, 8) = " + A.isPadre(9, 8));

        // A.descendientes(3);
        // System.out.println();
        // System.out.println("isHoja(3) = " + A.isHoja(3));
        // System.out.println("isHoja(7) = " + A.isHoja(7));
        // System.out.println("isHoja(8) = " + A.isHoja(8));
        // System.out.println("isHoja(9) = " + A.isHoja(9));
        // System.out.println("isHoja(5) = " + A.isHoja(5));

        // A.descendientes(3);
        // System.out.println();
        // System.out.println("delHoja(7)");
        // A.delHoja(7);
        // A.descendientes(3);
        // System.out.println();
        // System.out.println("delHoja(8)");
        // A.delHoja(8);
        // A.descendientes(3);
        // System.out.println();
        // System.out.println("delHoja(9)");
        // A.delHoja(9);
        // A.descendientes(3);
        // System.out.println();
        // System.out.println("delHoja(5)");
        // A.delHoja(5);
        // A.descendientes(3);
        // System.out.println();

        System.out.println("cantLadders() ==>> " + B.cantLadders());
        B.inorden();
        B.niveles();

        // System.out.println("B.isDescIncompleto(7) ==>> " + B.isDescIncompleto(7));
        // System.out.println("B.isDescIncompleto(45) ==>> " + B.isDescIncompleto(45));
        // System.out.println("B.isDescIncompleto(19) ==>> " + B.isDescIncompleto(19));
        // System.out.println("B.isDescIncompleto(60) ==>> " + B.isDescIncompleto(60));
        // System.out.println("B.isDescIncompleto(20) ==>> " + B.isDescIncompleto(20));

        // System.out.println("B.isBunch(19) ==>> " + B.isBunch(19));
        // System.out.println("B.isBunch(45) ==>> " + B.isBunch(45));
        // System.out.println("B.isBunch(9) ==>> " + B.isBunch(9));
        // System.out.println("B.isBunch(8) ==>> " + B.isBunch(8));
        // System.out.println("B.isBunch(46) ==>> " + B.isBunch(46));
        // System.out.println("B.isBunch(30) ==>> " + B.isBunch(30));
        // System.out.println("B.isBunch(1) ==>> " + B.isBunch(1));

        B.cutBunch(46);
        System.out.println("B.cutBunch(46)");
        B.niveles();
        B.cutBunch(8);
        System.out.println("B.cutBunch(8)");
        B.niveles();
        B.cutBunch(9);
        System.out.println("B.cutBunch(9)");
        B.niveles();
        B.cutBunch(45);
        System.out.println("B.cutBunch(46)");
        B.niveles();

    }

}
