package AMVias;

import java.util.LinkedList;

public class ArbolM {
    private NodoM raiz;
    private int n; // n=cantidad de nodos.

    public ArbolM() {
        raiz = null;
        n = 0;
    }

    public int getCantNodos() {
        return n;
    }

    public void add(int x) {
        if (raiz == null)
            raiz = new NodoM(x);
        else {
            int i = 0;
            NodoM ant = null, p = raiz;
            while (p != null) {
                if (!p.isLleno()) { // Hay espacio en el nodo p. Insertar x ahí.
                    p.insDataInOrden(x);
                    return;
                }

                i = hijoDesc(p, x);
                if (i == -1)
                    return; // x está en el nodo p.

                ant = p;
                p = p.getHijo(i);
            }
            // p llegó a null. Crear una nueva hoja y engancharla a ant.
            NodoM nuevo = new NodoM(x);
            ant.setHijo(i, nuevo);
        }

        n++;
    }

    private boolean hoja(NodoM t) {
        return (t != null && t.cantHijos() == 0);
    }

    public void inorden() {
        System.out.print("Inorden:");
        if (raiz == null)
            System.out.println(" (Árbol vacío)");
        else {
            inorden(raiz);
            System.out.println();
        }
    }

    private void inorden(NodoM T) {
        if (T != null) {
            int z = T.cantDatasUsadas(); // z = índice de la última data usada.
            for (int i = 1; i <= z; i++) {
                inorden(T.getHijo(i));
                System.out.print(" " + T.getData(i));
            }
            inorden(T.getHijo(z + 1));
        }
    }

    private int hijoDesc(NodoM N, int x) { // Corrutina de insertar.
        int i = 1;
        while (i <= N.cantDatasUsadas()) {
            if (x < N.getData(i))
                return i;

            if (x == N.getData(i))
                return -1;

            i++;
        }

        return N.cantDatasUsadas() + 1;
    }

    public void niveles() {
        niveles("");
    }

    public void niveles(String nombreVar) {
        if (nombreVar != null && nombreVar.length() > 0)
            nombreVar = " del Arbol " + nombreVar;
        else
            nombreVar = "";

        System.out.print("Niveles" + nombreVar + ": ");

        if (raiz == null)
            System.out.println("(Arbol vacío)");
        else
            niveles(raiz);
    }

    // ---------- Métodos auxiliares para mostrar el árbol por niveles
    // --------------
    private void niveles(NodoM T) { // Pre: T no es null.
        LinkedList<NodoM> colaNodos = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();

        int nivelActual = 0;
        String coma = "";

        colaNodos.addLast(T);
        colaNivel.addLast(1);

        do {
            NodoM p = colaNodos.pop(); // Sacar un nodo p de la cola
            int nivelP = colaNivel.pop(); // Sacar el nivel donde se encuentra p.

            if (nivelP != nivelActual) { // Se está cambiando de nivel
                System.out.println();
                System.out.print("  Nivel " + nivelP + ": ");
                nivelActual = nivelP;
                coma = "";
            }

            System.out.print(coma + p);
            coma = ", ";

            addHijos(colaNodos, colaNivel, p, nivelP);
        } while (colaNodos.size() > 0);

        System.out.println();
    }

    private void addHijos(LinkedList<NodoM> colaNodos, LinkedList<Integer> colaNivel, NodoM p, int nivelP) {
        for (int i = 1; i <= NodoM.M; i++) { // Insertar a la cola de nodos los hijos no-nulos de p
            NodoM hijo = p.getHijo(i);

            if (hijo != null) {
                colaNodos.addLast(hijo);
                colaNivel.addLast(nivelP + 1);
            }
        }
    }

    // ----------------------------------Practico---------------------------------
    // 1. En la class ArbolM escriba el método
    //
    // public boolean hnoCercano(int a, int b)
    // //Por comodidad, asuma que a < b
    //
    // el cual devuelve true, si el data a es hermano cercano del
    // data b. Si a o b no están en el árbol; o a y b no son
    // hermanos cercanos, devuelve false.
    // Dos datas son hermanos cercanos si no comparten el
    // mismo nodo, pero en el nodo padre tienen un data padre
    // en común P. Es decir, a está en el nodo de la izquierda de P,
    // y b está en el nodo de la derecha de P.

