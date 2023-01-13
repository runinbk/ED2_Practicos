package Graph;

import java.util.LinkedList;

public class Grafo { // Grafo NO-Dirigido
    private static final int MAXVERTEX = 49; // Máximo índice de V[]

    private Lista V[];
    private int n; // "Dimensión" de V[]

    public Grafo() {
        V = new Lista[MAXVERTEX + 1]; // V[0..MAXVERTEX]
        n = -1;

        marca = new boolean[MAXVERTEX + 1]; // Iniciar la ED para el marcado de los vértices.
    }

    public void addVertice() {
        if (n == MAXVERTEX) {
            // throw new Exception("Grafo.addVertice: Demasiados vértices (solo se permiten
            // "+(MAXVERTEX+1)+")");
            System.err.println("Grafo.addVertice: Demasiados vértices (solo se permiten " + (MAXVERTEX + 1) + ")");
            return;
        }

        n++;
        V[n] = new Lista(); // Crear un nuevo vértice sin adyacentes (o sea con su Lista de adyacencia
                            // vacía)
    }

    public int cantVertices() {
        return n + 1;
    }

    public boolean isVerticeValido(int v) {
        return (0 <= v && v <= n);
    }

    public void addArista(int u, int v) {
        String metodo = "addArista";
        if (!isVerticeValido(u, metodo) || !isVerticeValido(v, metodo))
            return; // No existe el vertice u o el vertice v.

        V[u].add(v);
        V[v].add(u);
    }

    public void delArista(int u, int v) {
        String metodo = "delArista";
        if (!isVerticeValido(u, metodo) || !isVerticeValido(v, metodo))
            return; // No existe el vertice u o el vertice v.

        V[u].del(v);
        V[v].del(u);
    }

    public int cantAristas() {
        int cont = 0;
        for (int i = 0; i <= n; i++) {
            cont = cont + V[i].length();
            if (tieneLazo(i))
                cont++; // Sumar como dos aristas a los lazos.
        }

        return cont / 2;
    }

    public boolean tieneLazo(int v) { // Devuelve true sii el vertice v tiene un lazo.
        if (!isVerticeValido(v, "tieneLazo"))
            return false; // No existe el vertice v.

        return V[v].existe(v);
    }

    public void dfs(int v) { // Recorrido Depth-First Search (en profundidad).
        if (!isVerticeValido(v, "dfs"))
            return; // Validación. v no existe en el Grafo.

        desmarcarTodos();
        System.out.print("DFS:");
        dfs1(v);
        System.out.println();
    }

    private void dfs1(int v) { // mask-function de void dfs(int)
        System.out.print(" " + v);
        marcar(v);

        for (int i = 0; i < V[v].length(); i++) { // for (cada w adyacente a v)
            int w = V[v].get(i);

            if (!isMarcado(w))
                dfs1(w);
        }
    }

    public void bfs(int u) { // Recorrido Breadth-First Search (en anchura).
        if (!isVerticeValido(u, "bfs"))
            return; // Validación. u no existe en el Grafo.

        desmarcarTodos();
        LinkedList<Integer> cola = new LinkedList<>(); // "cola" = (vacía) = (empty)
        cola.add(u); // Insertar u a la "cola" (u se inserta al final de la lista).
        marcar(u);

        System.out.print("BFS:");
        do {
            int v = cola.pop(); // Obtener el 1er elemento de la "cola".
            System.out.print(" " + v);

            for (int i = 0; i < V[v].length(); i++) { // for (cada w adyacente a v)
                int w = V[v].get(i);

                if (!isMarcado(w)) {
                    cola.add(w);
                    marcar(w);
                }
            }
        } while (!cola.isEmpty());

        System.out.println();
    }

    public void printListas() { // Muestra las listas del Grafo. Util para el programador de esta class
        if (cantVertices() == 0)
            System.out.println("(Grafo Vacío)");
        else {
            for (int i = 0; i <= n; i++) {
                System.out.println("V[" + i + "]-->" + V[i]);
            }
        }
    }

