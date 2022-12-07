package ListaV2;

public class SimpleCircularList implements Lista {

    NodoS root;
    int cant;

    //constructor
    public SimpleCircularList() {
        root = null;
        cant = 0;
    }

    @Override
    public boolean vacio() {
        return cant == 0;
    }

    @Override
    public void add(int data) {
        
        
        
    }

    @Override
    public void addLast(int data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addFirst(int data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteLast() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int get(int pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int length() {
        return cant;
    }

    @Override
    public String toString() {
        NodoS aux = root;
        String s = "[";
        // recorremos la lista
        while (aux != null) {
            // agregamos los elementos a string a mostrar
            s += aux.getData();
            aux = aux.getLink();
            if (aux != null) {
                s += ",";
            }
        }
        s += "]";
        return s;
    }

}
