package Practico_Listas_4;

public class Main {

    public static void main(String[] args) {

        Lotto3 l = new Lotto3(3);
        l.iniciar();
        int p = l.Registrar(5);
        int i = l.Registrar(5);
        int j = l.Registrar(5);
        if (j == 5) {
            System.out.println("Gano");
        }else
            System.out.println("Perdio");
    }
}
