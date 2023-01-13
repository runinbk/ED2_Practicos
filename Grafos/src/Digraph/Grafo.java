
package Digraph;

import java.util.LinkedList;

public class Grafo { // Grafo Dirigido
    private static final int MAXVERTEX = 49; // Máximo índice de V[]

    private Lista V[];
    private int n; // "Dimensión" de V[]

    public Grafo() {
        V = new Lista[MAXVERTEX + 1]; // V[0..MAXVERTEX]
        n = -1;

        marca = new boolean[MAXVERTEX + 1]; // Iniciar la ED para el marcado de los vértices.
    }

    public boolean isVerticeValido(int v) {
        return (0 <= v && v <= n);
    }

    public void addVertice() {
        if (n == MAXVERTEX) {
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

    public void addArista(int u, int v) { // Crea la arista u-->v
        String metodo = "addArista";
        if (!isVerticeValido(u, metodo) || !isVerticeValido(v, metodo))
            return; // No existe el vertice u o el vertice v.

        V[u].add(v); // Adicionar v a la lista V[u]
    }

    public void delArista(int u, int v) {
        String metodo = "delArista";
        if (!isVerticeValido(u, metodo) || !isVerticeValido(v, metodo))
            return; // No existe el vertice u o el vertice v.

        V[u].del(v); // Quitar v a la lista V[u]
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

    // 5. Informalmente decimos que un vértice V es una “hoja”, si V
    // no tiene adyacentes. En la class Grafo (dirigido), escriba usted el
    // procedimiento:

    // public void printHojas(int u)

    // el cual, muestre en consola (System.out.println) todos los
    // vértices hojas que son alcanzables a partir del vértice u.
    // Por ejemplo: (Los vértices hojas están sombreados

    // printHojas(0); //Consola: 0 (Partiendo desde el 0, la única hoja que
    // //se puede visitar es así misma)
    // printHojas(1); //Consola: 1
    // printHojas(2); //Consola: 7 (Partiendo desde el 2, la única hoja que se
    // //puede alcanzar es el 7)
    // printHojas(3); //Consola: 1, 0 (Partiendo desde el 3, podemos visitar
    // //las hojas 1 y 0)
    // printHojas(4); //Consola: 0

    public void printHojasR(int u) {
        if (!isVerticeValido(u)) {
            return;
        }
        desmarcarTodos();
        printHojasR2(u);
        System.out.println("");
    }

    private void printHojasR1(int u) {
        marcar(u);
        if (isHoja(u)) {
            System.out.print(u + " | ");
        }
        int w;
        for (int i = 0; i < V[u].length(); i++) {
            w = V[u].get(i);
            if (!isMarcado(w)) {
                printHojasR1(w);
            }
        }
        for (int i = 0; i <= n; i++) {
            if (V[i].existe(u) && !isMarcado(i)) {
                printHojasR1(i);
            }
        }
    }

    private void printHojasR2(int u) {
        marcar(u);
        if (isHoja(u)) {
            System.out.print(u + " | ");
        }
        LinkedList<Integer> lista = conexion(u);
        for (int i = 0; i < lista.size(); i++) {
            int w = lista.get(i);
            if (!isMarcado(w)) {
                printHojasR2(w);
            }
        }
    }

    // ----------------------------------------------------------------------------------------

    // 8. En la class Grafo (dirigido), escriba usted un DFS especial, con la
    // función

    // public String path(int a)

    // la cual partiendo de un vértice a, continúa con el vértice adyacente
    // (no-marcado) cuyo número de vértice sea el menor. Finalmente, devuelve
    // la ruta efectuada.
    // Por ejemplo

    // Tip: Cada vez que usted visite un vértice u, márquelo usando el método
    // private marcar(u)

    // Explicando path(3)
    // Visitamos el vértice 3 (y lo marcamos). Observamos que 3 tiene como
    // adyacentes no marcados a: 1, 4 y 6. Como el 1 es el vértice menor,
    // entonces visitamos el 1.
    // Visitamos el vértice 1 (y lo marcamos). El vértice 1 tiene como único
    // adyacente no marcado al 6. No tenemos más opción que visitar el 6.
    // Visitamos el vértice 6 (y lo marcamos). El vértice 6 tiene un adyacente no
    // marcado: el 2 (el 1 es adyacente del 6, pero está marcado)
    // Visitamos el vértice 2 (y lo marcamos). El vértice 2 NO tiene adyacentes no
    // marcados. Finalizamos.
    // La ruta seguida fue: 3 ---> 1 ---> 6 ---> 2

    // Recuerde: Un DFS jamás visita un vértice más de una vez (por eso se usa el
    // marcado de vértices).

    public String path(int a) {
        String s = "";
        if (isVerticeValido(a)) {
            desmarcarTodos();
            LinkedList<Integer> cola = new LinkedList<>();
            cola.add(a);
            marcar(a);
            s += a + "/";
            do {
                int w = cola.pop();
                for (int i = 0; i < V[w].length(); i++) { // recorremos los adyacentes
                    int d = verticeMenor(w); // nos devuelve el vertice menor y -1 si no existe
                    if (d != -1) { // si es -1 no existe
                        cola.add(d);
                        marcar(d);
                        s += d + "/";
                        break; // una vez escogemos el vertice siguiente, rompemos el ciclo
                        // ya que ya no encesitamos verificar los demas
                    }
                }
            } while (!cola.isEmpty());
        }
        return s;
    }

    // ----------------------------------------------------------------------------------------

    // 9. En la class Grafo, escriba la función

    // public int edges(int u)

    // la cual devuelva la cantidad de aristas que tiene la isla que contiene al
    // vértice u.
    // Por ejemplo

    // • El Grafo A tiene tres islas: (2, 6), (0, 8, 3, 1 ) y (5, 4, 7). Así,
    // A.edges(2)= 1 //Porque el vértice 2 está en la primera isla (2,6) y ésta
    // tiene una arista.
    // A.edges(0)= 5 //Porque el vértice 0 está en la isla(0, 8, 3, 1 ) y ésta tiene
    // 5 aristas.
    // A.edges(5)= 2 //Porque el vértice 5 está en la isla y (5, 4, 7) y ésta isla
    // tiene 2 aristas.
    // A.edges(7)= 2 //Porque el vértice 7 está en la isla y (5, 4, 7) y ésta isla
    // tiene 2 aristas.
    // • El Grafo B tiene cinco islas:
    // B.edges(6)= 4 //Porque el vértice 6 está en la isla (2, 6, 8) y ésta isla
    // tiene 4 aristas.
    // B.edges(4)= 0 //Porque el vértice 4 está en la isla (4) y ésta isla no tiene
    // aristas.

    // cantidad de aristas por isla
    public int edgesR(int u) {
        if (!isVerticeValido(u)) {
            return -1;
        }
        desmarcarTodos();
        return edgesR1(u);
    }

    public int edgesR1(int u) {
        marcar(u);
        int w, k = 0;
        for (int i = 0; i < V[u].length(); i++) {
            w = V[u].get(i);
            if (!isMarcado(w)) {
                k += edgesR1(w);
            }
        }
        for (int i = 0; i <= n; i++) {
            if (V[i].existe(u) && !isMarcado(i)) {
                k += edgesR1(i);
            }
        }
        return k + V[u].length();
    }

    // ----------------------------------------------------------------------------------------
    // -------------------------METODOS_AUXILIARES---------------------------------------------
    // ----------------------------------------------------------------------------------------

    // EJER 5
    public boolean isHoja(int u) {
        return V[u].length() == 0;
    }

    public LinkedList<Integer> conexion(int u) {
        LinkedList<Integer> cox = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            if (V[i].existe(u) && !isMarcado(i)) {
                cox.add(i);
            }
        }
        for (int i = 0; i < V[u].length(); i++) {
            int w = V[u].get(i);
            if (!isMarcado(w)) {
                cox.add(w);
            }
        }
        return cox;
    }

    // EJER 8
    public int verticeMenor(int v) {
        if (!isVerticeValido(v)) {
            return -1;
        }
        int m = -1;
        int w;
        // elegimos m el primer elemento no marcado
        for (int p = 0; p < V[v].length(); p++) {
            w = V[v].get(p);
            if (!isMarcado(w)) {
                m = w;
                break; // una vez encontramos el primer elemento no marcado compemos el ciclo
            }
        }
        return m;
    }

    // ----------------------------------------------------------------------------------------
    // ---------------------------------EJERCICIOS_EXTRA---------------------------------------
    // ----------------------------------------------------------------------------------------

}
