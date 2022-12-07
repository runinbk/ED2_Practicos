package Mod_examen_4;

public class Lista {

    private Nodo l;

    public Lista() {
        l = null;
        cargar();
    }

    public void add(int nuevoNro, int nroBotar) {
        if (nuevoNro != nroBotar) {
            if (cantexistente(nroBotar) == 0) {
                return;
            } else {
                Nodo p = l;
                while (p != null) {
                    if (p.getData() == nroBotar) {
                        p.setData(nuevoNro);
                        if (cantexistente(nuevoNro) == 3) {
                            dell(nuevoNro);
                        }
                        return;
                    }
                    p = p.getLink();
                }
            }
        }
    }

    private void dell(int x) {
        if (l != null) {
            Nodo p = l;
            Nodo ant = null;
            while (p != null) {
                if (p.getData() == x) {
                    if (ant == null) {
                        l = l.getLink();
                        p = l;
                        continue;
                    } else {
                        ant.setLink(p.getLink());
                    }
                }
                ant = p;
                p = p.getLink();
            }

        }
    }

    private void addr(int x) {

        if (l == null) {
            l = new Nodo(x);
        } else {
            if (cantexistente(x) < 3) {
                Nodo p = l;
                while (p.getLink() != null) {
                    p = p.getLink();
                }
                p.setLink(new Nodo(x));
            }
        }
    }

    public void cargar() {
        for (int i = 0; i < 4; i++) {
            addr((int) (Math.random() * 9 + 1));
        }
    }

    public int cantexistente(int x) {
        Nodo p = l;
        int c = 0;
        while (p != null) {
            if (p.getData() == x) {
                c++;
            }
            p = p.getLink();
        }
        return c;
    }

    @Override
    public String toString() {
        String S = "[";
        String coma = "";

        Nodo p = l;
        while (p != null) {
            S += coma + p.getData();
            coma = ", ";
            p = p.getLink();
        }

        return S + "]";
    }
}
