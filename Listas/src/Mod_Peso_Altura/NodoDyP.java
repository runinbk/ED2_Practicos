package Mod_Peso_Altura;

public class NodoDyP {

    int data;
    int peso;
    NodoDyP link;

    public NodoDyP() {
        data = 0;
        peso = 0;
        link = null;

    }

    public NodoDyP(int data, int peso) {
        this.data = data;
        this.peso = peso;
        this.link = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public NodoDyP getLink() {
        return link;
    }

    public void setLink(NodoDyP link) {
        this.link = link;
    }
    
    

}
