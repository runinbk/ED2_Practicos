package Mod_examen_5;

public class Nodo {

    int id;
    int valor;
    Nodo link;

    public Nodo() {
        id = 0;
        valor = 0;
        link = null;
    }

    public Nodo(int id, int valor) {
        this.id = id;
        this.valor = valor;
        link = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo getLink() {
        return link;
    }

    public void setLink(Nodo link) {
        this.link = link;
    }
    

}
