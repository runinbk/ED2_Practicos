package List;

public class Lista { // ADT Lista (Ordenada y sin duplicados).
    private Nodo L;
    private int n;

    public Lista() {
        L = null;
        n = 0;
    }

    public boolean isVacia() { // Devuelve true sii esta lista está vacía.
        return (L == null);
    }

    public void add(int x) { // Inserta x a la Lista.
        Nodo Ant = null;
        Nodo p = L;

        while (p != null && x >= p.getData()) {
            Ant = p;
            p = p.getLink();
        }

        Nodo nuevo;
        if (Ant == null) { // x es menor a todos los que están en la Lista (o L==null)
            nuevo = new Nodo(x);
            nuevo.setLink(L);
            L = nuevo;
            n++;
        } else if (Ant.getData() != x) { // x no está en la lista. Insertarlo entre Ant y p
            nuevo = new Nodo(x);
            Ant.setLink(nuevo);
            nuevo.setLink(p);
            n++;
        }
    }

    public void del(int x) {
        Nodo Ant = null;
        Nodo p = L;

        while (p != null && x > p.getData()) {
            Ant = p;
            p = p.getLink();
        }

        if (p != null && p.getData() == x) { // x existe en la Lista
            if (Ant == null)
                L = L.getLink(); // x era el primero de la Lista
            else
                Ant.setLink(p.getLink());

            p.setLink(null);
            n--;
        }
    }

    public int indexOf(int x) {
        int index = 0;
        Nodo p = L;
        while (p != null) {
            if (p.Data == x)
                return index;

            if (p.Data > x)
                return -1;

            p = p.Link;
            index++;
        }

        return -1;
    }

    public boolean contains(int x) {
        return (indexOf(x) != -1);
    }

    /**
     * PRE: k es un valor entre 0 y length()-1.<br/>
     * <br/>
     * Devuelve el elemento de la posición k de la Lista. Las posiciones se
     * enumeran desde el 0. <br/>
     * <br/>
     * Por ejemplo, si la lista es [4, 7, 9], entonces: <br/>
     * <blockquote>get(0)=4, get(1)=7 y get(2)=9</blockquote>
     * 
     * @param k Posición del elemento a obtener.
     * @return El elemento de la posición k de la Lista.
     */
    public int get(int k) {
        if (k < 0 || k > length() - 1) // Diverge con la PRE
            throw new RuntimeException("Lista.get: Índice " + k + ", fuera de rango.");

        Nodo p = L;
        for (int i = 1; i <= k; i++) { // Hacer que p salte k veces.
            p = p.getLink();
        }

        return p.getData();
    }

    public int length() {
        return n;
    }

    /*
     * 
     * PRACTICO 1 => LISTAS ENLASADAS
     * 
     */

    /*
     * 1. En la class Lista (ordenada y sin duplicados),
     * adicione los métodos:
     */

    /*
     * public void delNth(int i){
     * Pre: 0 ≤ i ≤ length()-1
     * Elimina el elemento de la posición i de la Lista.
     * Recuerde que las posiciones de los elementos se
     * enumeran desde el 0.
     */

    public void delNth(int i) {
        Nodo Ant = null;
        Nodo p = L;
        int cont = 0;

        while (p != null && i > cont) {
            Ant = p;
            p = p.getLink();
            cont++;
        }

        if (p != null && cont == i) { // x existe en la Lista
            if (Ant == null)
                L = L.getLink(); // x era el primero de la Lista
            else
                Ant.setLink(p.getLink());

            p.setLink(null);
            n--;
        }
    }

    /// iterativo
    /*
     * public void delNth(int i){
     * Nodo Ant = null;
     * Nodo p = L;
     * int cont = 0;
     * while(cont < i && p != null){
     * Ant = p;
     * p = p.getLink();
     * cont ++;
     * }
     * if (p != null && cont == i){ //x existe en la Lista
     * if (Ant == null)
     * L = L.getLink(); //x era el primero de la Lista
     * else
     * Ant.setLink(p.getLink());
     * 
     * p.setLink(null);
     * n--;
     * }
     * 
     * }
     */

