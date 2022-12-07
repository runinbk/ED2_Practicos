package Practico_Listas_5;

public class Main {

    public static void main(String[] args) {
        ColaPrioridad p = new ColaPrioridad();
        p.add2(9);
        p.add2(8);
        p.add2(6);
        p.add2(4);
        p.add2(5);
        p.add2(9);
        System.out.println(p.pop());
         p.add2(10);
        System.out.println(p);  //Llamar a p.toString()
    }
}
