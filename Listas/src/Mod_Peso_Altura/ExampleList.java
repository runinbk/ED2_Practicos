package Mod_Peso_Altura;

public class ExampleList {

    NodoDyP root;
    int cant;

    public ExampleList() {
        root = null;
        cant = 0;
    }

    public void add(int data, int peso) {
        NodoDyP elemento = new NodoDyP(data, peso);
        // si la lista esta vacia ingresamo el primer elemento
        if (root == null) {
            root = elemento;
        } else {
            // creamos dos nodos auxiliares por si los datas son iguales 
            NodoDyP aux = root;
            NodoDyP aux2 = root;
            // recorremos hasta 
            // 1) llegamos al final y el elemento es el mayor
            // 2) el eleemento esta en medio o al inicio
            // salimos una posicion antes de que nos topemos con un elemento mayor 
            // [1,2,4]    ingresar{3}    salida del ciclo = posicion 2  
            while (aux.getLink() != null && aux.getLink().getData() <= data) {
                aux2 = aux;
                aux = aux.getLink();
            }
            // verificamos si el data del puntero es igual al data a ingresar
            if (aux.getData() == data) {
                // verificamos si el peso del punteor es igual al peso a ingresar
                if (aux.getPeso() == peso) {
                    // retornamos por que ya esta ingresado ese elemento
                    return;
                }
                // verficamos si el peso del puntero es menor al peso a ingresar
                if (aux.getPeso() < peso) {
                    // si cumple la condicion el elemeneto va despues 
                    elemento.setLink(aux.getLink());
                    aux.setLink(elemento);
                } else {
                    // si no cumple el elemento va adelante
                    if (aux2 == aux) {
                        // si aux2 y aux son iguales el elemento a ingresar debe estar primero
                        elemento.setLink(root);
                        root = elemento;
                    } else {
                        // si no cumple la condicion se debe ingresar antes de la posicion actual pero no es el primero (esta en medio o final)
                        elemento.setLink(aux);
                        aux2.setLink(elemento);
                    }
                }
                // retornamos para no realizar las demas verificaciones 
                return;
            }
            // verificamos si tenemos que ingresar el valor adelante de la posicion actual
            if (aux.getData() > data) {
                elemento.setLink(root);
                root = elemento;
            } else {
                // ingremos despues del elemento actual
                elemento.setLink(aux.getLink());
                aux.setLink(elemento);
            }
        }
        cant++;
    }

    @Override
    public String toString() {
        NodoDyP aux = root;
        String s = "{";
        // recorremos la lista
        while (aux != null) {
            // agregamos los elementos a string a mostrar
            s += "[" + aux.getData() + "," + aux.getPeso() + "]";
            aux = aux.getLink();
            if (aux != null) {
                s += ",";
            }
        }
        s += "}";
        return s;
    }

}