    @Override
    public String toString() { // Este método debe ser cambiado si el grafo es dirigido.
        if (cantVertices() == 0)
            return "(Grafo Vacío)";

        // Mostrar el grafo en notación matemática
        desmarcarTodos();
        String S = "[";
        String coma = "";
        for (int i = 0; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (V[i].existe(j)) { // j es ady a i.
                    S += coma + "{" + i + "," + j + "}"; // Mostrar la arista i-j entre llaves.
                    coma = ", ";

                    marcar(i);
                    marcar(j); // Decir que i y j SÍ tienen aristas
                }
            }

            if (!isMarcado(i)) { // El vértice i no tiene aristas, mostrarlo entre paréntesis.
                S += coma + "(" + i + ")";
                coma = ", ";
            }
        }

        return S + "]";
    }

    private boolean isVerticeValido(int v, String metodo) {
        boolean b = isVerticeValido(v);
        if (!b)
            System.err.println("Grafo." + metodo + ": " + v + " no es un vértice del Grafo " + getIndicacion());

        return b;
    }

    private String getIndicacion() { // Corrutina de boolean isVerticeValido(int, String)
        switch (cantVertices()) {
            case 0:
                return "(el grafo no tiene vértices). ";
            case 1:
                return "(el 0 es el único vértice). ";
            case 2:
                return "(0 y 1 son los únicos vértices). ";
            default:
                return "(los vértices van de 0 a " + (cantVertices() - 1) + "). ";
        }
    }

    // ********* Para el marcado de los vértices
    private boolean marca[];

    private void desmarcarTodos() {
        for (int i = 0; i <= n; i++) {
            marca[i] = false;
        }
    }

    private void marcar(int u) {
        if (isVerticeValido(u))
            marca[u] = true;
    }

    private void desmarcar(int u) {
        if (isVerticeValido(u))
            marca[u] = false;
    }

    private boolean isMarcado(int u) { // Devuelve true sii el vertice u está marcado.
        return marca[u];
    }

    // ----------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------
    // ------------------------------------PRACTICO--------------------------------------------
    // --------------------------------------------------------------------------------------
    // --------------------------------------------------------------------------------------

    // 1. En la class Grafo (no-dirigido), escriba el método

    // public boolean hayCamino(int u, int v)

    // el cual devuelva true sii existe un camino desde u hasta v.

    // hay camino recursivo
    public boolean hayCaminoR(int u, int v) {
        if (!isVerticeValido(u) || !isVerticeValido(v)) {
            return false;
        }
        desmarcarTodos();
        return hayCaminoR1(u, v);
    }

    private boolean hayCaminoR1(int u, int v) {
        marcar(u);
        if (u == v) {
            return true;
        }
        for (int i = 0; i < V[u].length(); i++) {
            int w = V[u].get(i);
            if (!isMarcado(w)) {
                if (hayCaminoR1(w, v)) {
                    return true;
                }
            }
        }
        return false;
    }

    // hay camino iterativo
    public boolean hayCamino(int u, int v) {
        if (isVerticeValido(u) && isVerticeValido(v)) {
            desmarcarTodos();
            LinkedList<Integer> cola = new LinkedList<>();
            cola.add(u);
            marcar(u);
            do {
                int w = cola.pop();
                if (w == v) {
                    return true;
                }
                for (int i = 0; i < V[w].length(); i++) {
                    int d = V[w].get(i);
                    if (!isMarcado(d)) {
                        cola.add(d);
                        marcar(d);
                    }
                }
            } while (!cola.isEmpty());
        }
        return false;
    }

    // --------------------------------------------------------------------------------------------

    // 2. En la class Grafo (no-dirigido), escriba el método

    // public boolean isLineal()

    // el cual devuelva true sii el grafo es lineal. Un grafo es lineal sii
    // (a) El Grafo tiene a lo sumo un vértice, o
    // (b) Si el grafo tiene dos o más vértices: El grafo es conexo,
    // dos vértices tienen exactamente una arista, mientras
    // que los demás (si existen) tienen exactamente dos
    // aristas.
    // (a los vértices que tienen una sola arista, le dicen
    // “extremos”)

    // se puede mucho mas corto y mas simple pero me dio pereza reahacerlo
    public boolean isLineal() {
        if (n == 0 && !tieneLazo(0)) { // si tiene un solo vertices y no tienen lazos es lineal
            return true;
        }
        if (n >= 1 && conexo()) {
            int ContadorExtremos = -1; // inicializamos con -1 para conincidir con n
            int ContadorNormales = -1; // los mismo que arriba
            for (int i = 0; i <= n; i++) {
                if (tieneLazo(i)) { // si tiene lazo no es lineal
                    return false;
                }
                if (V[i].length() > 2) { // si supera las 2 aristas no es lineal
                    return false;
                }
                if (V[i].length() == 2) { // si tienen dos aristar incrementamos los vertices normales
                    ContadorNormales++;
                }
                if (V[i].length() == 1) { // si tiene 1 arista incrementamos los vertices extremos
                    ContadorExtremos++;
                }
            }
            // si los extremos son 1(2) y los normales n-2 es lineal
            // tener en cuenta que el contador empieza en -1 no en 0
            return ContadorExtremos == 1 && ContadorNormales == n - 2;
        }
        return false;
    }

    // ----------------------------------------------------------------------------------------

    // 3. En la class Grafo (no-dirigido), escriba el método

    // public int costoCamino(int u, int v)
    // // Por comodidad, asuma que u es diferente de v.

    // el cual devuelva –1 si desde u no existe un camino hasta v.
    // Si existe un camino desde u hasta v, el costo se calcula así:
    // Por cada arista (w, z) que se transite el costo será w+z. Por
    // ejemplo:

    public int costoCamino(int u, int v) {
        if (!isVerticeValido(u) || !isVerticeValido(v)) {
            return -1;
        }
        desmarcarTodos();
        int costo = 0;
        LinkedList<Integer> cola = new LinkedList<>();
        boolean marcas[] = new boolean[n + 1]; // creamos un vector para marcar
        cola.add(u);
        marcas[u] = true;
        do {
            int w = cola.pop();
            for (int i = 0; i < V[w].length(); i++) {
                int d = V[w].get(i);
                if (marcas[d] == false) {
                    if (hayCaminoNew(d, v, marcas)) {
                        costo += w + d;
                        cola.add(d);
                        marcas[d] = true;
                        break;
                    }
                    marcas[d] = true;
                }
            }
        } while (!cola.isEmpty());
        return (costo == 0) ? -1 : costo;
    }

    // ----------------------------------------------------------------------------------------

    // 4. En la class Grafo (no-dirigido), escriba el método

    // public boolean isCirculo()

    // el cual devuelva true si y solo si, el grafo forma un círculo con
    // todos sus vértices y todas sus aristas.

    // public boolean isCirculo(){

    // }

    // ----------------------------------------------------------------------------------------

    // 6. En la class Grafo (no-dirigido), escriba el método

    // public void delAristas(int u, int v)

    // el cual elimine las aristas que se encuentran al transitar desde el vértice u
    // al vértice v. Si no hay camino desde u hasta v, este algoritmo no
    // elimina ninguna arista.
    // Por comodidad, asuma que u es diferente de v.
    // (No elimine los lazos que encuentre en el camino)

    // public void delAristas(int u, int v){

    // }

    // ----------------------------------------------------------------------------------------

    // 7. En la class Grafo, escriba el método

    // public void delLazos(int u, int v)

    // el cual elimine los lazos que se encuentran al transitar desde el vértice u
    // al vértice v. Si no hay camino desde u hasta v, éste algoritmo no
    // elimina ningún lazo.
    // Por comodidad, asuma que u es diferente de v.

    // elimina los lazos que encuentra entre a y b si no hay camino no elimina nada
    public void delLazos(int u, int v) {
        if (!isVerticeValido(u) || !isVerticeValido(v)) {
            return;
        }
        desmarcarTodos();
        delLazos1(u, v);
    }

    private void delLazos1(int u, int v) {
        marcar(u);
        if (tieneLazo(u) && hayCamino(u, v, marca)) {
            V[u].del(u);
        }
        if (u == v) {
            return;
        }
        for (int i = 0; i < V[u].length(); i++) {
            int w = V[u].get(i);
            if (!isMarcado(w)) {
                delLazos1(w, v);
                if (isMarcado(v)) {
                    break;
                }
            }
        }
    }

    // ----------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------
    // -------------------------METODOS_AUXILIARES---------------------------------------------
    // ----------------------------------------------------------------------------------------

    // EJER 2
    // variante del dfs1 pero sin los print
    private void Corredor(int v) {
        marcar(v);
        for (int i = 0; i < V[v].length(); i++) {
            int w = V[v].get(i);
            if (!isMarcado(w)) {
                Corredor(w);
            }
        }
    }

    public boolean conexo() {
        desmarcarTodos();
        Corredor(0);
        for (int i = 0; i <= n; i++) {
            if (!isMarcado(i)) {
                return false;
            }
        }
        return true;
    }

    // EJER 3
    // recibe un vector de booleanos equivalente al vector de marcas
    public boolean hayCaminoNew(int u, int v, boolean[] marcas) {
        if (isVerticeValido(u) && isVerticeValido(v)) {
            LinkedList<Integer> cola = new LinkedList<>();
            boolean marcas2[] = marcas.clone();
            cola.add(u);
            marcas2[u] = true;
            do {
                int w = cola.pop();
                if (w == v) {
                    return true;
                }
                for (int i = 0; i < V[w].length(); i++) {
                    int d = V[w].get(i);
                    if (marcas2[d] == false) {
                        cola.add(d);
                        marcas2[d] = true;
                    }
                }
            } while (!cola.isEmpty());
        }
        return false;
    }

    // EJER 7
    public boolean hayCamino(int u, int v, boolean[] marcas) {
        if (isVerticeValido(u) && isVerticeValido(v)) {
            LinkedList<Integer> cola = new LinkedList<>();
            boolean marcas2[] = marcas.clone();
            cola.add(u);
            marcas2[u] = true;
            do {
                int w = cola.pop();
                if (w == v) {
                    return true;
                }
                for (int i = 0; i < V[w].length(); i++) {
                    int d = V[w].get(i);
                    if (marcas2[d] == false) {
                        cola.add(d);
                        marcas2[d] = true;
                    }
                }
            } while (!cola.isEmpty());
        }
        return false;
    }

    // ----------------------------------------------------------------------------------------
    // ---------------------------------EJERCICIOS_EXTRA---------------------------------------
    // ----------------------------------------------------------------------------------------

    // cantidad de islas
    public int cantIslas() {
        int c = 0;
        desmarcarTodos();
        Corredor(0);
        c++;
        for (int i = 0; i <= n; i++) {
            if (!isMarcado(i)) {
                Corredor(i);
                c++;
            }
        }
        return c;
    }

    public int pesoMenor(float[] peso) {
        float p = Float.MAX_VALUE;
        int pos = -1;
        for (int i = 0; i < peso.length; i++) {
            if (peso[i] < p && !isMarcado(i)) {
                p = peso[i];
                pos = i;
            }
        }
        return pos;
    }

    public float costoCaminoMinimo(int u, int v) {
        float peso[] = new float[n + 1];
        desmarcarTodos();
        for (int i = 0; i < peso.length; i++) {
            if (i == u) {
                peso[i] = 0;
            } else {
                peso[i] = Float.MAX_VALUE;
            }
        }
        while (!isMarcado(v)) {
            int w = pesoMenor(peso);
            marcar(w);
            for (int i = 0; i < V[w].length(); i++) {
                int d = V[w].get(i);
                peso[d] = peso[w] + (w + d);
            }
        }
        return peso[v];
    }

}
