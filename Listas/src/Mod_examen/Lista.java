package Mod_examen;

public class Lista {

    public Nodo l;

    public Lista() {
        l = null;
    }

    public void add(int x, int y) {
        if (l == null) {
            l = new Nodo(x, y);
        } else {
            Nodo p = l;
            Nodo ant = l;
            Nodo n = new Nodo(x, y); //Nodo a ingresar
            while (p.getLink() != null && p.getLink().getData().x <= x) { //sale antes de donde tenemos que insertar
                ant = p;
                p = p.getLink();
            }
            if (p.getData().x == x) {
                //iguales
                if (p.getData().y == y) {
                    return;
                } else {
                    //3 casos, si se debe ingresar despues de p, si se debe ingresar al inicio,
                    //si se debe ingresar antes de p
                    if (p.getData().y < y) {
                        //despues
                        n.setLink(p.getLink());
                        p.setLink(n);
                    } else if (ant == l) {
                        // al inicio
                        n.setLink(l);
                        l = n;
                    } else {
                        // antes
                        n.setLink(p);
                        ant.setLink(n);
                    }
                    return;
                }
            }
            if (ant == l) {
                // al inicio
                n.setLink(l);
                l = n;
            } else {
                // despues
                n.setLink(p.getLink());
                p.setLink(n);
            }
        }
    }

    public void add2(int x, int y) { //Inserta x a la Lista.
        Nodo Ant = null;
        Nodo p = l;
        while (p != null && x >= p.getData().x) {
            if (p.getData().x == x && p.getData().y == y) {
                return;
            }
            if (p.getData().x == x && y < p.getData().y) {
                break;
            }
            Ant = p;
            p = p.getLink();
        }
        Nodo nuevo;
        if (Ant == null) {   //x es menor a todos los que están en la Lista (o L==null)
            nuevo = new Nodo(x, y);
            nuevo.setLink(l);
            l = nuevo;
        } else if (Ant.getData().x != x) {    //x no está en la lista.  Insertarlo entre Ant y p
            nuevo = new Nodo(x, y);
            Ant.setLink(nuevo);
            nuevo.setLink(p);
        }
    }

    @Override
    public String toString() {
        Nodo aux = l;
        String s = "{";
        // recorremos la lista
        while (aux != null) {
            // agregamos los elementos a string a mostrar
            s += "[" + aux.getData().x + "," + aux.getData().y + "]";
            aux = aux.getLink();
            if (aux != null) {
                s += ",";
            }
        }
        s += "}";
        return s;
    }
}