    public boolean hnoCercano(int a, int b) {
        if (a < b) {
            return hnoCercano(raiz, a, b);
        }
        return false;
    }

    private boolean hnoCercano(NodoM T, int a, int b) {
        if (T != null) {
            int i = hijoDesc(T, a);
            if (T.getHijo(i) != null && T.getHijo(i).existe(a)) {
                if (T.getHijo(i + 1) != null) {
                    return T.getHijo(i + 1).existe(b);
                }
            }
            return hnoCercano(T.getHijo(i), a, b);
        }
        return false;
    }

    // 2. Asumiendo que el Árbol M-Vías es DESORDENADO
    // (pero sin duplicados), escriba el método:

    // public void podar()

    // el cual elimine todas las hojas del árbol.
    // (Si el árbol es vacío, “no pasa nada”. Si el árbol es una hoja,
    // es decir la Raíz es una hoja, entonces Raiz=null).

    public void podar() {
        podar(raiz);
    }

    private void podar(NodoM T) {
        if (T != null) {
            if (T == raiz && hoja(T)) {
                raiz = null;
            }
            for (int i = 1; i <= NodoM.M; i++) {
                if (hoja(T.getHijo(i))) {
                    T.setHijo(i, null);
                    n--;
                } else {
                    podar(T.getHijo(i));
                }
            }
        }
    }

    // 3. En la class ArbolM escriba el método NO-RECURSIVO

    // public void borrarHoja(int x)

    // el cual elimina la hoja donde se encuentra el data x. Si el
    // data x no existe o x está alojado en un nodo no-hoja, “no
    // pasa nada”

    // ITERATIVO
    public void borrarHoja(int x) {
        NodoM t = raiz;
        while (t != null) {
            int i = hijoDesc(t, x);
            if (i == -1) {
                if (hoja(t) && t == raiz) {
                    raiz = null;
                    n = 0;
                }
                return;
            }
            NodoM h = t.getHijo(i);
            if (hoja(h) && h.existe(x)) {
                n--;
                t.setHijo(i, null);
                return;
            }
            t = t.getHijo(i);
        }
    }

    // RECURSIVO
    public void borrarHojaR(int x) {
        borrarHojaR(raiz, x);
    }

    private void borrarHojaR(NodoM t, int x) {
        if (t != null) {
            if (t == raiz && t.existe(x) && hoja(t)) {
                raiz = null;
                n = 0;
                return;
            }
            for (int i = 1; i <= NodoM.M; i++) {
                if (hoja(t.getHijo(i)) && t.getHijo(i).existe(x)) {
                    t.setHijo(i, null);
                    n--;
                    return;
                }
                borrarHojaR(t.getHijo(i), x);
            }
        }
    }

    // 4. En la class ArbolM (m-vías) escriba el método

    // public void distInorden(int a, int b)

    // //Pre: a < b (asumir que a < b)

    // el cual devuelve la distancia entre a y b en la cadena
    // Inorden generada por el árbol. Si a o b no existen en el
    // árbol, este algoritmo devuelve ‒1.
    // Por ejemplo, si el Inorden del árbol es:

    // Cadena Inorden: 10, 20, 25, 50, 60, 70
    // distInorden(30, 60) = ‒1 //30 no existe
    // distInorden(20, 88) = ‒1 //88 no existe
    // distInorden(20, 60) = 3
    // //Porque partiendo del 20 necesitamos recorrer 3 datas (en la
    // //cadena Inorden), hasta llegar al 60: 10, 20, 25, 50, 60, 70
    // distInorden(25, 60) = 2
    // distInorden(10, 70) = 5

    public void distInorden(int a, int b) {

    }

    // -----------------------------------------------------------------

    // 5. En la class ArbolM (m-vías) escriba el método

    // public void delAlone(int x)

    // el cual elimine la hoja donde se encuentre el data x, siempre y
    // cuando esa hoja tiene como única data a x. Si no se cumplen estas
    // condiciones, este método no hace nada.
    // Por ejemplo, para el árbol A:
    // A.delAlone(200);
    // //La hoja [200| ] se eliminará, porque el 200 está solo (alone) en
    // //la hoja.
    // A.delAlone(5);
    // //No pasa nada, porque el 5 no existe en el árbol.
    // A.delAlone(110);
    // //No pasa nada, porque el 110 no está en una hoja.
    // A.delAlone(60);
    // //No pasa nada, porque el 60, aunque está en una hoja, no está
    // //solo.
    // A.delAlone(10);
    // //La hoja [10| ] se eliminará, porque el 10 está solo (alone) en la
    // //hoja.

