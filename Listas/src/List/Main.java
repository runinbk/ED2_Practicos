package List;

public class Main {
    private static final int[] datos = { 10, 8, 12, 3, 12, 8, 5, 1 };

    private static void cargarDatos(Lista L) {
        for (int i = 0; i < datos.length; i++) {
            L.add(datos[i]);
        }
    }

    public static void main(String[] args) {
        Lista p = new Lista();
        cargarDatos(p);
        System.out.println(p); // Llamar a p.toString()

        System.out.println(" p.delInto(1, 12)");
        p.delInto(1, 12);
        System.out.println(p); // Llamar a p.toString()
        System.out.println("p.delInto(1, 12)");
        p.delInto(8, 12);
        System.out.println(p); // Llamar a p.toString()
        System.out.println("p.delInto(1, 8)");
        p.delInto(1, 8);
        System.out.println(p); // Llamar a p.toString()
    }
}