    /*
     * public int getLast(){
     * //Pre: La lista no está vacía.
     * // Devuelve (return) el Data del último nodo de la Lista.
     * }
     */

    public int getLast() {
        Nodo Ant = null;
        Nodo p = L;
        while (p != null) {
            Ant = p;
            p = p.getLink();
        }
        return Ant.getData();
    }

    /*
     * public boolean exist(int x){ //Usar RECURSIÓN.
     * // Devuelve (return) true si x existe en la Lista; false si no.
     * //(Este método obviamente debe usar una función máscara recursiva).
     * }
     * 
     */

    public boolean exist(int x) {
        return exist(L, x);
    }

    private boolean exist(Nodo p, int x) {
        if (p == null) {
            return false;
        } else if (p.getData() == x) {
            return true;
        } else
            return exist(p.getLink(), x);

    }

    /*
     * 2. En una class, implemente el ADT Cola (de enteros), utilizando una
     * single-list con dos punteros principales : Frente y Atras. El puntero
     * Frente apunta al primer nodo, mientras que Atras al último.
     */
    /*
     * public class Cola{
     * private Nodo Frente, Atras;
     * private int n; //n=Cantidad de nodos.
     * public Cola(){ //Construye una cola vacía
     * Frente=Atras=null;
     * n=0;
     * }
     */
    public class Cola {
        private Nodo Frente, Atras;
        private int n; // n=Cantidad de nodos.

        public Cola() { // Construye una cola vacía
            Frente = Atras = null;
            // Frente = null;
            // Atras = Frente;
            n = 0;
        }

        /*
         * public boolean isVacia(){
         * //Devuelve true si la cola está vacía; false en caso contrario.
         * }
         */
        public boolean isVacia() {
            return Frente != Atras;
        }

        /*
         * public void meter(int x){ //Inserta x al final de la Cola }
         */
        public void meter(int x) {
            if (!isVacia()) {
                Nodo p = new Nodo();
                // Nodo ant = new Nodo();
                while (p.getLink() != Frente) {
                    // ant = p;
                    p = p.getLink();
                }
                Nodo q = new Nodo(x);
                q = p.getLink();
                p = q;
            }
            n++;
        }

        /*
         * public int sacar(){
         * //Pre: La lista no está vacía.
         * //Extrae el 1er. elemento de la cola, devolviendo (return) este elemento.
         * }
         */
        public int sacar() {
            int dato = Frente.getLink().getData();
            Frente = Frente.getLink();
            return dato;
        }

        /*
         * public int length(){ //Return la cantidad de elementos }
         * }
         * 
         * Cree un nuevo Proyecto de NetBeans, para implementar este ADT.
         */
        public int length() {
            return n;
        }

    }

    // EJERCICIOS TIPO PARCIAL

    public void delInto(int a, int b) {
        delInto(L, a, b, false);
    }

    private void delInto(Nodo p, int a, int b, boolean flag) {
        if (p != null) {
            if (p.getData() == b) {
                flag = false;
            }
            if (p.getData() == a) {
                flag = true;
            }
            if (flag) {
                eliminar(a);
            } else {
                delInto(p.getLink(), a, b, flag);
            }

            // delInto(p.getLink(), a, b, flag);

        }
    }

    private void eliminar(int x) {
        eliminar(L, x);
    }

    private void eliminar(Nodo p, int x) {
        if (p != null) {
            if (p.getData() == x) {
                p.setLink(p.getLink().getLink());
            }
            eliminar(p.getLink(), x);
        }
    }

    /////////////////////////////////////////////////////////////////
    @Override
    public String toString() {
        String S = "[";
        String coma = "";

        Nodo p = L;
        while (p != null) {
            S += coma + p.getData();
            coma = ", ";
            p = p.getLink();
        }

        return S + "]";
    }

}
