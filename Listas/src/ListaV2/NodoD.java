package ListaV2;

public class NodoD {

    int data;
    NodoD Ant;
    NodoD Sig;

    public NodoD() {
        data = 0;
        Ant = null;
        Sig = null;
    }

    public NodoD(int data) {
        this.data = data;
        Ant = null;
        Sig = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public NodoD getAnt() {
        return Ant;
    }

    public void setAnt(NodoD ant) {
        this.Ant = ant;
    }

    public NodoD getSig() {
        return Sig;
    }

    public void setSig(NodoD sig) {
        this.Sig = sig;
    }

}
