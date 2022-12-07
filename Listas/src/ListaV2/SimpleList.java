package ListaV2;

public class SimpleList implements Lista {

    // raiz de la lista
    NodoS root;
    // cantidad de elementos de la lista
    int cant;

    // Contructor de la lista
    public SimpleList() {
        root = null;
        cant = 0;
    }

    //devuelve si la lista esta vacia
    @Override
    public boolean vacio() {
        return cant == 0;
    }

    // devuelve la posicion del primer elemento de la lista
    public NodoS primero() {
        return root;
    }

    //devuelve la posicion del ultimo elemento de la lista
    public NodoS ultimo() {
        //si no esta vacio la lista entra al if 
        if (!vacio()) {
            NodoS aux = root;
            // mientras el link de aux no sea null (penultimo) entra en el ciclo
            while (aux.getLink() != null) {
                aux = aux.getLink();
            }
            // retorna la direccion del ultimo elemento
            return aux;
        }
        return null;
    }

    //añade un elemento ordenadamente
    @Override
    public void add(int data) {
        NodoS aux = new NodoS(data);
        // si esta vacio ingresamos el primer elemento
        if (vacio()) {
            root = aux;
        } else {
            NodoS corredor = root;
            // recorremos hasta que estemos en la poscion del elemento mayor al elemento a ingresar
            while (corredor.getLink() != null && corredor.getLink().getData() <= data) {
                corredor = corredor.getLink();
            }
            // verificamos que el elemento a ingresar no se encuentre en la lista 
            if (corredor.getData() == data) {
                System.err.println("Lista.SingleList.add: El elemento " + data + " ya pertenece a la lista");
                return;
            }
            // verificamos si tenemos que ingresar el valor adelante del numero actual
            if (corredor.getData() > data) {
                aux.setLink(root);
                root = aux;
            } else {
                // introducimos el numero despues del numero actual
                aux.setLink(corredor.getLink());
                corredor.setLink(aux);
            }
        }
        cant++;
    }

    // añade a la lista un elemento al final
    @Override
    public void addLast(int data) {
        //creamos un nuevo nodo con la informacion 
        NodoS aux = new NodoS(data);
        if (vacio()) {
            // si la lista esta vacia ingresamos el primer valor (redireccionamos el root a el nuevo nodo)
            root = aux;
        } else {
            // si la lista no esta vacia ingremos el elemento al final de la lista 
            ultimo().setLink(aux);
        }
        // incrementamos la cantidad de elementos de la lista
        cant++;
    }

    //añade un elemento al inicio de la lista 
    @Override
    public void addFirst(int data) {
        //creamos un nuevo nodo con la informacion
        NodoS aux = new NodoS(data);
        // modificamos el link del nuevo nodo con la direccion del anterior primer elemento 
        aux.setLink(root);
        //redireccionamos el primer elemento 
        root = aux;
        // incrementamos la cantidad de elementos de la lista
        cant++;
    }

    // elimina un elemento de la lista por su posicion
    @Override
    public void delete(int pos) {
        // verificamos si la posicion es menor a la cantidad de digitos de la lista
        if (pos < cant) {
            // verificamos si la posicion a eliminar es el primer elemenot de la lista
            if (pos == 0) {
                // redireccionamos el root por su link 
                root = root.getLink();
            } else {
                NodoS aux = root;
                int x = 0;
                // recorremos la lists en busca del elemento anterior a eliminar 
                while (x < pos) {
                    aux = aux.getLink();
                    x++;
                }
                // modificamos el link del elemento anterior a eleminar por el elemento siguiente a eliminar
                aux.setLink(aux.getLink().getLink());
            }
            // decrementamos la cantidad de elementos de la lista
            cant--;
        }
    }

    // elimina el primer elemento de la lista 
    @Override
    public void deleteFirst() {
        if (!vacio()) {
            root = root.getLink();
            cant--;
        }
    }

    // elimina el ultimo elemento de la lista
    @Override
    public void deleteLast() {
        // si la lista no esta vacia ingresamos al if
        if (!vacio()) {
            //verificamos si hay un solo elemento en la lista
            if (cant == 1) {
                root = null;
            } else {
                NodoS aux = root;
                // creamos un nodo donde guardaremos el anterior al ultimo elemento
                NodoS ant = null;
                // recorremos la lista en busca del ultimo elemento
                while (aux.getLink() != null) {
                    ant = aux;
                    aux = aux.getLink();
                }
                // cambiamos a null el link del penultimo elemento 
                ant.setLink(null);
            }
            // disminuimos la cantidad de elementos
            cant--;
        }
    }

    // retornamos el valor de la posicion indicada
    @Override
    public int get(int pos) {
        // verificamos que la posicion sea menor a la cantidad de digitos (verificar si existe esa posicion)
        if (pos < cant) {
            NodoS corredor = root;
            int posAct = 0;
            while (corredor != null) {
                // verificamos si estamos en la posicion a retornar
                if (posAct == pos) {
                    return corredor.getData();
                }
                corredor = corredor.getLink();
                posAct++;
            }
        } else {
            System.err.println("Lista.SingleList.get: No existe la posicion indicada");
            return 0;
        }
        return 0;
    }

