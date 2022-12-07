package Practico_Listas_2;

public class Cola {

    private Nodo frente, atras;
    private int n;

    public Cola() {
        frente = atras = null;
        n = 0;
    }

    public boolean isVacia() {
        return n == 0;
    }

    public void meter(int x) {
        if (isVacia()) {
            frente = atras = new Nodo(x);
            n++;
        } else {
            Nodo p = new Nodo(x);
            atras.setLink(p);
            atras = p;
            n++;
        }
    }

    public int sacar() {
        if (!isVacia()) {
            int d = frente.getData();
            frente = frente.getLink();
            n--;
            return d;
        }
        return -1;
    }

    public int length() {
        return n;
    }
}
