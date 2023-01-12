package AMVias;

import java.util.LinkedList;

public class ArbolM {
    private NodoM raiz;
    private int n; // n=cantidad de nodos.

    public ArbolM() {
        raiz = null;
        n = 0;
    }

    public int getCantNodos() {
        return n;
    }

    public void add(int x) {
        if (raiz == null)
            raiz = new NodoM(x);
        else {
            int i = 0;
            NodoM ant = null, p = raiz;
            while (p != null) {
                if (!p.isLleno()) { // Hay espacio en el nodo p. Insertar x ahí.
                    p.insDataInOrden(x);
                    return;
                }

                i = hijoDesc(p, x);
                if (i == -1)
                    return; // x está en el nodo p.

                ant = p;
                p = p.getHijo(i);
            }
            // p llegó a null. Crear una nueva hoja y engancharla a ant.
            NodoM nuevo = new NodoM(x);
            ant.setHijo(i, nuevo);
        }

        n++;
    }

    private boolean hoja(NodoM t) {
        return (t != null && t.cantHijos() == 0);
    }

    public void inorden() {
        System.out.print("Inorden:");
        if (raiz == null)
            System.out.println(" (Árbol vacío)");
        else {
            inorden(raiz);
            System.out.println();
        }
    }

    private void inorden(NodoM T) {
        if (T != null) {
            int z = T.cantDatasUsadas(); // z = índice de la última data usada.
            for (int i = 1; i <= z; i++) {
                inorden(T.getHijo(i));
                System.out.print(" " + T.getData(i));
            }
            inorden(T.getHijo(z + 1));
        }
    }

    private int hijoDesc(NodoM N, int x) { // Corrutina de insertar.
        int i = 1;
        while (i <= N.cantDatasUsadas()) {
            if (x < N.getData(i))
                return i;

            if (x == N.getData(i))
                return -1;

            i++;
        }

        return N.cantDatasUsadas() + 1;
    }

    public void niveles() {
        niveles("");
    }

    public void niveles(String nombreVar) {
        if (nombreVar != null && nombreVar.length() > 0)
            nombreVar = " del Arbol " + nombreVar;
        else
            nombreVar = "";

        System.out.print("Niveles" + nombreVar + ": ");

        if (raiz == null)
            System.out.println("(Arbol vacío)");
        else
            niveles(raiz);
    }

    // ---------- Métodos auxiliares para mostrar el árbol por niveles
    // --------------
    private void niveles(NodoM T) { // Pre: T no es null.
        LinkedList<NodoM> colaNodos = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();

        int nivelActual = 0;
        String coma = "";

        colaNodos.addLast(T);
        colaNivel.addLast(1);

        do {
            NodoM p = colaNodos.pop(); // Sacar un nodo p de la cola
            int nivelP = colaNivel.pop(); // Sacar el nivel donde se encuentra p.

            if (nivelP != nivelActual) { // Se está cambiando de nivel
                System.out.println();
                System.out.print("  Nivel " + nivelP + ": ");
                nivelActual = nivelP;
                coma = "";
            }

            System.out.print(coma + p);
            coma = ", ";

            addHijos(colaNodos, colaNivel, p, nivelP);
        } while (colaNodos.size() > 0);

        System.out.println();
    }

    private void addHijos(LinkedList<NodoM> colaNodos, LinkedList<Integer> colaNivel, NodoM p, int nivelP) {
        for (int i = 1; i <= NodoM.M; i++) { // Insertar a la cola de nodos los hijos no-nulos de p
            NodoM hijo = p.getHijo(i);

            if (hijo != null) {
                colaNodos.addLast(hijo);
                colaNivel.addLast(nivelP + 1);
            }
        }
    }

    // ----------------------------------Practico---------------------------------
    // 1. En la class ArbolM escriba el método
    //
    // public boolean hnoCercano(int a, int b)
    // //Por comodidad, asuma que a < b
    //
    // el cual devuelve true, si el data a es hermano cercano del
    // data b. Si a o b no están en el árbol; o a y b no son
    // hermanos cercanos, devuelve false.
    // Dos datas son hermanos cercanos si no comparten el
    // mismo nodo, pero en el nodo padre tienen un data padre
    // en común P. Es decir, a está en el nodo de la izquierda de P,
    // y b está en el nodo de la derecha de P.