    // retornamos la cantidad de elementos de la lista
    @Override
    public int length() {
        return cant;
    }

    // mostramos el contenido de la lists
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

    //ejercicios para practicar
    // Eliminar un segmente de la lista 
    public void deleteAyB(int a, int b) {
        // verificando las 3 condiciones que inpone el ejercicio
        // verificar que a y b esten entre el rango de la lista y verificar que a y b tengas un orden de menor(a) a mayor(b)
        if (a >= 0 && b < cant && a <= b) {
            // cramos el corredor
            int pos = 0;
            // iniciamos los dos nodos donde se guardara el link de A y B
            NodoS NodoB = root;
            NodoS NodoA = root;
            while (pos != b) {
                if (pos == a) {
                    NodoA = NodoB;
                }
                NodoB = NodoB.getLink();
                pos++;
            }
            // eliminamos la seccion modificando el link del nodo A  
            NodoA.setLink(NodoB);
            // Modificamos la cantidad de elementos
            cant = cant - (b - (a + 1));
        }

    }

    // eliminar una secuencia de numeros solo elimina una vez aunque existan mas valores iguales
    // [1,2,3,4,1,2] = deleteSecAB(1,2) = [3,4,1,2]
    public void deleteSecAB(int a, int b) {
        // cremoas dos nodos uno que ira adelante y otro que ira un paso atras
        NodoS aux = root;
        NodoS aux2 = root;
        int pos = 0;
        // verificamos si la cantidad es mayor a dos
        if (cant >= 2) {
            while (aux.getLink() != null) {
                // preguntamos si de el valor de la direccion donde estamos es igual a A
                if (aux.getData() == a) {
                    // preguntamos si de el valor de la siguiente direccion es igual a B
                    if (aux.getLink().getData() == b) {
                        // si se cumple las dos condiciones entonces preguntamos si la pos es igual a 0 si es igual a cero entonces 
                        // tenemos que eliminar el primer y segundo valor, modificamos la direccion de la raiz
                        if (pos == 0) {
                            root = aux.getLink().getLink();
                            cant -= 2;
                            break;
                        } else {
                            // si la pos no es igual a 0 entonces los valores a eliminar son del medio o final y usamos el segundo nodo (AUX2 que esta una
                            // direccion atras del actual) para modificar los link 
                            aux2.setLink(aux.getLink().getLink());
                            cant -= 2;
                            break;
                        }
                    }
                }
                aux2 = aux;
                aux = aux.getLink();
                pos++;
            }
        }
    }

    // elimina un serie de sesecciones de A y B [1,2,2,1,2] = deleteSetAVMul(1,2) = [2]
    public void deleteSecABMul(int a, int b) {
        // cremoas dos nodos uno que ira adelante y otro que ira un paso atras
        NodoS aux = root;
        NodoS aux2 = root;
        int pos = 0;
        // verificamos si la cantidad es mayor a dos
        if (cant >= 2) {
            // verificamos si la siguiente posicion es diferente de null (ya que tienen que haber dos elementos para poder completar la eliminacion)
            while (aux.getLink() != null) {
                // preguntamos si de el valor de la direccion donde estamos es igual a A
                if (aux.getData() == a) {
                    // preguntamos si de el valor de la siguiente direccion es igual a B
                    if (aux.getLink().getData() == b) {
                        // si se cumple las dos condiciones entonces preguntamos si la pos es igual a 0 si es igual a cero entonces 
                        // tenemos que eliminar el primer y segundo valor, modificamos la direccion de la raiz
                        if (pos == 0) {
                            root = aux.getLink().getLink();
                            cant -= 2;
                            // redirrecionamos aux a la nueva root (reiniciamos el ciclo)
                            aux = root;
                            // ponemos en -1 la pos por si el siguiente elemento es igual el primero y segundo y asi sucesivamente 
                            // Elimina la lista completa si llegara a ser el caso que toda la lista seria igual al intervalo (repetidamente)
                            pos = -1;
                        } else {
                            // si la pos no es igual a 0 entonces los valores a eliminar son del medio o final y usamos el segundo nodo (AUX2 que esta una
                            // direccion atras del actual) para modificar los link 
                            aux2.setLink(aux.getLink().getLink());
                            // redireccionamos aux volvemos un paso atras para continuar verificando
                            aux = aux2;
                            cant -= 2;
                            // verificamos si el siguiente elemento es null para terminar el ciclo y no producir una excepcion
                            if (aux.getLink() == null) {
                                break;
                            }
                        }
                    }
                }
                aux2 = aux;
                // preguntamos si pos es != -1 para seguir con el ciclo como normalmente debe seguir
                // si es -1 esq estamos "reiniciando el ciclo"
                if (pos != -1) {
                    aux = aux.getLink();
                }
                // si aux es null terminamos el ciclo para no producir una excepcion en el verificador del ciclo
                if (aux == null) {
                    break;
                }
                // incrementamos la posicion
                pos++;
            }
        }
    }
}
