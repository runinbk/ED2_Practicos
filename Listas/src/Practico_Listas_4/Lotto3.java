package Practico_Listas_4;

public class Lotto3 {

    private Nodo l;
    private int n;

    public Lotto3(int n) {
        l = null;
        cargarRandom(n);
    }

    public void iniciar() {
        Nodo p = l;
        while (p != null) {
            p.setAsiertos(0);
            p = p.getLink();
        }
    }

    public int Registrar(int b) {
        Nodo p = l;
        if (p != null) {
            while (p != null) {
                if (p.getTicket() == b) {
                    p.acerto();
                    if (p.gano()) {
                        return p.getTicket();
                    } else {
                        return -1;
                    }
                }
                p = p.getLink();
            }
        }
        return -2;
    }

    public void add(int x) {
        if (l == null) {
            l = new Nodo(x);
            n++;
        } else {
            if (!existe(x)) {
                l.setLink(new Nodo(x));
                n++;
            }
        }
    }

    public boolean existe(int x) {
        if (l != null) {
            Nodo p = l;
            while (p != null) {
                if (p.getTicket() == x) {
                    return true;
                }
                p = p.getLink();
            }
        }
        return false;
    }

    public void cargarRandom(int n) {
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                int d = (int) Math.floor(Math.random() * 4 + 1);
                add(d);
                if (this.n == n) {
                    break;
                }
            }
        }
    }
}
