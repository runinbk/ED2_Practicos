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

        // G.bfs(0);
        System.out.println("Son vecinos 2 y 0 ? :" + G.vecindos(2, 0));
        System.out.println("Son vecinos 3 y 7 ? :" + G.vecindos(3, 7));
        System.out.println("Son vecinos 0 y 8 ? :" + G.vecindos(0, 8));
        System.out.println("Son vecinos 4 y 3 ? :" + G.vecindos(4, 3));
        System.out.println("Son vecinos 6 y 8 ? :" + G.vecindos(6, 8));
        System.out.println("Son vecinos 1 y 7 ? :" + G.vecindos(1, 7));
        // System.out.println("Son vecinos 2 y 0 ? :" + G.vecindos(2, 0));
        // System.out.println("Son vecinos 2 y 0 ? :" + G.vecindos(2, 0));
        // System.out.println("Son vecinos 2 y 0 ? :" + G.vecindos(2, 0));
        // System.out.println("Son vecinos 2 y 0 ? :" + G.vecindos(2, 0));

    }
}
