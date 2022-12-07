package ListaV2;

public class StringList implements Lista {

    String list;
    int cant;

    // constructor
    public StringList() {
        list = "|";
        cant = 0;
    }

    @Override
    public boolean vacio() {
        return cant == 0;
    }

    private boolean buscar(String dato) {
        int x = 1;
        String elemento_lista = "";
        //recorremos la lista
        while (x < list.length()) {
            //formamos el numero 
            while (list.charAt(x) != '|') {
                elemento_lista += list.charAt(x);
                x++;
            }
            // transforamos el numero de la cadena en una variable integer
            int numero_lista = Integer.parseInt(elemento_lista);
            int data = Integer.parseInt(dato);
            // comparamos el numero con nuestro dato 
            if (numero_lista == data) {
                return true;
            }
            elemento_lista = "";
            x++;
        }
        return false;
    }

    //ingresa un elemento de forma ordenada numericamente sin repeticion de elementos
    @Override
    public void add(int data) {
        if (vacio()) {
            // si esta vacio lo ingresamos al final 
            list += data + "|";
            cant++;
        } else {
            String elemento_lista = "";
            int x = 1;
            int cant_dig = 1;
            boolean bandera = false;
            // recorre toda la lista
            while (x < list.length() && bandera == false) {
                //formamos el numero 
                while (list.charAt(x) != '|') {
                    elemento_lista += list.charAt(x);
                    cant_dig++;
                    x++;
                }
                // transforamos el numero de la cadena en una variable integer
                int numero_lista = Integer.parseInt(elemento_lista);
                // comparamos el numero con nuestro dato a ingresar
                if (numero_lista > data) {
                    bandera = true;
                } else if (numero_lista == data) {
                    // verificamos que no sea igual para no agregar repetidos
                    return;
                } else {
                    //reiniciamos los valores del elemento para volver a formar el numero/elemento 
                    elemento_lista = "";
                    cant_dig = 1;
                }

                x++;
            }
            // crear la nueva lista con el dato introducido
            int hasta_donde_copiar = x - cant_dig;
            String newLista;
            // verificamos si tenemos que ingresar el numero al medio/inicio
            if (bandera == true) {
                newLista = list.substring(0, hasta_donde_copiar);
                newLista += data + "|";
                newLista += list.substring(hasta_donde_copiar, list.length());
                list = newLista;
            } else {
                //ingresamos el valor al final de la lista
                list += data + "|";
            }
            cant++;
        }
    }

    // ingresa elementos a la lista pero pudiendo repetirse los elementos
    public void add_repet(int data) {
        if (vacio()) {
            list += data + "|";
            cant++;
        } else {
            String elemento_lista = "";
            int x = 1;
            int cant_dig = 1;
            boolean bandera = false;
            // recorre toda la lista
            while (x < list.length() && bandera == false) {
                //formamos el numero 
                while (list.charAt(x) != '|') {
                    elemento_lista += list.charAt(x);
                    cant_dig++;
                    x++;
                }
                // transforamos el numero de la cadena en una variable integer
                int numero_lista = Integer.parseInt(elemento_lista);
                // comparamos el numero con nuestro dato a ingresar
                if (numero_lista > data) {
                    bandera = true;
                } else {
                    //reiniciamos los valores del elemento para volver a formar el numero/elemento 
                    elemento_lista = "";
                    cant_dig = 1;
                }
                x++;
            }
            // crear la nueva lista con el dato introducido
            int hasta_donde_copiar = x - cant_dig;
            String newLista;
            // verificamos si tenemos que ingresar el numero al medio/inicio
            if (bandera == true) {
                newLista = list.substring(0, hasta_donde_copiar);
                newLista += data + "|";
                newLista += list.substring(hasta_donde_copiar, list.length());
                list = newLista;
            } else {
                //ingresamos el valor al final de la lista
                list += data + "|";
            }
            cant++;
        }
    }

    //ingresa un elemento al inicio de la lista
    @Override
    public void addFirst(int data) {
        if (vacio()) {
            list += data + "|";
        } else {
            String aux = "|" + data;
            aux += list;
            list = aux;
        }
        cant++;
    }

    //ingresa un elemento al final de la lista
    @Override
    public void addLast(int data) {
        if (!vacio()) {
            list += data + "|";
            cant++;
        }
    }

    //elimina un elemento de la lista respecto a su posicion empezando desde 0
    @Override
    public void delete(int pos) {
        //verificamos que la posicion no sea mayor al numero de elementos de la lista y que no sea negativo
        if (pos <= cant && pos >= 0) {
            int x = 1;
            int cant_dig = 1;
            int posicion_actual = -1;
            //verificamos que x no sea mayor a la longitud del string y si no estamos en la posicion a eliminar
            while (x < list.length() && pos != posicion_actual) {
                cant_dig = 1;
                // ubicamos los elementos de la lista y guardamos al cantidad de digitos que tiene 
                while (list.charAt(x) != '|') {
                    cant_dig++;
                    x++;
                }
                posicion_actual++;
                x++;
            }

            int hasta_donde_copiar = x - cant_dig;
            String newLista;
            // copiamos de la lista original los datos que tenemos que guardar en la nueva lista
            newLista = list.substring(0, hasta_donde_copiar);
            //al aumentar la cantidad de digitos de la posicion de donde tenemos que copiar, estamos eliminando el elemento 
            hasta_donde_copiar += cant_dig;
            //copiamos el sobrante de la lists, ya habiendo eliminado el elemento
            newLista += list.substring(hasta_donde_copiar, list.length());
            list = newLista;
            cant--;
        }
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
    public int length() {
        return cant;
    }

    @Override
    public String toString() {
        return list;
    }

    @Override
    public int get(int pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