    public void delAlone(int x) {
        delAlone(raiz, x);
    }

    private void delAlone(NodoM t, int x) {
        if (t != null) {
            if (t == raiz && t.existe(x) && hoja(t) && t.dataUnico()) {
                raiz = null;
                n = 0;
                return;
            }
            for (int i = 1; i <= NodoM.M; i++) {
                if (hoja(t.getHijo(i)) && t.getHijo(i).existe(x) && t.getHijo(i).dataUnico()) {
                    t.setHijo(i, null);
                    n--;
                    return;
                }
                delAlone(t.getHijo(i), x);
            }
        }
    }

    // ----------------------------------------------------------------------------------
    // 6. En la class ArbolM, escriba el procedimiento:

    // public void delHoja(int sum)

    // que elimina todas las hojas donde la suma de sus Datas es igual a sum.
    // Obviamente, el puntero que sostiene la hoja a eliminar, debe ponerse en null.
    // Por ejemplo: Si tomamos el siguiente árbol A:

    // /* Suma de la hoja h1 = 5+10+15=30, suma de h2=45+50+55=150, suma de h3 =
    // 74+76 =150, suma de h4 = 130+140 = 270
    // suma de h5 = 175+180+185 = 540 suma de h6 = 270, suma de h7 = 305+310=615,
    // suma de h8 = 410+420+500=1330
    // */

    // A.delHoja(150); //Se eliminan del árbol las hojas h2 y h3, porque las sumas
    // de sus Datas = 150
    // A.delHoja(220); //No se elimina ninguna hoja del árbol, porque no existe
    // ninguna hoja cuya suma de sus Datas=220
    // A.delHoja(270); //Se eliminan del árbol las hojas h4 y h6, porque las sumas
    // de sus Datas = 270
    // A.delHoja(540); //Se elimina la hoja h5, porque la sumas de sus Datas = 540
    // Tome en cuenta que se puede saber cuántas Datas usadas tiene un NodoM N,
    // usando la función N.cantDatasUsadas().

    public void delHoja(int sum) {
        delHoja(raiz, sum);
    }

    private void delHoja(NodoM t, int sum) {
        if (t != null) {
            if (t == raiz && hoja(t) && t.sumDatas() == sum) {
                raiz = null;
                n = 0;
            }
            for (int i = 1; i <= NodoM.M; i++) {
                if (hoja(t.getHijo(i)) && t.getHijo(i).sumDatas() == sum) {
                    t.setHijo(i, null);
                    n--;
                }
                delHoja(t.getHijo(i), sum);
            }
        }
    }

    // --------------------------------------------------------------------------------------
    // 7. En un Árbol m-vías, se dice que p es un lastParent de h, si h está en una
    // hoja y p está en el nodo que sostiene a esta hoja
    // (i.e el Nodo donde se encuentra p es padre del nodo hoja, donde se encuentra
    // h). En la class ArbolM, escriba la función:

    // public boolean lastParent(int p, int h)

    // la cual devuelva true, si p es un lastParent de h. Si p no es un lastParent
    // de h o p o h no existen, devuelve false.
    // Por ejemplo: Si tomamos el siguiente árbol A:

    // lastParent(600,500)=false //El 600 no existe.
    // lastParent(170,5)=false //El 5 no existe.
    // lastParent(190,180)=true //180 está en una hoja y 190 está en el nodo padre.
    // lastParent(68,75)=true //75 está en una hoja y 68 está en el nodo padre.
    // lastParent(300,40)=false //Aunque el 300 esté en el nodo padre de 40, 40 no
    // está en una hoja.
    // lastParent(160,185)=false //El nodo del 160, no es padre del nodo donde se
    // encuentra el 185.
    // Tome en cuenta que se puede saber cuántas Datas usadas tiene un NodoM N,
    // usando la función N.cantDatasUsadas().

    public boolean lastParent(int p, int h) {
        return lastParent(raiz, p, h);
    }

