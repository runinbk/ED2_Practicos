package Practico_Listas_5;

public class ColaPrioridad {

    private Nodo l;
    private int n;

    public ColaPrioridad() {
        l = null;
        n = 0;
    }

    public void add(int x) {
        if (l == null) {
            l = new Nodo(x);
            n++;
        } else {
            Nodo p = l;
            Nodo ant = null;
            if (x % 2 == 0) {
                if (p.getData() % 2 != 0) {
                    Nodo d = new Nodo(x);
                    d.setLink(p);
                    l = d;
                    n++;
                    return;
                }
                while (p != null) {
                    if (p.getData() % 2 != 0) {
                        Nodo d = new Nodo(x);
                        d.setLink(p);
                        ant.setLink(d);
                        n++;
                        return;
                    }
                    ant = p;
                    p = p.getLink();
                }
                ant.setLink(new Nodo(x));
            } else {
                Nodo d = new Nodo(x);
                while (p.getLink() != null) {
                    p = p.getLink();
                }
                p.setLink(d);
            }
            n++;
        }
    }

    public void add2(int x) {
        if (l == null) {
            l = new Nodo(x);
            n++;
        } else {
            Nodo p = l;
            Nodo ant = null;
            Nodo d = new Nodo(x); // nuevo nodo
            //verif par
            if (x % 2 == 0) {
                //si el primer numer es par
                if (p.getData() % 2 == 0) {
                    while (p != null) {
                        //verif si es impar
                        if (p.getData() % 2 != 0) {
                            d.setLink(p);
                            ant.setLink(d);
                            n++;
                            return;
                        }
                        ant = p;
                        p = p.getLink();
                    }
                    //si toda la fila es de numeros pares inserto al final
                    ant.setLink(d);
                    n++;
                } else {
                    //si la lista empieza con un numero impar
                    d.setLink(p);
                    l = d;
                    n++;
                }
            } else {
                // si x es impar se inserta al final
                while (p.getLink() != null) {
                    p = p.getLink();
                }
                p.setLink(d);
                n++;
            }
        }
    }

    public int pop() {
        if (l != null) {
            int d = l.getData();
            l = l.getLink();
            n--;
            return d;
        }
        return -1;
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
