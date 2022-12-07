package Practico_Listas_3;

public class Cola {

    private Nodo l;
    private int n;

    public Cola() {
        l = null;
        n = 0;
    }

    public boolean isVacia() {
        return n == 0;
    }

    public void meter(int x) {
        Nodo p = l;
        if (isVacia()) {
            l = new Nodo(x);
            l.setLink(l);
            n++;
        } else {
            Nodo d = new Nodo(x);
            d.setLink(l.getLink());
            l.setLink(d);
            l = d;
            n++;
        }
    }

    public int sacar() {
        if (!isVacia()) {
            int d = l.getLink().getData();
            l.setLink(l.getLink().getLink());
            n--;
            return d;
        }
        return -1;
    }

    
}