    private boolean lastParent(NodoM t, int p, int h) {
        if (t != null) {
            for (int i = 1; i <= NodoM.M; i++) {
                if (t.getHijo(i).existe(p)) {
                    for (int j = 1; i <= NodoM.M; i++) {
                        if (t.getHijo(i).getHijo(j).existe(h)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }

    // --------------------------------------------------------------------------------------
    // 8. En un Árbol M-Vías, se dice que un data x, es un super-parent de un data
    // z, si z se encuentra en la rama izquierda o en la
    // rama derecha de x (i.e. si descendemos por el hijo-izquierdo o el
    // hijo-derecho de x, y encontramos a z, entonces x es un superparent de z)
    // En la class ArbolM, escriba la función

    // public boolean superParent(int x, int z)

    // la cual devuelva true, si x es un superParent de z. Si x o z no existen o x
    // no es un super-parent de z, devuelve false. Por ejemplo:

    // superParent(30, 15) =true //Porque partiendo del hijo izquierdo del 30,
    // podemos llegar al 15
    // superParent(100,118)=true //Porque partiendo del hijo derecho del 100,
    // podemos llegar al 118
    // superParent(200,118)=true //Porque partiendo del hijo izquierdo del 200,
    // podemos llegar al 118
    // superParent(15,30)=false //Porque yendo por el hijo izq o por el hijo der del
    // 15, no llegamos al 30
    // superParent(100,290)=false //Porque yendo por el hijo izq o por el hijo der
    // del 100, no llegamos al 290
    // superParent(200,290)=true //Porque yendo por el hijo derecho del 200,
    // llegamos al 290
    // superParent(330,400)=false //Porque yendo por el hijo izq o por el hijo der
    // del 330, no llegamos al 400 (330 está en el mismo nodo del 400).
    // superParent(600,150)=false //600 no existe.
    // superParent(30, 8) =false //8 no existe.

    // ITERATIVO
    public boolean superParent(int x, int z) {
        NodoM Nx = getNodo(x);
        NodoM Nz = getNodo(z);
        if (Nx != null && Nz != null) { // verificar si ambos nodos existen
            int p = Nx.posData(x); // pos del data x
            int i = hijoDesc(Nx, z); // sacamos por donde debe descender para llegar a z
            if (i == p || i == p + 1) { // si cumple esta en el camino correcto
                while (Nx != null) {
                    if (Nx == Nz) {
                        return true;
                    }
                    i = hijoDesc(Nx, z);
                    Nx = Nx.getHijo(i);
                }
            }
        }
        return false;
    }

    public boolean superParent2(int x, int z) {
        NodoM Nx = getNodo(x);
        if (Nx != null) { // verificar si no es nulo
            int p = Nx.posData(x); // pos del data x
            int i = hijoDesc(Nx, z); // sacamos por donde debe descender para llegar a z
            if (i == -1) {
                return false;
            }
            if (i == p || i == p + 1) { // si cumple esta en el camino correcto
                while (Nx != null) {
                    if (Nx.existe(z)) {
                        return true;
                    }
                    i = hijoDesc(Nx, z);
                    Nx = Nx.getHijo(i);
                }
            }
        }
        return false;
    }

    // RECURSIVO
    public boolean superParentR(int a, int b) {
        NodoM t = this.getNodo(a);
        return superParentR(t, b, t.posData(a), 1);
    }

    private boolean superParentR(NodoM t, int b, int pos, int n) {
        if (t == null) {
            return false;
        }
        for (int i = pos; i <= pos + n; i++) {
            if (t.getHijo(i) != null && t.getHijo(i).existe(b)) {
                return true;
            }
            if (superParentR(t.getHijo(i), b, 1, NodoM.M - 1)) {
                return true;
            }
        }
        return false;
    }

    // -------------------------------------------------------------------------------------
    // 9. Se dice que un data t, es tío de un data s, si y solo si, s está alojado
    // en un nodo hijo, hermano del nodo donde está t.
    // En la class ArbolM, escriba la función:

    // public boolean tio(int t, int s)

    // la cual devuelva true, si t es un tío de s. Si t o s no existen o t no es un
    // tío de s, devuelve false.
    // Por ejemplo:

    // tio(500,210)=false //El 500 no existe.
    // tio(260,195)=false //195 no existe
    // tio(10, 75)=true
    // //Porque el nodo padre del 75 (nodo [65|68|80]), es hermano del nodo donde
    // está el 10 (nodo [10|25|30])
    // tio(75, 10)=false

    // tio(350,80)=true
    // tio(100,85)=false //100 es padre del 85, no su tío.
    // tio(400,12)=false //El padre del 12 (nodo [10|25|30], no es hermano del 400
    // (nodo [320|350|400]

    public boolean tio(int t, int s) {
        NodoM p = getNodoPadre(t);
        if (p != null) {
            int i = hijoDesc(p, s);
            if (p.getHijo(i) != null && p.getHijo(i).existe(t)) {
                return false;
            }
            p = p.getHijo(i);
            while (p != null) {
                i = hijoDesc(p, s);
                if (p.getHijo(i) != null && p.getHijo(i).existe(s)) {
                    return true;
                }
                p = p.getHijo(i);
            }
        }
        return false;
    }

    public boolean tio2(int t, int s) {
        NodoM p = getNodoPadre(t);
        if (p != null) {
            int i = hijoDesc(p, s);
            if (p.getHijo(i) != null && p.getHijo(i).existe(t)) {
                return false;
            }
            p = p.getHijo(i);
            if (p != null) {
                i = hijoDesc(p, s);
                if (p.getHijo(i) != null && p.getHijo(i).existe(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    // ------------------------------------------------------------------------------------
    // 10. Se dice que un Árbol m-vías está perfectamente balanceado si y solo si,
    // para todo nodo N, cada uno de sus subárboles
    // tienen exactamente la misma altura.
    // En la class ArbolM, escriba la función:

    // public boolean balanced()

    // la cual devuelva true, sii el árbol está perfectamente balanceado. Por
    // ejemplo:

    // A.balanced()=true //Porque los hijos de cada uno de los nodos tienen la misma
    // altura

    // B.balanced()=false //Porque el hijo-2 del nodo [110|190], no tiene la misma
    // altura que la de sus hermanos.

    // ---------------------------------------------------------------------------------------------------------

    // 11. En la class ArbolM, escriba el procedimiento

    // public void delHojas(int nivel)

    // el cual elimine todas las hojas del nivel dado. Si el nivel dado no existe o
    // no existen hojas en el nivel dado, este procedimiento no hace nada.
    // Por ejemplo: Dado el árbol A

    // A.delHojas(5); //El nivel 5 no existe: “No pasa nada” (el árbol permanece
    // igual)
    // A.delHojas(2); //Hay una sola hoja en el nivel 2 (el nodo [270| | ]), ésta
    // será eliminada. El árbol queda:
    // A.delHojas(2); //El nivel 2 existe, pero no tiene hojas. El árbol permanece
    // igual.
    // A.delHojas(4); //Las hojas del nivel 4 serán eliminadas. El árbol queda así:

    public void delHojas(int nivel) {
        delHojas(raiz, nivel);
    }

    private void delHojas(NodoM t, int nivel) {
        if (t != null) {
            if (t == raiz && hoja(t) && getNivel(t.getData(1)) == nivel) {
                raiz = null;
                n = 0;
            }
            for (int i = 1; i <= NodoM.M; i++) {
                if (hoja(t.getHijo(i)) && getNivel(t.getHijo(i).getData(1)) == nivel) {
                    t.setHijo(i, null);
                    n--;
                }
                delHojas(t.getHijo(i), nivel);
            }
        }

    }

    // metodos auxs
    public NodoM getNodo(int x) {
        return getNodo(raiz, x);
    }

    private NodoM getNodo(NodoM t, int x) {
        if (t == null) {
            return null;
        }
        if (t.existe(x)) {
            return t;
        }
        int i = hijoDesc(t, x);
        return getNodo(t.getHijo(i), x);
    }

    public NodoM getNodoPadre(int x) {
        return getNodoPadre(raiz, x);
    }

    private NodoM getNodoPadre(NodoM t, int x) {
        if (t == null) {
            return null;
        }
        int i = hijoDesc(t, x);
        if (i == -1) {
            return null;
        }
        if (t.getHijo(i) != null && t.getHijo(i).existe(x)) {
            return t;
        }
        return getNodoPadre(t.getHijo(i), x);
    }

    // obtener el nivel de x
    public int getNivel(int x) {
        return getNivel(raiz, x);
    }

    private int getNivel(NodoM t, int x) {
        if (t == null) {
            return 0;
        }
        if (t.existe(x)) {
            return 1;
        }
        int c = 0;
        for (int i = 1; i <= NodoM.M; i++) {
            c += getNivel(t.getHijo(i), x);
            if (c > 0) {
                c++;
                break;
            }
        }
        return c;
    }

    // obtener nivel por nodo
    public int getNivels(NodoM t) {
        LinkedList<NodoM> colaNodos = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();

        int nivelActual = 0;

        colaNodos.addLast(t);
        colaNivel.addLast(1);

        do {
            NodoM p = colaNodos.pop(); // Sacar un nodo p de la cola
            int nivelP = colaNivel.pop(); // Sacar el nivel donde se encuentra p.

            if (nivelP != nivelActual) { // Se está cambiando de nivel

                nivelActual = nivelP;

            }
            addHijos(colaNodos, colaNivel, p, nivelP);
        } while (colaNodos.size() > 0);
        return nivelActual;
    }

    // ------------------------------------------------------------------------
    // -------------------------MODELOS DE EXAMENES----------------------------
    // ------------------------------------------------------------------------

    // 2-2022/2doParcial
    // Llamamos suma de un nodo N, a la suma de sus Datas.
    // En la class NodoM, existe una función suma( ) que realiza este cálculo.
    // Por ejemplo, si el nodo N=[10|25| ], entonces N.suma( )=10+25=35.
    // Sabiendo esto, en la class ArbolM, escriba el procedimiento:

    // public void delLeafs(int s)

    // el cual elimine todas las hojas que cuelgan de aquellos nodos cuya suma es s.
    // Por ejemplo, para el árbol A:

    // A.delLeafs(185);
    // El nodo [40|60|85] tiene suma=40+60+85=185.
    // Entonces, cada una de sus hojas serán eliminadas

    // A.delLeafs(1070);
    // El nodo [320|350|400] tiene suma=1070.
    // Entonces, cada una de las hojas de este nodo serán eliminadas.

    // A.delLeafs(185);
    // El nodo [40|60|85] tiene suma=185.
    // Pero, como no tiene hojas que eliminar, el árbol queda igual.

    public void delLeafs(int sum) {
        delLeafs(raiz, sum);
    }

    private void delLeafs(NodoM t, int sum) {
        if (t != null) {
            for (int i = 1; i <= NodoM.M; i++) {
                if (hoja(t.getHijo(i)) && t.sumDatas() == sum) {
                    t.setHijo(i, null);
                    n--;
                }
                delLeafs(t.getHijo(i), sum);
            }
        }
    }

    // ------------------------------------------------------------------------
    // 1. En la class ArbolM, escriba el procedimiento:

    // public void delLeafData(int x)

    // el cual elimine el data x, siempre y cuando esta data esté alojada en una
    // hoja. Si luego de eliminar el data x, la hoja
    // queda vacía, la hoja debe ser quitada del árbol.
    // Por ejemplo, para el árbol A:

    // A.delLeafData(550); //El 550 NO existe, el árbol queda igual.
    // A.delLeafData(170); //El 170 existe, pero no está alojado en una hoja. El
    // árbol queda igual.
    // A.delLeafData(180); //El 180 está alojado en una hoja, por tanto, este Data
    // es quitada del árbol
    // A.delLeafData(5);
    // A.delLeafData(310); //Los Datas 5 y 310 son quitadas,porque ellas están
    // alojadas en sendas hojas del árbol
    // A.delLeafData(270); //El 270 es quitado de la hoja que lo aloja. Pero al
    // quitar el 270, la hoja queda vacía, por tanto, la hoja es eliminada del
    // árbol.
    // A.delLeafData(305); //Al quitar el data 305 de su hoja, la hoja queda vacía.
    // Por tanto, esta hoja es eliminada del árbol.

    public void delLeafData(int x) {
        delLeafData(raiz, x);
    }

    private void delLeafData(NodoM t, int x) {
        if (t != null) {
            if (t == raiz && hoja(t) && t.existe(x)) {
                t.delData(x);
                if (t.cantDatasVacias() == NodoM.M) {
                    raiz = null;
                    n = 0;
                }
            }
            for (int i = 1; i <= NodoM.M; i++) {
                if (hoja(t.getHijo(i)) && t.getHijo(i).existe(x)) {
                    t.getHijo(i).delData(x);
                    if (t.getHijo(i).cantDatasVacias() == NodoM.M) {
                        t.setHijo(i, null);
                        n--;
                    }
                }
            }
        }
    }

}
