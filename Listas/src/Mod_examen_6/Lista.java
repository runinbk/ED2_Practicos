package Mod_examen_6;

public class Lista {    //ADT Lista (Ordenada y sin duplicados).

    private Nodo L;
    private int n;

    public Lista() {
        L = null;
        n = 0;
    }

    public boolean isVacia() {  //Devuelve true sii esta lista está vacía.
        return (L == null);
    }

    public void add(int x) { //Inserta x a la Lista.
        Nodo Ant = null;
        Nodo p = L;

        while (p != null && x >= p.getData()) {
            Ant = p;
            p = p.getLink();
        }

        Nodo nuevo;
        if (Ant == null) {   //x es menor a todos los que están en la Lista (o L==null)
            nuevo = new Nodo(x);
            nuevo.setLink(L);
            L = nuevo;
            n++;
        } else if (Ant.getData() != x) {    //x no está en la lista.  Insertarlo entre Ant y p
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

        if (p != null && p.getData() == x) {  //x existe en la Lista 
            if (Ant == null) {
                L = L.getLink();    //x era el primero de la Lista
            } else {
                Ant.setLink(p.getLink());
            }

            p.setLink(null);
            n--;
        }
    }

    public int indexOf(int x) {
        int index = 0;
        Nodo p = L;
        while (p != null) {
            if (p.Data == x) {
                return index;
            }

            if (p.Data > x) {
                return -1;
            }

            p = p.Link;
            index++;
        }

        return -1;
    }

    public boolean existe(int x) {
        Nodo p = L;

        while (p != null && x > p.getData()) {
            p = p.getLink();
        }

        return (p != null && p.getData() == x);
    }

    // invertir un segmento de la lista
    public Nodo getNodo(int pos) {
        if (pos < n) {
            Nodo p = L;
            int c = 0;
            while (p != null) {
                if (c == pos) {
                    return p;
                }
                c++;
                p = p.Link;
            }

        }
        return null;
    }

    public void invX(int pos, int cant) {
        int a = pos;
        int b = pos + cant - 1;
        while (a <= b) {
            Nodo me = getNodo(a);
            Nodo ma = getNodo(b);
            if (me != null && ma != null) {
                int aux = me.getData();
                me.setData(ma.getData());
                ma.setData(aux);
                a++;
                b--;
            }
        }
    }

    /**
     * PRE: 0 <= k <= length()-1 Devuelve el elemento de la posición k de la
     * Lista. Las posiciones se enumeran desde el 0. <br/><br/> Por ejemplo, si
     * la lista es [4, 7, 9], entonces:
     * <br/>
     * <blockquote>get(0)=4, get(1)=7 y get(2)=9
     * </blockquote>
     *
     * @param k Posición del elemento a obtener (k debe ser un valor entre 0 y
     * length()-1)
     * @return
     */
    public int get(int k) {
        if (k < 0 || k > length() - 1) //Diverge con la PRE
        {
            throw new RuntimeException("Lista.get: Índice fuera de rango");
        }

        Nodo p = L;
        int index = 0;
        while (p != null) {
            if (index == k) {
                return p.getData();
            }

            p = p.getLink();
            index++;
        }

        return -1;
    }

    public int length() {
        return n;
    }

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
