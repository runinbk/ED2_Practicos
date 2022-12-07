package Practico_Listas_3;

public class Main {

    public static void main(String[] args) {
        Cola p = new Cola();
        p.meter(0);
        p.meter(1);
        //p.meter(2);
        System.out.println(p.sacar());  //Llamar a p.toString()
        System.out.println(p.sacar());
        System.out.println(p.sacar());
        System.out.println(p.sacar());
    }
}
