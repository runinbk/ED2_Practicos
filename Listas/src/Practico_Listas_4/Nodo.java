package Practico_Listas_4;

public class Nodo {     //Nodo que usa la class Lista

    public int Ticket;
    public int asiertos;
    public Nodo Link;

    public Nodo() {
        this(0);
    }

    public Nodo(int Data) {
        this.Ticket = Data;
        this.asiertos = 0;
        this.Link = null;
    }

    public int getTicket() {
        return Ticket;
    }

    public void setTicket(int Ticket) {
        this.Ticket = Ticket;
    }

    public int getAsiertos() {
        return asiertos;
    }

    public void setAsiertos(int asiertos) {
        this.asiertos = asiertos;
    }

    public Nodo getLink() {
        return Link;
    }

    public void setLink(Nodo Link) {
        this.Link = Link;
    }

    public void acerto() {
        asiertos++;
    }

    public boolean gano() {
        return asiertos == 3;
    }
}
