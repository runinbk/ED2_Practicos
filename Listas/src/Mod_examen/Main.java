package Mod_examen;

public class Main {

    public static void main(String[] args) {
        Lista p = new Lista();
        p.add2(3, 5);
        p.add2(3, 4);
        p.add2(1, 3);
        p.add2(9, 4);
        p.add2(9, 2);
        p.add2(3, 5);
        System.out.println(p);  //Llamar a p.toString()
    }
}
