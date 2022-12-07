package ListaV2;

public class DoubleList implements Lista {

    NodoD root;
    int cant;

    // constructor de la lista
    public DoubleList() {
        root = null;
        cant = 0;
    }

    // devuelve si la lista esta vacia
    @Override
    public boolean vacio() {
        return cant == 0;
    }

    //devuelve la posicion del ultimo elemento de la lista
    public NodoD ultimo() {
        //si no esta vacio la lista entra al if 
        if (!vacio()) {
            NodoD aux = root;
            // mientras el link de aux no sea null (penultimo) entra en el ciclo
            while (aux.getSig() != null) {
                aux = aux.getSig();
            }
            // retorna la direccion del ultimo elemento
            return aux;
        }
        return null;
    }

    // ingresamos un elmento a la lista ordenadamente
    @Override
    public void add(int data) {
        NodoD aux = new NodoD(data);
        if (vacio()) {
            root = aux;
        } else {
            NodoD corredor = root;
            // recorremos hasta que estemos en la poscion del elemento mayor al elemento a ingresar
            while (corredor.getSig() != null && corredor.getSig().getData() <= data) {
                corredor = corredor.getSig();
            }
            // verificamos que el elemento a ingresar no se encuentre en la lista 
            if (corredor.getData() == data) {
                System.err.println("Lista.SingleList.add: El elemento " + data + " ya pertenece a la lista");
                return;
            }
            // verificamos si tenemos que ingresar el valor adelante del numero actual
            if (corredor.getData() > data) {
                aux.setSig(root);
                root.setAnt(aux);
                root = aux;
            } else {
                // introducimos el numero despues del numero actual
                aux.setSig(corredor.getSig());
                aux.setAnt(corredor);
                corredor.setSig(aux);
            }
        }
        cant++;

    }

    // inserta un elemento al inicio de la lista
    @Override
    public void addFirst(int data) {
        NodoD aux = new NodoD(data);
        aux.setSig(root);
        root = aux;
        cant++;
    }

    // inserta un elemento al final de la lista
    @Override
    public void addLast(int data) {
        NodoD aux = new NodoD(data);
        // agregamos los valores al nuevo nodo
        // si esta vacio la lista ingresamos al if
        if (vacio()) {
            root = aux;
        } else {
            // modificamos el link ant del aux 
            aux.setAnt(ultimo());
            // si la lista tiene elementos ingresamos al final
            ultimo().setSig(aux);
        }
        cant++;
    }

    // elimina un elemento de lista por su posicion
    @Override
    public void delete(int pos) {
        // verificamos si la posicion es menor a la cantidad de elementos de la lista
        if (pos < cant) {
            // verificamos si la posicion es del primer elemento
            if (pos == 0) {
                root = root.getSig();
                // redireccionamos el ant a null
                if (root != null) {
                    root.setAnt(null);
                }
            } else {
                NodoD aux = root;
                int x = 0;
                // recorremos hasta llegar al elemento anterior a eliminar
                while (0 < pos) {
                    aux = aux.getSig();
                    x++;
                }
                // modificamos el link del elemento anterior a eleminar por el elemento siguiente a eliminar
                aux.setSig(aux.getSig().getSig());
                // modificamos el anterior del aux.sig
                aux.getSig().setAnt(aux);
            }
            // decrementamos la cantidad de elementos
            cant--;
        }
    }

    // retorna el valor del elemento de la posicion 
    @Override
    public int get(int pos) {
        // verificamos que la posicion sea menor a la cantidad de digitos (verificar si existe esa posicion)
        if (pos < cant) {
            NodoD corredor = root;
            int posAct = 0;
            while (corredor != null) {
                // verificamos si estamos en la posicion a retornar
                if (posAct == pos) {
                    return corredor.getData();
                }
                corredor = corredor.getSig();
                posAct++;
            }
        } else {
            System.err.println("Lista.SingleList.get: No existe la posicion indicada");
            return 0;
        }
        return 0;
    }

    // revuelve la cantidad de elementos de la lista
    @Override
    public int length() {
        return cant;
    }

    // muestra los elementos de la lista
    @Override
    public String toString() {
        NodoD aux = root;
        String s = "[";
        // recorremos la lista
        while (aux != null) {
            // agregamos los elementos a string a mostrar
            s += aux.getData();
            aux = aux.getSig();
            if (aux != null) {
                s += ",";
            }
        }
        s += "]";
        return s;
    }

    @Override
    public void deleteFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteLast() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
