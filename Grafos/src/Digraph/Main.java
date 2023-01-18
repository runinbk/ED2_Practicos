package Digraph;

public class Main {

    public static void main(String[] args) {
        Grafo G = new Grafo();

        G.addVertice();
        G.addVertice();
        G.addVertice();
        G.addVertice();
        G.addVertice();
        G.addVertice();
        G.addVertice();
        G.addVertice();
        G.addVertice();
        G.addArista(0, 0);
        G.addArista(0, 8);
        G.addArista(2, 8);
        G.addArista(1, 4);
        G.addArista(1, 6);
        G.addArista(4, 6);
        G.addArista(6, 4);
        G.addArista(7, 3);
        G.addArista(7, 6);
        G.addArista(3, 3);
        G.addArista(5, 3);
        G.addArista(5, 7);
        G.printListas();
        G.bfsForce(6);
        G.bfsForce(0);
        // System.out.println("El grafo G esta conectado ? : " + G.conectado());
        // System.out.println(G.edges(0));
        // System.out.println(G.edges(1));
        System.out.println();

        Grafo H = new Grafo();
        H.addVertice();
        H.addVertice();
        H.addVertice();
        H.addVertice();
        H.addVertice();
        H.addVertice();
        H.addVertice();
        H.addArista(0, 6);
        H.addArista(0, 3);
        H.addArista(0, 1);
        H.addArista(2, 1);
        H.addArista(4, 2);
        H.addArista(5, 1);
        H.addArista(5, 4);
        H.printListas();
        System.out.println();
        G.bfsForce(6);
        G.bfsForce(0);
        H.bfsForce(0);
        // G.bfs(0);

        // System.out.println("El grafo H esta conectado ? : " + H.conectado());
        // System.out.println("Cantidad de Hojas en la isla del Vert 0 es :" +
        // H.cantHojas(0));
        // System.out.println("Cantidad de Hojas en la isla del Vert 1 es :" +
        // G.cantHojas(1));

        // G.path2(0);
        // G.path2(6);
        // G.path2(3);
        // G.path2(1);
        // H.path2(0);
        // H.path2(1);
        // H.path2(6);

        // G.printHojas(0);
        // G.printHojas(3);
        // G.printHojas(4);
        // H.printHojas(6);
        // H.printHojas(4);

        System.out.println("kAlcanzable ( 5, 4, 3) -> " + G.kAlcanzable(5, 4, 3));
        System.out.println("kAlcanzable ( 5, 4, 2) -> " + G.kAlcanzable(5, 4, 2));
        System.out.println("kAlcanzable ( 5, 4, 5) -> " + G.kAlcanzable(5, 4, 5));

    }
}
