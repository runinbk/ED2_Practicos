
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
        // System.out.print("BFS:");
        do {
            int v = cola.pop(); // Obtener el 1er elemento de la "cola".
            // System.out.print(" " + v);
            for (int i = 0; i < V[v].length(); i++) { // for (cada w adyacente a v)
                int w = V[v].get(i);
                if (!isMarcado(w)) {
                    cola.add(w);
                    marcar(w);
                }
            }
        } while (!cola.isEmpty());
        // System.out.println();
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
            // marca[i] = false;
            desmarcar(i);
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

    public void bfsForce(int u) { // Recorrido Breadth-First Search ( en anchura ) y doble sentido
        if (!isVerticeValido(u))
            return; // Validacion. u no existe en el grafo

        desmarcarTodos();
        LinkedList<Integer> cola = new LinkedList<>(); // "cola" = (vacia) = (empty)
        cola.add(u);
        marcar(u);
        System.out.print("BFS doble sentido :");

        do {
            int v = cola.pop(); // Obtener el 1er elemento de la "cola".
            System.out.print(" " + v);

            for (int i = 0; i < V[v].length(); i++) {
                int w = V[v].get(i);

                if (!isMarcado(w)) {
                    cola.add(w);
                    marcar(w);
                }
            }
            for (int j = 0; j < n; j++) {
                if (V[j].existe(v) && !isMarcado(j)) {
                    cola.add(j);
                    marcar(j);
                }
            }
        } while (!cola.isEmpty());
        System.out.println();
    }

    // ----------------------------------------------------------------------------------------
    // ---------------------------------EJERCICIOS_EXTRA---------------------------------------
    // ----------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------
    // ---------------------------------EXAMENES_RESUELTOS---------------------------------------
    // ----------------------------------------------------------------------------------------

    // 1-2017 / EXAMEN FINAL
    // Imaginando que los vértices del grafo son bolas de “plastoform” y que las
    // aristas son alambres
    // que se unen a los vértices, es criba la función

    // public boolean conectado()

    // la cual devuelva true sii todo el grafo puede ser cargado como una sola
    // pieza. Por ejemplo:

    public boolean conectado() {
        bfsForce(0);
        for (int i = 0; i < n; i++) {
            if (!isMarcado(i)) {
                return false;
            }
        }
        return true;
    }

    // ----------------------------------------------------------------------------------------

    // 1-2021 / EXAMEN FINAL
    // .1 En la class Grafo, escriba la función

    // public int edges(int u)

    // la cual devuelva la cantidad de aristas que tiene la isla que contiene al
    // vértice u

    public int edges(int u) {
        if (!isVerticeValido(u))
            return 0;

        desmarcarTodos();
        LinkedList<Integer> cola = new LinkedList<>();
        cola.add(u);
        marcar(u);
        int count = 1;

        do {
            int v = cola.pop();

            for (int i = 0; i < V[v].length(); i++) {
                int w = V[v].get(i);

                if (!isMarcado(w)) {
                    cola.add(w);
                    count++;
                    marcar(w);
                }
            }
            for (int j = 0; j < n; j++) {
                if (V[j].existe(v) && !isMarcado(j)) {
                    cola.add(j);
                    count++;
                    marcar(j);
                }
            }
        } while (!cola.isEmpty());
        return count;
    }

    // .2 Informalmente decimos que un vértice V es una “hoja”, si V no tiene
    // adyacentes. En la class Grafo, escriba usted el
    // procedimiento

    // public int cantHojas(int u)

    // la cual devuelva la cantidad de hojas que se encuentran en la misma “isla”
    // del vértice u.

    public int cantHojas(int u) {
        if (!isVerticeValido(u))
            return 0;

        desmarcarTodos();
        LinkedList<Integer> cola = new LinkedList<>();
        cola.add(u);
        marcar(u);
        int count = 0;

        do {
            int v = cola.pop();
            if (V[v].length() == 0) {
                count++;
            }

            for (int i = 0; i < V[v].length(); i++) {
                int w = V[v].get(i);
                if (!isMarcado(w)) {
                    cola.add(w);
                    marcar(w);
                }
            }
            for (int j = 0; j < n; j++) {
                if (V[j].existe(v) && !isMarcado(j)) {
                    cola.add(j);
                    marcar(j);
                }
            }
        } while (!cola.isEmpty());
        return count;
    }

    // ----------------------------------------------------------------------------------------

    // 2-2018 / EXAMEN FINAL
    // En la class Grafo, escriba el método

    // public void path2(int u)

    // el cual muestre en consola (print) los vértices que cruzando 2 aristas
    // (sin importar si es contraflecha) se pueden visitar.

    // A.path2(0)  1, 5
    // A.path2(1)  0, 5, 2
    // A.path2(3)  4, 5, 2
    // A.path2(6)  (nada)

    public void path2(int u) {
        if (!isVerticeValido(u))
            return;

        desmarcarTodos();
        LinkedList<Integer> cola = new LinkedList<>();
        cola.add(u);
        marcar(u);
        int c = 0;
        int a = 0;
        // String s = "path2 de " + u + " : ";
        System.out.print("path2 de " + u + " : ");
        do {
            int v = cola.pop(); // Obtener el 1er elemento de la "cola".
            for (int i = 0; i < V[v].length(); i++) {
                int w = V[v].get(i);
                if (!isMarcado(w)) {
                    if (c == 1) {
                        // s = s + " " + w;
                        System.out.print(" " + w);
                    }
                    cola.add(w);
                    marcar(w);
                    a++;
                }
            }
            for (int j = 0; j < n; j++) {
                if (V[j].existe(v) && !isMarcado(j)) {
                    if (c == 1) {
                        // s = s + " " + j;
                        System.out.print(" " + j);
                    }
                    cola.add(j);
                    marcar(j);
                    a++;
                }
            }
            if (a != 0) {
                c++;
            }
            a = 0;
        } while (!cola.isEmpty());
        System.out.println();
    }

    // ----------------------------------------------------------------------------------------

    // 2-2020 / 2do PARCIAL
    // Informalmente decimos que un vértice V es una “hoja”, si V no tiene
    // adyacentes.
    // En la class Grafo, escriba usted el procedimiento

    // public void printHojas(int u)

    // el cual muestre en consola (System.out.println), todos los vértices hojas que
    // se
    // encuentran en la misma “isla” del vértice u.

    // Por ejemplo: (Los vértices hojas están sombreados)
    // El orden en el cual se muestran en consola las hojas de una isla, no tiene
    // importancia.

    // printHojas(0); //Consola: 0, 1 (En la isla del vértice 0, las hojas son el 0
    // y el 1)
    // printHojas(1); //Consola: 0, 1
    // printHojas(5); //Consola: 0, 1
    // printHojas(9); //Consola: 9
    // printHojas(8); //Consola: 7

    public void printHojas(int u) {
        if (!isVerticeValido(u))
            return;

        desmarcarTodos();
        LinkedList<Integer> cola = new LinkedList<>();
        cola.add(u);
        marcar(u);
        System.out.print("printHolas de " + u + " : ");
        if (V[u].length() == 0) {
            System.out.print(" " + u);
        }

        do {
            int v = cola.pop();

            for (int i = 0; i < V[v].length(); i++) {
                int w = V[v].get(i);
                if (!isMarcado(w)) {
                    cola.add(w);
                    marcar(w);
                    if (V[w].length() == 0) {
                        System.out.print(" " + w);
                    }
                }
            }
            for (int j = 0; j < n; j++) {
                if (V[j].existe(v) && !isMarcado(j)) {
                    cola.add(j);
                    marcar(j);
                    if (V[j].length() == 0) {
                        System.out.print(" " + j);
                    }
                }
            }
        } while (!cola.isEmpty());
        System.out.println();
    }

    // ----------------------------------------------------------------------------------------

    // 2-2018 / 2do PARCIAL
    // En la class Grafo (dirigido), escriba la función

    // public boolean kAlcanzable(int u, int v, int k)
    // //Por comodidad, asuma que u≠v que k > 0 y que el grafo no tiene lazos.

    // La cual devuelva true si y solo si, el vértice v es k-alcanzable desde el
    // vértice u.
    // Un vértice z es k-alcanzable a partir de un vértice a, si partiendo desde a,
    // se puede alcanzar z, recorriendo hasta k aristas.

    public boolean kAlcanzable(int u, int v, int k) {
        if (!isVerticeValido(u) && !isVerticeValido(v))
            return false;

        desmarcarTodos();
        LinkedList<Integer> cola = new LinkedList<>();
        cola.add(u);
        marcar(u);
        int count = 0;
        int a = 0;
        System.out.print("BFS:");

        do {
            int x = cola.pop();
            // System.out.print(" " + v);
            if (a != 0) {
                count++;
            }
            a = 0;
            if (x == v && count <= k) {
                return true;
            }

            for (int i = 0; i < V[x].length(); i++) {
                int y = V[x].get(i);
                if (!isMarcado(y)) {
                    cola.add(y);
                    marcar(y);
                    a++;
                }
            }

        } while (!cola.isEmpty());
        // System.out.println();
        return false;
    }

    // ----------------------------------------------------------------------------------------

    // 2-2022/2doParcial.
    // Informalmente, decimos que dos vértices u y v son vecindos, si desde u es
    // posible visitar a v, recorriendo
    // exactamente dos aristas no-lazos, sin importar el sentido (i.e. se puede ir
    // en contraflecha). Sabiendo esto, en la class
    // Grafo, escriba la función

    // public boolean vecindos(int u, int v)
    // //Por comodidad, asuma que uv

    // la cual devuelva true si y solo si los vértices u y v son vecindos.
    // Por ejemplo, con el Grafo A:

    // A.vecindos(2,0)=true //Desde el vértice 2 cruzando dos aristas llegamos a 0
    // (2 → 8 →0)
    // A.vecindos(3,7)=true //Es posible recorrer dos aristas para llegar del 3 al 7
    // (3 → 5 →7).
    // A.vecindos(0,8)=false //Desde el 0 no es posible llegar al 8 recorriendo dos
    // aristas (no se puede transitar por los lazos).
    // A.vecindos(4,3)=false //Necesitamos recorrer más de dos aristas para ir del 4
    // al 3.
    // A.vecindos(6,8)=false //Desde el 6 no se puede llegar al 8.
    // A.vecindos(1,7)=true //Es posible recorrer dos aristas para llegar del 1 al 7
    // (1 → 6 →7)

    public boolean vecindos(int u, int v) {
        if (isVerticeValido(u) && isVerticeValido(v)) {
            for (int i = 0; i < V[u].length(); i++) {
                int w = V[u].get(i);
                for (int j = 0; j < V.length; j++) {
                    // for (int k = 0; k < V[j].length(); k++) {
                    // if (j == V[j].get(k)) {
                    // k++;
                    // }
                    if (V[j].existe(w) && j == v) {
                        return true;
                    }
                    // }
                }
            }
        }
        return false;
    }

}
