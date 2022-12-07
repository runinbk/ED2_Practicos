package Mod_examen;

public class Nodo {

    private Par data;
    private Nodo link;

    public Nodo() {
        data = new Par();
        link = null;
    }

    public Nodo(int x, int y) {
        data = new Par();
        data.setX(x);
        data.setY(y);
        link = null;
    }

    public Par getData() {
        return data;
    }

    public void setData(Par data) {
        this.data = data;
    }

    public Nodo getLink() {
        return link;
    }

    public void setLink(Nodo link) {
        this.link = link;
    }

}
