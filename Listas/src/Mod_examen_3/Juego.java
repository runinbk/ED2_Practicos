package Mod_examen_3;

public class Juego {

    private static final int n = 3;
    private Lista tarros[] = new Lista[n];

    public Juego() {
        for (int i = 0; i < n; i++) {
            tarros[i] = new Lista();
        }
    }

    public void lanzar(int i, int moneda) {
        if (i < n && i >= 0) {
            tarros[i].add(moneda);
        }
    }

    public boolean gano(int i) {
        return tarros[i].cant() == 45;
    }
}