    public boolean hnoCercano(int a, int b) {
        if (a < b) {
            return hnoCercano(raiz, a, b);
        }
        return false;
    }

    private boolean hnoCercano(NodoM T, int a, int b) {
        if (T != null) {
            int i = hijoDesc(T, a);
            if (T.getHijo(i) != null && T.getHijo(i).existe(a)) {
                if (T.getHijo(i + 1) != null) {
                    return T.getHijo(i + 1).existe(b);
                }
            }
            return hnoCercano(T.getHijo(i), a, b);
        }
        return false;
    }

    // 2. Asumiendo que el Árbol M-Vías es DESORDENADO
    // (pero sin duplicados), escriba el método:

    // public void podar()

    // el cual elimine todas las hojas del árbol.
    // (Si el árbol es vacío, “no pasa nada”. Si el árbol es una hoja,
    // es decir la Raíz es una hoja, entonces Raiz=null).

    public void podar() {
        podar(raiz);
    }

    private void podar(NodoM T) {
        if (T != null) {
            if (T == raiz && hoja(T)) {
                raiz = null;
            }
            for (int i = 1; i <= NodoM.M; i++) {
                if (hoja(T.getHijo(i))) {
                    T.setHijo(i, null);
                    n--;
                } else {
                    podar(T.getHijo(i));
                }
            }
        }
    }

    // 3. En la class ArbolM escriba el método NO-RECURSIVO

    // public void borrarHoja(int x)

    // el cual elimina la hoja donde se encuentra el data x. Si el
    // data x no existe o x está alojado en un nodo no-hoja, “no
    // pasa nada”

    public void borrarHoja(int x) {
        borrarHoja(raiz, x);
    }

    private void borrarHoja(NodoM t, int x) {
        if (t != null) {
            if (t == raiz && t.existe(x) && hoja(t)) {
                raiz = null;
                n = 0;
                return;
            }
            for (int i = 1; i <= NodoM.M; i++) {
                if (hoja(t.getHijo(i)) && t.getHijo(i).existe(x)) {
                    t.setHijo(i, null);
                    n--;
                    return;
                }
                borrarHoja(t.getHijo(i), x);
            }
        }
    }

    // 4. En la class ArbolM (m-vías) escriba el método

    // public void distInorden(int a, int b)

    // //Pre: a < b (asumir que a < b)

    // el cual devuelve la distancia entre a y b en la cadena
    // Inorden generada por el árbol. Si a o b no existen en el
    // árbol, este algoritmo devuelve ‒1.
    // Por ejemplo, si el Inorden del árbol es:

    // Cadena Inorden: 10, 20, 25, 50, 60, 70
    // distInorden(30, 60) = ‒1 //30 no existe
    // distInorden(20, 88) = ‒1 //88 no existe
    // distInorden(20, 60) = 3
    // //Porque partiendo del 20 necesitamos recorrer 3 datas (en la
    // //cadena Inorden), hasta llegar al 60: 10, 20, 25, 50, 60, 70
    // distInorden(25, 60) = 2
    // distInorden(10, 70) = 5

    public void distInorden(int a, int b) {
        // if ( a > b && raiz.existe(a) && raiz.existe(b) ) distInorden(raiz, a, b);
        if ( a > b ) distInorden(raiz, a, b);
    }

    // private void distInorden(NodoM t, int a, int b) {
    // if (t.existe(a) && t.existe(b)) {
    // int v [] ;
    // inordencVec(t, v);
    // }

    // }

    // private void inordencVec(NodoM T, int a []) {
    // if (T != null) {
    // int z = T.cantDatasUsadas(); // z = índice de la última data usada.
    // for (int i = 1; i <= z; i++) {
    // inorden(T.getHijo(i));
    // a [i] = T.getData(i);
    // }
    // inorden(T.getHijo(z + 1));
    // }
    // }

    private void distInorden ( NodoM t, int a, int b) {
        if (t!= null) {
            distInorden(t.getHijo(a), a+1, b
            );
            distInorden(t.getHijo(b), a, b+1
            );
            }
            }
            
    }

}
