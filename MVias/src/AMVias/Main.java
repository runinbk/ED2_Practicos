package AMVias;

public class Main {
    // private static final int V[] = { 50, 20, 30, 10, 5, 15 };
    private static final int W[] = { 300, 200, 100, 400, 350, 320, 270, 160, 150, 110, 85, 60, 40, 500, 420, 410, 310,
            305, 190, 170, 165, 140, 130, 80, 70, 65, 55, 50, 45, 15, 10, 5, 185, 180, 175, 76, 74 };

    // private static void cargarDatos(ArbolM A) {
    // for (int i = 0; i < V.length; i++)
    // A.add(V[i]);
    // }

    private static void cargarDatosV(ArbolM A) {
        for (int i = 0; i < W.length; i++)
            A.add(W[i]);
    }

    public static void main(String[] args) {
        // ArbolM A = new ArbolM();
        ArbolM B = new ArbolM();
        // cargarDatos(A);
        cargarDatosV(B);
        B.niveles();
        B.inorden();

        // System.out.print(A.hnoCercano(10, 30));

        // A.podar();
        // A.borrarHoja(30);
        // A.niveles();
        // A.inorden();

        // B.podar();
        // B.borrarHoja(185);
        // B.delAlone(74);
        // B.delHoja(270);
        System.out.println("lastParent(190, 180) -> " + B.lastParent2(190, 180));
        System.out.println("superParentR(300, 310) -> " + B.superParentR(300, 310));
        // System.out.print(B.tio(350, 50));

        // B.delHojas(1);

        // B.delLeafData(270);
        // B.delLeafs(185);

        // B.delSemiHoja(70);
        // B.niveles();
        // B.inorden();

        // B.delSemiHoja(400);
        // B.niveles();
        // B.inorden();

        // B.delSemiHoja(190);
        // B.niveles();
        // B.inorden();
    }

}
