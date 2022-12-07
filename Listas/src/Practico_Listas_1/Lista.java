package Practico_Listas_1;

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
            p = p.getSig();
        }

        Nodo nuevo;
        if (Ant == null) {   //x es menor a todos los que están en la Lista (o L==null)
            nuevo = new Nodo(x);
            nuevo.setSig(L);
            L = nuevo;
            n++;
        } else if (Ant.getData() != x) {    //x no está en la lista.  Insertarlo entre Ant y p
            nuevo = new Nodo(x);
            Ant.setSig(nuevo);
            nuevo.setSig(p);
            n++;
        }
    }

    public void del(int x) {
        Nodo Ant = null;
        Nodo p = L;

        while (p != null && x > p.getData()) {
            Ant = p;
            p = p.getSig();
        }

        if (p != null && p.getData() == x) {  //x existe en la Lista 
            if (Ant == null) {
                L = L.getSig();    //x era el primero de la Lista
            } else {
                Ant.setSig(p.getSig());
            }

            p.setSig(null);
            n--;
        }
    }

    public int indexOf(int x) {
        int index = 0;
        Nodo p = L;
        while (p != null) {
            if (p.data == x) {
                return index;
            }

            if (p.data > x) {
                return -1;
            }

            p = p.getSig();
            index++;
        }

        return -1;
    }

    public boolean existe(int x) {
        Nodo p = L;

        while (p != null && x > p.getData()) {
            p = p.getSig();
        }

        return (p != null && p.getData() == x);
    }

    //Ejercicio UNO
    public void delNth(int x) {
        if (x >= 0 && x < n) {
            Nodo p = L;
            Nodo ant = null;
            int pos = 0;
            while (p != null && pos != x) {
                ant = p;
                p = p.getSig();
                pos++;
            }
            if (pos == 0) {
                L = L.sig;
                n--;
                return;
            }
            ant.setSig(p.getSig());
            n--;
        }
    }

    public int getLast() {
        Nodo p = L;
        if (p != null) {
            while (p.getSig() != null) {
                p = p.getSig();
            }
            return p.getData();
        }
        return -1;
    }

    public boolean exist(int x) {
        return exist(L, x);
    }

    public boolean exist(Nodo t, int x) {
        if (t == null) {
            return false;
        }
        boolean b = exist(t.getSig(), x);
        if (t.getData() == x) {
            return true;
        }
        return b;
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

            p = p.getSig();
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
            p = p.getSig();
        }

        return S + "]";
    }

}
