package Arbol;

import java.util.LinkedList;

public class Arbol {
    private Nodo Raiz;
    private int n;

    public Arbol() {
        Raiz = null;
        n = 0;
    }

    public int cantNodos() {
        return n;
    }

    public void add(int x) {
        if (Raiz == null)
            Raiz = new Nodo(x);
        else { // Caso general
            Nodo Ant = null;
            Nodo p = Raiz;

            while (p != null) {
                Ant = p;
                if (x < p.getData())
                    p = p.getHI();
                else if (x > p.getData())
                    p = p.getHD();
                else
                    return; // Salir. x ya está en el árbol.
            }

            Nodo N = new Nodo(x);

            if (x < Ant.getData())
                Ant.setHI(N);
            else
                Ant.setHD(N);
        }

        n++;
    }

    private boolean hoja(Nodo T) { // Para usar en la RECURSION.
        return (T != null && T.cantHijos() == 0);
    }

    public void inorden() {
        System.out.print("Inorden : ");

        if (Raiz == null)
            System.out.println("(Arbol vacío)");
        else {
            inorden(Raiz, "");
            System.out.println();
        }
    }

    private void inorden(Nodo T, String coma) {
        if (T != null) {
            inorden(T.getHI(), ", ");
            System.out.print(coma + T.getData());
            inorden(T.getHD(), ", ");
        }
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

        if (Raiz == null)
            System.out.println("(Arbol vacío)");
        else
            niveles(Raiz);
    }

    // ---------- Métodos auxiliares para mostrar el árbol por niveles
    // --------------
    private void niveles(Nodo T) { // Pre: T no es null.
        LinkedList<Nodo> colaNodos = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();

        int nivelActual = 0;
        String coma = "";

        colaNodos.addLast(T);
        colaNivel.addLast(1);

        do {
            Nodo p = colaNodos.pop(); // Sacar un nodo p de la cola
            int nivelP = colaNivel.pop(); // Sacar el nivel donde se encuentra p.

            if (nivelP != nivelActual) { // Se está cambiando de nivel
                System.out.println();
                System.out.print("  Nivel " + nivelP + ": ");
                nivelActual = nivelP;
                coma = "";
            }

            System.out.print(coma + p.getData());
            coma = ", ";

            addHijos(colaNodos, colaNivel, p, nivelP);
        } while (colaNodos.size() > 0);

        System.out.println();
    }

    private void addHijos(LinkedList<Nodo> colaNodos, LinkedList<Integer> colaNivel, Nodo p, int nivelP) {
        for (int i = 1; i <= Nodo.M; i++) { // Insertar a la cola de nodos los hijos no-nulos de p
            Nodo hijo = p.getHijo(i);

            if (hijo != null) {
                colaNodos.addLast(hijo);
                colaNivel.addLast(nivelP + 1);
            }
        }
    }

    /*
     * PRACTICO DE ARBOLES BINARIOS - PREVIO AL 1ER PARCIAL...
     */

    // PREGUNTA 1
    public int cantGajos() {
        return cantGajos(Raiz) - 1;
    }

    private int cantGajos(Nodo T) {
        if (T == null)
            return 0;
        if (hoja(T))
            return 1;
        int c = 0;
        c = c + cantGajos(T.getHI());
        c = c + cantGajos(T.getHD());
        return c + 1;
    }

    // PREGUNTA 2
    public void descendientes(int x) {
        System.out.print("Descendientes de " + x + ": ");
        descendientes(getNodo(x), x);

    }

    private void descendientes(Nodo T, int x) {
        if (T == null)
            return;
        if (T.getData() != x)
            System.out.print(T.getData() + ", ");
        descendientes(T.getHI(), x);
        descendientes(T.getHD(), x);

    }

    private Nodo getNodo(int x) {
        return getNodo(Raiz, x);
    }

    private Nodo getNodo(Nodo T, int x) {
        if (T == null)
            return null;
        if (T.getData() == x)
            return T;
        Nodo p = getNodo(T.getHI(), x);
        if (p != null)
            return p;
        return getNodo(T.getHD(), x);
    }

    // PREGUNTA 3

    public void podar() {
        podar(Raiz);
    }

    private void podar(Nodo T) {
        if (T != null) {
            if (hoja(T.getHI())) {
                T.setHI(null);
            }
            if (hoja(T.getHD())) {
                T.setHD(null);
            }
            podar(T.getHI());
            podar(T.getHD());

        }
    }

    // PREGUNTA 4

    public boolean isPadre(int p, int h) {
        return isPadre(Raiz, p, h);
    }

    private boolean isPadre(Nodo T, int p, int h) {
        if (T == null)
            return false;
        if (T.getData() == p) {
            if (T.getHI() != null && T.getHI().getData() == h)
                return true;
            if (T.getHD() != null && T.getHD().getData() == h)
                return true;
        }
        return isPadre(T.getHI(), p, h) || isPadre(T.getHD(), p, h);
    }

