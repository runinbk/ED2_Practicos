package Mod_examen_5;

public class Lista {

    private Nodo l;

    public Lista() {
        l = null;
    }

    public void add(int id, int valor) {
        if (l == null) {
            l = new Nodo(id, valor);
        } else {
            Nodo p = l;
            Nodo ant = null;
            while (p != null && id >= p.getId()) {
                ant = p;
                p = p.getLink();
            }
            if (ant == null) {
                Nodo d = new Nodo(id, valor);
                d.setLink(l);
                l = d;
            } else {
                if (ant.getId() == id) {
                    ant.setValor(valor);
                } else {
                    Nodo nuevo = new Nodo(id, valor);
                    ant.setLink(nuevo);
                    nuevo.setLink(p);
                }
            }

        }

    }

    @Override
    public String toString() {
        String S = "[";
        String coma = "";

        Nodo p = l;
        while (p != null) {
            S += coma + p.getId() + "|" + p.getValor();
            coma = ", ";
            p = p.getLink();
        }

        return S + "]";
    }
}