    // PREGUNTA 5

    public boolean isHoja(int x) {
        return isHoja(Raiz, x);
    }

    private boolean isHoja(Nodo T, int x) {
        if (T == null)
            return false;
        if (T.getData() == x) {
            if (T.getHI() == null && T.getHD() == null)
                return true;
        }
        return isHoja(T.getHI(), x) || isHoja(T.getHD(), x);
    }

    // PREGUNTA 6 ===>>> SIN RECURCION

    public void delHoja(int x) {
        Nodo Ant = null;
        Nodo p = Raiz;
        while (p != null && p.getData() != x) {
            Ant = p;
            if (x < p.getData())
                p = p.getHI();
            else if (x > p.getData())
                p = p.getHD();
        }
        if (hoja(p)) {
            if (Ant.getHI() == p)
                Ant.setHI(null);
            else
                Ant.setHD(null);
        }

    }

    // private void delHoja(Nodo T, int x) {
    // if (T != null) {
    // if (hoja(T.getHI()) && T.getHI().getData() == x) {
    // T.setHI(null);
    // }
    // if (hoja(T.getHD()) && T.getHD().getData() == x) {
    // T.setHD(null);
    // }
    // delHoja(T.getHI(), x);
    // delHoja(T.getHD(), x);

    // }
    // }

    // PREGUNTA 7

    public int cantLadders() {
        return cantLadders(Raiz);
    }

    private int cantLadders(Nodo T) {
        if (T == null)
            return 0;
        if (hoja(T))
            return 0;
        int c = 0;
        if (isLadder(T))
            c = 1;
        c = c + cantLadders(T.getHI());
        c = c + cantLadders(T.getHD());
        return c;

    }

    private boolean isLadder(Nodo T) {
        if (T != null) {
            if (T.getHD() != null && T.getHI() != null
                    && T.getHD().getData() == (T.getData() + 1)
                    && T.getHI().getData() == (T.getData() - 1)) {
                return true;
            }
        }
        return false;
    }

    // PREGUNTA 8
    public void delHojaR(int x) {
        delHojaR(Raiz, x);
    }

    private void delHojaR(Nodo T, int x) {
        if (T != null) {
            if (hoja(T.getHI()) && T.getHI().getData() == x) {
                T.setHI(null);
            }
            if (hoja(T.getHD()) && T.getHD().getData() == x) {
                T.setHD(null);
            }
            delHojaR(T.getHI(), x);
            delHojaR(T.getHD(), x);

        }
    }

    // PREGUNTA 9

    // public boolean isDescIncompleto(int x) {
    // return isDescIncompleto(Raiz, x);
    // }

    // private boolean isDescIncompleto(Nodo T, int x) {
    // if (T == null)
    // return false;
    // if (T.getData() == x) {
    // if (T.getHI() != null && T.getHD() == null)
    // return true;
    // if (T.getHI() == null && T.getHD() != null)
    // return true;
    // }
    // return isDescIncompleto(T.getHI(), x) || isDescIncompleto(T.getHD(), x);
    // }

    // PREGUNTA 10

    public boolean isBunch(int x) {
        return isBunch(Raiz, x);
    }

    private boolean isBunch(Nodo T, int x) {
        if (T == null)
            return false;
        if (T.getData() == x) { // encontrar el elemento en el nodo padre
            if (hoja(T.getHD()) && hoja(T.getHI())) { // si los hijos son hojas
                return true;
            }
        } else if (T.getHI() != null && T.getHI().getData() == x) { // si el nodo es el hijo izquierdo
            if (hoja(T.getHD()) && hoja(T.getHI())) { // si los hijos son hojas
                return true;
            }
        } else if (T.getHD() != null && T.getHD().getData() == x) { // si el nodo es el hijo derecho
            if (hoja(T.getHD()) && hoja(T.getHI())) { // si los hijos son hojas
                return true;
            }
        }
        return isBunch(T.getHI(), x) || isBunch(T.getHD(), x);
    }

    // PREGUNTA 11

    public void cutBunch(int x) {
        cutBunch(Raiz, x);
    }

    private void cutBunch(Nodo T, int x) {
        if (T != null) {
            if (T.getHI() != null && T.getHI().getData() == x) { // si el nodo es el hijo izquierdo
                if (isBunchPadre(T.getHI())) { // si los hijos son hojas
                    T.setHD(null);
                    T.setHI(null);
                }
            } else if (T.getHD() != null && T.getHD().getData() == x) { // si el nodo es el hijo derecho
                if (isBunchPadre(T.getHD())) { // si los hijos son hojas
                    T.setHD(null);
                    T.setHI(null);
                }
            }
            cutBunch(T.getHI(), x);
            cutBunch(T.getHD(), x);
        }
    }

    private boolean isBunchPadre(Nodo T) {
        if (T != null) {
            if (hoja(T.getHD()) && hoja(T.getHI())) {
                return true;
            }
        }
        return false;
    }

}
